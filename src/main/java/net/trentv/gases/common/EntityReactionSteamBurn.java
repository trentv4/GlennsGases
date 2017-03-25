package net.trentv.gases.common;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.trentv.gases.Gases;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.reaction.entity.IEntityReaction;

public class EntityReactionSteamBurn implements IEntityReaction
{
	@Override
	public void react(Entity e, IBlockAccess access, GasType gas, BlockPos pos)
	{
		e.attackEntityFrom(Gases.damageSourceSteamBurn, 4f);
	}
}
