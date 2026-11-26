package com.ecomiebatch.iebatch.imp.processor;

import com.ecomiebatch.iebatch.imp.dto.RegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class ImportProcessor implements ItemProcessor<RegisterRequest,RegisterRequest> {



    @Override
    public @Nullable RegisterRequest process (RegisterRequest item) throws Exception {


        if (item.getNome() == null||item.getCognome() == null || item.getEmail() == null|| item.getPassword() == null|| item.getPasswordConf() == null) {
            log.warn("Riga scartata per campo obbligatorio nullo");
            return null;
        }

        if (item.getPasswordConf().equals(item.getPassword())) {
            item.setTemporaryPassword(true);
            return item;
        }

        log.warn("Riga scartata per password non coincidenti (email={})", item.getEmail());
        return null;
    }




}
