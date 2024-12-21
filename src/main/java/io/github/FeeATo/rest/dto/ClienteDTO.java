package io.github.FeeATo.rest.dto;

import io.github.FeeATo.domain.entity.Cliente;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Integer id;
    @NotEmpty
    private String nome;
    @CPF
    private String cpf;

    public static ClienteDTO convertDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getId(),
                cliente.getNome(),
                cliente.getCpf());
    }

    public static List<ClienteDTO> convertDTO(List<Cliente> clienteList) {
        return clienteList.stream().map(ClienteDTO::convertDTO).collect(Collectors.toList());
    }
}
