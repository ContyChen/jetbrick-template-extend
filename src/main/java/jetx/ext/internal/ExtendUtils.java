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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jetbrick.template.JetContext;
import jetbrick.template.JetContextAware;
import jetbrick.template.web.JetWebContext;

/**
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.7
 */
public final class ExtendUtils {

	public ExtendUtils() {
	}
	
	/* 
	 ------------------------------------------------------------------------------------------------------------------- */

	public static HttpServletRequest getHttpServletRequest(JetContextAware jetContextAware) {
		JetContext ctx = jetContextAware.getContext();
		return ctx != null ? (HttpServletRequest) ctx.get(JetWebContext.REQUEST) : null;
	}
	
	public static HttpServletResponse getHttpServletResponse(JetContextAware jetContextAware) {
		JetContext ctx = jetContextAware.getContext();
		return ctx != null ? (HttpServletResponse) ctx.get(JetWebContext.RESPONSE) : null;
	}
	
	public static ServletContext getServletContext(JetContextAware jetContextAware) {
		JetContext ctx = jetContextAware.getContext();
		return ctx != null ? (ServletContext) ctx.get(JetWebContext.SERVLET_CONTEXT) : null;
	}
	
	public static HttpSession getHttpSession(JetContextAware jetContextAware) {
		return getHttpSession(jetContextAware, true);
	}
	
	public static HttpSession getHttpSession(JetContextAware jetContextAware, boolean create) {
		HttpServletRequest request = getHttpServletRequest(jetContextAware);
		return request != null ? request.getSession(create) : null;
	}

}
