package io.github.FeeATo.rest.service;

import io.github.FeeATo.domain.entity.Produto;
import io.github.FeeATo.rest.dto.PedidoDTO;
import io.github.FeeATo.rest.dto.ProdutoDTO;
import io.github.FeeATo.rest.exception.VendasException;

public interface ProdutoService {

    Produto salvar(ProdutoDTO pedidoDTO) throws VendasException;
    Produto salvar(Produto pedido) throws VendasException;

    Produto getProdutoById(Integer id);

    Produto removerProduto(Integer id);

}
