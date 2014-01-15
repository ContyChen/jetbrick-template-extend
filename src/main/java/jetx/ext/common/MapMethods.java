package jetx.ext.common;

import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;

import jetbrick.template.JetAnnotations.Methods;

/**
 * 对java.util.Map做的method
 * 
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.7
 */
@Methods
public final class MapMethods {

	public static <K, V> Map<K, V> asImmutable(Map<K, V> self) {
		if (self == null) return null;
		return Collections.unmodifiableMap(self);
	}

	public static <K, V> SortedMap<K, V> asImmutable(SortedMap<K, V> self) {
		if (self == null) return null;
		return Collections.unmodifiableSortedMap(self);
	}
	
	public static <K, V> Map<K, V> asSynchronized(Map<K, V> self) {
		if (self == null) return null;
		return Collections.synchronizedMap(self);
	}

	public static <K, V> SortedMap<K, V> asSynchronized(SortedMap<K, V> self) {
		if (self == null) return null;
		return Collections.synchronizedSortedMap(self);
	}

}
