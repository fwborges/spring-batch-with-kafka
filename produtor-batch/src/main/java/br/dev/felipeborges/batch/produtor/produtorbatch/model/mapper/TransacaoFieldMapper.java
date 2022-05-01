package br.dev.felipeborges.batch.produtor.produtorbatch.model.mapper;

import br.dev.felipeborges.batch.produtor.produtorbatch.model.Transacao;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
public class TransacaoFieldMapper implements FieldSetMapper<Transacao> {
    @Override
    public Transacao mapFieldSet(FieldSet fieldSet) throws BindException {

        int id = fieldSet.readInt("id");
        int numero = fieldSet.readInt("numero");
        BigDecimal valor = fieldSet.readBigDecimal("valor");

        return new Transacao(id, numero, valor);
    }
}
