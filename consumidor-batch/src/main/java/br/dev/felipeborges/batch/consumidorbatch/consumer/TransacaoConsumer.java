package br.dev.felipeborges.batch.consumidorbatch.consumer;

import br.dev.felipeborges.batch.consumidorbatch.model.Transacao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Slf4j
@Service
public class TransacaoConsumer {

    @KafkaListener(
            topics = "topicTransacaoToS3",
            groupId = "groupId")
    public void consume(String transacao) throws JsonProcessingException {
        // process greeting message
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = mapper.readValue(transacao, Map.class);

        if(Integer.valueOf(map.get("id").toString()) == 1) {
            log.info("+++++++++ Leu primeira mensagem de transacao" + transacao);
        }

        if(Integer.valueOf(map.get("id").toString()) == 3) {
            log.info("+++++++++ Leu ultima mensagem de transacao" + transacao);
        }
    }
}
