package net.trentv.gases.common.block;

import java.util.Random;

import net.minecraft.block.BlockEmptyDrops;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.trentv.gasesframework.api.GFManipulationAPI;
import net.trentv.gasesframework.api.GasType;

public class BlockModifiedBedrock extends BlockEmptyDrops
{
	private GasType producedGas;
	private int producedAmount;
	private int maxLightLevel;

	public BlockModifiedBedrock(GasType producedGas, int producedAmount, int maxLightLevel, ResourceLocation registry)
	{
		super(Material.ROCK);
		this.producedGas = producedGas;
		this.producedAmount = producedAmount;
		this.maxLightLevel = maxLightLevel;
		setResistance(6000000.0F);
		setSoundType(SoundType.STONE);
		setBlockUnbreakable();
		disableStats();
		setRegistryName(registry);
		setTickRandomly(true);
		setTranslationKey(registry.getPath());
	}

	@Override
	public void updateTick(World world, BlockPos pos, IBlockState state, Random rand)
	{
		BlockPos newPos = pos.offset(EnumFacing.values()[rand.nextInt(6)]);
		if (GFManipulationAPI.canPlaceGas(newPos, world, producedGas))
		{
			if (world.getLight(newPos) < maxLightLevel)
			{
				GFManipulationAPI.addGasLevel(newPos, world, producedGas, producedAmount);
			}
		}
	}
}
