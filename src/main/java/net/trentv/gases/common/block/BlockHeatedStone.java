package net.trentv.gases.common.block;

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
	public static final PropertyInteger HEAT = PropertyInteger.create("heat", 0, 9);
	public static final PropertyInteger REFINED = PropertyInteger.create("refined", 0, 2);
	// unrefined: 0
	// refined: 1
	// ruined: 2
	
	public BlockHeatedStone()
	{
		super(Material.ROCK);
		setHardness(1.5F);
		setResistance(10.0F);
		setSoundType(SoundType.STONE);
		setRegistryName(new ResourceLocation(Gases.MODID, "heated_stone"));
		setUnlocalizedName("heated_stone");
		setCreativeTab(GasesFramework.CREATIVE_TAB);
		this.setDefaultState(blockState.getBaseState().withProperty(HEAT, 0).withProperty(REFINED, 0));
	}
	
	public void onBlockAdded(World world, BlockPos pos, IBlockState state)
	{
		world.setBlockState(pos, state.withProperty(HEAT, 6).withProperty(REFINED, 1)); // for testing purposes
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
