package com.vini.app.translate;

import java.util.HashMap;
import java.util.Map;

import com.vini.app.types.ColorEnum;

public class ColorTranslate {
	public static final Map<ColorEnum, String> book = new HashMap<ColorEnum, String>();
	static {
		book.put(ColorEnum.WHITE, "White");
		book.put(ColorEnum.BLACK, "Black");
	}
}
