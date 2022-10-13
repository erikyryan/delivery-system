package br.com.delivery.pidao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class PidaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PidaoApplication.class, args);
	}

}
