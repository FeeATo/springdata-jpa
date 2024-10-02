package io.github.FeeATo.domain.repository;

import io.github.FeeATo.domain.entity.Cliente;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientesRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "select c from Cliente c where c.nome like %:nome%", nativeQuery = false)
        //pode ser HQL ou SQL nativo. O nativeQuery indica se é nativa ou não
    List<Cliente> encontrarPorNome(@Param("nome") String nome);

    @Query("delete from Cliente c where c.nome like :nome")
    @Modifying
        //tem que colocar isso pra dizer que vai rolar uma edição
    void deleteByNome(String nome);

    List<Cliente> findByNomeLikeOrIdOrderById(String nome, Integer id);

    Cliente findOneByNome(String nome); //se tiver mais de um, vai dar exception por causa do "findOne"

    boolean existsByNome(String nome);

    @Query("select c from Cliente c LEFT JOIN FETCH c.pedidos where c.id =:id")
    Cliente findClienteFetchPedidos(@Param("id") Integer id);
}
