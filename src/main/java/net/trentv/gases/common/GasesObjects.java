package net.trentv.gases.common;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.trentv.gases.Gases;
import net.trentv.gases.common.block.BlockHeatedStone;
import net.trentv.gases.common.gastype.GasTypeLightSensitive;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.Combustibility;
import net.trentv.gasesframework.api.GFRegistrationAPI;
import net.trentv.gasesframework.api.GasType;

public class GasesObjects
{
	public static final GasType NATURAL_GAS = new GasType("natural", 0x6F7F6F, 2, 1, Combustibility.FLAMMABLE).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType RED_GAS = new GasType("red", 0x7F0000, 2, -1, Combustibility.EXPLOSIVE).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType COAL_DUST = new GasType("coal_dust", 0x000000, 2, 0, Combustibility.EXPLOSIVE).setCohesion(8).setDissipation(2, 4).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType STEAM = new GasType("steam", 0xFFFFFF, 2, 1, Combustibility.EXPLOSIVE).setCohesion(2).setDissipation(4, 2).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType IOCALFAEUS = new GasTypeLightSensitive("iocalfaeus", 0xFFFFFF, 2, -1, Combustibility.NONE).setCreativeTab(GasesFramework.CREATIVE_TAB);

	public static final Block HEATED_STONE = new BlockHeatedStone();
	
	public static void init()
	{
		registerGas(NATURAL_GAS);
		registerGas(RED_GAS);
		registerGas(COAL_DUST);
		registerGas(STEAM);
		registerGas(IOCALFAEUS);
		
		registerBlock(HEATED_STONE);
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
