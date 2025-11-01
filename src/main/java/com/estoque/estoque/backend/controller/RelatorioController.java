package com.estoque.estoque.backend.controller;

import com.estoque.estoque.backend.dto.ProdutoDTO;
import com.estoque.estoque.backend.model.Produto;
import com.estoque.estoque.backend.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    // Relatório 1: Lista de Preços
    // Rota: GET /api/relatorios/lista-precos
    @GetMapping("/lista-precos")
    public ResponseEntity<List<ProdutoDTO>> getListaPrecos() {
        return ResponseEntity.ok(relatorioService.listarPrecos());
    }

    // Relatório 2: Balanço Físico/Financeiro
    // Rota: GET /api/relatorios/balanco
    @GetMapping("/balanco")
    public ResponseEntity<Map<String, Object>> getBalanco() {
        return ResponseEntity.ok(relatorioService.balancoFinanceiro());
    }

    // Relatório 3: Produtos abaixo da quantidade mínima
    // Rota: GET /api/relatorios/estoque-critico
    @GetMapping("/estoque-critico")
    public ResponseEntity<List<Produto>> getEstoqueCritico() {
        return ResponseEntity.ok(relatorioService.estoqueCritico());
    }

    // Relatório 4: Produtos por Categoria
    // Rota: GET /api/relatorios/por-categoria
    @GetMapping("/por-categoria")
    public ResponseEntity<List<Map<String, Object>>> getProdutosPorCategoria() {
        return ResponseEntity.ok(relatorioService.produtosPorCategoria());
    }

    // Relatório 5: Produto com mais ou menos rotatividade
    // Rota: GET /api/relatorios/rotatividade
    @GetMapping("/rotatividade")
    public ResponseEntity<Map<String, Object>> getRotatividade() {
        Map<String, Object> relatorio = relatorioService.obterRotatividadeDeProduto();
        return ResponseEntity.ok(relatorio);
    }
}
