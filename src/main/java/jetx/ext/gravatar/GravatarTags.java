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
