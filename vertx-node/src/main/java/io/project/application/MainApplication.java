package io.project.application;

import io.vertx.core.Vertx;
import io.project.resources.StaticServer;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@Configuration
@EnableJpaRepositories("io.project.repositories")
@ComponentScan(basePackages = {"io.project"})
@EntityScan("io.project.model")
@Import(SpringConfig.class)
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Autowired
    private StaticServer staticServer;

    @PostConstruct
    public void deployVerticle() {
        Vertx.vertx().deployVerticle(staticServer);

    }

}
