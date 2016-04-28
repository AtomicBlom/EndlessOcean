package com.github.atomicblom.endlessocean.world;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.chunk.ChunkPrimer;

@SuppressWarnings("deprecation") //base class uses BLOCK_STATE_IDS
public class EndlessSeaChunkPrimer extends ChunkPrimer {
    private static final IBlockState defaultState = Blocks.WATER.getDefaultState();
    private final char[] data = new char[65536];

    public EndlessSeaChunkPrimer()
    {
        for (int i = 0; i < 65536; ++i) {
            data[i] = (char)Block.getStateId(defaultState);
        }
    }

    @Override
    public IBlockState getBlockState(int x, int y, int z)
    {
        final IBlockState iblockstate = Block.BLOCK_STATE_IDS.getByValue(data[getBlockIndex(x, y, z)]);
        return iblockstate == null ? defaultState : iblockstate;
    }

    @Override
    public void setBlockState(int x, int y, int z, IBlockState state)
    {
        data[getBlockIndex(x, y, z)] = (char)Block.BLOCK_STATE_IDS.get(state);
    }

    private static int getBlockIndex(int x, int y, int z)
    {
        return x << 12 | z << 8 | y;
    }

    /**
     * Counting down from the highest block in the sky, find the first non-air block for the given location
     * (actually, looks like mostly checks x, z+1? And actually checks only the very top sky block of actual x, z)
     */
    @Override
    public int findGroundBlockIdx(int x, int z)
    {
        final int i = (x << 12 | z << 8) + 256 - 1;

        for (int j = 255; j >= 0; --j)
        {
            final IBlockState iblockstate = Block.BLOCK_STATE_IDS.getByValue(data[i + j]);

            if (iblockstate != null && iblockstate != defaultState)
            {
                return j;
            }
        }

        return 0;
    }
}
