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
import net.trentv.gases.common.reaction.EntityReactionFinine;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.Combustibility;
import net.trentv.gasesframework.api.GFRegistrationAPI;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionBlindness;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionSlowness;
import net.trentv.gasesframework.api.reaction.entity.EntityReactionSuffocation;

public class GasesObjects
{
	public static final DamageSource DAMAGE_SOURCE_STEAM = new DamageSource("gas_steam").setDamageBypassesArmor();
	public static final DamageSource DAMAGE_SOURCE_VOID = new DamageSource("gas_void").setDamageBypassesArmor();

	public static final GasType NATURAL_GAS = new GasType("natural", 0x6F7F6F, 8, 1, Combustibility.FLAMMABLE).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType RED_GAS = new GasType("red", 0x7F0000, 2, -1, Combustibility.EXPLOSIVE).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType COAL_DUST = new GasType("coal_dust", 0x000000, 2, 0, Combustibility.EXPLOSIVE).setCohesion(8).setDissipation(2, 4).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType STEAM = new GasType("steam", 0xFFFFFF, 12, 1, Combustibility.NONE).registerEntityReaction(new EntityReactionDamage(DAMAGE_SOURCE_STEAM, 4)).setCohesion(2).setDissipation(4, 2).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType IOCALFAEUS = new GasTypeLightSensitive("iocalfaeus", 0x5C2B77, 6, -1, Combustibility.NONE).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType BLACK_DAMP = new GasTypeBlackDamp("black_damp", 0x000000, 16, 0, Combustibility.NONE).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType VOID_GAS = new GasType("void", 0x222222, 16, -1, Combustibility.NONE).registerEntityReaction(new EntityReactionDamage(DAMAGE_SOURCE_VOID, 8)).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType NITROUS = new GasType("nitrous", 0x6F0000, 4, -1, Combustibility.NONE).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType FININE = new GasType("finine", 0xFFFFFF, 0, 0, Combustibility.NONE).setCohesion(8).setCreativeTab(GasesFramework.CREATIVE_TAB).setTexture(new ResourceLocation(Gases.MODID, "block/finine"), false).registerEntityReaction(new EntityReactionFinine());
	public static final GasType HELIUM = new GasType("helium", 0x30E3FF, 14, 0, Combustibility.NONE).setCohesion(16).setCreativeTab(GasesFramework.CREATIVE_TAB);

	private static final GasType[] IMPLEMENTED_GASES = new GasType[] { NATURAL_GAS, RED_GAS, COAL_DUST, STEAM, IOCALFAEUS, BLACK_DAMP, VOID_GAS, NITROUS, FININE, HELIUM };

	public static final GasType ELECTRIC = new GasType("electric", 0x000000, 0, 0, Combustibility.NONE).setCohesion(8).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType CORROSIVE = new GasType("corrosive", 0x000000, 0, 0, Combustibility.NONE).setCohesion(8).setCreativeTab(GasesFramework.CREATIVE_TAB);

	private static final HashMap<Block, BlockHeated> HEATED_RECIPE_LIST = new HashMap<Block, BlockHeated>();

	public static final BlockModifiedBedrock MODIFIED_BEDROCK = new BlockModifiedBedrock();

	public static void init()
	{
		for (GasType type : IMPLEMENTED_GASES)
		{
			type.registerEntityReaction(new EntityReactionSlowness(4));
			type.registerEntityReaction(new EntityReactionSuffocation(2, 3));
			type.registerEntityReaction(new EntityReactionBlindness(3));
			registerGas(type);
		}

		registerHeatedRecipe(new BlockHeated(Blocks.IRON_ORE.getDefaultState(), Blocks.IRON_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "iron"));
		registerHeatedRecipe(new BlockHeated(Blocks.DIAMOND_ORE.getDefaultState(), Blocks.DIAMOND_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "diamond"));
		registerHeatedRecipe(new BlockHeated(Blocks.GOLD_ORE.getDefaultState(), Blocks.GOLD_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "gold"));
		registerHeatedRecipe(new BlockHeated(Blocks.REDSTONE_ORE.getDefaultState(), Blocks.REDSTONE_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "redstone"));
		registerHeatedRecipe(new BlockHeated(Blocks.LAPIS_ORE.getDefaultState(), Blocks.LAPIS_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "lapis"));
		registerHeatedRecipe(new BlockHeated(Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState(), "stone"));

		try
		{
			GameRegistry.addSubstitutionAlias("bedrock", Type.BLOCK, MODIFIED_BEDROCK);
		} catch (ExistingSubstitutionException e)
		{
			e.printStackTrace();
		}
	}

	@Nullable
	public static BlockHeated getHeated(Block block)
	{
		return HEATED_RECIPE_LIST.get(block);
	}

	public static BlockHeated[] getAllHeated()
	{
		return HEATED_RECIPE_LIST.values().toArray(new BlockHeated[HEATED_RECIPE_LIST.size()]);
	}
	
	public static void registerHeatedRecipe(BlockHeated block)
	{
		registerBlock(block);
		GasesModelLoader.registeredLocations.put(block.getRegistryName(), block);
		HEATED_RECIPE_LIST.put(block.original.getBlock(), block);
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
