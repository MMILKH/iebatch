package com.ecomiebatch.iebatch.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "RIGA_ORDINE")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class RigaOrdine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "ORDINE_ID", nullable = false)
    private Integer ordineId;
    @Column(name = "PRODOTTO_ID", nullable = false)
    private int prodottoId;

    @Column(name = "RIGA_CARRELLO_ID")
    private Integer rigaCarrelloId;

    @Column(name = "QUANTITA", nullable = false)
    private int quantita;

    @Column(name = "PREZZO_UNITARIO", nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzoUnitario;

    @Column(name = "PREZZO_RIGA", nullable = false, precision = 10, scale = 2)
    private BigDecimal prezzoRiga;

    @Column(name = "DATA_CREAZIONE", nullable = false, updatable = false)
    private LocalDateTime dataCreazione;

}
