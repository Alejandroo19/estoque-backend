package com.estoque.estoque.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.estoque.estoque.backend.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
