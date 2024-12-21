package io.github.FeeATo.rest.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.FeeATo.domain.entity.ItemPedido;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class ItemPedidoDTO {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer produtoId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private ProdutoDTO produto;
    private Integer quantidade;
    private Double preco;

    public ItemPedidoDTO(ProdutoDTO produto, Integer quantidade, Double preco) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public static ItemPedidoDTO convertDTO(ItemPedido itemPedido) {
        return new ItemPedidoDTO(ProdutoDTO.convertDTO(itemPedido.getProduto()),
                itemPedido.getQuantidade(),
                itemPedido.getPreco().doubleValue());
    }
}
