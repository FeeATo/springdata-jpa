package io.github.FeeATo.rest.service.imp;

import io.github.FeeATo.domain.entity.ItemPedido;
import io.github.FeeATo.domain.entity.Pedido;
import io.github.FeeATo.domain.entity.Produto;
import io.github.FeeATo.domain.repository.ClientesRepository;
import io.github.FeeATo.domain.repository.ItemPedidoRepository;
import io.github.FeeATo.domain.repository.PedidoRepository;
import io.github.FeeATo.domain.repository.ProdutosRepository;
import io.github.FeeATo.rest.dto.ItemPedidoDTO;
import io.github.FeeATo.rest.dto.PedidoDTO;
import io.github.FeeATo.rest.dto.ProdutoDTO;
import io.github.FeeATo.rest.exception.VendasException;
import io.github.FeeATo.rest.exception.VendasRuntimeException;
import io.github.FeeATo.rest.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidosServiceImp implements PedidoService {

    private final PedidoRepository pedidoRepository; //tem que ser final pro Lombok botar na hora q criar essa classe
    private final ClientesRepository clientesRepository;
    private final ProdutosRepository produtosRepository;
    private final ItemPedidoRepository itemPedidoRepository;

    @Override
    @Transactional //garante que OU ele salva TUDO (pedido e itens pedido) ou ele n salva nd, porque se der erro o Spring vai dar rollback na transaction
    public PedidoDTO salvar(PedidoDTO pedidoDTO) throws VendasException {
        try {
            Pedido pedido = montaPedido(pedidoDTO);
            pedidoRepository.save(pedido);
            itemPedidoRepository.saveAll(pedido.getItensPedido());

            return montaPedidoDTO(pedido);
        } catch (VendasRuntimeException vne) {
            throw vne;
        } catch (Exception ex) {
            throw new VendasException("Erro ao salvar pedido", ex);
        }
    }

    private PedidoDTO montaPedidoDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO(pedido.getId());
        pedidoDTO.setItens(pedido.getItensPedido().stream().map(i->new ItemPedidoDTO(new ProdutoDTO(i.getProduto()), i.getQuantidade())).collect(Collectors.toList()));
        pedidoDTO.setTotal(pedido.getItensPedido().stream().map(i->{
            return i.getProduto().getPreco().multiply(BigDecimal.valueOf(i.getQuantidade()));
        }).reduce(BigDecimal::add).map(BigDecimal::doubleValue).orElse(0.0));
        return pedidoDTO;
    }

    private Pedido montaPedido(PedidoDTO pedidoDTO) {
        Pedido pedido = new Pedido();
        pedido.setCliente(clientesRepository
                .findById(pedidoDTO.getCliente())
                .orElseThrow(() -> new VendasRuntimeException("Cliente não encontrado para criar pedido")));

        Map<Integer, Produto> produtoMap = new HashMap<>();
        List<ItemPedido> itemPedidoList = new ArrayList<>();
        for (ItemPedidoDTO item : pedidoDTO.getItens()) {
            Produto produto = produtoMap.get(item.getProduto());
            if (produto == null) {
                produto = produtosRepository
                        .findById(item.getProdutoId())
                        .orElseThrow(() -> new VendasRuntimeException("Produto não encontrado para criar pedido"));
            }
            produtoMap.put(item.getProdutoId(),
                    produto);

            itemPedidoList.add(new ItemPedido(pedido, produto, item.getQuantidade()));
        }
        pedido.setItensPedido(itemPedidoList);
        return pedido;
    }
}