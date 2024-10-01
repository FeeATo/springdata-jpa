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
            clientesRepository.salvar(new Cliente("Douglas"));
            clientesRepository.salvar(new Cliente("Miguel"));

            List<Cliente> clienteList = clientesRepository.obterTodos();
            clienteList.forEach(System.out::println);

            System.out.println("---- Atualizando clientes ----");
            clienteList.forEach(c->{
                c.setNome(c.getNome().concat(" atualizado"));
                clientesRepository.atualizar(c);
            });

            clienteList = clientesRepository.obterTodos();
            clienteList.forEach(System.out::println);

            System.out.println("---- Buscando clientes por nome ----");
            clientesRepository.listarPorNome("Mi").forEach(System.out::println);

            System.out.println("---- Deletando todos ----");
            clienteList.forEach(clientesRepository::deletar);

            clienteList = clientesRepository.obterTodos();
            System.out.println("---- Resultado ----");
            clienteList.forEach(System.out::println);


        };

    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}