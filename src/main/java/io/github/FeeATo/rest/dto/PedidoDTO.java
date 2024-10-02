package io.github.FeeATo.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.FeeATo.domain.entity.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer clienteId;
    private Cliente cliente;
    private List<ItemPedidoDTO> itens;
    private String dataPedido;
    private String status;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double total;

    public PedidoDTO() {
        id = null;
    }

    public PedidoDTO(Integer id) {
        this.id = id;
    }


}
