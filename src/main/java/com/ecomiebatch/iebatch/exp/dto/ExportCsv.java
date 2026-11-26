package com.ecomiebatch.iebatch.exp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExportCsv {

    private String uuidOrdine;
    private String userUuid;
    private String indirizzoSpedizione;
    private String indirizzoFatturazione;
    private BigDecimal importoTotale;
    private LocalDateTime data;
    private String righeOrdine;
}