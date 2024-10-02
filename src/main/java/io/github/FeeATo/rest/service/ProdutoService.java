package io.github.FeeATo.rest.service;

import io.github.FeeATo.domain.entity.Produto;
import io.github.FeeATo.domain.repository.ProdutosRepository;
import io.github.FeeATo.rest.exception.VendasEnumException;
import io.github.FeeATo.rest.exception.VendasException;
import io.github.FeeATo.rest.exception.VendasRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutosRepository produtosRepository;

    public Produto getProdutoById(Integer id) throws VendasException {

        try {
            if (id == null) {
                throw new VendasRuntimeException("ID não pode ser nulo", VendasEnumException.BAD_REQ);
            }

            return produtosRepository
                    .findById(id)
                    .orElseThrow(() -> new VendasRuntimeException("Produto não encontrado", VendasEnumException.NOT_FOUND));
        } catch (VendasRuntimeException vne) {
            throw vne;
        } catch (Exception ex) {
            throw new VendasException("Erro ao buscar produto por id", ex);
        }
    }

    public Produto save(Produto produto) {
        if (produto.getId()!=null) {
            throw new VendasRuntimeException("Produto já cadastrado");
        }

        return produtosRepository.save(produto);
    }

    public Produto update(Produto produto) {
        if (produto.getId() == null) {
            throw new VendasRuntimeException("Produto não cadastrado");
        }

        return produtosRepository
                .findById(produto.getId())
                .map(p->produtosRepository.save(produto))
                .orElseThrow(()->new VendasRuntimeException("Produto não encontrado"));
    }

    public void delete(Integer id){
        if (id == null) {
            throw new VendasRuntimeException("ID não pode ser nulo");
        }

        produtosRepository
                .findById(id)
                .map(p->{
                    produtosRepository.delete(p);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new VendasRuntimeException("Produto não encontrado"));
    }

    public List<Produto> find(Produto filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        return produtosRepository.findAll(example);
    }
}
