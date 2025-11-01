package com.estoque.estoque.backend.service;

import com.estoque.estoque.backend.model.Produto;
import com.estoque.estoque.backend.dto.ProdutoDTO;
import com.estoque.estoque.backend.repository.MovimentacaoRepository;
import com.estoque.estoque.backend.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RelatorioService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    // Relatório 1: Lista de Preços
    public List<ProdutoDTO> listarPrecos() {
        return produtoRepository.findAllByOrderByNomeAsc().stream()
            .map(ProdutoDTO::fromEntity)
            .collect(Collectors.toList());
    }

    // Relatório 2: Balanço Físico/Financeiro
    public Map<String, Object> balancoFinanceiro() {
        List<Produto> produtos = produtoRepository.findAllByOrderByNomeAsc();
        Double valorTotalEstoque = produtoRepository.calcularValorTotalEstoque();

        List<Map<String, Object>> itens = produtos.stream()
            .map(p -> {
                Map<String, Object> item = new HashMap<>();
                item.put("nome", p.getNome());
                item.put("quantidade_disponivel", p.getQuantidadeEstoque());
                item.put("valor_unitario", p.getPrecoUnitario());
                item.put("valor_total_produto", p.getPrecoUnitario().doubleValue() * p.getQuantidadeEstoque());
                return item;
            })
            .collect(Collectors.toList());

        Map<String, Object> resultado = new HashMap<>();
        resultado.put("valor_total_estoque", valorTotalEstoque);
        resultado.put("produtos", itens);
        return resultado;
    }

    // Relatório 3: Produtos abaixo da quantidade mínima
    public List<Produto> estoqueCritico() {
        List<Produto> todosProdutos = produtoRepository.findAll();

        return todosProdutos.stream()
                .filter(p -> p.getQuantidadeEstoque() < p.getQuantidadeMinima())
                .collect(Collectors.toList());
    }

    // Relatório 4: Produtos por Categoria
    public List<Map<String, Object>> produtosPorCategoria() {
        List<Object[]> resultados = produtoRepository.countProdutosPorCategoria();

        return resultados.stream()
            .map(arr -> {
                Map<String, Object> item = new HashMap<>();
                item.put("nome_categoria", (String) arr[0]);
                item.put("quantidade_produtos_distintos", (Long) arr[1]);
                return item;
            })
            .collect(Collectors.toList());
    }

    // Relatório 5: Produto com mais ou menos rotatividade
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
