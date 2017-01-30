package com.eldon.dao;

import javax.sql.DataSource;

public abstract class AccesoJDBCBaseDAO {
	protected DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
