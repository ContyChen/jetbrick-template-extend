package jetx.ext.internal;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import jetbrick.template.runtime.JetPageContext;
import jetbrick.template.runtime.JetTagContext;
import jetbrick.template.web.JetWebContext;

public abstract class InternalUtils {

	public static HttpServletRequest getRequest(JetPageContext ctx) {
		return (HttpServletRequest) ctx.getContext().get(JetWebContext.REQUEST);
	}
	
	public static ServletContext getServletContext(JetPageContext ctx) {
		return (ServletContext) ctx.getContext().get(JetWebContext.SERVLET_CONTEXT);
	}
	
	public static HttpServletRequest getRequest(JetTagContext ctx) {
		return (HttpServletRequest) ctx.getContext().get(JetWebContext.REQUEST);
	}

	public static ServletContext getServletContext(JetTagContext ctx) {
		return (ServletContext) ctx.getContext().get(JetWebContext.SERVLET_CONTEXT);
	}

}
