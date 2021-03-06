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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import jetbrick.template.JetAnnotations.Functions;
import jetbrick.template.runtime.JetPageContext;
import jetx.ext.internal.ExtendUtils;
import jetx.ext.internal.FunctionUtils;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.ui.context.Theme;
import org.springframework.ui.context.ThemeSource;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.FlashMapManager;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ThemeResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

/**
 * 针对SpringMVC使用者的扩展函数
 * 
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.0
 */
@Functions
public final class SpringMvcFunctions {

	private static final List<String> EMPTY_STRING_LIST = Collections.unmodifiableList(new ArrayList<String>());
	
	/* common
	 ------------------------------------------------------------------------------------------------------------------- */

	/**
	 * 获取Spring ROOT上下文 
	 */
	public static WebApplicationContext getWebApplicationContext(JetPageContext ctx) {
		return WebApplicationContextUtils.getWebApplicationContext(
				ExtendUtils.getServletContext(ctx), WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
	}
	
	/**
	 * 获取MessageSource
	 */
	public static MessageSource getMessageSource(JetPageContext ctx) {
		return getWebApplicationContext(ctx);
	}
	
	/**
	 * 获取当前的Locale 
	 */
	public static Locale getLocale(JetPageContext ctx) {
		HttpServletRequest request = ExtendUtils.getHttpServletRequest(ctx);
		return RequestContextUtils.getLocale(request);
	}
	
	/**
	 * 获取LocaleResolver
	 */
	public static LocaleResolver getLocaleResolver(JetPageContext ctx) {
		HttpServletRequest request = ExtendUtils.getHttpServletRequest(ctx);
		return RequestContextUtils.getLocaleResolver(request);
	}

	/**
	 * 获取FlashMapManager
	 */
	public static FlashMapManager getFlashMapManager(JetPageContext ctx) {
		HttpServletRequest request = ExtendUtils.getHttpServletRequest(ctx);
		return RequestContextUtils.getFlashMapManager(request);
	}
	
	/**
	 * 获取InputFlashMap
	 */
	public static Map<String, ?> getInputFlashMap(JetPageContext ctx) {
		HttpServletRequest request = ExtendUtils.getHttpServletRequest(ctx);
		return RequestContextUtils.getInputFlashMap(request);
	}
	
	/**
	 * 获取OutputFlashMap
	 */
	public static Map<String, ?> getOutputFlashMap(JetPageContext ctx) {
		HttpServletRequest request = ExtendUtils.getHttpServletRequest(ctx);
		return RequestContextUtils.getOutputFlashMap(request);
	}
	
	/**
	 * 获取Theme
	 */
	public static Theme getTheme(JetPageContext ctx) {
		HttpServletRequest request = ExtendUtils.getHttpServletRequest(ctx);
		return RequestContextUtils.getTheme(request);
	}
	
	/**
	 * 获取ThemeResolver
	 */
	public static ThemeResolver getThemeResolver(JetPageContext ctx) {
		HttpServletRequest request = ExtendUtils.getHttpServletRequest(ctx);
		return RequestContextUtils.getThemeResolver(request);
	}
	
	/**
	 * 获取ThemeSource 
	 */
	public static ThemeSource getThemeSource(JetPageContext ctx) {
		HttpServletRequest request = ExtendUtils.getHttpServletRequest(ctx);
		return RequestContextUtils.getThemeSource(request);
	}
	
	/* validation
	 ------------------------------------------------------------------------------------------------------------------- */

	/**
	 * 查看是否存在字段错误
	 */
	public static boolean hasFieldErrors(JetPageContext ctx, String fieldName) {
		return countFieldErrors(ctx, fieldName) != 0;
	}
	
	/**
	 * 得到字段错误信息的个数 
	 */
	public static int countFieldErrors(JetPageContext ctx, String fieldName) {
		Errors errors = FunctionUtils.findErrors(ctx.getContext());
		return errors != null ? errors.getFieldErrorCount(fieldName) : 0;
	}
	
	/**
	 * 获取所有的字段错误信息
	 */
	public static List<String> fieldErrors(JetPageContext ctx, String fieldName) {

		Errors errors = FunctionUtils.findErrors(ctx.getContext());
		if (errors == null) {
			return EMPTY_STRING_LIST;
		}
		
		List<FieldError> fes =  errors.getFieldErrors(fieldName);
		List<String> msgs = new ArrayList<String>(0);

		for(FieldError fe : fes) {
			String[] codes = fe.getCodes();
			String defaultMsg = fe.getDefaultMessage();
			Object[] args = fe.getArguments();
			Locale locale = getLocale(ctx);
			MessageSource ms = getMessageSource(ctx);
			
			if (codes == null || codes.length == 0 || ms == null) {
				msgs.add(defaultMsg);
			} else {
				String msg = null;
				for (int i = 0; i < codes.length; i ++) {
					try {
						msg = ms.getMessage(codes[i], args, locale);
					} catch (NoSuchMessageException e) {
						// 忽略
					}
					if (msg == null) {
						msg = defaultMsg;
					}
				}
				msgs.add(msg);
			}
		}
		return Collections.unmodifiableList(msgs);
	}
	
	/**
	 * 查看是否存在全局错误
	 */
	public static boolean hasGlobalErrors(JetPageContext ctx) {
		return countGlobalErrors(ctx) != 0;
	}
	
	/**
	 * 得到全局错误信息的个数 
	 */
	public static int countGlobalErrors(JetPageContext ctx) {
		Errors errors = FunctionUtils.findErrors(ctx.getContext());
		return errors != null ? errors.getGlobalErrorCount() : 0;
	}

	/**
	 * 获取所有的全局错误信息
	 */
	public static List<String> globalErrors(JetPageContext ctx) {
		
		Errors errors = FunctionUtils.findErrors(ctx.getContext());
		if (errors == null) {
			return EMPTY_STRING_LIST;
		}
		
		List<ObjectError> oes = errors.getGlobalErrors();
		List<String> msgs = new ArrayList<String>(0);

		for (ObjectError oe : oes) {
			String[] codes = oe.getCodes();
			String defaultMsg = oe.getDefaultMessage();
			Object[] args = oe.getArguments();
			Locale locale = getLocale(ctx);
			MessageSource ms = getMessageSource(ctx);
			
			if (codes == null || codes.length == 0 || ms == null) {
				msgs.add(defaultMsg);
			} else {
				String msg = null;
				for (int i = 0; i < codes.length; i ++) {
					try {
						msg = ms.getMessage(codes[i], args, locale);
					} catch (NoSuchMessageException e) {
						// 忽略
					}
					if (msg == null) {
						msg = defaultMsg;
					}
				}
				msgs.add(msg);
			}
		}
		return Collections.unmodifiableList(msgs);
	}

	
}
