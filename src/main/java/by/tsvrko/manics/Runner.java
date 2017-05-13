package by.tsvrko.manics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("by.tsvrko.manics")
public class Runner {

    public static void main( String[] args ){
        SpringApplication.run(Runner.class, args);

    }



}
