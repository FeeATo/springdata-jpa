package io.github.FeeATo.rest.controller;

import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.domain.entity.Produto;
import io.github.FeeATo.rest.exception.VendasEnumException;
import io.github.FeeATo.rest.exception.VendasException;
import io.github.FeeATo.rest.exception.VendasRuntimeException;
import io.github.FeeATo.rest.model.Response;
import io.github.FeeATo.rest.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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
    public Response<Produto> getProdutoById(@PathVariable Integer id){
        try {
            return new Response<>(produtoService.getProdutoById(id));
        } catch (VendasRuntimeException vne) {
            if (vne.getExceptionEnum().equals(VendasEnumException.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, vne.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, vne.getMessage());
            }
        } catch (VendasException ve) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ve.getMessage());
        }
    }

    @PostMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Produto> save(@RequestBody Produto produto) {
        try {
            return new Response<>("Produto cadastrado com sucesso", produtoService.save(produto));
        } catch (VendasRuntimeException vne) {
            if (vne.getExceptionEnum().equals(VendasEnumException.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, vne.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, vne.getMessage());
            }
        } /*catch (VendasException ve) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ve.getMessage());
        }*/
    }

    @PutMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.OK)
    public Response<Produto> update(@RequestBody Produto produto) {
        try {
            return new Response<>("Produto atualizado com sucesso", produtoService.update(produto));
        } catch (VendasRuntimeException vne) {
            if (vne.getExceptionEnum().equals(VendasEnumException.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, vne.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, vne.getMessage());
            }
        } /*catch (VendasException ve) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ve.getMessage());
        }*/
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) throws VendasException {
        try {
            produtoService.delete(id);
        } catch (VendasRuntimeException vne) {
            if (vne.getExceptionEnum().equals(VendasEnumException.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, vne.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, vne.getMessage());
            }
        } /*catch (VendasException ve) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ve.getMessage());
        }*/
    }

    @GetMapping(value = {"/", ""})
    public Response<Produto> find(Produto filtro) {
        return new Response<>(produtoService.find(filtro));
    }

}
