package by.tsvrko.manics.common;

import by.tsvrko.manics.dao.interfaces.SessionDAO;
import by.tsvrko.manics.dao.interfaces.UserDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by irats on 11/23/2016.
 */

@SpringBootApplication
@ComponentScan("by.tsvrko.manics")
public class Runner {
    public static void main( String[] args ){
        SpringApplication.run(Runner.class, args);
}}
