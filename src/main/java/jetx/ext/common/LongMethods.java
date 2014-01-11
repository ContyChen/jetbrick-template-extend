package jetx.ext.common;

import java.util.Iterator;

import jetbrick.template.JetAnnotations.Methods;
import jetx.ext.internal.LoopIterators;

/**
 * 对java.lang.Long做的method
 * 
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.6
 */
@Methods
public final class LongMethods {
	
	public static Iterator<Long> upto(long start, long stop) {
		return upto(start, stop, 1);
	}
	
	public static Iterator<Long> upto(long start, long stop, long step) {
		return LoopIterators.upto(start, stop, step);
	}
	
	public static Iterator<Long> downto(long start, long stop) {
		return downto(start, stop, 1);
	}
	
	public static Iterator<Long> downto(long start, long stop, long step) {
		return LoopIterators.downto(start, stop, step);
	}

}
