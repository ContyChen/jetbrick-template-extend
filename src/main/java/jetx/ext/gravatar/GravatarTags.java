package jetx.ext.gravatar;

import static jetx.ext.internal.TagUtils.formatAttributes;

import java.io.IOException;
import java.util.Map;

import jetbrick.template.JetAnnotations.Tags;
import jetbrick.template.runtime.JetTagContext;

/**
 * 针对gravatar使用者的扩展Tag
 * 
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.6
 */
@Tags
public class GravatarTags {

	private static final String TEMPLATE = "<img src=\"#URL#\" #ATTRS#/>";
	
	public static void gravatar(JetTagContext ctx, String email, int size) throws IOException {
		gravatar(ctx, email, size, null, "G", "IDENTICON");
	}
	
	public static void gravatar(JetTagContext ctx, String email, int size, Map<String, Object> attrs) throws IOException {
		gravatar(ctx, email, size, attrs, "G", "IDENTICON");
	}
	
	public static void gravatar(JetTagContext ctx, String email, int size, Map<String, Object> attrs, String rating) throws IOException {
		gravatar(ctx, email, size, attrs, rating, "IDENTICON");
	}
	
	public static void gravatar(JetTagContext ctx, String email, int size, Map<String, Object> attrs, String rating, String defaultImg)
			throws IOException {
		String url = GravatarFunctions.gravatar(email, size, rating, defaultImg);
		String output = TEMPLATE.replaceFirst("#URL#", url);
		output = output.replaceFirst("#ATTRS#", formatAttributes(attrs));
		
		if (ctx != null) {
			ctx.getWriter().print(output);
		}
	}
}
