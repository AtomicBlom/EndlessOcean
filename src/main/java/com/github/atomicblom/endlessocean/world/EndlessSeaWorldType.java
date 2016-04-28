package com.github.atomicblom.endlessocean.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.IChunkGenerator;

public class EndlessSeaWorldType extends WorldType {
    public EndlessSeaWorldType() {
        super("endlessocean");
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {
        return super.getBiomeProvider(world);
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        return new EndlessSeaChunkProvider(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled());
    }
}
