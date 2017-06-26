package net.trentv.gases.common.gastype;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gases.common.GasesObjects;
import net.trentv.gases.common.block.BlockHeated;
import net.trentv.gasesframework.api.Combustibility;
import net.trentv.gasesframework.api.GFManipulationAPI;
import net.trentv.gasesframework.api.GasType;

public class GasTypeLightSensitive extends GasType
{
	private int tickDelay = 0;
	private static Random rand = new Random();

	public GasTypeLightSensitive(String name, int color, int opacity, int density, Combustibility combustability)
	{
		super(name, color, opacity, density, combustability);
	}

	@Override
	public boolean preTick(World world, IBlockState state, BlockPos p)
	{
		if (tickDelay-- == 0)
		{
			int currentGasLevel = GFManipulationAPI.getGasLevel(p, world);
			tickDelay = 0 + (16 - currentGasLevel) + rand.nextInt(8);
			if (!world.isRemote)
			{
				int brightness = world.getLight(p);
				if (brightness >= 4)
				{
					int radius = 3;
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
								if (a instanceof BlockHeated)
								{
									((BlockHeated) a).heat(world, world.getBlockState(pos), pos, (BlockHeated) a);
								}
								if (r != null)
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
