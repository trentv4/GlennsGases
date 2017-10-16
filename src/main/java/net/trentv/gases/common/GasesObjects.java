package net.trentv.gases.common;

import java.util.Arrays;
import java.util.HashMap;

import javax.annotation.Nullable;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.EnumHelper;
import net.trentv.gases.Gases;
import net.trentv.gases.GasesRegistry;
import net.trentv.gases.client.GasesModelLoader;
import net.trentv.gases.common.block.BlockHeated;
import net.trentv.gases.common.block.BlockModifiedBedrock;
import net.trentv.gases.common.gastype.GasTypeBlackDamp;
import net.trentv.gases.common.gastype.GasTypeLightSensitive;
import net.trentv.gases.common.item.ItemDiabalineRefined;
import net.trentv.gases.common.item.ItemRespirator;
import net.trentv.gases.common.reaction.EntityReactionDamage;
import net.trentv.gases.common.reaction.EntityReactionFinine;
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

	public static final GasType NATURAL_GAS = new GasType("natural", 0x6F7F6F, 8, 1, Combustibility.FLAMMABLE).setCreativeTab(Gases.CREATIVE_TAB);
	public static final GasType RED_GAS = new GasType("red", 0x7F0000, 2, -1, Combustibility.EXPLOSIVE).setCreativeTab(Gases.CREATIVE_TAB);
	public static final GasType COAL_DUST = new GasType("coal_dust", 0x000000, 2, 0, Combustibility.EXPLOSIVE).setCohesion(8).setDissipation(2, 4).setCreativeTab(Gases.CREATIVE_TAB);
	public static final GasType STEAM = new GasType("steam", 0xFFFFFF, 12, 1, Combustibility.NONE).registerEntityReaction(new EntityReactionDamage(DAMAGE_SOURCE_STEAM, 4)).setCohesion(2).setDissipation(4, 2).setCreativeTab(Gases.CREATIVE_TAB);
	public static final GasType IOCALFAEUS = new GasTypeLightSensitive("iocalfaeus", 0x5C2B77, 6, -1, Combustibility.NONE).setCreativeTab(Gases.CREATIVE_TAB);
	public static final GasType BLACK_DAMP = new GasTypeBlackDamp("black_damp", 0x000000, 16, 0, Combustibility.NONE).setCreativeTab(Gases.CREATIVE_TAB);
	public static final GasType VOID_GAS = new GasType("void", 0x222222, 0, -1, Combustibility.NONE).registerEntityReaction(new EntityReactionDamage(DAMAGE_SOURCE_VOID, 8)).setCreativeTab(Gases.CREATIVE_TAB);
	public static final GasType NITROUS = new GasType("nitrous", 0x6F0000, 4, -1, Combustibility.NONE).setCreativeTab(Gases.CREATIVE_TAB);
	public static final GasType FININE = new GasType("finine", 0xFFFFFF, 0, 0, Combustibility.NONE).setCohesion(8).setCreativeTab(Gases.CREATIVE_TAB).setTexture(new ResourceLocation(Gases.MODID, "block/finine"), false).registerEntityReaction(new EntityReactionFinine());
	public static final GasType HELIUM = new GasType("helium", 0x30E3FF, 14, 0, Combustibility.NONE).setCohesion(16).setCreativeTab(Gases.CREATIVE_TAB);
	public static final GasType WHISPERING_FOG = new GasType("whispering_fog", 0x000000, 15, -1, Combustibility.HIGHLY_EXPLOSIVE).setCreativeTab(Gases.CREATIVE_TAB);

	private static final GasType[] IMPLEMENTED_GASES = new GasType[] { NATURAL_GAS, RED_GAS, COAL_DUST, STEAM, IOCALFAEUS, BLACK_DAMP, VOID_GAS, NITROUS, FININE, HELIUM, WHISPERING_FOG };

	public static final GasType ELECTRIC = new GasType("electric", 0x000000, 0, 0, Combustibility.NONE).setCohesion(8).setCreativeTab(Gases.CREATIVE_TAB);
	public static final GasType CORROSIVE = new GasType("corrosive", 0x000000, 0, 0, Combustibility.NONE).setCohesion(8).setCreativeTab(Gases.CREATIVE_TAB);

	private static final HashMap<Block, BlockHeated> HEATED_RECIPE_LIST = new HashMap<Block, BlockHeated>();

	public static final BlockModifiedBedrock MODIFIED_BEDROCK = new BlockModifiedBedrock(VOID_GAS, 4, new ResourceLocation("minecraft:bedrock"));

	public static final BlockModifiedBedrock WHISPERING_FOG_EMITTER = (BlockModifiedBedrock) new BlockModifiedBedrock(WHISPERING_FOG, 1, new ResourceLocation(Gases.MODID, "whispering_fog_emitter")).setCreativeTab(Gases.CREATIVE_TAB);

	public static final ItemDiabalineRefined DIABALINE_REFINED = new ItemDiabalineRefined();
	public static final ItemRespirator PRIMITIVE_RESPIRATOR = new ItemRespirator(Arrays.asList(EntityReactionSlowness.class, EntityReactionSuffocation.class), EnumHelper.addArmorMaterial("primitive_respirator", Gases.MODID + ":primitive_respirator", 20, new int[] { 2, 0, 0, 0 }, 12, null, 5), "primitive_respirator", Items.COAL);
	public static final ItemRespirator ADVANCED_RESPIRATOR = new ItemRespirator(Arrays.asList(EntityReactionSlowness.class, EntityReactionSuffocation.class, EntityReactionBlindness.class), EnumHelper.addArmorMaterial("advanced_respirator", Gases.MODID + ":advanced_respirator", 50, new int[] { 2, 0, 0, 0 }, 12, null, 5), "advanced_respirator", Items.IRON_INGOT);

	public static void init()
	{
		for (GasType type : IMPLEMENTED_GASES)
		{
			type.registerEntityReaction(new EntityReactionSlowness(4));
			type.registerEntityReaction(new EntityReactionSuffocation(2, 3));
			type.registerEntityReaction(new EntityReactionBlindness(3));
			GFRegistrationAPI.registerGasType(type, new ResourceLocation(Gases.MODID, "gas_" + type.name));
		}

		registerHeatedRecipe(new BlockHeated(Blocks.IRON_ORE.getDefaultState(), Blocks.IRON_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "iron"));
		registerHeatedRecipe(new BlockHeated(Blocks.DIAMOND_ORE.getDefaultState(), Blocks.DIAMOND_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "diamond"));
		registerHeatedRecipe(new BlockHeated(Blocks.GOLD_ORE.getDefaultState(), Blocks.GOLD_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "gold"));
		registerHeatedRecipe(new BlockHeated(Blocks.REDSTONE_ORE.getDefaultState(), Blocks.REDSTONE_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "redstone"));
		registerHeatedRecipe(new BlockHeated(Blocks.LAPIS_ORE.getDefaultState(), Blocks.LAPIS_BLOCK.getDefaultState(), Blocks.STONE.getDefaultState(), "lapis"));
		registerHeatedRecipe(new BlockHeated(Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState(), Blocks.STONE.getDefaultState(), "stone"));

		GasesRegistry.registerItem(DIABALINE_REFINED, PRIMITIVE_RESPIRATOR, ADVANCED_RESPIRATOR);
		GasesRegistry.registerBlockAndItem(MODIFIED_BEDROCK, WHISPERING_FOG_EMITTER);
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
		GasesRegistry.registerBlockAndItem(block);
		GasesModelLoader.registeredLocations.put(block.getRegistryName(), block);
		HEATED_RECIPE_LIST.put(block.original.getBlock(), block);
	}
}
