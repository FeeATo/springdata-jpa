package io.github.FeeATo.rest.service;

import io.github.FeeATo.domain.entity.Pedido;
import io.github.FeeATo.rest.dto.PedidoDTO;
import io.github.FeeATo.rest.exception.VendasException;

import java.util.List;

public interface PedidoService {

    Pedido salvar(Pedido pedido) throws VendasException;
    Pedido salvar(PedidoDTO pedido) throws VendasException;

    Pedido getPedidoById(Integer id);
    List<Pedido> getPedidosByProdutoId(Integer produtoId);

    Pedido cancelarPedido(Integer id);
    Pedido cancelarPedido(Pedido pedido);
}
