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

import jetbrick.template.JetAnnotations.Functions;

/**
 * 针对gravatar使用者的扩展Function
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
		return GravatarBuilder.create().email(email).rating(Rating.valueOf(rating))
					.defaultImage(DefaultImage.valueOf(defaultImg)).size(size)
					.build();
	}

}
