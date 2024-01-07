package com.sdevuyst.pingyswaddles.entity.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.function.IntFunction;

public abstract class AbstractSurfboard extends Entity {

    private static final EntityDataAccessor<Integer> DATA_ID_TYPE = SynchedEntityData.defineId(AbstractSurfboard.class, EntityDataSerializers.INT);

    public AbstractSurfboard(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public AbstractSurfboard(EntityType<?> pEntityType, Level pLevel, double pX, double pY, double pZ) {
        this(pEntityType, pLevel);
        this.setPos(pX, pY, pZ);
        this.xo = pX;
        this.yo = pY;
        this.zo = pZ;
    }

    protected void defineSynchedData() {
        this.entityData.define(DATA_ID_TYPE, SurfboardEntity.Type.OAK.ordinal());
    }

    protected void addAdditionalSaveData(CompoundTag pCompound) {
        pCompound.putString("Type", this.getModVariant().getSerializedName());
    }

    protected void readAdditionalSaveData(CompoundTag pCompound) {
        if (pCompound.contains("Type", 8)) {
            this.setVariant(SurfboardEntity.Type.byName(pCompound.getString("Type")));
        }
    }

    public void setVariant(SurfboardEntity.Type pVariant) {
        this.entityData.set(DATA_ID_TYPE, pVariant.ordinal());
    }

    public SurfboardEntity.Type getModVariant() {
        return SurfboardEntity.Type.byId(this.entityData.get(DATA_ID_TYPE));
    }

    public SurfboardEntity.Type getVariant() {
        return SurfboardEntity.Type.byId((Integer)this.entityData.get(DATA_ID_TYPE));
    }

    public static enum Type implements StringRepresentable {
        OAK(Blocks.OAK_PLANKS, "oak");

        private final String name;
        private final Block planks;
        public static final StringRepresentable.EnumCodec<SurfboardEntity.Type> CODEC = StringRepresentable.fromEnum(SurfboardEntity.Type::values);
        private static final IntFunction<Type> BY_ID = ByIdMap.continuous(Enum::ordinal, values(), ByIdMap.OutOfBoundsStrategy.ZERO);

        private Type(Block pPlanks, String pName) {
            this.name = pName;
            this.planks = pPlanks;
        }

        public String getSerializedName() {
            return this.name;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        public String toString() {
            return this.name;
        }

        /**
         * Get a surfboard type by its enum ordinal
         */
        public static SurfboardEntity.Type byId(int pId) {
            return BY_ID.apply(pId);
        }

        public static SurfboardEntity.Type byName(String pName) {
            return CODEC.byName(pName, OAK);
        }
    }

}
