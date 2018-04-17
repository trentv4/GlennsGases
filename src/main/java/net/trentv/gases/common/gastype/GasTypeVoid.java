package net.trentv.gases.common.gastype;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gasesframework.api.Combustibility;
import net.trentv.gasesframework.api.GFManipulationAPI;
import net.trentv.gasesframework.api.GasType;

public class GasTypeVoid extends GasType
{
	public GasTypeVoid(String name, int color, int opacity, int density, Combustibility combustability)
	{
		super(name, color, opacity, density, combustability);
	}

	@Override
	public boolean preTick(World world, IBlockState state, BlockPos pos)
	{
		if (world.getLight(pos) > 5)
		{
			GFManipulationAPI.removeGasLevel(pos, world, 16);
		}
		return true;
	}
}
