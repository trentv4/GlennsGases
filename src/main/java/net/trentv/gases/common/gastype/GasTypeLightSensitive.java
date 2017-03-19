package net.trentv.gases.common.gastype;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gases.common.block.BlockHeatedStone;
import net.trentv.gases.init.GasesObjects;
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
								if(a == Blocks.STONE || a == GasesObjects.HEATED_STONE)
								{
									heat(world, world.getBlockState(pos), pos);
								}
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
		PropertyInteger HEAT = BlockHeatedStone.HEAT;
		PropertyInteger REFINED = BlockHeatedStone.REFINED;
		//so the code is shorter. remove when done developing this

		if(state.getBlock() == Blocks.STONE)
		{
			world.setBlockState(p, GasesObjects.HEATED_STONE.getDefaultState().withProperty(HEAT, 0));
		}
		else if(state.getBlock() == GasesObjects.HEATED_STONE)
		{
			int heat = state.getValue(HEAT);
			if(heat < 5)
			{
				world.setBlockState(p, state.withProperty(HEAT, heat + 1).withProperty(REFINED, 0));
			}
			else if(heat == 5)
			{
				int addAmount = (int) Math.ceil(Math.random() * 3);
				if(addAmount == 1)
				{
					world.setBlockState(p, state.withProperty(HEAT, heat + addAmount).withProperty(REFINED, 1));
				}
				else
				{
					world.setBlockState(p, state.withProperty(HEAT, heat + addAmount).withProperty(REFINED, 2));
				}
			}
			else if(heat >= 6 & heat <= 8)
			{
				world.setBlockState(p, state.withProperty(HEAT, 9).withProperty(REFINED, 2));
			}
			else if(heat == 9)
			{
				world.setBlockState(p, Blocks.LAVA.getDefaultState());
			}
		}
	}
	
	void p(Object o)
	{
		System.out.print(o + "\n");
	}
}
