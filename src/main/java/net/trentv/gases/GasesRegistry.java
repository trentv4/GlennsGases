package net.trentv.gases;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GasesRegistry
{
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	public static ArrayList<Item> items = new ArrayList<Item>();

	// Utility methods

	public static void registerBlock(Block... toRegister)
	{
		for (Block in : toRegister) blocks.add(in);
	}

	public static void registerBlockAndItem(Block... toRegister)
	{
		for (Block in : toRegister)
		{
			blocks.add(in);
			ItemBlock a = new ItemBlock(in);
			a.setRegistryName(in.getRegistryName());
			items.add(a);
		}
	}

	public static void registerItem(Item... toRegister)
	{
		for (Item in : toRegister) items.add(in);
	}
	
	// Events
	@SubscribeEvent
	public void registerRenderers(ModelRegistryEvent event)
	{
		for (Item obj : items)
		{
			ModelLoader.setCustomModelResourceLocation(obj, 0, new ModelResourceLocation(obj.getRegistryName(), "inventory"));
		}
	}
	
	@SubscribeEvent
	public void registerBlocks(RegistryEvent.Register<Block> event)
	{
		for (Block block : blocks) event.getRegistry().register(block);
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event)
	{
		for (Item item : items) event.getRegistry().register(item);
	}
}
