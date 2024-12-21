package io.github.FeeATo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.FeeATo.domain.enums.CadastroStatus;
import io.github.FeeATo.rest.dto.ClienteDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Set;

//ao rodar o projeto, ele cria no banco a entidade se n tiver lá

@Entity //diz que isso é uma entidade/tabela para o JPA
@Table(name = "cliente") //não precisaria pq o nome é igual, mas coloquei pra mostrar q dá
@Getter
@Setter
@AllArgsConstructor
public class Cliente {

    @Id //define a primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") //não precisa colocar pq o nome do campo é igual
    private Integer id;

    @Column(name = "nome", length = 100/*, updatable = false*/)
    @NotEmpty(message = "Campo nome é obrigatório")
    private String nome;

    @Column(name = "cpf", length = 11)
    @NotEmpty
    @CPF(message = "CPF inválido")
    private String cpf;

    @Enumerated(EnumType.STRING)
    private CadastroStatus cadastroStatus;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente") //fala qual coluna da tabela de Pedido que está associado ao Cliente
    private Set<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(Integer id) {
        this.id = id;
    }

    public Cliente(String nome, String cpf, Set<Pedido> pedidos) {
        this.nome = nome;
        this.cpf = cpf;
        this.pedidos = pedidos;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(ClienteDTO clienteDTO) {
        this.id = clienteDTO.getId();
        this.nome = clienteDTO.getNome();
        this.cpf = clienteDTO.getCpf();
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
