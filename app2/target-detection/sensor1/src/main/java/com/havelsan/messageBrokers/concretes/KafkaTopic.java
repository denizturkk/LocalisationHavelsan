package com.havelsan.messageBrokers.concretes;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {


    @Bean
    public NewTopic targetTopic1(){
        return TopicBuilder.name("sensor1_data").build();
    }
}
