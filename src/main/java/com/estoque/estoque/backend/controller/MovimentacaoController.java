package com.estoque.estoque.backend.controller;

import com.estoque.estoque.backend.dto.MovimentacaoDTO;
import com.estoque.estoque.backend.model.Movimentacao;
import com.estoque.estoque.backend.service.MovimentacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;

    @PostMapping
    public ResponseEntity<Movimentacao> registrarMovimentacao(@RequestBody Movimentacao movimentacao) {
        Movimentacao novaMovimentacao = movimentacaoService.registrarMovimentacao(movimentacao);
        return new ResponseEntity<>(novaMovimentacao, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MovimentacaoDTO>> listarMovimentacoes() {
        List<Movimentacao> movimentacoes = movimentacaoService.listarTodas();
        List<MovimentacaoDTO> dtoList = movimentacoes.stream()
                .map(MovimentacaoDTO::fromEntity)
                .toList();

        return ResponseEntity.ok(dtoList);
    }
}
