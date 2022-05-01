package br.dev.felipeborges.batch.produtor.produtorbatch;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class ProdutorBatchApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProdutorBatchApplication.class, args);
	}

}
