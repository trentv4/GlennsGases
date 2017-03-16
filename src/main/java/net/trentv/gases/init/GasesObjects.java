package net.trentv.gases.init;

import net.minecraft.util.ResourceLocation;
import net.trentv.gases.Gases;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.Combustibility;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.impl.GFRegistrationAPI;

public class GasesObjects
{
	public static final GasType NATURAL_GAS = new GasType("natural", 0x6F7F6F, 2, 1, Combustibility.FLAMMABLE).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType RED_GAS = new GasType("red", 0x7F0000, 2, -1, Combustibility.EXPLOSIVE).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType COAL_DUST = new GasType("coal_dust", 0x000000, 2, 0, Combustibility.EXPLOSIVE).setCohesion(8).setDissipation(2, 4).setCreativeTab(GasesFramework.CREATIVE_TAB);
	public static final GasType STEAM = new GasType("steam", 0xFFFFFF, 2, 1, Combustibility.EXPLOSIVE).setCohesion(2).setDissipation(4, 2).setCreativeTab(GasesFramework.CREATIVE_TAB);

	public static void init()
	{
		registerGas(NATURAL_GAS);
		registerGas(RED_GAS);
		registerGas(COAL_DUST);
		registerGas(STEAM);
	}
	
	private static void registerGas(GasType in)
	{
		GFRegistrationAPI.registerGasType(in, new ResourceLocation(Gases.MODID, "gas_" + in.name));
	}
}
