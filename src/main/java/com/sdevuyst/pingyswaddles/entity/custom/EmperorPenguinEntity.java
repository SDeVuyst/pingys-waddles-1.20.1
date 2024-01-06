package com.sdevuyst.pingyswaddles.entity.custom;

import com.sdevuyst.pingyswaddles.entity.ModEntities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EmperorPenguinEntity extends AbstractPenguin {

    public EmperorPenguinEntity(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.EMPEROR_PENGUIN.get().create(serverLevel);
    }

    static {
        NORMAL_DIMENSIONS = EntityDimensions.fixed(1.0F, 1.9F);
    }
}
