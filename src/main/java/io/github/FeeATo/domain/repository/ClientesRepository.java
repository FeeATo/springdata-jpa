package io.github.FeeATo.domain.repository;

import io.github.FeeATo.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository //ele é um component e também essa classe  tem o exception translator
public class ClientesRepository {

    private static String INSERT = "insert into cliente (nome) values (?)";
    private static String SELECT_ALL = "select * from cliente";
    private static String UPDATE = "update cliente set nome = ? where id = ?";
    private static String DELETE = "delete from cliente where id = ?";
    private static String SELECT_BY_NAME = "select * from cliente where nome like ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvar(Cliente cliente) {
        jdbcTemplate.update(INSERT, cliente.getNome());
        return cliente;
    }

    public List<Cliente> obterTodos() {
        return jdbcTemplate.query(SELECT_ALL, getClienteRowMapper());
    }

    private static RowMapper<Cliente> getClienteRowMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Cliente(rs.getInt("ID"), rs.getString("NOME"));
            }
        };
    }

    public List<Cliente> listarPorNome(String nome) {
        return jdbcTemplate.query(SELECT_BY_NAME,
                new Object[]{"%"+nome+"%"},
                getClienteRowMapper());
    }

    public Cliente atualizar(Cliente cliente) {
        jdbcTemplate.update(UPDATE, cliente.getNome(), cliente.getId());
        return cliente;
    }

    public void deletar(Cliente cliente) {
        deletar(cliente.getId());
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, id);
    }

}
