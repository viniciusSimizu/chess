package com.vini.app.helper;

import java.util.List;

public class Helper {
	static public boolean indexesInsideArray(List<?> array, int[] indexes) {
		return indexesInsideArray(array, indexes, 0);
	}

	static private boolean indexesInsideArray(List<?> array, int[] indexes, int i) {
		if (indexes[i] < 0 || indexes[i] >= array.size()) {
			return false;
		}

		if (i == indexes.length - 1) {
			return true;
		}

		if (!(array.get(indexes[i]) instanceof List)) {
			return false;
		}

		return indexesInsideArray((List<?>) array.get(indexes[i]), indexes, i+1);
	}
}
