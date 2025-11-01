package com.estoque.estoque.backend.repository;

import com.estoque.estoque.backend.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovimentacaoRepository extends JpaRepository<Movimentacao,Integer> {
    // Retorna o produto que mais teve um determinado TIPO de Movimentação
    @Query(value = """
        SELECT 
            p.id, p.nome, SUM(m.quantidade_movimentada) as total_movimentado
        FROM movimentacao m
        JOIN produto p ON m.produto_id = p.id
        WHERE m.tipo_movimentacao = :tipo
        GROUP BY p.id, p.nome
        ORDER BY total_movimentado DESC
        LIMIT 1
    """, nativeQuery = true)
    // MUDANÇA: Agora retorna Optional de uma LISTA de arrays
    Optional<List<Object[]>> findProdutoComMaiorMovimentacao(String tipo);
}
