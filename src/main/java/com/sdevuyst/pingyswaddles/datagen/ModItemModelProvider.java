package com.sdevuyst.pingyswaddles.datagen;


import com.sdevuyst.pingyswaddles.PingysWaddles;
import com.sdevuyst.pingyswaddles.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;


public class ModItemModelProvider extends ItemModelProvider
{

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper)
    {
        super(output, PingysWaddles.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels()
    {
        withExistingParent(ModItems.EMPEROR_PENGUIN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        simpleItem(ModItems.OAK_SURFBOARD);
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(PingysWaddles.MOD_ID,"item/" + item.getId().getPath())
        );
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(PingysWaddles.MOD_ID,"item/" + item.getId().getPath()));
    }

}
