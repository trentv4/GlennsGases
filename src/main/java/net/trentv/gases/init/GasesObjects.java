package net.trentv.gases.init;

import net.minecraft.util.ResourceLocation;
import net.trentv.gases.Gases;
import net.trentv.gasesframework.GasesFramework;
import net.trentv.gasesframework.api.Combustibility;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.impl.GFRegistrationAPI;

public class GasesObjects
{
	public static final GasType RED_GAS = new GasType("red", 0x7F0000, 2, -1, Combustibility.EXPLOSIVE).setCreativeTab(GasesFramework.CREATIVE_TAB);

	public static void init()
	{
		GFRegistrationAPI.registerGasType(RED_GAS, new ResourceLocation(Gases.MODID, "gas_" + RED_GAS.name));
	}
}
