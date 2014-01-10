package jetx.ext.common;

import java.util.Iterator;

import jetbrick.template.JetAnnotations.Methods;


/**
 * 对java.lang.Integer做的method
 * 
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.6
 */
@Methods
public class IntegerMethods {

	public static Iterator<Integer> upto(int start, int stop) {
		return upto(start, stop, 1);
	}
	
	public static Iterator<Integer> upto(int start, int stop, int step) {
		return new UptoIterator(start, stop, step);
	}
	
	public static Iterator<Integer> downto(int start, int stop) {
		return downto(start, stop, 1);
	}
	
	public static Iterator<Integer> downto(int start, int stop, int step) {
		return new DowntoIterator(start, stop, step);
	}
	
	private static final class UptoIterator implements Iterator<Integer> {
		private int start;
		private int stop;
		private int step;
		public UptoIterator(int start, int stop, int step) {
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
	
	private static final class DowntoIterator implements Iterator<Integer> {
		private int start;
		private int stop;
		private int step;
		public DowntoIterator(int start, int stop, int step) {
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
