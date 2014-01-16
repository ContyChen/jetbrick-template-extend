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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import jetbrick.template.JetAnnotations.Methods;

/**
 * 对java.util.Collection做的method
 * 
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.7
 */
@Methods
public class CollectionMethods {

	public static <E> Collection<E> asImmutable(Collection<E> self) {
		if (self == null) return null;
		return Collections.unmodifiableCollection(self);
	}

	public static <E> List<E> asImmutable(List<E> self) {
		if (self == null) return null;
		return Collections.unmodifiableList(self);
	}
	
	public static <E> Set<E> asImmutable(Set<E> self) {
		if (self == null) return null;
		return Collections.unmodifiableSet(self);
	}
	
	public static <E> SortedSet<E> asImmutable(SortedSet<E> self) {
		if (self == null) return null;
		return Collections.unmodifiableSortedSet(self);
	}
	
	// -----------------------------------------------------------------------------------
	
	public static <E> Collection<E> asSynchronized(Collection<E> self) {
		if (self == null) return null;
		return Collections.synchronizedCollection(self);
	}
	
	public static <E> List<E> asSynchronized(List<E> self) {
		if (self == null) return null;
		return Collections.synchronizedList(self);
	}

	public static <E> Set<E> asSynchronized(Set<E> self) {
		if (self == null) return null;
		return Collections.synchronizedSet(self);
	}
	
	public static <E> SortedSet<E> asSynchronized(SortedSet<E> self) {
		return Collections.synchronizedSortedSet(self);
	}

}
