package io.github.FeeATo.rest.controller;

import io.github.FeeATo.domain.entity.Produto;
import io.github.FeeATo.rest.dto.ProdutoDTO;
import io.github.FeeATo.rest.dto.Response;
import io.github.FeeATo.rest.exception.VendasException;
import io.github.FeeATo.rest.service.imp.ProdutoServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {


    private ProdutoServiceImp produtoServiceImp;

    public ProdutoController(ProdutoServiceImp produtoServiceImp) {
        this.produtoServiceImp = produtoServiceImp;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello produtos";
    }

    @GetMapping("/{id}")
    public Response<ProdutoDTO> getProdutoById(@PathVariable Integer id) {
        return new Response<>(ProdutoDTO.convertDTO(produtoServiceImp.getProdutoById(id)));
    }

    @PostMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    public Response<ProdutoDTO> save(@RequestBody @Valid Produto produto) throws VendasException {
        try {
            return new Response<>("Produto cadastrado com sucesso", ProdutoDTO.convertDTO(produtoServiceImp.salvar(produto)));
        } catch (Exception ex) {
            throw new VendasException("Erro ao salvar produto", ex);
        }
    }

    @PutMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.OK)
    public Response<ProdutoDTO> update(@RequestBody Produto produto) {
        return new Response<>("Produto atualizado com sucesso", ProdutoDTO.convertDTO(produtoServiceImp.update(produto)));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Response<ProdutoDTO> delete(@PathVariable Integer id) {
        return new Response<>("Produto removido com sucesso", ProdutoDTO.convertDTO(produtoServiceImp.removerProduto(id)));
    }

    @GetMapping(value = {"/", ""})
    public Response<Produto> find(Produto filtro) {
        return new Response<>(produtoServiceImp.find(filtro));
    }

}
