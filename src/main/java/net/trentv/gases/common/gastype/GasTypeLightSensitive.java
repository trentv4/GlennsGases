package net.trentv.gases.common.gastype;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gases.common.GasesObjects;
import net.trentv.gases.common.block.BlockHeated;
import net.trentv.gasesframework.api.Combustibility;
import net.trentv.gasesframework.api.GasType;

public class GasTypeLightSensitive extends GasType
{
	private int tickDelay = 0;
	
	public GasTypeLightSensitive(String name, int color, int opacity, int density, Combustibility combustability)
	{
		super(name, color, opacity, density, combustability);
	}

	@Override
	public boolean preTick(World world, IBlockState state, BlockPos p)
	{
		if(tickDelay-- == 0)
		{
			tickDelay = 15 + (int) Math.floor(Math.random() * 10);
			tickDelay = 1;
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
								BlockHeated r = GasesObjects.getHeated(a);
								if(a instanceof BlockHeated)
								{
									((BlockHeated) a).heat(world, world.getBlockState(pos), pos, (BlockHeated) a);
								}
								if(r != null)
								{
									world.setBlockState(pos, r.getDefaultState());
								}
							}
						}
					}
				}
			}
		}
		return true;
	}
}
