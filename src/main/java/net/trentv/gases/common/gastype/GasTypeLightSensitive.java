package net.trentv.gases.common.gastype;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gasesframework.api.Combustibility;
import net.trentv.gasesframework.api.GasType;

public class GasTypeLightSensitive extends GasType
{
	public GasTypeLightSensitive(String name, int color, int opacity, int density, Combustibility combustability)
	{
		super(name, color, opacity, density, combustability);
	}

	@Override
	public boolean preTick(World world, IBlockState state, BlockPos p)
	{
		this.density = 0;
		if (!world.isRemote)
		{
			int brightness = world.getLight(p);
			if(brightness >= 4)
			{
				int radius = 2;
				for (int x = -radius; x < radius; x++)
				{
					for (int y = -radius; y < radius; y++)
					{
						for (int z = -radius; z < radius; z++)
						{
							BlockPos pos = new BlockPos(p.getX() + x, p.getY() + y, p.getZ() + z);
							IBlockState s = world.getBlockState(pos);
							Block a = s.getBlock();
							if(a == Blocks.STONE /* || GasesObjects.HEATED_STONE*/)
							{
								heat(world, world.getBlockState(pos), pos);
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	private void heat(World world, IBlockState state, BlockPos p)
	{
		p("asf");
	}
	
	void p(Object o)
	{
		System.out.print(o + "\n");
	}
}
