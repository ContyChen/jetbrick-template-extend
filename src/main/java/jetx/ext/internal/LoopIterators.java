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
