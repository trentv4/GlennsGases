package net.trentv.gases.client;

import java.util.HashMap;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.trentv.gases.common.block.BlockHeated;

public class GasesModelLoader implements ICustomModelLoader
{
	public static HashMap<ResourceLocation, BlockHeated> registeredLocations = new HashMap<ResourceLocation, BlockHeated>();

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager)
	{

	}

	@Override
	public boolean accepts(ResourceLocation modelLocation)
	{
		if (registeredLocations.containsKey(convert(modelLocation)))
		{
			return true;
		}
		return false;
	}

	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws Exception
	{
		ModelResourceLocation res = (ModelResourceLocation) modelLocation;
		BlockHeated a = registeredLocations.get(convert(res));
		if ((res.getVariant().equals("inventory")))
		{
			return new ModelBlockHeated(5, "block/heated/" + a.getRegistryName().getResourcePath());
		}
		else
		{
			String[] str = res.getVariant().split(",");
			for (String s : str)
			{
				if (s.contains("heat="))
				{
					int heat = (int) Integer.valueOf(s.replaceAll("heat=", ""));
					return new ModelBlockHeated(heat, "block/heated/" + a.getRegistryName().getResourcePath());
				}
			}
			return new ModelBlockHeated(7, "block/heated/" + a.getRegistryName().getResourcePath());
		}
	}

	private ResourceLocation convert(ResourceLocation in)
	{
		return new ResourceLocation(in.getResourceDomain(), in.getResourcePath());
	}
}
