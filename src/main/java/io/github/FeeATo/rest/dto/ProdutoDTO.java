package io.github.FeeATo.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.FeeATo.domain.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {

    private Integer id;
    private String descricao;
    private Double preco;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<PedidoDTO> pedidos;

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.descricao = produto.getDescricao();
        this.preco = produto.getPreco().doubleValue();
    }

    public static ProdutoDTO convertDTO(Produto produto) {
        return new ProdutoDTO(produto.getId(),
                produto.getDescricao(),
                produto.getPreco().doubleValue(),
                PedidoDTO.convertDTO(produto.getItemPedidoList()));
    }
}
