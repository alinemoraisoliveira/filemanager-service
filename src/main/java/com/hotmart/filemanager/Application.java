package com.hotmart.filemanager;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	/**
	 * Swagger UI:   http://localhost:8080/swagger/index.html
	 */
	public static void main(String[] args) {
		new Application()
				.configure(new SpringApplicationBuilder(Application.class))
				.run(args);
	}

}
