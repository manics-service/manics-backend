package by.tsvrko.manics.service;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import java.sql.SQLException;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by tsvrko on 1/25/2017.
 */
@Configuration
@EnableTransactionManagement
@EnableAutoConfiguration
public class AppConfig {

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan( "by.tsvrko.manics" );
        return sessionFactory;
    }

    @Bean(name = "dataSource")
    public BasicDataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUrl("jdbc:mysql://localhost:3306/manics");
        ds.setUsername("root");
        ds.setPassword("123");
        return ds;
    }

    @Bean
    public PlatformTransactionManager transactionManager() throws SQLException {
        return new JpaTransactionManager();
    }

}
