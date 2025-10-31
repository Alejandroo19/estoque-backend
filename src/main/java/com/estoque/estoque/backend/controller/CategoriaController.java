package com.estoque.estoque.backend.controller;

import com.estoque.estoque.backend.model.Categoria;
import com.estoque.estoque.backend.repository.CategoriaRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@CrossOrigin(origins = "*") // permite que o front (React, etc.) acesse a API
public class CategoriaController {

    private final CategoriaRepository repository;

    public CategoriaController(CategoriaRepository repository) {
        this.repository = repository;
    }

    // LISTAR todas as categorias
    @GetMapping
    public List<Categoria> listar() {
        return repository.findAll();
    }

    // CRIAR uma nova categoria
    @PostMapping
    public Categoria criar(@RequestBody Categoria categoria) {
        return repository.save(categoria);
    }

    // ATUALIZAR categoria existente
    @PutMapping("/{id}")
    public Categoria atualizar(@PathVariable Long id, @RequestBody Categoria categoriaAtualizada) {
        return repository.findById(id).map(categoria -> {
            categoria.setNome(categoriaAtualizada.getNome());
            categoria.setAtivo(categoriaAtualizada.isAtivo());
            return repository.save(categoria);
        }).orElseThrow(() -> new RuntimeException("Categoria não encontrada"));
    }

    // EXCLUIR uma categoria
    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
