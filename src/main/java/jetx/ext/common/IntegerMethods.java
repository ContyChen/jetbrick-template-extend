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
