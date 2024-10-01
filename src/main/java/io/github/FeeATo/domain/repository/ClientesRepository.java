package io.github.FeeATo.domain.repository;

import io.github.FeeATo.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeLike(String nome);
    List<Cliente> findByNomeLikeOrIdOrderById(String nome, Integer id);
    Cliente findOneByNome(String nome); //se tiver mais q um, vai dar exception

    boolean existsByNome(String nome);
}
