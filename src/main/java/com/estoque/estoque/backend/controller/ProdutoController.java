package com.estoque.estoque.backend.controller;

import com.estoque.estoque.backend.model.Produto;
import com.estoque.estoque.backend.repository.ProdutoRepository;
import com.estoque.estoque.backend.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoController(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Produto produto) {
        if (produto.getCategoria() == null || produto.getCategoria().getId() == null) {
            return ResponseEntity.badRequest().body("Categoria é obrigatória");
        }

        var categoria = categoriaRepository.findById(produto.getCategoria().getId());
        if (categoria.isEmpty()) {
            return ResponseEntity.badRequest().body("Categoria não encontrada");
        }

        produto.setCategoria(categoria.get());
        Produto salvo = produtoRepository.save(produto);
        return ResponseEntity.ok(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        Optional<Produto> existente = produtoRepository.findById(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Produto atualizado = existente.get();
        atualizado.setNome(produto.getNome());
        atualizado.setPreco(produto.getPreco());
        atualizado.setQuantidade(produto.getQuantidade());
        atualizado.setAtivo(produto.isAtivo());
        atualizado.setCategoria(produto.getCategoria());

        return ResponseEntity.ok(produtoRepository.save(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!produtoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        produtoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
