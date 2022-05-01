package br.dev.felipeborges.batch.produtor.produtorbatch.producer;

import br.dev.felipeborges.batch.produtor.produtorbatch.model.Transacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransacaoProducer {

    @Autowired
    private KafkaTemplate<Integer, Transacao> kafkaTemplate;

    public void send(Transacao transacao) {
        kafkaTemplate.send("topicTransacaoToS3", transacao);
    }
}
