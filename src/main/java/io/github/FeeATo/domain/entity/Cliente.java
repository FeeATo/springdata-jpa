package io.github.FeeATo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

//ao rodar o projeto, ele cria no banco a entidade se n tiver lá

@Entity //diz que isso é uma entidade/tabela para o JPA
@Table(name = "cliente") //não precisaria pq o nome é igual, mas coloquei pra mostrar q dá
@Getter
@Setter
public class Cliente {

    @Id //define a primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id") //não precisa colocar pq o nome do campo é igual
    private Integer id;

    @Column(name = "nome", length = 100/*, updatable = false*/)
    @NotEmpty(message = "Campo nome é obrigatório")
    private String nome;

    @Column(name = "cpf", length = 11)
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente") //fala qual coluna da tabela de Pedido que está associado ao Cliente
    private Set<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
