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


/**
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.8
 */
public class MySqlDatabaseResourceLoader extends AbstractConfiguredDatabaseResourceLoader {

	public MySqlDatabaseResourceLoader() {
		super();
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
