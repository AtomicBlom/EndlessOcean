package com.github.atomicblom.endlessocean.world;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.IRenderHandler;

/**
 * Created by codew on 28/04/2016.
 */
public class EndlessSeaWorldProvider extends WorldProvider
{
    @Override
    public DimensionType getDimensionType()
    {
        return DimensionType.OVERWORLD;
    }

    @Override
    public boolean canDoRainSnowIce(Chunk chunk)
    {
        return false;
    }

    @Override
    public boolean canDoLightning(Chunk chunk)
    {
        return false;
    }

    @Override
    public IRenderHandler getCloudRenderer()
    {
        return null;
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }
}
