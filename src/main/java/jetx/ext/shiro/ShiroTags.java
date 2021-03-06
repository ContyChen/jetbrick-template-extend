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
package jetx.ext.shiro;

import java.io.IOException;

import jetbrick.template.JetAnnotations.Tags;
import jetbrick.template.runtime.JetTagContext;
import jetbrick.template.utils.ExceptionUtils;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * 针对apache-shiro使用者的扩展Tag
 * 
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.0
 */
@Tags
public final class ShiroTags {

	/**
	 * Displays body content only if the current Subject (user) 'has' (implies)
	 * the specified permission (i.e the user has the specified ability).
	 */
	public static void hasPermission(JetTagContext ctx, String permission)
			throws IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null)
			return;

		if (subject.isPermitted(permission)) {
			ctx.writeBodyContent();
		}
	}

	/**
	 * Displays body content only if the current Subject (user) does NOT have
	 * (not imply) the specified permission (i.e. the user lacks the specified
	 * ability)
	 */
	public static void lacksPermission(JetTagContext ctx, String permission)
			throws IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null)
			return;

		if (!subject.isPermitted(permission)) {
			ctx.writeBodyContent();
		}
	}

	/**
	 * Displays body content only if the current user has the specified role.
	 */
	public static void hasRole(JetTagContext ctx, String role)
			throws IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null)
			return;

		boolean show = subject.hasRole(role);

		if (show) {
			ctx.writeBodyContent();
		}
	}

	/**
	 * Displays body content only if the current user has one of the specified
	 * roles from a list of role names.
	 */
	public static void hasAnyRole(JetTagContext ctx, String... roles)
			throws IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null)
			return;

		boolean show = false;
		for (String role : roles) {
			if (subject.hasRole(role)) {
				show = true;
				break;
			}
		}

		if (show) {
			ctx.writeBodyContent();
		}
	}

	/**
	 * Displays body content only if the current user does NOT have the
	 * specified role (i.e. they explicitly lack the specified role)
	 */
	public static void lacksRole(JetTagContext ctx, String role)
			throws IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null)
			return;

		boolean show = !subject.hasRole(role);
		if (show) {
			ctx.writeBodyContent();
		}
	}

	/**
	 * Displays body content only if the current user has successfully
	 * authenticated _during their current session_. It is more restrictive than
	 * the 'user' tag. It is logically opposite to the 'notAuthenticated' tag.
	 */
	public static void authenticated(JetTagContext ctx) throws IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null)
			return;

		boolean show = subject.isAuthenticated();
		if (show) {
			ctx.writeBodyContent();
		}
	}

	/**
	 * Displays body content only if the current user has NOT succesfully
	 * authenticated _during their current session_. It is logically opposite to
	 * the 'authenticated' tag.
	 */
	public static void notAuthenticated(JetTagContext ctx) throws IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null)
			return;

		boolean show = !subject.isAuthenticated();
		if (show) {
			ctx.writeBodyContent();
		}
	}
	
	/**
	 * Displays body content only if the current user has remembered.
	 */
	public static void remembered(JetTagContext ctx) throws IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null) 
			return;
		
		boolean show = subject.isRemembered();
		if (show) {
			ctx.writeBodyContent();
		}
	}

	/**
	 * Displays body content only if the current user has remembered.
	 */
	public static void notRemembered(JetTagContext ctx) throws IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null) 
			return;
		
		boolean show = !subject.isRemembered();
		if (show) {
			ctx.writeBodyContent();
		}
	}

	/**
	 * Displays body content only if the current Subject has a known identity,
	 * either from a previous login or from 'RememberMe' services. Note that
	 * this is semantically different from the 'authenticated' tag, which is
	 * more restrictive. It is logically opposite to the 'guest' tag.
	 */
	public static void user(JetTagContext ctx) throws IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null)
			return;

		boolean show = (subject.getPrincipal() != null);
		if (show) {
			ctx.writeBodyContent();
		}
	}

	/**
	 * Displays body content only if the current Subject IS NOT known to the
	 * system, either because they have not logged in or they have no
	 * corresponding 'RememberMe' identity. It is logically opposite to the
	 * 'user' tag.
	 */
	public static void guest(JetTagContext ctx) throws IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null)
			return;

		boolean show = subject.getPrincipal() == null;
		if (show) {
			ctx.writeBodyContent();
		}
	}
	
	/**
	 * Displays the user's principal (toString() method will be called).
	 */
	public static void principal(JetTagContext ctx) throws IOException {
		principal(ctx, null);
	}

	/**
	 * Displays the user's principal or a property of the user's principal.
	 */
	public static void principal(JetTagContext ctx, String property) throws IOException {
		Subject subject = SecurityUtils.getSubject();
		if (subject == null)
			return;

		String val = null;
		Object principal = subject.getPrincipal();
		if (principal != null) {
			if (property == null) {
				val = principal.toString();
			} else {
				try {
					val = PropertyUtils.getProperty(principal, property).toString();
				} catch (Exception e) {
					throw ExceptionUtils.uncheck(e);
				}
			}
		}

		if (val != null) {
			ctx.getWriter().print(val);
		}
	}
}
