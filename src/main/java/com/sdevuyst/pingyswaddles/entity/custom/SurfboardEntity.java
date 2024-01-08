package com.sdevuyst.pingyswaddles.entity.custom;

import com.sdevuyst.pingyswaddles.entity.ModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public class SurfboardEntity extends AbstractSurfboard {

    public SurfboardEntity(EntityType<?> entityEntityType, Level level) {
        super(entityEntityType, level);
    }

    public SurfboardEntity(Level pLevel, double pX, double pY, double pZ) {
        super(ModEntities.SURFBOARD.get(), pLevel, pX, pY, pZ);
    }

}