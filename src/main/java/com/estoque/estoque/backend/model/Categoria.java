package com.estoque.estoque.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "categoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Mapeia para INT no MySQL

    @Column(nullable = false, unique = true)
    private String nome;

    private String tamanho; // Pequeno, Médio, Grande

    private String embalagem; // Lata, Vidro, Plástico
}
