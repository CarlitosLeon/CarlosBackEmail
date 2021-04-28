package com.naib.sinapsist.api.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;;

@SpringBootApplication
public class NaibReedExhibitionsApiApplication extends ServletInitializer {

	//@Autowired
	//private BCryptPasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(NaibReedExhibitionsApiApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(NaibReedExhibitionsApiApplication.class);
	}

	/**@Override
	public void run(String... args) throws Exception {
		String password = "test3";

		for (int i = 0; i < 4; i++) {
			String passwordEncriptada = passwordEncoder.encode(password);
			System.out.println(passwordEncriptada);
		}

	}**/
}
