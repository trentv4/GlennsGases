package net.trentv.gases.common;

import java.util.HashMap;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ExistingSubstitutionException;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry.Type;
import net.trentv.gases.Gases;
import net.trentv.gases.client.GasesModelLoader;
import net.trentv.gases.common.block.BlockHeated;
import net.trentv.gases.common.block.BlockModifiedBedrock;
import net.trentv.gases.common.gastype.GasTypeBlackDamp;
import net.trentv.gases.common.gastype.GasTypeLightSensitive;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.Combustibility;
import net.trentv.gasesframework.api.GFRegistrationAPI;
import net.trentv.gasesframework.api.GasType;

public class GasesObjects
{
	public static DamageSource damageSourceSteamBurn = new DamageSource("gas_steam").setDamageBypassesArmor();
	public static DamageSource damageSourceVoid = new DamageSource("gas_void").setDamageBypassesArmor();

	public static final GasType NATURAL_GAS = new GasType("natural", 0x6F7F6F, 2, 1, Combustibility.FLAMMABLE).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType RED_GAS = new GasType("red", 0x7F0000, 2, -1, Combustibility.EXPLOSIVE).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType COAL_DUST = new GasType("coal_dust", 0x000000, 2, 0, Combustibility.EXPLOSIVE).setCohesion(8).setDissipation(2, 4).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType STEAM = new GasType("steam", 0xFFFFFF, 2, 1, Combustibility.NONE).registerEntityReaction(new EntityReactionDamage(damageSourceSteamBurn, 4)).setCohesion(2).setDissipation(4, 2).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType IOCALFAEUS = new GasTypeLightSensitive("iocalfaeus", 0x5C2B77, 2, -1, Combustibility.NONE).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType BLACK_DAMP = new GasTypeBlackDamp("black_damp", 0x000000, 2, 0, Combustibility.NONE).setTexture(new ResourceLocation(Gases.MODID, "block/black_damp"), false).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType VOID_GAS = new GasType("void", 0x222222, 2, -1, Combustibility.NONE).registerEntityReaction(new EntityReactionDamage(damageSourceVoid, 8)).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType NITROUS = new GasType("nitrous", 0x6F0000, 2, -1, Combustibility.NONE).setCreativeTab(GasesFramework.CREATIVE_TAB);

	public static HashMap<Block, BlockHeated> heatedRecipe = new HashMap<Block, BlockHeated>();
	public static final BlockHeated HEATED_IRON = new BlockHeated(Blocks.IRON_ORE.getDefaultState(), Blocks.IRON_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "iron");
	public static final BlockHeated HEATED_DIAMOND = new BlockHeated(Blocks.DIAMOND_ORE.getDefaultState(), Blocks.DIAMOND_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "diamond");
	public static final BlockHeated HEATED_GOLD = new BlockHeated(Blocks.GOLD_ORE.getDefaultState(), Blocks.GOLD_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "gold");
	public static final BlockHeated HEATED_REDSTONE = new BlockHeated(Blocks.REDSTONE_ORE.getDefaultState(), Blocks.REDSTONE_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "redstone");
	public static final BlockHeated HEATED_LAPIS = new BlockHeated(Blocks.LAPIS_ORE.getDefaultState(), Blocks.LAPIS_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "lapis");
	public static final BlockHeated HEATED_STONE = new BlockHeated(Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState(), "stone");

	public static void init()
	{
		registerGas(NATURAL_GAS);
		registerGas(RED_GAS);
		registerGas(COAL_DUST);
		registerGas(STEAM);
		registerGas(IOCALFAEUS);
		registerGas(BLACK_DAMP);
		registerGas(VOID_GAS);
		registerGas(NITROUS);
		
		registerHeatedRecipe(HEATED_IRON);
		registerHeatedRecipe(HEATED_DIAMOND);
		registerHeatedRecipe(HEATED_GOLD);
		registerHeatedRecipe(HEATED_REDSTONE);
		registerHeatedRecipe(HEATED_LAPIS);
		registerHeatedRecipe(HEATED_STONE);
		
		try
		{
			GameRegistry.addSubstitutionAlias("bedrock", Type.BLOCK, new BlockModifiedBedrock());
		} catch (ExistingSubstitutionException e)
		{
			e.printStackTrace();
		}
	}

	@Nullable
	public static BlockHeated getHeated(Block block)
	{
		return heatedRecipe.get(block);
	}
	
	public static void registerHeatedRecipe(BlockHeated block)
	{
		registerBlock(block);
		GasesModelLoader.registeredLocations.put(block.getRegistryName(), block);
		heatedRecipe.put(block.original.getBlock(), block);
	}
	
	private static void registerBlock(Block in)
	{
		ItemBlock a = new ItemBlock(in);
		a.setRegistryName(in.getRegistryName());
		GameRegistry.register(in);
		GameRegistry.register(a);
	}
	
	private static void registerGas(GasType in)
	{
		GFRegistrationAPI.registerGasType(in, new ResourceLocation(Gases.MODID, "gas_" + in.name));
	}
}
