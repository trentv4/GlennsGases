package net.trentv.gases.client;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.trentv.gases.common.CommonProxy;
import net.trentv.gases.common.GasesObjects;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		setModel(GasesObjects.HEATED_IRON);
	}
	
	private void setModel(Block obj)
	{
		ModelLoader.setCustomModelResourceLocation(ItemBlock.REGISTRY.getObject(obj.getRegistryName()), 0, new ModelResourceLocation((obj.getRegistryName()), "inventory"));
	}
	
	@Override
	public void registerEventHandlers()
	{
		super.registerEventHandlers();
	}
}
