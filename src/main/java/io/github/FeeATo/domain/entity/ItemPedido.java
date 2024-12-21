package io.github.FeeATo.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "item_pedido")
@Getter
@Setter
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;
    @Column(name = "preco")
    private BigDecimal preco;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column
    private Integer quantidade;

    public ItemPedido() {
    }

    public ItemPedido(Pedido pedido, BigDecimal preco, Produto produto, Integer quantidade) {
        this.pedido = pedido;
        this.produto = produto;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public ItemPedido(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }
}
