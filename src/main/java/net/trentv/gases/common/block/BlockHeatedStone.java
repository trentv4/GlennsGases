package net.trentv.gases.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.trentv.gases.Gases;
import net.trentv.gasesframework.GasesFramework;

public class BlockHeatedStone extends Block
{
	private static final int tickRate = 100;
	public static final PropertyInteger HEAT = PropertyInteger.create("heat", 0, 9);
	public static final PropertyInteger REFINED = PropertyInteger.create("refined", 0, 2);
	// unrefined: 0
	// refined: 1
	// ruined: 2
	public final Block original;
	public final Block refined;
	public final Block ruined;
	
	public BlockHeatedStone(Block original, Block refined, Block ruined)
	{
		super(Material.ROCK);
		setHardness(1.5F);
		setResistance(10.0F);
		setSoundType(SoundType.STONE);
		setRegistryName(new ResourceLocation(Gases.MODID, "heated_stone"));
		setUnlocalizedName("heated_stone");
		setCreativeTab(GasesFramework.CREATIVE_TAB);
		this.original = original;
		this.refined = refined;
		this.ruined = ruined;
		this.setDefaultState(blockState.getBaseState().withProperty(HEAT, 0).withProperty(REFINED, 0));
	}
	
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		world.scheduleBlockUpdate(pos, this, tickRate, 1);
	}
	
	public void updateTick(World world, BlockPos pos, IBlockState state, Random random)
	{
		int level = state.getValue(HEAT);
		int ref = state.getValue(REFINED);
		if(level > 1)
		{
			world.setBlockState(pos, state.withProperty(HEAT, level - 1));
		}
		else
		{
			switch(ref)
			{
				case 0: world.setBlockState(pos, original.getDefaultState()); break;
				case 1: world.setBlockState(pos, refined.getDefaultState()); break;
				case 2: world.setBlockState(pos, ruined.getDefaultState()); break;
			}
		}
		world.scheduleBlockUpdate(pos, this, tickRate, 1);
	}

	@Override
	public BlockStateContainer createBlockState()
	{
		return new BlockStateContainer(this, HEAT, REFINED);
	}

	public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
	{
		return state;
	}
	
	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return getDefaultState().withProperty(HEAT, meta);
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return state.getValue(HEAT);
	}
}
