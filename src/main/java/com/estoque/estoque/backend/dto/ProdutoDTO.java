package com.estoque.estoque.backend.dto;

import com.estoque.estoque.backend.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {
    private Integer id;
    private String nome;
    private BigDecimal preco;
    private String categoria;
    private Integer quantidadeEstoque;
    private Integer quantidadeMinima;
    private Integer quantidadeMaxima;

    public static ProdutoDTO fromEntity(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setPreco(produto.getPrecoUnitario());
        dto.setCategoria(produto.getCategoria() != null ? produto.getCategoria().getNome() : "Sem Categoria");
        dto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        dto.setQuantidadeMinima(produto.getQuantidadeMinima());
        dto.setQuantidadeMaxima(produto.getQuantidadeMaxima());
        return dto;
    }
}
