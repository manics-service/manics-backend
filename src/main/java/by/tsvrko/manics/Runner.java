package main.java.by.tsvrko.manics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("main.java.by.tsvrko.manics")
public class Runner {

    public static void main( String[] args ){
        SpringApplication.run(Runner.class, args);

    }



}
