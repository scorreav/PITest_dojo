package com.dojo.dojo_pitest.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "model")
public class ModelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JoinColumn(name = "brand_id", referencedColumnName = "id")
    @Column(name = "brand_id")
    private long brandId;

    @Column(name = "name")
    private String name;

    @Column(name = "creation_date")
    private int year;
}
