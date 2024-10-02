package io.github.FeeATo.rest.service;

import io.github.FeeATo.domain.entity.Produto;
import io.github.FeeATo.domain.repository.ProdutosRepository;
import io.github.FeeATo.rest.exception.VendasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutosRepository produtosRepository;

    public Produto getProdutoById(Integer id) throws VendasException {
        if (id == null) {
            throw new VendasException("ID não pode ser nulo", VendasException.VendasExceptionEnum.BAD_REQ);
        }
        
        return produtosRepository
                .findById(id)
                .orElseThrow(()->new VendasException("Produto não encontrado", VendasException.VendasExceptionEnum.NOT_FOUND));
    }

    public Produto save(Produto produto) throws VendasException {
        if (produto.getId()!=null) {
            throw new VendasException("Produto já cadastrado");
        }

        return produtosRepository.save(produto);
    }

    public Produto update(Produto produto) throws VendasException {
        if (produto.getId() == null) {
            throw new VendasException("Produto não cadastrado");
        }

        return produtosRepository
                .findById(produto.getId())
                .map(p->produtosRepository.save(produto))
                .orElseThrow(()->new VendasException("Produto não encontrado"));
    }

    public void delete(Integer id) throws VendasException {
        if (id == null) {
            throw new VendasException("ID não pode ser nulo");
        }

        produtosRepository
                .findById(id)
                .map(p->{
                    produtosRepository.delete(p);
                    return p;
                })
                .orElseThrow(()-> new VendasException("Produto não encontrado"));
    }
}
