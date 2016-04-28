package com.github.atomicblom.endlessocean.world;

import com.google.common.collect.Lists;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.chunk.IChunkGenerator;

import java.util.List;

class EndlessSeaChunkProvider implements IChunkGenerator {

    private static final IBlockState ground = Blocks.sand.getDefaultState();

    private final World world;
    private final long seed;
    private final boolean mapFeaturesEnabled;
    private BiomeGenBase[] biomesForGeneration;
    private final double[] heightMap;

    EndlessSeaChunkProvider(World world, long seed, boolean mapFeaturesEnabled) {
        this.world = world;
        this.seed = seed;
        this.mapFeaturesEnabled = mapFeaturesEnabled;
        heightMap = new double[825];
    }

    @Override
    public Chunk provideChunk(int x, int z) {
        final ChunkPrimer chunkprimer = new EndlessSeaChunkPrimer();
        setBlocksInChunk(x, z, chunkprimer);

        final Chunk chunk = new Chunk(world, chunkprimer, x, z);
        /*byte[] abyte = chunk.getBiomeArray();

        for (int i = 0; i < abyte.length; ++i)
        {
            abyte[i] = (byte)BiomeGenBase.getIdForBiome(this.biomesForGeneration[i]);
        }*/

        chunk.generateSkylightMap();
        return chunk;
    }

    public void setBlocksInChunk(int chunkX, int chunkZ, ChunkPrimer primer)
    {
        biomesForGeneration = world.getBiomeProvider().getBiomesForGeneration(biomesForGeneration, chunkX * 4 - 2, chunkZ * 4 - 2, 10, 10);
        generateHeightmap(chunkX * 4, 0, chunkZ * 4);

        /*for (int i = 0; i < 4; ++i)
        {
            int j = i * 5;
            int k = (i + 1) * 5;

            for (int l = 0; l < 4; ++l)
            {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;

                for (int i2 = 0; i2 < 32; ++i2)
                {
                    double d0 = 0.125D;
                    double d1 = this.heightMap[i1 + i2];
                    double d2 = this.heightMap[j1 + i2];
                    double d3 = this.heightMap[k1 + i2];
                    double d4 = this.heightMap[l1 + i2];
                    double d5 = (this.heightMap[i1 + i2 + 1] - d1) * d0;
                    double d6 = (this.heightMap[j1 + i2 + 1] - d2) * d0;
                    double d7 = (this.heightMap[k1 + i2 + 1] - d3) * d0;
                    double d8 = (this.heightMap[l1 + i2 + 1] - d4) * d0;

                    for (int j2 = 0; j2 < 8; ++j2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * d9;
                        double d13 = (d4 - d2) * d9;

                        for (int k2 = 0; k2 < 4; ++k2)
                        {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * d14;
                            double lvt_45_1_ = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2)
                            {
                                if ((lvt_45_1_ += d16) > 0.0D)
                                {

                                }
                                /*else if (i2 * 8 + j2 < this.settings.seaLevel)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.oceanBlock);
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }*/

        for (int x = 0; x < 16; ++x) {
            for (int z = 0; z < 32; ++z) {
                for (int y = 0; y < 16; ++y) {
                    primer.setBlockState(x, y, z, ground);
                }
            }
        }
    }


    @Override
    public void populate(int x, int z) {

    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<BiomeGenBase.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return Lists.newArrayList();
    }

    @Override
    public BlockPos getStrongholdGen(World worldIn, String structureName, BlockPos position) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    private void generateHeightmap(int x, int y, int z) {
        int i = 0;

        for (int k = 0; k < 5; ++k) {
            for (int l = 0; l < 5; ++l) {
                for (int l1 = 0; l1 < 33; ++l1) {
                    heightMap[i] = 32;
                    i++;
                }
            }
        }
    }
}
