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

import static net.trentv.gases.common.GasesObjects.VOID_GAS;

public class BlockModifiedBedrock extends BlockEmptyDrops
{
	public BlockModifiedBedrock()
	{
		super(Material.ROCK);
		setBlockUnbreakable();
		setResistance(6000000.0F);
		setSoundType(SoundType.STONE);
		setUnlocalizedName("bedrock");
		disableStats();
		setRegistryName(Gases.MODID, "bedrock");
		setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
		setTickRandomly(true);
	}

	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		BlockPos newPos = pos.offset(EnumFacing.values()[rand.nextInt(6)]);
		if (GFManipulationAPI.canPlaceGas(newPos, world, VOID_GAS))
		{
			GFManipulationAPI.addGasLevel(newPos, world, VOID_GAS, 1);
		}
	}
}
