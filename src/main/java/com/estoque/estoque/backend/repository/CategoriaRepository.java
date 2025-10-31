package com.estoque.estoque.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estoque.estoque.backend.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
