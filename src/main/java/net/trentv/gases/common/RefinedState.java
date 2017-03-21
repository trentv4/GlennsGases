package net.trentv.gases.common;

import net.minecraft.util.IStringSerializable;

public enum RefinedState implements IStringSerializable
{
	REFINED("refined"),
	UNREFINED("unrefined"),
	RUINED("ruined");

	public final String name;
	
	RefinedState(String name)
	{
		this.name = name;
	}
	
	@Override
	public String getName()
	{
		return name;
	}
}
