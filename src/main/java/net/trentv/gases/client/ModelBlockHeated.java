package net.trentv.gases.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.lwjgl.util.vector.Vector3f;

import com.google.common.base.Function;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockFaceUV;
import net.minecraft.client.renderer.block.model.BlockPartFace;
import net.minecraft.client.renderer.block.model.FaceBakery;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.block.model.ModelRotation;
import net.minecraft.client.renderer.block.model.SimpleBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.common.model.IModelState;
import net.trentv.gases.Gases;

public class ModelBlockHeated implements IModel
{
	private FaceBakery bakery = new FaceBakery();
	public ResourceLocation texture;

	public ModelBlockHeated(int heat, String original)
	{
		if (heat == 9)
		{
			texture = new ResourceLocation(Gases.MODID, original + "_7");
		}
		else if (heat > 5 && heat < 9)
		{
			texture = new ResourceLocation(Gases.MODID, original + "_6_" + (heat - 6));
		}
		else if (heat < 6)
		{
			texture = new ResourceLocation(Gases.MODID, original + "_" + heat);
		}
	}

	@Override
	public Collection<ResourceLocation> getDependencies()
	{
		return new ArrayList<ResourceLocation>();
	}

	@Override
	public Collection<ResourceLocation> getTextures()
	{
		ArrayList<ResourceLocation> a = new ArrayList<ResourceLocation>();
		a.add(texture);
		return a;
	}

	@Override
	public IBakedModel bake(IModelState state, VertexFormat format, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter)
	{
		// facings in D-U-N-S-W-E order
		BakedQuad faceDown = getQuad(new Vector3f(0, 0, 0), new Vector3f(16, 0, 16), new BlockFaceUV(new float[] { 0, 0, 16, 16 }, 0), EnumFacing.DOWN, bakedTextureGetter, 16);
		BakedQuad faceUp = getQuad(new Vector3f(0, 0, 0), new Vector3f(16, 16, 16), new BlockFaceUV(new float[] { 0, 0, 16, 16 }, 0), EnumFacing.UP, bakedTextureGetter, 16);
		BakedQuad faceNorth = getQuad(new Vector3f(0, 0, 0), new Vector3f(16, 16, 16), new BlockFaceUV(new float[] { 0, 0, 16, 16 }, 0), EnumFacing.NORTH, bakedTextureGetter, 16);
		BakedQuad faceSouth = getQuad(new Vector3f(0, 0, 0), new Vector3f(16, 16, 16), new BlockFaceUV(new float[] { 0, 0, 16, 16 }, 0), EnumFacing.SOUTH, bakedTextureGetter, 16);
		BakedQuad faceWest = getQuad(new Vector3f(0, 0, 0), new Vector3f(16, 16, 16), new BlockFaceUV(new float[] { 0, 0, 16, 16 }, 0), EnumFacing.WEST, bakedTextureGetter, 16);
		BakedQuad faceEast = getQuad(new Vector3f(0, 0, 0), new Vector3f(16, 16, 16), new BlockFaceUV(new float[] { 0, 0, 16, 16 }, 0), EnumFacing.EAST, bakedTextureGetter, 16);
		List<BakedQuad> allQuads = Arrays.asList(faceDown, faceUp, faceNorth, faceSouth, faceWest, faceEast);

		HashMap<EnumFacing, List<BakedQuad>> faceQuads = new HashMap<EnumFacing, List<BakedQuad>>();
		for (int i = 0; i < allQuads.size(); i++)
		{
			BakedQuad a = allQuads.get(i);
			faceQuads.put(a.getFace(), Arrays.asList(a));
		}

		SimpleBakedModel newModel = new SimpleBakedModel(allQuads, faceQuads, true, true, bakedTextureGetter.apply(texture), ItemCameraTransforms.DEFAULT, ItemOverrideList.NONE);
		return newModel;
	}

	private BakedQuad getQuad(Vector3f from, Vector3f to, BlockFaceUV uv, EnumFacing direction, Function<ResourceLocation, TextureAtlasSprite> bakedTextureGetter, int quantity)
	{
		return bakery.makeBakedQuad(from, to, new BlockPartFace(null, 0, texture.toString(), uv), bakedTextureGetter.apply(texture), direction, ModelRotation.X0_Y0, null, true, true);
	}

	@Override
	public IModelState getDefaultState()
	{
		return null;
	}
}
