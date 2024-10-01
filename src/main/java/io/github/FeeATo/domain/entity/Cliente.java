package io.github.FeeATo.domain.entity;

import jakarta.persistence.*;

//ao rodar o projeto, ele cria no banco a entidade se n tiver lá

@Entity //diz que isso é uma entidade/tabela para o JPA
@Table(name = "CLIENTE") //não precisaria pq o nome é igual, mas coloquei pra mostrar q dá
public class Cliente {

    @Id //define a primary key
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID") //não precisa colocar pq o nome do campo é igual
    private Integer id;

    @Column(name = "nome", length = 100/*, updatable = false*/)
    private String nome;

    public Cliente() {
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
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
