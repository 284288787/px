/**create by liuhua at 2017年5月18日 下午4:00:29**/
package com.booting.config;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.star.framework.jdbc.datasource.ReadWriteDataSource;
import com.star.framework.jdbc.resource.XmlResource;

@Configuration
//启用注解事务管理，使用CGLib代理
@EnableTransactionManagement(proxyTargetClass = true)
public class JDBCConfig {

	@Bean
	public XmlResource xmlResource(){
		XmlResource xmlResource = new XmlResource();
		try {
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();        
			Resource[] resources=resolver.getResources("classpath*:/sqlMap/*/sqlMap_*.xml");
			xmlResource.setResources(resources);
	    } catch (IOException e) {
			e.printStackTrace();
		}
		return xmlResource;
	}
	
	@Bean(name = "writeDataSource")
	public JndiObjectFactoryBean writeDataSource(){
		JndiObjectFactoryBean write = new JndiObjectFactoryBean();
		write.setJndiName("java:comp/env/jdbc/test-write");
		return write;
	}
	
	@Bean(name = "readDataSource1")
	public JndiObjectFactoryBean readDataSource1(){
		JndiObjectFactoryBean write = new JndiObjectFactoryBean();
		write.setJndiName("java:comp/env/jdbc/test-read");
		return write;
	}
	
	@Bean(name = "readDataSource2")
	public JndiObjectFactoryBean readDataSource2(){
		JndiObjectFactoryBean write = new JndiObjectFactoryBean();
		write.setJndiName("java:comp/env/jdbc/test-read");
		return write;
	}
	
	@Bean(name = "readWriteDataSource")
	public ReadWriteDataSource readWriteDataSource(DataSource writeDataSource, DataSource readDataSource1, DataSource readDataSource2){
		ReadWriteDataSource dataSource = new ReadWriteDataSource();
		dataSource.setWriteDataSource(writeDataSource);
		Map<String, DataSource> readDataSourceMap = new HashMap<>();
		readDataSourceMap.put("readDataSource1", readDataSource1);
		readDataSourceMap.put("readDataSource2", readDataSource2);
		dataSource.setReadDataSourceMap(readDataSourceMap);
		return dataSource;
	}
	
	@Bean(name = "txManager")
	public DataSourceTransactionManager txManager(ReadWriteDataSource readWriteDataSource){
		DataSourceTransactionManager manager = new DataSourceTransactionManager();
		manager.setDataSource(readWriteDataSource);
		return manager;
	}
	
	@Bean(name = "dataSourceProxy")
	public TransactionAwareDataSourceProxy dataSourceProxy(ReadWriteDataSource readWriteDataSource){
		TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy();
		proxy.setTargetDataSource(readWriteDataSource);
		return proxy;
	}
	
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate(TransactionAwareDataSourceProxy dataSourceProxy){
		JdbcTemplate template = new JdbcTemplate(dataSourceProxy);
		return template;
	}
}
