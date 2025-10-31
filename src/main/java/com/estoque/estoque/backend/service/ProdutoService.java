package com.estoque.estoque.backend.service;

import com.estoque.estoque.backend.model.Produto;
import com.estoque.estoque.backend.model.Categoria;
import com.estoque.estoque.backend.repository.ProdutoRepository;
import com.estoque.estoque.backend.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto salvar(Produto produto) {

        if (produto.getCategoria() != null && produto.getCategoria().getId() != null) {

            Optional<Categoria> categoria = categoriaRepository.findById(produto.getCategoria().getId());

            if (categoria.isPresent()) {
                produto.setCategoria(categoria.get());
            } else {
                produto.setCategoria(null);
            }
        }

        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public void deletar(Integer id) {
        produtoRepository.deleteById(id);
    }
}

