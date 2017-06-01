package net.trentv.gases.common.reaction;

import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.trentv.gasesframework.api.GasType;
import net.trentv.gasesframework.api.reaction.entity.IEntityReaction;

public class EntityReactionFinine implements IEntityReaction
{
	private Random random = new Random();

	@Override
	public void react(Entity e, IBlockAccess access, GasType gas, BlockPos pos) {
		BlockPos originalPosition = e.getPosition();
		BlockPos newPosition = new BlockPos(originalPosition);			
		int iterations = 0;
		do {
			iterations++;
			newPosition = new BlockPos(originalPosition);

			int newX = random.nextInt(16)-8;
			int newY = random.nextInt(16)-8;
			int newZ = random.nextInt(16)-8;
			newPosition = newPosition.add(newX, newY, newZ);

		} while(iterations < 200 & (access.isAirBlock(pos) & access.isAirBlock(pos.up())));

		e.setPositionAndRotation(newPosition.getX(), newPosition.getY(), newPosition.getZ(), random.nextInt(360), random.nextInt(180)-90);
	}
}
