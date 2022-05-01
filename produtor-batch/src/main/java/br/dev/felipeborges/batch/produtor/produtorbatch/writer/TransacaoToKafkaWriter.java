package br.dev.felipeborges.batch.produtor.produtorbatch.writer;

import br.dev.felipeborges.batch.produtor.produtorbatch.model.Transacao;
import br.dev.felipeborges.batch.produtor.produtorbatch.producer.TransacaoProducer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransacaoToKafkaWriter implements ItemWriter<Transacao> {

    private TransacaoProducer transacaoProducer;

    public TransacaoToKafkaWriter(TransacaoProducer transacaoProducer) {
        this.transacaoProducer = transacaoProducer;
    }

    @Override
    public void write(List<? extends Transacao> items) throws Exception {
        items.forEach(item -> transacaoProducer.send(item));
    }
}
