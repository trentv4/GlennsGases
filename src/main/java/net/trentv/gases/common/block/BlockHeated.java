package net.trentv.gases.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.trentv.gases.Gases;
import net.trentv.gasesframework.GasesFramework;

public class BlockHeated extends Block
{
	private static final int tickRate = 100;
	public static final PropertyInteger HEAT = PropertyInteger.create("heat", 0, 9);
	public static final PropertyInteger REFINED = PropertyInteger.create("refined", 0, 2);
	// unrefined: 0
	// refined: 1
	// ruined: 2
	public final IBlockState original;
	public final IBlockState refined;
	public final IBlockState ruined;
	
	public BlockHeated(IBlockState original, IBlockState refined, IBlockState ruined, String id)
	{
		super(Material.ROCK);
		setHardness(1.5F);
		setResistance(10.0F);
		setSoundType(SoundType.STONE);
		setRegistryName(new ResourceLocation(Gases.MODID, "heated_" + id));
		setUnlocalizedName("heated_" + id);
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
				case 0: world.setBlockState(pos, original); break;
				case 1: world.setBlockState(pos, refined); break;
				case 2: world.setBlockState(pos, ruined); break;
			}
		}
		world.scheduleBlockUpdate(pos, this, tickRate, 1);
	}
	
	public void heat(World world, IBlockState state, BlockPos p, BlockHeated r)
	{
		PropertyInteger HEAT = BlockHeated.HEAT;
		PropertyInteger REFINED = BlockHeated.REFINED;
		//so the code is shorter. remove when done developing this

		if(state.getBlock() == r.original)
		{
			world.setBlockState(p, r.getDefaultState().withProperty(HEAT, 0).withProperty(REFINED, 0));
		}
		else if(state.getBlock() == r)
		{
			int heat = state.getValue(HEAT);
			if(heat < 5)
			{
				world.setBlockState(p, state.withProperty(HEAT, heat + 1));
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
