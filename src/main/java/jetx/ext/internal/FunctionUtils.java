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
