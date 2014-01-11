package jetx.ext.internal;

import java.util.Iterator;


/**
 * @author 应卓(yingzhor@gmail.com)
 */
public final class LoopIterators {

	private LoopIterators() {}
	
	public static Iterator<Long> upto(long start, long stop, long step) {
		return new UptoIterator(start, stop, step);
	}

	public static Iterator<Long> downto(long start, long stop, long step) {
		return new DowntoIterator(start, stop, step);
	}

	private static final class UptoIterator implements Iterator<Long> {
		private long start;
		private long stop;
		private long step;
		public UptoIterator(long start, long stop, long step) {
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
	
	private static final class DowntoIterator implements Iterator<Long> {
		private long start;
		private long stop;
		private long step;
		public DowntoIterator(long start, long stop, long step) {
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
}
