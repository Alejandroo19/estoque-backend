package com.estoque.estoque.backend.controller;

import com.estoque.estoque.backend.model.Produto;
import com.estoque.estoque.backend.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Integer id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @RequestBody Produto produtoDetalhes) {
        return produtoService.buscarPorId(id).map(produtoExistente -> {

            produtoExistente.setNome(produtoDetalhes.getNome());
            produtoExistente.setPrecoUnitario(produtoDetalhes.getPrecoUnitario());
            produtoExistente.setUnidade(produtoDetalhes.getUnidade());
            produtoExistente.setQuantidadeMinima(produtoDetalhes.getQuantidadeMinima());
            produtoExistente.setQuantidadeMaxima(produtoDetalhes.getQuantidadeMaxima());

            produtoExistente.setQuantidadeEstoque(produtoDetalhes.getQuantidadeEstoque());

            Produto produtoAtualizado = produtoService.salvar(produtoExistente);
            return ResponseEntity.ok(produtoAtualizado);

        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
        if (produtoService.buscarPorId(id).isPresent()) {
            produtoService.deletar(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
