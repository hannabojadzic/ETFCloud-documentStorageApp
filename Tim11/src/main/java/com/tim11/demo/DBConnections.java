package com.tim11.demo;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class DBConnections {
	@Bean
	@Primary
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource DBOracle() {
		DataSource a = DataSourceBuilder.create().build();
		System.out.println(a);
	    return DataSourceBuilder.create().build();
	}
	@Bean
	@ConfigurationProperties(prefix="spring.secondDatasource")
	public DataSource DBMySQL() {
	    return DataSourceBuilder.create().build();
	}
}
