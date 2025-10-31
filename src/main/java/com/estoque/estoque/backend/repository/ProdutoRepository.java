package com.estoque.estoque.backend.repository;

import com.estoque.estoque.backend.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    List<Produto> findByQuantidadeEstoqueLessThan(Integer quantidadeMinima);

    Produto findByNome(String nome);
}
