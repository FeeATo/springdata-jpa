package io.github.FeeATo.rest.controller;

import io.github.FeeATo.domain.entity.Produto;
import io.github.FeeATo.rest.exception.VendasException;
import io.github.FeeATo.rest.service.ProdutoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Produto getProdutoById(@PathVariable Integer id){
        try {
            return produtoService.getProdutoById(id);
        } catch (VendasException ve) {
            if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.BAD_REQ)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ve.getMessage());
            } else if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ve.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ve.getMessage());
            }
        }
    }

    @PostMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto) {
        try {
            return produtoService.save(produto);
        } catch (VendasException ve) {
            if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.BAD_REQ)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ve.getMessage());
            } else if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ve.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ve.getMessage());
            }
        }
    }

    @PutMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.OK)
    public Produto update(@RequestBody Produto produto) {
        try {
            return produtoService.update(produto);
        } catch (VendasException ve) {
            if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.BAD_REQ)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ve.getMessage());
            } else if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ve.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ve.getMessage());
            }
        }
    }

    @DeleteMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Integer id) throws VendasException {
        try {
            produtoService.delete(id);
        } catch (VendasException ve) {
            if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.BAD_REQ)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ve.getMessage());
            } else if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ve.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ve.getMessage());
            }
        }
    }

}
