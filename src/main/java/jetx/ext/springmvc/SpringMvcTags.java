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
package jetx.ext.springmvc;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import jetbrick.template.JetAnnotations.Tags;
import jetbrick.template.runtime.JetTagContext;
import jetbrick.template.utils.ExceptionUtils;
import jetx.ext.internal.ExtendUtils;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.web.servlet.support.RequestContextUtils;


/**
 * 针对SpringMVC使用者的扩展Tag
 * 
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.0
 */
@Tags
public final class SpringMvcTags {

	/* i18n
	 ------------------------------------------------------------------------------------------------------------------- */

	public static void i18n(JetTagContext ctx, String code) throws IOException {
		i18n(ctx, code, null);
	}
	
	public static void i18n(JetTagContext ctx, String code, Object[] args) throws IOException {
		i18n(ctx, code, args, "");
	}
	
	public static void i18n(JetTagContext ctx, String code, Object[] args, String defaultMessage)
			throws IOException {

		HttpServletRequest request = ExtendUtils.getHttpServletRequest(ctx);
		
		MessageSource messageSource = RequestContextUtils.getWebApplicationContext(request);
		Locale locale = RequestContextUtils.getLocale(request);
		String value = null;
		
		try {
			value = messageSource.getMessage(code, args, defaultMessage, locale);
		} catch (NoSuchMessageException e) {
			throw ExceptionUtils.uncheck(e);
		}
		
		if (value != null) {
			ctx.getWriter().print(value);
		}
	}

}
