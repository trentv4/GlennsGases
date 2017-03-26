package net.trentv.gases.common.gastype;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gasesframework.api.Combustibility;
import net.trentv.gasesframework.api.GasType;

public class GasTypeBlackDamp extends GasType
{
	public GasTypeBlackDamp(String name, int color, int opacity, int density, Combustibility combustability)
	{
		super(name, color, opacity, density, combustability);
	}
	
	@Override
    public void randomDisplayTick(IBlockState state, World world, BlockPos pos, Random rand)
    {
		if(rand.nextInt(60) == 0)
		{
			double x = pos.getX() + rand.nextFloat();
			double y = pos.getY() + rand.nextFloat();
			double z = pos.getZ() + rand.nextFloat();
	        world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x, y, z, 0, 0, 0, new int[0]);
		}
    }
	
	@Override
	public boolean preTick(World world, IBlockState state, BlockPos pos)
	{
		return true;
	}
}
