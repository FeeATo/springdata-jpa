package io.github.FeeATo;

import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.domain.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class VendasApplication {


    @Bean
    public CommandLineRunner init(@Autowired ClientesRepository clientesRepository) {
        return args -> {
            System.out.println("---- Salvando clientes ----");
            clientesRepository.save(new Cliente("Douglas"));
            clientesRepository.save(new Cliente("Miguel"));

            boolean existe = clientesRepository.existsByNome("Douglas");
            System.out.println(existe);
        };

    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}