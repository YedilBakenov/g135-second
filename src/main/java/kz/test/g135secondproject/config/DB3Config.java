package kz.test.g135secondproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(
        basePackages = "kz.test.g135secondproject.repository3",
        mongoTemplateRef = "mongoTemplateRef"
)
public class DB3Config {

    @Bean(name = "mongoTemplateRef")
    public MongoTemplate mongoTemplate(MongoDatabaseFactory factory){
        return new MongoTemplate(factory);
    }

    @Bean
    public MongoTransactionManager mongoTransactionManager(MongoDatabaseFactory factory){
       return new MongoTransactionManager(factory);
    }
}
