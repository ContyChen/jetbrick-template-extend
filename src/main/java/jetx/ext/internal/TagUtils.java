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

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import jetbrick.template.runtime.JetTagContext;
import jetbrick.template.web.JetWebContext;

public final class TagUtils {

	private TagUtils() {
	}
	
	/*
	 ------------------------------------------------------------------------------------------------------------------- */
	
	@Deprecated
	public static HttpServletRequest getRequest(JetTagContext ctx) {
		return (HttpServletRequest) ctx.getContext().get(JetWebContext.REQUEST);
	}

	@Deprecated
	public static ServletContext getServletContext(JetTagContext ctx) {
		return (ServletContext) ctx.getContext().get(JetWebContext.SERVLET_CONTEXT);
	}
	
	/*
	 ------------------------------------------------------------------------------------------------------------------- */

	/**
	 * @since 1.0.6
	 */
	public static String formatAttributes(Map<String, Object> attributes) {
		if (attributes == null || attributes.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> entry : attributes.entrySet()) {
			String key   = entry.getKey();
			Object value = entry.getValue();
			if (key != null && value != null) {
				/*
				 * 如果是style属性,里面的双引号用单引号代替
				 */
				boolean isStyleAttr = key.toLowerCase().equals("style");
				
				if (isStyleAttr) {
					sb.append(key + "=\"" + value.toString().replaceAll("\"", "'") + "\" " );
				} else {
					sb.append(key + "=\"" + value.toString() + "\" " );
				}
			}
		}
		return sb.toString();
	}
}
