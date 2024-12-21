package io.github.FeeATo.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.domain.entity.ItemPedido;
import io.github.FeeATo.domain.entity.Pedido;
import io.github.FeeATo.domain.enums.PedidoStatus;
import io.github.FeeATo.rest.exception.VendasRuntimeException;
import io.github.FeeATo.validation.NotEmptyList;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @NotEmptyList(messages = "Pedido não pode ser realizado sem itens.")
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

    public static PedidoDTO convertDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO(pedido.getId());
        pedidoDTO.setItens(pedido.getItensPedido().stream().map(i -> new ItemPedidoDTO(new ProdutoDTO(i.getProduto()), i.getQuantidade(), i.getPreco().doubleValue())).collect(Collectors.toList()));
        pedidoDTO.setStatus(pedido.getPedidoStatus().name());
        if (pedido.getPedidoStatus().equals(PedidoStatus.CONFIRMADO)) {
            pedidoDTO.setTotal(pedido.getItensPedido().stream().map(i -> {
                        return i.getProduto().getPreco().multiply(BigDecimal.valueOf(i.getQuantidade()));
                    }).reduce(BigDecimal::add)
                    .map(BigDecimal::doubleValue)
                    .orElse(0.0));
        }
        pedidoDTO.setCliente(pedido.getCliente());
        pedidoDTO.setDataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        return pedidoDTO;
    }

    public static List<PedidoDTO> convertDTO(List<ItemPedido> itemPedidoList) {
        List<PedidoDTO> pedidoList = null;
        if (itemPedidoList!=null && !itemPedidoList.isEmpty()) {
            pedidoList = new ArrayList<>();
            for (ItemPedido itensPedido : itemPedidoList) {
                Pedido pedido = itensPedido.getPedido();
                pedidoList.add(PedidoDTO.convertDTO(pedido));
            }
        }
        return pedidoList;
    }

}
