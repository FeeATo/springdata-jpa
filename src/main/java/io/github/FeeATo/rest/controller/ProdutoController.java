package io.github.FeeATo.rest.controller;

import io.github.FeeATo.domain.entity.Produto;
import io.github.FeeATo.rest.exception.VendasException;
import io.github.FeeATo.rest.dto.Response;
import io.github.FeeATo.rest.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {


    private ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello produtos";
    }

    @GetMapping("/{id}")
    public Response<Produto> getProdutoById(@PathVariable Integer id) {
        return new Response<>(produtoService.getProdutoById(id));
    }

    @PostMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Produto> save(@RequestBody Produto produto) {
        return new Response<>("Produto cadastrado com sucesso", produtoService.save(produto));
    }

    @PutMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.OK)
    public Response<Produto> update(@RequestBody Produto produto) {
        return new Response<>("Produto atualizado com sucesso", produtoService.update(produto));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) throws VendasException {
        produtoService.delete(id);
    }

    @GetMapping(value = {"/", ""})
    public Response<Produto> find(Produto filtro) {
        return new Response<>(produtoService.find(filtro));
    }

}
