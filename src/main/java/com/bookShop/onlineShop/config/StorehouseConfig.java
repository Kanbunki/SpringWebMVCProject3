package com.bookShop.onlineShop.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

import com.bookShop.onlineShop.storehouse.converter.DateConverter;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan({"com.bookShop.onlineShop.storehouse.controller", "com.bookShop.onlineShop.main.controller"})
@PropertySource("classpath:properties/online-shop.properties")
@SuppressWarnings("deprecation")
public class StorehouseConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/onlineShop/", ".jsp");
	}
	
	@Bean
	public DataSource dataSource(@Value("${database.driverClassName}") String driverClassName, 
								 @Value("${database.url}") String url, 
								 @Value("${database.username}") String username, 
								 @Value("${database.password}") String password, 
								 @Value("${cp.maxTotal}") int maxTotal, 
								 @Value("${cp.maxIdle}") int maxIdle, 
								 @Value("${cp.minIdle}") int minIdle, 
								 @Value("${cp.maxWaitMillis}") long maxWaitMillis) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setMaxTotal(maxTotal);
		dataSource.setMaxIdle(maxIdle);
		dataSource.setMinIdle(minIdle);
		dataSource.setMaxWaitMillis(maxWaitMillis);
		
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	@Bean
	public NamedParameterJdbcTemplate namedParamJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
//	@Override
//	public Validator getValidator() {
//		return validator();
//	}
//	
//	@Bean
//	public LocalValidatorFactoryBean validator() {
//		LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
//		validator.setValidationMessageSource(messageSource());
//		return validator;
//	}
	
	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	@Bean
	public ConversionService conversionService() {
		DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
//		DateTimeFormatterRegistrar registrar = new DateTimeFormatterRegistrar();
//		registrar.setDateFormatter(DateTimeFormatter.ofPattern("yyyyMMdd"));
//		registrar.registerFormatters(conversionService);
		conversionService.addConverter(new DateConverter());
		return conversionService;
	}
}
