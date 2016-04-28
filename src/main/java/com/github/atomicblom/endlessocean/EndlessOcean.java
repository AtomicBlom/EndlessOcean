package com.github.atomicblom.endlessocean;

import com.github.atomicblom.endlessocean.world.EndlessSeaWorldProvider;
import com.github.atomicblom.endlessocean.world.EndlessSeaWorldType;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

@Mod(modid = EndlessOcean.MODID, version = EndlessOcean.VERSION)
public class EndlessOcean
{
    public static final String MODID = "endlessocean";
    public static final String VERSION = "0.1";

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        new EndlessSeaWorldType();
    }

    public static DimensionType endlessOceanDimensionType = DimensionType.register("endlessocean", "_endlessocean", 3, EndlessSeaWorldProvider.class, true);

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        DimensionManager.unregisterDimension(0);
        DimensionManager.registerDimension(0, endlessOceanDimensionType);
    }
}
