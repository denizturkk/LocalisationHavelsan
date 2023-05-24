package com.havelsan.messageBrokers.concretes.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic targetTopicLocation(){
        return TopicBuilder.name("target_location").build();
    }
}
