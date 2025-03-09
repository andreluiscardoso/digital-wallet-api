package com.andreluiscardoso.digitalwallet.domain.model;

import com.andreluiscardoso.digitalwallet.util.RecordInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Wallet implements Serializable {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "agency", nullable = false, length = 4)
    private Integer agency;

    @Column(name = "number", nullable = false, length = 8)
    private String number;

    @Column(name = "bank", nullable = false, length = 4)
    private String bank;

    @Column(name = "provider", nullable = false, length = 100)
    private String provider;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Embedded
    private RecordInfo recordInfo;

}
