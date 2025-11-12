package com.estoque.estoque.backend.dto;

import com.estoque.estoque.backend.model.Movimentacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimentacaoDTO {
    private Integer id;
    private String nomeProduto;
    private LocalDateTime dataMovimentacao;
    private Integer quantidade;
    private String tipo;

    public static MovimentacaoDTO fromEntity(Movimentacao mov) {
        MovimentacaoDTO dto = new MovimentacaoDTO();
        dto.setId(mov.getId());
        dto.setNomeProduto(mov.getProduto().getNome());
        dto.setDataMovimentacao(mov.getDataMovimentacao());
        dto.setQuantidade(mov.getQuantidade());
        dto.setTipo(mov.getTipo());
        return dto;
    }
}
