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
package jetx.ext.springsecurity;

import java.io.IOException;

import jetbrick.template.JetAnnotations.Tags;
import jetbrick.template.runtime.JetTagContext;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.taglibs.velocity.Authz;
import org.springframework.security.taglibs.velocity.AuthzImpl;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;
import org.springframework.security.web.access.WebInvocationPrivilegeEvaluator;

/**
 * 为spring-security提供的jetbrick-template标签。<br>
 * <strong>注意：</strong> 依赖commons-beanutils-1.8.3.jar <br>
 * 
 * 并需要在spring中指定下面的bean，用于属性注入
 * <pre>
 * &lt;bean class="jetx.ext.springsecurity.SpringSecurityTags" /&gt;
 * </pre>
 * 
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.1
 */
@Tags
public final class SpringSecurityTags implements ApplicationContextAware {

	private static final Authz AUTHZ = new AuthzImpl();
	private static ApplicationContext AC;
	
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		AUTHZ.setAppCtx(applicationContext);
		AC = applicationContext;
	}

	/* 
	 ------------------------------------------------------------------------------------------------------------------- */
	
	public static void allGranted(JetTagContext ctx, String roles) throws IOException {
		boolean show = AUTHZ.allGranted(roles);
		if (show) {
			ctx.writeBodyContent();
		}
	}
	
	public static void anyGranted(JetTagContext ctx, String roles) throws IOException {
		boolean show = AUTHZ.anyGranted(roles);
		if (show) {
			ctx.writeBodyContent();
		}
	}
	
	public static void noneGranted(JetTagContext ctx, String roles) throws IOException {
		boolean show = AUTHZ.noneGranted(roles);
		if (show) {
			ctx.writeBodyContent();
		}
	}
	
	public static void principal(JetTagContext ctx, String property) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal != null) {
				try {
					String val = PropertyUtils.getProperty(principal, property).toString();
					ctx.getWriter().print(val);
				} catch (Exception e) {
					// 忽略异常
				}
			}
		}
	}
	
	public static void grantedSameWithUri(JetTagContext ctx, String uri) throws IOException {
		WebInvocationPrivilegeEvaluator evaluator = getWebInvocationPrivilegeEvaluator(ctx);
		if (evaluator == null) {
			throw new UnsupportedOperationException(
					"No visible WebInvocationPrivilegeEvaluator instance could be found in the application context. There must be at least one in order to support the use of URL access checks in 'authorize' tags.");
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		boolean show = evaluator.isAllowed(uri, authentication);
		if (show) {
			ctx.writeBodyContent();
		}
	}
	
	private static WebInvocationPrivilegeEvaluator getWebInvocationPrivilegeEvaluator(JetTagContext ctx) {
		WebInvocationPrivilegeEvaluator evaluator = null;
		try {
			evaluator = AC.getBean(WebInvocationPrivilegeEvaluator.class);
		} catch (NoSuchBeanDefinitionException e) {
			// 用户并没有配置WebInvocationPrivilegeEvaluator
		}
		
		if (evaluator != null) {
			return evaluator;
		}
		
		AbstractSecurityInterceptor interceptor = null; 
		try {
			interceptor = AC.getBean(AbstractSecurityInterceptor.class);
		} catch (NoSuchBeanDefinitionException e) {
			// 用户并没有配置FilterSecurityInterceptor
			// 这不可能发生，没有FilterSecurityInterceptor，SpringSecurity无法工作
		}
		
		if (interceptor == null) {
			return null;
		}
		
		evaluator = new DefaultWebInvocationPrivilegeEvaluator(interceptor);
		return evaluator;
	}

}
