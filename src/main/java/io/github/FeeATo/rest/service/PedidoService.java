package io.github.FeeATo.rest.service;

import io.github.FeeATo.domain.entity.Pedido;
import io.github.FeeATo.rest.dto.PedidoDTO;
import io.github.FeeATo.rest.exception.VendasException;

public interface PedidoService {

    PedidoDTO salvar(PedidoDTO pedidoDTO) throws VendasException;
}
