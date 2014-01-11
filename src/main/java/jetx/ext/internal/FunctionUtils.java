package jetx.ext.internal;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import jetbrick.template.runtime.JetPageContext;
import jetbrick.template.web.JetWebContext;

public abstract class FunctionUtils {

	public static HttpServletRequest getRequest(JetPageContext ctx) {
		return (HttpServletRequest) ctx.getContext().get(JetWebContext.REQUEST);
	}
	
	public static ServletContext getServletContext(JetPageContext ctx) {
		return (ServletContext) ctx.getContext().get(JetWebContext.SERVLET_CONTEXT);
	}

}
