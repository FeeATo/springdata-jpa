package io.github.FeeATo.domain.entity;

import io.github.FeeATo.domain.enums.PedidoStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "pedido")
@Getter
@Setter
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Column(name = "data_pedido")
    private LocalDate dataPedido;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itensPedido;
    @Enumerated(EnumType.STRING)
    private PedidoStatus pedidoStatus;

    public Pedido(Cliente cliente, LocalDate dataPedido, PedidoStatus pedidoStatus) {
        this.cliente = cliente;
        this.dataPedido = dataPedido;
        this.itensPedido = new ArrayList<>();
        this.pedidoStatus = pedidoStatus;
    }

    public void addItemPedido(ItemPedido itemPedido) {
        this.itensPedido.add(itemPedido);
    }

    public Pedido() {
        dataPedido = LocalDate.now();
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", dataPedido=" + dataPedido +
                '}';
    }
}
