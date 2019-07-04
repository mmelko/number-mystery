package com.redhat.fuse.fis.stability;

import org.apache.cxf.Bus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.apache.cxf.jaxrs.swagger.Swagger2Feature;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class GuesserApplication {

	@Autowired
	private Bus bus;

	public static void main(String[] args) {
		SpringApplication.run(GuesserApplication.class, args);
	}

	@Bean
	public Server rsServer() {
		// setup CXF-RS
		JAXRSServerFactoryBean endpoint = new JAXRSServerFactoryBean();
		endpoint.setBus(bus);
		endpoint.setServiceBeans(Arrays.<Object>asList(new NumberGuesser()));
		endpoint.setAddress("/");
		endpoint.setFeatures(Arrays.asList(new Swagger2Feature()));
		return endpoint.create();
	}

}


