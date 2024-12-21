package io.github.FeeATo.rest.service;

import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.domain.entity.Pedido;
import io.github.FeeATo.rest.dto.ClienteDTO;
import io.github.FeeATo.rest.dto.PedidoDTO;
import io.github.FeeATo.rest.exception.VendasException;

import java.util.List;

public interface ClienteService {

    Cliente salvar(Cliente cliente) throws VendasException;
    Cliente salvar(ClienteDTO pedido) throws VendasException;

    Cliente getClienteById(Integer id);
    List<Cliente> getPedidosByClienteId(Integer produtoId);

    Cliente removerCliente(Integer id);
    Cliente removerCliente(Cliente pedido);

}
