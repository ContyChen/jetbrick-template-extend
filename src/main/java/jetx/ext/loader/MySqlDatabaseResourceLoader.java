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
package jetx.ext.loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import jetbrick.template.utils.ExceptionUtils;
import jetx.ext.internal.db.DriverManagerDataSource;

/**
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.8
 */
public class MySqlDatabaseResourceLoader extends AbstractDatabaseResourceLoader {

	private static final ClassLoader DEFAULT_CLASS_LOADER = Thread.currentThread().getContextClassLoader();
	
	private final String driverClassName;
	private final String url;
	private final String username;
	private final String password;
	
	public MySqlDatabaseResourceLoader() {
		InputStream config = DEFAULT_CLASS_LOADER.getResourceAsStream("jetx-ext-dbloader.properties");
		if (config == null) {
			throw ExceptionUtils.uncheck(new FileNotFoundException("config file not found. ['classpath:jetx-ext-dbloader.properties']"));
		}
		
		Properties props = new Properties();
		
		try {
			props.load(config);
		} catch (IOException e) {
			throw ExceptionUtils.uncheck(e);
		}
		
		driverClassName = props.getProperty("jdbc.driver");
		url = props.getProperty("jdbc.url");
		username = props.getProperty("jdbc.username");
		password = props.getProperty("jdbc.password");
	}
	
	@Override
	public DataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}

	@Override
	public String getLoadAllSql() {
		return "SELECT NAME FROM JETX_TEMPLATE";
	}

	@Override
	public String getLoadSql() {
		return "SELECT LAST_MODIFIED, CONTENT FROM JETX_TEMPLATE WHERE NAME = ?";
	}
	
	@Override
	public String getCheckExistsSql() {
		return "SELECT COUNT(NAME) <> 0 FROM JETX_TEMPLATE WHERE NAME = ?";
	}

	@Override
	public long getReloadLastModifiedMinMillis() {
		return 60000L;	// 一分钟
	}

}
