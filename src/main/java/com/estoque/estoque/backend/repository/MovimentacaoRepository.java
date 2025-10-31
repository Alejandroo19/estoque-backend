package com.estoque.estoque.backend.repository;

import com.estoque.estoque.backend.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao,Integer> {
}
