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
