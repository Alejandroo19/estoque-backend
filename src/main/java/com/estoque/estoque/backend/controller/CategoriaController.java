package com.estoque.estoque.backend.controller;

import com.estoque.estoque.backend.model.Categoria;
import com.estoque.estoque.backend.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> listarCategorias() {
        List<Categoria> categorias = categoriaService.listarTodas();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Integer id) {
        return categoriaService.buscarPorId(id)
                // Se encontrar, retorna 200 (OK) com o objeto
                .map(ResponseEntity::ok)
                // Se não encontrar, retorna 404 (Not Found)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Integer id, @RequestBody Categoria categoriaDetalhes) {
        return categoriaService.buscarPorId(id).map(categoriaExistente -> {

            categoriaExistente.setNome(categoriaDetalhes.getNome());
            categoriaExistente.setTamanho(categoriaDetalhes.getTamanho());
            categoriaExistente.setEmbalagem(categoriaDetalhes.getEmbalagem());

            Categoria categoriaAtualizada = categoriaService.salvar(categoriaExistente);
            return ResponseEntity.ok(categoriaAtualizada);

            // Se a categoria não for encontrada, retorna 404
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Integer id) {
        if (categoriaService.buscarPorId(id).isPresent()) {
            categoriaService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        // Se não encontrar para deletar, retorna 404 (Not Found)
        return ResponseEntity.notFound().build();
    }
}
