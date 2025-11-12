package com.estoque.estoque.backend.service;

import com.estoque.estoque.backend.model.Movimentacao;
import com.estoque.estoque.backend.model.Produto;
import com.estoque.estoque.backend.repository.MovimentacaoRepository;
import com.estoque.estoque.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {
    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Movimentacao registrarMovimentacao(Movimentacao movimentacao) {

        Optional<Produto> produtoOpt = produtoRepository.findById(movimentacao.getProduto().getId());

        if (produtoOpt.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado.");
        }

        Produto produto = produtoOpt.get();
        int quantidade = movimentacao.getQuantidade();
        String tipo = movimentacao.getTipo();

        if ("SAIDA".equalsIgnoreCase(tipo)) {
            if (produto.getQuantidadeEstoque() < quantidade) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "Estoque insuficiente. Disponível: " + produto.getQuantidadeEstoque()
                );
            }
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - quantidade);
        } else if ("ENTRADA".equalsIgnoreCase(tipo)) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + quantidade);
        } else {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Tipo de movimentação inválido. Use 'ENTRADA' ou 'SAIDA'."
            );
        }

        movimentacao.setDataMovimentacao(LocalDateTime.now());
        movimentacao.setProduto(produto);

        produtoRepository.save(produto);
        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> listarTodas() {
        return movimentacaoRepository.findAll();
    }
}

