package com.javaee.artastic.Artastic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Hello world!
 *
 */
@EnableTransactionManagement
@SpringBootApplication
//@MapperScan("com.javaee.artastic.Artastic.mapper")
public class App 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }
}
