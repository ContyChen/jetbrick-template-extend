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

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import jetbrick.template.JetEngine;
import jetbrick.template.resource.Resource;
import jetbrick.template.resource.loader.ResourceLoader;
import jetbrick.template.utils.ExceptionUtils;
import jetx.ext.internal.io.CharSequenceInputStream;

/**
 * @author 应卓(yingzhor@gmail.com)
 * @since 1.0.8
 */
public abstract class AbstractDatabaseResourceLoader implements ResourceLoader {

	private String encoding;
	
	@Override
	public final void initialize(JetEngine engine, String basepath, String encoding) {
		this.encoding = encoding;
	}

	@Override
	public final Resource load(String name) {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		
		try {
			conn = getDataSource().getConnection();
			pstat = conn.prepareStatement(getCheckExistsSql());
			pstat.setString(1, name);
			rs = pstat.executeQuery();
			rs.next();
			boolean found = rs.getBoolean(1);
			if (found == false) {
				return null;
			}
		} catch (SQLException e) {
			throw ExceptionUtils.uncheck(e);
		} finally {
			if (rs != null) try {rs.close();} catch (SQLException e) {}
			if (pstat != null) try {pstat.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}
		
		return new DatabaseResource(name, encoding, getDataSource(), getLoadSql(), getReloadLastModifiedMinMillis());
	}

	@Override
	public final List<String> loadAll() {
		
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		
		try {
			conn = getDataSource().getConnection();
			pstat = conn.prepareStatement(getLoadAllSql());
			rs = pstat.executeQuery();
			
			List<String> list = new ArrayList<String>();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
			return list;
		} catch (SQLException e) {
			throw ExceptionUtils.uncheck(e);
		} finally {
			if (rs != null) try {rs.close();} catch (SQLException e) {}
			if (pstat != null) try {pstat.close();} catch (SQLException e) {}
			if (conn != null) try {conn.close();} catch (SQLException e) {}
		}
	}
	
	public abstract DataSource getDataSource();

	public abstract String getLoadAllSql();

	public abstract String getLoadSql();
	
	public abstract String getCheckExistsSql();
	
	public abstract long getReloadLastModifiedMinMillis();

	//~ -----------------------------------------------------------------------------------------
	
	private static final class DatabaseResource extends Resource {
		
		private DataSource dataSource;
		private String loadSql;
		private long reloadLastModifiedMinMillis;
		
		private long lastLoad = -1;

		public DatabaseResource(String name, String encoding, DataSource dataSource, String loadSql, long reloadLastModifiedMinMillis) {
			super(name, encoding);
			this.dataSource = dataSource;
			this.loadSql = loadSql;
			this.reloadLastModifiedMinMillis = reloadLastModifiedMinMillis;
		}

		@Override
		public String getAbsolutePath() {
			return super.getName();
		}

		@Override
		public InputStream getInputStream() throws IOException {
			Connection conn = null;
			PreparedStatement pstat = null;
			ResultSet rs = null;

			try {
				conn = dataSource.getConnection();
				pstat = conn.prepareStatement(loadSql);
				pstat.setString(1, super.getName());
				
				rs = pstat.executeQuery();
				boolean found = rs.next();
				if (found == false) {
					return null;
				}
				String templateContent = rs.getString(2);
				return new CharSequenceInputStream(templateContent, super.getEncoding());
			} catch (SQLException e) {
				throw ExceptionUtils.uncheck(e);
			} finally {
				if (rs != null) try {rs.close();} catch (SQLException e) {}
				if (pstat != null) try {pstat.close();} catch (SQLException e) {}
				if (conn != null) try {conn.close();} catch (SQLException e) {}
			}
		}

		@Override
		public long lastModified() {
			if (this.lastLoad > 0 && (System.currentTimeMillis() - this.lastLoad) < this.reloadLastModifiedMinMillis) {
				return lastLoad;
			}
			Connection conn = null;
			PreparedStatement pstat = null;
			ResultSet rs = null;
			try {
				conn = dataSource.getConnection();
				pstat = conn.prepareStatement(loadSql);
				pstat.setString(1, super.getName());
				rs = pstat.executeQuery();
				rs.next();
				return rs.getDate(1).getTime();
			} catch (SQLException e) {
				throw ExceptionUtils.uncheck(e);
			} finally {
				if (rs != null) try {rs.close();} catch (SQLException e) {}
				if (pstat != null) try {pstat.close();} catch (SQLException e) {}
				if (conn != null) try {conn.close();} catch (SQLException e) {}
			}
		}
	}
}
