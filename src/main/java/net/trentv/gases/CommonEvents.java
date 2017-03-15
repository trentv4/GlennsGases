package net.trentv.gases;

import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.trentv.gases.init.GasesObjects;
import net.trentv.gasesframework.impl.GFManipulationAPI;

public class CommonEvents
{
	@SubscribeEvent
	public void onBlockBreak(BlockEvent.BreakEvent event)
	{
		System.out.print(event.getState().getBlock().getRegistryName());
		if(event.getState().getBlock() == Blocks.COAL_ORE)
		{
			GFManipulationAPI.addGasLevel(event.getPos(), event.getWorld(), GasesObjects.COAL_DUST, 8);
			event.setCanceled(true);
		}
	}
}
