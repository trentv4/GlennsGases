package net.trentv.gases.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.trentv.gases.common.GasesObjects;
import net.trentv.gasesframework.api.GFManipulationAPI;

public class ClientEvents
{
	@SubscribeEvent
	public void onPlaySoundEvent(PlaySoundEvent event)
	{
		if (Minecraft.getMinecraft().world == null)
			return;
		World world = Minecraft.getMinecraft().world;

		if (event.getSound() instanceof PositionedSoundRecord)
		{
			PositionedSoundRecord sound = (PositionedSoundRecord) event.getSound();
			BlockPos pos = new BlockPos(sound.getXPosF(), sound.getYPosF() + 1, sound.getZPosF());
			if (GFManipulationAPI.getGasType(pos, world) == GasesObjects.HELIUM)
			{
				PositionedSoundRecord replacedSound = new PositionedSoundRecord(sound.getSoundLocation(), sound.getCategory(), 1, 5, sound.canRepeat(), sound.getRepeatDelay(), sound.getAttenuationType(), sound.getXPosF(), sound.getYPosF(), sound.getZPosF());
				event.setResultSound(replacedSound);
			}
		}
	}
}
