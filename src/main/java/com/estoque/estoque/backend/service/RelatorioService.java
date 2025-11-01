package com.estoque.estoque.backend.service;

import com.estoque.estoque.backend.repository.MovimentacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RelatorioService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    public Map<String, Object> obterRotatividadeDeProduto() {

        Map<String, Object> resultado = new HashMap<>();

        Optional<List<Object[]>> maisSaidaLista = movimentacaoRepository.findProdutoComMaiorMovimentacao("SAIDA");
        if (maisSaidaLista.isPresent() && !maisSaidaLista.get().isEmpty()) {
            Object[] dados = maisSaidaLista.get().get(0);

            Map<String, Object> produtoSaida = new HashMap<>();

            produtoSaida.put("id", (Integer) dados[0]);
            produtoSaida.put("nome", (String) dados[1]);
            produtoSaida.put("total_saida", ((Number) dados[2]).longValue());

            resultado.put("produto_maior_saida", produtoSaida);
        } else {
            resultado.put("produto_maior_saida", "Nenhuma saída registrada.");
        }

        Optional<List<Object[]>> maisEntradaLista = movimentacaoRepository.findProdutoComMaiorMovimentacao("ENTRADA");
        if (maisEntradaLista.isPresent() && !maisEntradaLista.get().isEmpty()) {
            Object[] dados = maisEntradaLista.get().get(0);

            Map<String, Object> produtoEntrada = new HashMap<>();
            produtoEntrada.put("id", (Integer) dados[0]);
            produtoEntrada.put("nome", (String) dados[1]);
            produtoEntrada.put("total_entrada", ((Number) dados[2]).longValue());

            resultado.put("produto_maior_entrada", produtoEntrada);
        } else {
            resultado.put("produto_maior_entrada", "Nenhuma entrada registrada.");
        }

        return resultado;
    }
}
