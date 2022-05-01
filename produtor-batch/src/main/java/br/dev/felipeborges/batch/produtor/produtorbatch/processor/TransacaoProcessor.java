package br.dev.felipeborges.batch.produtor.produtorbatch.processor;

import br.dev.felipeborges.batch.produtor.produtorbatch.model.Transacao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;

@Slf4j
public class TransacaoProcessor implements ItemProcessor<Transacao, Transacao> {

    private static int count = 1;
    @Override
    public Transacao process(Transacao item) throws Exception {

        if(count % 100000 == 0) {

            log.info("Chegou no " + count );
        }


        count = count + 1;

        return new Transacao(item.getId(), item.getNumero(), item.getValor());

    }
}
