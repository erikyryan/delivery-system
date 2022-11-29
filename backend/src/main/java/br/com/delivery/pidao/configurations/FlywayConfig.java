package br.com.delivery.pidao.configurations;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@ConfigurationProperties(prefix = "spring.datasource")
@Configuration
public class FlywayConfig {

//    private String url;
//
//    private String user;
//
//    private String password;

    public FlywayConfig () {
        Flyway.configure().baselineOnMigrate(true).dataSource("jdbc:postgresql://localhost:5432/postgres","postgres","maria123").load().migrate();
    }

}
