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

import java.io.PrintWriter;
import java.io.StringWriter;

import jetbrick.template.JetAnnotations.Methods;


/**
 * 对java.lang.Throwable做的method
 * 
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.1
 */
@Methods
public final class ThrowableMethods {

	/**
	 * Returns the innermost cause of {@code throwable}. The first throwable in
	 * a chain provides context from when the error or exception was initially
	 * detected. Example usage:
	 * 
	 * <pre>
	 * assertEquals(&quot;Unable to assign a customer id&quot;, Throwables.getRootCause(e)
	 * 		.getMessage());
	 * </pre>
	 */
	public static Throwable rootCause(Throwable throwable) {
		Throwable cause;
		while ((cause = throwable.getCause()) != null) {
			throwable = cause;
		}
		return throwable;
	}

	/**
	 * Returns a string containing the result of {@link Throwable#toString()
	 * toString()}, followed by the full, recursive stack trace of
	 * {@code throwable}. Note that you probably should not be parsing the
	 * resulting string; if you need programmatic access to the stack frames,
	 * you can call {@link Throwable#getStackTrace()}.
	 */
	public static String trace(Throwable throwable) {
		StringWriter stringWriter = new StringWriter();
		throwable.printStackTrace(new PrintWriter(stringWriter));
		return stringWriter.toString();
	}

}
