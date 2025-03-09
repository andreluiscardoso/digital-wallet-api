package com.andreluiscardoso.digitalwallet.domain.model;

import com.andreluiscardoso.digitalwallet.util.RecordInfo;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "zip_code", nullable = false, length = 8)
    private String zipCode;

    @Column(name = "street", nullable = false, length = 100)
    private String street;

    @Column(name = "complement", nullable = false, length = 200)
    private String complement;

    @Column(name = "neighborhood", nullable = false, length = 100)
    private String neighborhood;

    @Column(name = "city", nullable = false, length = 60)
    private String city;

    @Column(name = "state", nullable = false, length = 60)
    private String state;

    @Column(name = "country", nullable = false, length = 60)
    private String country;

    @Embedded
    private RecordInfo recordInfo;

}
