package com.estoque.estoque.backend.controller;

import com.estoque.estoque.backend.service.RelatorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/relatorios")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping("/rotatividade")
    public ResponseEntity<Map<String, Object>> getRotatividade() {
        Map<String, Object> relatorio = relatorioService.obterRotatividadeDeProduto();
        return ResponseEntity.ok(relatorio);
    }
}
