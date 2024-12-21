package io.github.FeeATo.rest.service.imp;

import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.domain.enums.CadastroStatus;
import io.github.FeeATo.domain.repository.ClientesRepository;
import io.github.FeeATo.rest.dto.ClienteDTO;
import io.github.FeeATo.rest.exception.VendasEnumException;
import io.github.FeeATo.rest.exception.VendasRuntimeException;
import io.github.FeeATo.rest.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImp implements ClienteService {

    @Autowired
    ClientesRepository clientesRepository;

    @Override
    public Cliente salvar(ClienteDTO clienteDTO) {
        return salvar(new Cliente(clienteDTO));
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        if (cliente.getId() != null) {
            throw new VendasRuntimeException("Cliente já cadastrado");
        }

        return clientesRepository.save(cliente);
    }

    @Override
    public Cliente removerCliente(Integer id) {
        Optional<Cliente> cliente = clientesRepository.findById(id);
        if (cliente.isPresent()) {
            return removerCliente(cliente.get());
        } else {
            throw new VendasRuntimeException("Cliente não encontrado");
        }
    }

    @Override
    public Cliente removerCliente(Cliente cliente) {
        cliente.setCadastroStatus(CadastroStatus.DESATIVADO);
        cliente = clientesRepository.save(cliente);
        return cliente;
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

    @Override
    public Cliente getClienteById(Integer id) {
        if (id == null) {
            throw new VendasRuntimeException("ID não pode ser nulo");
        }
        return clientesRepository
                .findById(id)
                .orElseThrow(()-> new VendasRuntimeException("Cliente não encontrado", VendasEnumException.NOT_FOUND));
    }

    @Override
    public List<Cliente> getPedidosByClienteId(Integer produtoId) {
        return null;
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
