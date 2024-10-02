package io.github.FeeATo.rest.service;

import io.github.FeeATo.domain.entity.Pedido;
import io.github.FeeATo.rest.dto.PedidoDTO;
import io.github.FeeATo.rest.exception.VendasException;

import java.util.List;

public interface PedidoService {

    PedidoDTO salvar(PedidoDTO pedidoDTO) throws VendasException;

    PedidoDTO getPedidoById(Integer id);

    PedidoDTO cancelarPedido(Integer id);
}
