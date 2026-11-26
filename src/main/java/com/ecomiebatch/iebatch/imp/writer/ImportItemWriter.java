package com.ecomiebatch.iebatch.imp.writer;

import com.ecomiebatch.iebatch.imp.dto.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.step.StepExecution;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;


@Component
@Slf4j
public class ImportItemWriter implements ItemWriter<RegisterRequest> {

    private final RestClient restClient;
    private final String registerPath;



    public ImportItemWriter(RestClient.Builder restClientBuilder,
                            @Value("${core-user.base-url}") String baseUrl, @Value("${core-user.register-path}")String registerPath) {
        this.restClient = restClientBuilder
                .baseUrl(baseUrl)
                .build();
        this.registerPath = registerPath;
    }


    @Override
    public void write(Chunk<? extends RegisterRequest> chunk) {
        for (RegisterRequest request : chunk.getItems()) {
            try {
                log.info("Invio registrazione a coreUser per email: {}", request.getEmail());

                restClient.post()
                        .uri(registerPath)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(request)
                        .retrieve()
                        .toBodilessEntity();

                log.info("Registrazione completata per email: {}", request.getEmail());
            } catch (RestClientResponseException ex) {

                log.warn("Errore HTTP {} da coreUser per email {}: {}",
                        ex.getStatusCode().value(),
                        request.getEmail(),
                        ex.getResponseBodyAsString());
                throw ex;
            } catch (Exception ex) {
                log.error("Errore  server side per email: {}",
                        request.getEmail(), ex);
                throw ex;
            }
        }
    }
}
