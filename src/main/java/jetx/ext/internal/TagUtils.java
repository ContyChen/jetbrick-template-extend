package jetx.ext.internal;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import jetbrick.template.runtime.JetTagContext;
import jetbrick.template.web.JetWebContext;

public class TagUtils {

	public static HttpServletRequest getRequest(JetTagContext ctx) {
		return (HttpServletRequest) ctx.getContext().get(JetWebContext.REQUEST);
	}

	public static ServletContext getServletContext(JetTagContext ctx) {
		return (ServletContext) ctx.getContext().get(JetWebContext.SERVLET_CONTEXT);
	}

	public static String formatAttributes(Map<String, Object> attrs) {
		if (attrs == null || attrs.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, Object> entry : attrs.entrySet()) {
			String key   = entry.getKey();
			Object value = entry.getValue();
			if (key != null && value != null) {
				sb.append(key + "=\"" + value.toString() + "\" " );
			}
		}
		return sb.toString();
	}
}
