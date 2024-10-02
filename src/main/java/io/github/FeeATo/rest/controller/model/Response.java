package io.github.FeeATo.rest.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.FeeATo.domain.entity.Cliente;

import java.util.List;

public class Response {

    @JsonProperty
    private String mensagem;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Cliente> clientes;

    public Response(String mensagem) {
        this.mensagem = mensagem;
    }

    public Response(Cliente clientes) {
        this.clientes = List.of(clientes);
    }

    public Response(String mensagem, Cliente clientes) {
        this.mensagem = mensagem;
        this.clientes = List.of(clientes);
    }

    public Response(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Cliente clientes) {
        this.clientes = List.of(clientes);
    }
}
