package com.ecomiebatch.iebatch.exp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdineDto {
    private Integer id;
    private String utenteId;
    private LocalDateTime dataCreazione;
    private String indirizzoSpedizione;
    private String indirizzoFatturazione;
    private BigDecimal importo;
    private String codice;
    private int  statoId;
    private List<RigaOrdineDto> righeOrdine;

}

