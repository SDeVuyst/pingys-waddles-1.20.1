package com.sdevuyst.pingyswaddles.item.custom;

import com.sdevuyst.pingyswaddles.entity.custom.AbstractSurfboard;
import com.sdevuyst.pingyswaddles.entity.custom.SurfboardEntity;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Predicate;

public class SurfboardItem extends Item {

    private static final Predicate<Entity> ENTITY_PREDICATE;
    private final AbstractSurfboard.Type type;
    private final boolean hasChest;

    public SurfboardItem(boolean pHasChest, SurfboardEntity.Type pType, Item.Properties pProperties) {
        super(pProperties);
        this.hasChest = pHasChest;
        this.type = pType;
    }

    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemInHand = pPlayer.getItemInHand(pHand);
        HitResult hitResult = getPlayerPOVHitResult(pLevel, pPlayer, ClipContext.Fluid.ANY);

        if (hitResult.getType() == HitResult.Type.MISS) {
            return InteractionResultHolder.pass(itemInHand);

        } else {
            Vec3 viewVector = pPlayer.getViewVector(1.0F);
            List<Entity> entityList = pLevel.getEntities(pPlayer, pPlayer.getBoundingBox().expandTowards(viewVector.scale(5.0)).inflate(1.0), ENTITY_PREDICATE);
            if (!entityList.isEmpty()) {
                Vec3 playerEyePosition = pPlayer.getEyePosition();

                for (Entity nextEntity : entityList) {
                    AABB entityBB = nextEntity.getBoundingBox().inflate((double) nextEntity.getPickRadius());
                    if (entityBB.contains(playerEyePosition)) {
                        return InteractionResultHolder.pass(itemInHand);
                    }
                }
            }
        }

        if (hitResult.getType() == HitResult.Type.BLOCK) {
            SurfboardEntity surfboard = this.getSurfboard(pLevel, hitResult);
            surfboard.setVariant(this.type);
            surfboard.setYRot(pPlayer.getYRot());
            if (!pLevel.noCollision(surfboard, surfboard.getBoundingBox())) {
                return InteractionResultHolder.fail(itemInHand);
            } else {
                if (!pLevel.isClientSide) {
                    pLevel.addFreshEntity(surfboard);
                    pLevel.gameEvent(pPlayer, GameEvent.ENTITY_PLACE, hitResult.getLocation());
                    if (!pPlayer.getAbilities().instabuild) {
                        itemInHand.shrink(1);
                    }
                }

                pPlayer.awardStat(Stats.ITEM_USED.get(this));
                return InteractionResultHolder.sidedSuccess(itemInHand, pLevel.isClientSide());
            }
        } else {
            return InteractionResultHolder.pass(itemInHand);
        }
    }

    private SurfboardEntity getSurfboard(Level pLevel, HitResult pHitResult) {
        return new SurfboardEntity(pLevel, pHitResult.getLocation().x, pHitResult.getLocation().y, pHitResult.getLocation().z);
    }

    static {
        ENTITY_PREDICATE = EntitySelector.NO_SPECTATORS.and(Entity::isPickable);
    }
}

