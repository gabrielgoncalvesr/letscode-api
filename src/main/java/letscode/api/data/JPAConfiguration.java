//package letscode.api.data;
//
//import java.sql.SQLException;
//
//import javax.persistence.EntityManagerFactory;
//import javax.sql.DataSource;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//@Configuration
//@EnableAutoConfiguration
//@ComponentScan(basePackages = { "letscode.api.repository", "letscode.api.service" })
//@EntityScan(basePackages = "letscode.api.entity")
//@EnableJpaRepositories(basePackages = "letscode.api.repository", entityManagerFactoryRef = "entityManagerFactory")
//@EnableTransactionManagement
//public class JPAConfiguration {
//
//	@Primary
//	@Bean
//	public DataSource dataSource() throws SQLException {
//		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
//		return builder.setType(EmbeddedDatabaseType.H2).build();
//	}
//
//	@Bean(name = "entityManagerFactory")
//	public EntityManagerFactory entityManagerFactory() throws SQLException {
//		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//		vendorAdapter.setGenerateDdl(true);
//
//		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//		factory.setJpaVendorAdapter(vendorAdapter);
//		factory.setPackagesToScan("letscode.api.repository");
//		factory.setDataSource(dataSource());
//		factory.afterPropertiesSet();
//
//		return factory.getObject();
//	}
//
////	@Bean(name = "entityManagerFactory")
////	public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws SQLException {
////		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
////		vendorAdapter.setGenerateDdl(false);
////
////		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
////		factory.setJpaVendorAdapter(vendorAdapter);
////		// Add package to scan for entities.
////		factory.setPackagesToScan("letscode.api.repository");
////		factory.setDataSource(dataSource());
////		return factory;
////	}
////
////	@Bean
////	public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
////		JpaTransactionManager txManager = new JpaTransactionManager();
////		txManager.setEntityManagerFactory(entityManagerFactory);
////		return txManager;
////	}
//
//	@Bean(name = "transactionManager")
//	public PlatformTransactionManager transactionManager() throws SQLException {
//		JpaTransactionManager txManager = new JpaTransactionManager();
//		txManager.setEntityManagerFactory(entityManagerFactory());
//		return txManager;
//	}
//
//	@Bean
//	public HibernateExceptionTranslator hibernateExceptionTranslator() {
//		return new HibernateExceptionTranslator();
//	}
//}