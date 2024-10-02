package io.github.FeeATo.rest.controller;

import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.domain.repository.ClientesRepository;
import io.github.FeeATo.rest.controller.exception.VendasException;
import io.github.FeeATo.rest.controller.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    ClientesRepository clientesRepository;

    public Response save(Cliente cliente) throws VendasException {

        if (cliente.getId() != null) {
            throw new VendasException("Cliente já cadastrado");
        }

        return new Response("Cliente cadastrado com sucesso", clientesRepository.save(cliente));
    }

    public Response delete(Integer id) throws VendasException {
        Optional<Cliente> cliente = clientesRepository.findById(id);
        if (cliente.isPresent()) {
            clientesRepository.delete(cliente.get());
            return new Response("Cliente deletado com sucesso");
        } else {
            throw new VendasException("Cliente não encontrado");
        }
    }

    public Response update(Cliente cliente) throws VendasException {
        if (cliente.getId() == null) {
            throw new VendasException("O cliente precisa ter um ID para poder ser atualizado");
        }
        Optional<Cliente> clienteOptional = clientesRepository.findById(cliente.getId());
        if (clienteOptional.isPresent()) {
            cliente = clientesRepository.save(cliente);
            return new Response("Cliente atualizado com sucesso", cliente);
        }
        throw new VendasException("Cliente não encontrado");

    }

    public Response getClienteById(Integer id) throws VendasException {
        if (id == null) {
            throw new VendasException("ID não pode ser nulo");
        }
        return clientesRepository
                .findById(id)
                .map(Response::new)
                .orElseThrow(()-> new VendasException("Cliente não encontrado", VendasException.VendasExceptionEnum.NOT_FOUND));
    }
}
