package com.feri.bookmanagment.config;

import com.mongodb.client.MongoClients;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {

    private final Dotenv dotenv;

    public MongoConfig() {
        dotenv = Dotenv.load();
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        String uri = dotenv.get("MONGO_URI");
        return new MongoTemplate(MongoClients.create(uri), dotenv.get("DB_NAME"));
    }
}
