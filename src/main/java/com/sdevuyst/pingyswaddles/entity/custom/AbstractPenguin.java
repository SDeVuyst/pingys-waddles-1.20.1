package com.sdevuyst.pingyswaddles.entity.custom;

import com.sdevuyst.pingyswaddles.PingysWaddles;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;

public abstract class AbstractPenguin extends Animal {
    private static final Ingredient FOOD_ITEMS;

    protected static EntityDimensions NORMAL_DIMENSIONS;
    protected static EntityDimensions BABY_DIMENSIONS;

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public final AnimationState fallingAnimationState = new AnimationState();
    private static final EntityDataAccessor<Boolean> FALLING =
            SynchedEntityData.defineId(AbstractPenguin.class, EntityDataSerializers.BOOLEAN);

    protected AbstractPenguin(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.refreshDimensions();
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            setupAnimationStates();
        }
    }

    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = this.random.nextInt(80) + 80;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }

    }

    @Override
    public void aiStep() {
        super.aiStep();

        // detect fall
        Vec3 deltaMovement = this.getDeltaMovement();
        if (!this.onGround() && deltaMovement.y < 0.0 && !this.isFalling()) {
            this.setFalling(true);

            // stop idle animation
            this.idleAnimationState.stop();
            this.idleAnimationTimeout = 0;

            // start falling animation
            this.fallingAnimationState.start(this.tickCount);

        } else if (this.isFalling() && this.onGround()) {
            // stop falling animation
            this.setFalling(false);
            this.fallingAnimationState.stop();
        }
    }

    @Override
    protected void updateWalkAnimation(float pPartialTick) {
        float f;
        if(this.getPose() == Pose.STANDING) {
            f = Math.min(pPartialTick * 6F, 1f);
        } else {
            f = 0f;
        }

        this.walkAnimation.update(f, 0.2f);
    }


    public static AttributeSupplier.Builder createAttributes()
    {
        return Animal.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8)
                .add(Attributes.MOVEMENT_SPEED, 0.12D)
                .add(Attributes.FOLLOW_RANGE, 15);
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0, AbstractPenguin.class));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0, FOOD_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 0.7));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    public static boolean canSpawn(EntityType<EmperorPenguinEntity> emperorPenguinEntityEntityType, ServerLevelAccessor serverLevelAccessor, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        return checkAnimalSpawnRules(emperorPenguinEntityEntityType, serverLevelAccessor, mobSpawnType, blockPos, randomSource);
    }
    @Override
    public boolean isFood(ItemStack pStack) {
        return FOOD_ITEMS.test(pStack);
    }

    @Override
    public EntityDimensions getDimensions(Pose pPose) {
        EntityDimensions dimension = this.isBaby() ? BABY_DIMENSIONS : NORMAL_DIMENSIONS;
        return dimension.scale(this.getScale());
    }

    protected float getStandingEyeHeight(Pose pPose, EntityDimensions pSize) {
        return pSize.height * 0.85F;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FALLING, false);
    }

    public void setFalling(boolean falling) {
        this.entityData.set(FALLING, falling);
    }

    public boolean isFalling() {
        return this.entityData.get(FALLING);
    }


    static {
        FOOD_ITEMS = Ingredient.of(new ItemLike[]{Items.SALMON, Items.TROPICAL_FISH, Items.COD});
    }

}
