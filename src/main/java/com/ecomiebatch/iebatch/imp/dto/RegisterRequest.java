package com.ecomiebatch.iebatch.imp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String nome;

    private String cognome;

    private String email;

    private String password;

    private String passwordConf;

    private String numero;

    private @Nullable String indirizzo;

    private boolean temporaryPassword ;
}
