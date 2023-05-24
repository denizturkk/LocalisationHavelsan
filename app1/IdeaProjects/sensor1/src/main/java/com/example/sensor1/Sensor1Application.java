package com.example.sensor1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class Sensor1Application {

	public static void main(String[] args) {
		SpringApplication.run(Sensor1Application.class, args);
	}

}
