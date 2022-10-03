package br.com.delivery.pidao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class PidaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PidaoApplication.class, args);
	}

}
