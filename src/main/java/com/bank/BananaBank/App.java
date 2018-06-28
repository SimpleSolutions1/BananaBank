package com.bank.BananaBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@RestController
public class App {
	
	 @RequestMapping(value = "/")
	    public String hello(){
	    	return "Welcome to Banana Best Bank";
	    }
	 
    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }

}
