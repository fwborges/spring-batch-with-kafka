package br.dev.felipeborges.batch.produtor.produtorbatch;

import com.opencsv.CSVWriter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@SpringBootTest
class ProdutorBatchApplicationTests {

	@Test
	void contextLoads() {

// first create file object for file placed at location
		// specified by filepath
		File file = new File("/home/felipeb/Documents/git/batch-to-s3-on-demand/produtor-batch/src/main/resources/transacao.csv");
		try {
			// create FileWriter object with file as parameter
			FileWriter outputfile = new FileWriter(file);

			// create CSVWriter object filewriter object as parameter
			CSVWriter writer = new CSVWriter(outputfile, ';' );

			// adding header to csv
			String[] header = { "id", "numero", "valor" };
			writer.writeNext(header);

			String[] data1 = { "2", "10", "620" };

			for(int i = 1; i <= 20000000; i++) {
				// add data to csv

				if(i == 20000000) {
					String[] data = { "3", "10", "620" };
					writer.writeNext(data);
				} else if(i == 1) {
					String[] data = { "1", "10", "620" };
					writer.writeNext(data);
				} else {
					writer.writeNext(data1);
				}
			}

			// closing writer connection
			writer.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
