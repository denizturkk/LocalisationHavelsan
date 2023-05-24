package com.example.sensor2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class Sensor2Application {

	public static void main(String[] args) {
		SpringApplication.run(Sensor2Application.class, args);
	}

}
