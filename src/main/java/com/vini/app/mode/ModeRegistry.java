package com.vini.app.mode;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class ModeRegistry {
	private static ModeRegistry instance;
	private Map<ModeEnum, ModeImpl> registry = new EnumMap<>(ModeEnum.class);
	private ModeFactory factory = ModeFactory.instance();

	private ModeRegistry() {
		this.registry.put(ModeEnum.CLASSIC, this.factory.makeClassic());
	}

	public List<ModeImpl> modes() {
		return new ArrayList<ModeImpl>(this.registry.values());
	}
 

	public ModeImpl getByModeEnum(ModeEnum mode) {
		return this.registry.get(mode);
	}

	public static ModeRegistry instance() {
		if (ModeRegistry.instance == null) {
			ModeRegistry.instance = new ModeRegistry();
		}
		return ModeRegistry.instance;
	}
}
