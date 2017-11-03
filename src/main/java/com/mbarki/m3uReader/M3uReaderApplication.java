package com.mbarki.m3uReader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class M3uReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(M3uReaderApplication.class, args);
	}
}
