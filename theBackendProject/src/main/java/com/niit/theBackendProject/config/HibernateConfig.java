package com.niit.theBackendProject.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

	// change the following based on your DBMS choice 
		private final static String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
		private final static String DATABASE_DRIVER = "oracle.jdbc.driver.OracleDriver";
		private final static String DATABASE_DIALECT = "org.hibernate.dialect.Oracle10gDialect";
		private final static String DATABASE_USERNAME = "hr";
		private final static String DATABASE_PASSWORD = "hr";
		
		//dataSource bean will be available
		@Bean 		//(name="dataSource")
		public DataSource getDataSource() {
			
			BasicDataSource dataSource =new BasicDataSource();
			
			// providing the databse connection information
			dataSource.setDriverClassName(DATABASE_DRIVER);
			dataSource.setUrl(DATABASE_URL);
			dataSource.setUsername(DATABASE_USERNAME);
			dataSource.setPassword(DATABASE_PASSWORD);
			
			return dataSource;
		}
		
		//sessionFactory bean will be available
		@Bean
		public SessionFactory getSessionFactory(DataSource dataSource) {
			
			LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(dataSource);
			
			builder.addProperties(getHibernateProperties());
			builder.scanPackages("com.niit.theBackendProject");
			return builder.buildSessionFactory();
			
		}

		
		// All the Hibernate properties will be returned in this method
		private Properties getHibernateProperties() {
			Properties  properties = new Properties();
			
			properties.put("hibernate.dialect",DATABASE_DIALECT);
			properties.put("hibernate.show_sql","true");
			properties.put("hibernate.format_sql","true");
			
			
			
			return properties;
		}
		
		// transactionManager bean
		@Bean
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
			
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
			return transactionManager;
			
		}
	
}
