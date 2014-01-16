/*
 * Copyright 2002-2012 Zhuo Ying. All rights reserved.
 * Email: yingzhor@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
