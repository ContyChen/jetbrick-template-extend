package jetx.ext.common;

import java.util.Iterator;

import jetbrick.template.JetAnnotations.Methods;
import jetx.ext.internal.LoopIterators;


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
		return LoopIterators.upto(start, stop, step);
	}
	
	public static Iterator<Integer> downto(int start, int stop) {
		return downto(start, stop, 1);
	}
	
	public static Iterator<Integer> downto(int start, int stop, int step) {
		return LoopIterators.downto(start, stop, step);
	}

}
