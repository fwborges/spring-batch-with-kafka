package br.dev.felipeborges.batch.produtor.produtorbatch.config;

import br.dev.felipeborges.batch.produtor.produtorbatch.listeners.TransacaoJobListener;
import br.dev.felipeborges.batch.produtor.produtorbatch.model.Transacao;
import br.dev.felipeborges.batch.produtor.produtorbatch.model.mapper.TransacaoFieldMapper;
import br.dev.felipeborges.batch.produtor.produtorbatch.processor.TransacaoProcessor;
import br.dev.felipeborges.batch.produtor.produtorbatch.producer.TransacaoProducer;
import br.dev.felipeborges.batch.produtor.produtorbatch.writer.TransacaoToKafkaWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.kafka.KafkaItemWriter;
import org.springframework.batch.item.kafka.builder.KafkaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private TransacaoFieldMapper fieldMapper;

    @Autowired
    private TransacaoProducer transacaoProducer;

    @Bean
    public FlatFileItemReader<Transacao> reader() {
        return new FlatFileItemReaderBuilder<Transacao>()
                .name("TransacaoItemReader")
                .resource(new ClassPathResource("transacao.csv"))
                .linesToSkip(1)
                .delimited()
                .names("id", "numero", "valor", "data")
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<>(){{
                    setTargetType(Transacao.class);
                }}).build();
    }

    @Bean
    public LineMapper<Transacao> lineMapper() {
        final DefaultLineMapper<Transacao> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("id", "numero", "valor", "data");

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldMapper);

        return defaultLineMapper;
    }

    @Bean
    public TransacaoProcessor processor() {
        return new TransacaoProcessor();
    }

    @Bean
    public Step step() {
        return stepBuilderFactory.get("step")
                .<Transacao, Transacao> chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    public TransacaoToKafkaWriter writer() {
        return new TransacaoToKafkaWriter(transacaoProducer);
    }

    @Bean
    public Job job(TransacaoJobListener listener, Step step) {
        return jobBuilderFactory.get("transacao_job")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step)
                .end().build();
    }
}
