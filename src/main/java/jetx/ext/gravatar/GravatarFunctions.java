package jetx.ext.gravatar;

import jetbrick.template.JetAnnotations.Functions;

/**
 * 针对gravatar使用者的扩展Tag
 * 
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.2
 */
@Functions
public final class GravatarFunctions {
	
	public static String gravatar(String email, int size) {
		return gravatar(email, size, "G");
	}
	
	public static String gravatar(String email, int size, String rating) {
		return gravatar(email, size, rating, "IDENTICON");
	}
	
	public static String gravatar(String email, int size, String rating, String defaultImg) {
		String url = GravatarBuilder.create()
						.email(email)
						.rating(Rating.valueOf(rating))
						.defaultImage(DefaultImage.valueOf(defaultImg))
						.size(size)
						.build();
		
		return url;
	}

}
