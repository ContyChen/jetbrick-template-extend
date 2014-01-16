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
package jetx.ext.internal;

import java.util.Iterator;


/**
 * @author 应卓(yingzhor@gmail.com)
 */
public final class LoopIterators {

	private LoopIterators() {}
	
	public static Iterator<Long> upto(long start, long stop, long step) {
		return new UptoIteratorLong(start, stop, step);
	}

	public static Iterator<Long> downto(long start, long stop, long step) {
		return new DowntoIteratorLong(start, stop, step);
	}

	public static Iterator<Integer> upto(int start, int stop, int step) {
		return new UptoIteratorInt(start, stop, step);
	}
	
	public static Iterator<Integer> downto(int start, int stop, int step) {
		return new DowntoIteratorInt(start, stop, step);
	}

	private static final class UptoIteratorLong implements Iterator<Long> {
		private long start;
		private long stop;
		private long step;
		public UptoIteratorLong(long start, long stop, long step) {
			this.start = start;
			this.stop  = stop + 1;
			this.step  = step;
		}
		public boolean hasNext() {
			return start < stop;
		}
		public Long next() {
			try {return start;} finally {start += step;}
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	private static final class DowntoIteratorLong implements Iterator<Long> {
		private long start;
		private long stop;
		private long step;
		public DowntoIteratorLong(long start, long stop, long step) {
			this.start = start + 1;
			this.stop  = stop;
			this.step  = step;
		}
		public boolean hasNext() {
			return start > stop;
		}
		public Long next() {
			return start -= step;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	private static final class UptoIteratorInt implements Iterator<Integer> {
		private int start;
		private int stop;
		private int step;
		public UptoIteratorInt(int start, int stop, int step) {
			this.start = start;
			this.stop  = stop + 1;
			this.step  = step;
		}
		public boolean hasNext() {
			return start < stop;
		}
		public Integer next() {
			try {return start;} finally {start += step;}
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	private static final class DowntoIteratorInt implements Iterator<Integer> {
		private int start;
		private int stop;
		private int step;
		public DowntoIteratorInt(int start, int stop, int step) {
			this.start = start + 1;
			this.stop  = stop;
			this.step  = step;
		}
		public boolean hasNext() {
			return start > stop;
		}
		public Integer next() {
			return start -= step;
		}
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
}
