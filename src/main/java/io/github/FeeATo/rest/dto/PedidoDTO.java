package io.github.FeeATo.rest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*
*
* {
    "cliente": 1,
    "produto": 1,
    "itensPedido": [
        {
            "produto": 1,
            "quantidade": 2
        }
    ]
}
* */
@Getter
@Setter
public class PedidoDTO {

    private final Integer id;
    private Integer cliente;
    private List<ItemPedidoDTO> itens;
    private Double total;

    public PedidoDTO() {
        id = null;
    }

    public PedidoDTO(Integer id) {
        this.id = id;
    }


}
