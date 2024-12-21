package io.github.FeeATo.rest.service.imp;

import io.github.FeeATo.domain.entity.Pedido;
import io.github.FeeATo.domain.entity.Produto;
import io.github.FeeATo.domain.enums.CadastroStatus;
import io.github.FeeATo.domain.repository.ProdutosRepository;
import io.github.FeeATo.rest.dto.ProdutoDTO;
import io.github.FeeATo.rest.exception.VendasEnumException;
import io.github.FeeATo.rest.exception.VendasRuntimeException;
import io.github.FeeATo.rest.service.PedidoService;
import io.github.FeeATo.rest.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProdutoServiceImp implements ProdutoService {

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private PedidoService pedidoService;

    @Override
    public Produto getProdutoById(Integer id) {
        if (id == null) {
            throw new VendasRuntimeException("ID não pode ser nulo", VendasEnumException.BAD_REQ);
        }

        return produtosRepository
                .findById(id)
                .orElseThrow(() -> new VendasRuntimeException("Produto não encontrado", VendasEnumException.NOT_FOUND));

    }

    public Produto salvar(ProdutoDTO produtoDTO) {
        Produto produto = new Produto(produtoDTO.getDescricao(), BigDecimal.valueOf(produtoDTO.getPreco()));
        return salvar(produto);
    }

    public Produto salvar(Produto produto) {
        if (produto.getId() != null) {
            throw new VendasRuntimeException("Produto já cadastrado ou ID encontrado na requisição");
        }

        return produtosRepository.save(produto);
    }

    public Produto update(Produto produto) {
        if (produto.getId() == null) {
            throw new VendasRuntimeException("Produto não cadastrado");
        }

        return produtosRepository
                .findById(produto.getId())
                .map(p -> produtosRepository.save(produto))
                .orElseThrow(() -> new VendasRuntimeException("Produto não encontrado"));
    }

    public Produto removerProduto(Integer id) {
        if (id == null) {
            throw new VendasRuntimeException("ID não pode ser nulo");
        }
        Produto produto = produtosRepository
                .findById(id)
                .map(p -> {
                    p.setStatus(CadastroStatus.DESATIVADO);
                    produtosRepository.save(p);
                    return p;
                })
                .orElseThrow(() -> new VendasRuntimeException("Produto não encontrado"));
        List<Pedido> pedidoList = pedidoService.getPedidosByProdutoId(id);
        for (Pedido pedido : pedidoList) {
            pedidoService.cancelarPedido(pedido);
        }
        return produto;
    }

    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return produtosRepository.findAll(example);
    }
}
