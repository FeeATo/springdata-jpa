package io.github.FeeATo;

import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.domain.entity.ItemPedido;
import io.github.FeeATo.domain.entity.Pedido;
import io.github.FeeATo.domain.entity.Produto;
import io.github.FeeATo.domain.enums.CadastroStatus;
import io.github.FeeATo.domain.enums.PedidoStatus;
import io.github.FeeATo.domain.repository.ClientesRepository;
import io.github.FeeATo.domain.repository.PedidoRepository;
import io.github.FeeATo.domain.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class VendasApplication {


    @Bean
    public CommandLineRunner init(@Autowired ClientesRepository clientesRepository,
                                  @Autowired PedidoRepository pedidoRepository,
                                  @Autowired ProdutosRepository produtosRepository) {
        return args -> {
            System.out.println("---- Salvando clientes ----");
            clientesRepository.save(new Cliente("Douglas", "51099188806", null));
            Cliente miguel = new Cliente("Miguel", "06692152879", null);
            miguel = clientesRepository.save(miguel);

            Produto produto = new Produto("IPhone 17", BigDecimal.valueOf(500.0), CadastroStatus.ATIVO);
            produto = produtosRepository.save(produto);


            Pedido pedido = new Pedido(miguel, LocalDate.now(), PedidoStatus.CONFIRMADO);
            pedido.addItemPedido(new ItemPedido(pedido, produto.getPreco(), produto, 1));
            pedido = pedidoRepository.save(pedido);


//            Pedido p = new Pedido();
//            p.setCliente(miguel);
//            p.setDataPedido(LocalDate.now());
//            p.setTotal(BigDecimal.valueOf(100));
//            pedidoRepository.save(p);

        };

    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}