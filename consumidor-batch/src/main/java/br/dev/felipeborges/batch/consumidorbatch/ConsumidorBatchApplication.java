package br.dev.felipeborges.batch.consumidorbatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
public class ConsumidorBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumidorBatchApplication.class, args);
	}

}
