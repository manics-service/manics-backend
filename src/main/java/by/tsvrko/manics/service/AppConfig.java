package main.java.by.tsvrko.manics.service;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created main.main.java.by tsvrko on 1/25/2017.
 */

@Configuration
@EnableAutoConfiguration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"main.java.by.tsvrko.manics"})
@EntityScan(basePackages = {"main.java.by.tsvrko.manics.model.hibernate"})
public class AppConfig {

    @Bean
    public static HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }

    @Bean
    public static JpaTransactionManager transactionManager(){
        return  new JpaTransactionManager();
    }


}
