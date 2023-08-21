package com.blackshark.SpringBootRest.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor  @AllArgsConstructor
@Table(name = "fabricante")
public class Maker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fabricante_id", nullable = false)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @OneToMany(targetEntity = Product.class,
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL,
        mappedBy = "maker",
        orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}