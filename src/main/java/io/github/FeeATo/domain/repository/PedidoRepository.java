package io.github.FeeATo.domain.repository;

import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.domain.entity.Pedido;
import io.github.FeeATo.rest.dto.PedidoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

    @Query("select p from Pedido p" +
            " JOIN FETCH p.cliente" +
            " WHERE p.id=:id ")
    Optional<Pedido> findByIdFetchCliente(@Param("id") Integer id);

    @Query("select p from Pedido p" +
            " JOIN FETCH p.itensPedido ip" +
            " JOIN FETCH ip.produto pro" +
            " WHERE pro.id=:produtoId")
    Optional<List<Pedido>> findByProdutoId(@Param("produtoId") Integer produtoId);
}
