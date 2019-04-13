package com.ginkgo.bing.wallpaper.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.ginkgo.bing.wallpaper.mvc.dao.Mapper;

//@Configuration
public class ContextConfigurer  {

	public final static String MAPPER_BASE_PACKAGE = Mapper.class.getPackage().getName();
	public final static String MAPPER_RESOURCES = "classpath*:mapper/*Mapper.xml";
	
	@Bean("sqlSessionFactory")
	public static SqlSessionFactory sessionFactory(@Autowired DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sf = new org.mybatis.spring.SqlSessionFactoryBean();
		sf.setDataSource(dataSource);
		// 1.XML configuration 
		// sf.setConfigLocation(new ClassPathResource("mybatis-conf.xml"));

		// 2. Java injection configuration
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sf.setMapperLocations(resolver.getResources(MAPPER_RESOURCES));
		return sf.getObject();
	}

	@Bean("sqlSessionTemplate")
	public static SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("sqlSessionFactory") SqlSessionFactory sessionFactory) {
		return new org.mybatis.spring.SqlSessionTemplate(sessionFactory);
	}

//	@Bean
//	public static MapperScannerConfigurer MapperScannerConfigurer() {
//		MapperScannerConfigurer conf = new MapperScannerConfigurer();
//		conf.setBasePackage(MAPPER_BASE_PACKAGE);
//		conf.setSqlSessionTemplateBeanName("sqlSessionTemplate");
//		return conf;
//	}

	@Bean
	public static DataSourceTransactionManager transactionManager(@Autowired DataSource dataSource) {
		org.springframework.jdbc.datasource.DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource);
		return tm;
	}
}
