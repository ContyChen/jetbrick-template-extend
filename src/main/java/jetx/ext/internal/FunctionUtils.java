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

import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import jetbrick.template.JetContext;
import jetbrick.template.runtime.JetPageContext;
import jetbrick.template.web.JetWebContext;

import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

public final class FunctionUtils {

	private FunctionUtils() {
	}
	
	/*
	 ------------------------------------------------------------------------------------------------------------------- */

	@Deprecated
	public static HttpServletRequest getRequest(JetPageContext ctx) {
		return (HttpServletRequest) ctx.getContext().get(JetWebContext.REQUEST);
	}
	
	@Deprecated
	public static ServletContext getServletContext(JetPageContext ctx) {
		return (ServletContext) ctx.getContext().get(JetWebContext.SERVLET_CONTEXT);
	}
	
	/*
	 ------------------------------------------------------------------------------------------------------------------- */

	/**
	 * @since 1.0.7
	 */
	public static Errors findErrors(JetContext jetContext) {
		Set<String> keyset = jetContext.keySet();
		
		Errors result = null;
		for (String key : keyset) {
			if (key.startsWith(BindingResult.MODEL_KEY_PREFIX)) {
				try {
					result = (Errors) jetContext.get(key);
				} catch (ClassCastException e) {
					// 忽略
				}
				if (result != null) return null;	// 找到Errors
			}
		}
		
		// 递归查找链式JetContext
		JetContext parent = jetContext.getParent();
		return parent != null ? findErrors(parent) : null;
	}

}
