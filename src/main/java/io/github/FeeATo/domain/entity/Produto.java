package io.github.FeeATo.domain.entity;

import io.github.FeeATo.domain.enums.CadastroStatus;
import io.github.FeeATo.validation.NotZeroNumber;
import io.github.FeeATo.validation.Preco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "produto")
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    @NotEmpty(message = "Descrição é obrigatória")
    private String descricao;
    @Column
    @NotZeroNumber(message= "Preço é obrigatório")
    @Preco
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private CadastroStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "produto")
    private List<ItemPedido> itemPedidoList;

    public Produto() {
    }



    public Produto(Integer id) {
        this.id = id;
    }

    public Produto(String descricao, BigDecimal preco) {
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto(Integer id, String descricao, BigDecimal preco) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
    }

    public Produto(String descricao, BigDecimal preco, CadastroStatus status) {
        this.descricao = descricao;
        this.preco = preco;
        this.status = status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Produto produto = (Produto) object;
        return Objects.equals(id, produto.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descricao, preco);
    }
}
