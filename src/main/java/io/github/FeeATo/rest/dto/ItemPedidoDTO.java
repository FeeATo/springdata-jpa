package io.github.FeeATo.rest.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
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

    public ItemPedidoDTO(ProdutoDTO produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
}
