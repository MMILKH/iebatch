package com.ecomiebatch.iebatch.imp.mapper;

import com.ecomiebatch.iebatch.exp.dto.RigaOrdineDto;
import com.ecomiebatch.iebatch.imp.dto.RegisterRequest;
import org.springframework.batch.infrastructure.item.file.mapping.FieldSetMapper;
import org.springframework.batch.infrastructure.item.file.transform.FieldSet;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.BindException;

public class RegisterFieldSetMapper implements FieldSetMapper<RegisterRequest> {

    @Override

    public RegisterRequest mapFieldSet(FieldSet fieldSet) throws BindException {


        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(fieldSet.readString("email"));
        registerRequest.setNome(fieldSet.readString("nome"));
        registerRequest.setCognome(fieldSet.readString("cognome"));
        registerRequest.setNumero(fieldSet.readString("numero"));
        registerRequest.setPassword(fieldSet.readString("password"));
        registerRequest.setPasswordConf(fieldSet.readString("passwordConf"));
        registerRequest.setIndirizzo(fieldSet.readString("indirizzo"));

        return registerRequest;
    }
}
