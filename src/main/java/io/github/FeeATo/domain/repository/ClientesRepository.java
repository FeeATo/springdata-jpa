package io.github.FeeATo.domain.repository;

import io.github.FeeATo.domain.entity.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository //ele é um component e também essa classe  tem o exception translator
public class ClientesRepository {

    @Autowired
    private EntityManager entityManager; //faz todas as operações


    @Transactional //precisa de uma transação pra persistir na base de dados
    public Cliente salvar(Cliente cliente) { //quando a classe entidade não foi salva, ela é uma entidade TRANSIENTE. (o JPA não está gerenciando ela ainda)
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional(readOnly = true)
    public List<Cliente> obterTodos() {
        return entityManager.createQuery("from Cliente", Cliente.class).getResultList();
    }

    @Transactional(readOnly = true) //é mais optimizado
    public List<Cliente> listarPorNome(String nome) {
        String jpql = "SELECT c FROM Cliente c WHERE c.nome = :nome";
        TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
        query.setParameter("nome", "%"+nome+"%");
        return query.getResultList();
    }

    @Transactional
    public Cliente atualizar(Cliente cliente) {
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente) {
        if (!entityManager.contains(cliente)) {
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void deletar(Integer id) {
        Cliente cliente = entityManager.find(Cliente.class, id); //busca pela chave primária
        entityManager.remove(cliente);
    }

}
