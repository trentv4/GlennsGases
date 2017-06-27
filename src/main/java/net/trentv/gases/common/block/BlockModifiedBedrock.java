package net.trentv.gases.common.block;

import java.util.Random;

import net.minecraft.block.BlockEmptyDrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gases.Gases;
import net.trentv.gasesframework.api.GFManipulationAPI;
import net.trentv.gasesframework.api.GasType;

public class BlockModifiedBedrock extends BlockEmptyDrops
{
	private GasType producedGas;
	private int producedAmount;

	public BlockModifiedBedrock(GasType producedGas, int producedAmount, String regName)
	{
		super(Material.ROCK);
		this.producedGas = producedGas;
		this.producedAmount = producedAmount;
		setBlockUnbreakable();
		setResistance(6000000.0F);
		setSoundType(SoundType.STONE);
		setUnlocalizedName(regName);
		disableStats();
		setRegistryName(Gases.MODID, regName);
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setTickRandomly(true);
	}

	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		BlockPos newPos = pos.offset(EnumFacing.values()[rand.nextInt(6)]);
		if (GFManipulationAPI.canPlaceGas(newPos, world, producedGas))
		{
			GFManipulationAPI.addGasLevel(newPos, world, producedGas, producedAmount);
		}
	}
}
