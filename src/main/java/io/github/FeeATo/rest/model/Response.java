package io.github.FeeATo.rest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.domain.entity.Produto;

import java.util.List;

public class Response<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String mensagem;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<T> results;


    public Response(String mensagem) {
        this.mensagem = mensagem;
    }

    public Response(T obj) {
        this.results = List.of(obj);
    }

    public Response(String mensagem, T obj) {
        this.mensagem = mensagem;
        this.results = List.of(obj);
    }

    public Response(List<T> elements) {
        this.results = elements;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
