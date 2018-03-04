package com.suresh;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ComponentScan("com.suresh")
@PropertySource("classpath:db.properties")
public class AppConfig {
	@Autowired
	Environment env;

	@Bean
	public DataSource getDataSource() {
		BasicDataSource datasource = new BasicDataSource();
		datasource.setDriverClassName(env.getProperty("MYSQL_DB_DRIVER_CLASS"));
		datasource.setUrl(env.getProperty("MYSQL_DB_URL"));
		datasource.setUsername(env.getProperty("MYSQL_DB_USERNAME"));
		datasource.setPassword(env.getProperty("MYSQL_DB_PASSWORD"));
		System.out.println(datasource.getUsername());
		return datasource;
	}
}
