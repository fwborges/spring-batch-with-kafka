package br.dev.felipeborges.batch.produtor.produtorbatch.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TransacaoJobListener extends JobExecutionListenerSupport {
    @Override
    public void beforeJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.STARTED) {
            log.info("Job de transacao iniciado");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Job de transacao finalizado");
        }
    }
}
