package com.estoque.estoque.backend.repository;

import com.estoque.estoque.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Produto findByNome(String nome);

    // Relatório 1: Lista de Preços
    List<Produto> findAllByOrderByNomeAsc();

    // Relatório 2: Balanço Físico/Financeiro
    @Query("SELECT SUM(p.precoUnitario * p.quantidadeEstoque) FROM Produto p")
    Double calcularValorTotalEstoque();

    // Relatório 3: Produtos abaixo da quantidade mínima
    List<Produto> findByQuantidadeEstoqueLessThan(Integer quantidadeMinima);

    // Relatório 4: Produtos por Categoria
    @Query("""
        SELECT 
            p.categoria.nome, COUNT(p.id) 
        FROM Produto p 
        GROUP BY p.categoria.nome
    """)
    List<Object[]> countProdutosPorCategoria();

}
