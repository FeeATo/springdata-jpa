package io.github.FeeATo.rest.service;

import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.domain.repository.ClientesRepository;
import io.github.FeeATo.rest.exception.VendasEnumException;
import io.github.FeeATo.rest.exception.VendasException;
import io.github.FeeATo.rest.exception.VendasRuntimeException;
import io.github.FeeATo.rest.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClientesRepository clientesRepository;

    public Cliente save(Cliente cliente) {
        if (cliente.getId() != null) {
            throw new VendasRuntimeException("Cliente já cadastrado");
        }

        return clientesRepository.save(cliente);
    }

    public void delete(Integer id) {
        Optional<Cliente> cliente = clientesRepository.findById(id);
        if (cliente.isPresent()) {
            clientesRepository.delete(cliente.get());
        } else {
            throw new VendasRuntimeException("Cliente não encontrado");
        }
    }

    public Cliente update(Cliente cliente) {
        if (cliente.getId() == null) {
            throw new VendasRuntimeException("O cliente precisa ter um ID para poder ser atualizado");
        }
        Optional<Cliente> clienteOptional = clientesRepository.findById(cliente.getId());
        if (clienteOptional.isPresent()) {
            return clientesRepository.save(cliente);
        }
        throw new VendasRuntimeException("Cliente não encontrado");

    }

    public Cliente getClienteById(Integer id) {
        if (id == null) {
            throw new VendasRuntimeException("ID não pode ser nulo");
        }
        return clientesRepository
                .findById(id)
                .orElseThrow(()-> new VendasRuntimeException("Cliente não encontrado", VendasEnumException.NOT_FOUND));
    }

    public List<Cliente> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        List<Cliente> clientes = clientesRepository.findAll(example);
        return clientes;
    }
}
