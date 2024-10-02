package io.github.FeeATo;

import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.domain.entity.Pedido;
import io.github.FeeATo.domain.entity.Produto;
import io.github.FeeATo.domain.repository.ClientesRepository;
import io.github.FeeATo.domain.repository.PedidoRepository;
import io.github.FeeATo.domain.repository.ProdutosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class VendasApplication {


    @Bean
    public CommandLineRunner init(@Autowired ClientesRepository clientesRepository,
                                  @Autowired PedidoRepository pedidoRepository,
                                  @Autowired ProdutosRepository produtosRepository) {
        return args -> {
            System.out.println("---- Salvando clientes ----");
            clientesRepository.save(new Cliente("Douglas"));
            Cliente miguel = new Cliente("Miguel");
            clientesRepository.save(miguel);

            produtosRepository.save(new Produto("IPhone 17", BigDecimal.valueOf(500.0)));


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