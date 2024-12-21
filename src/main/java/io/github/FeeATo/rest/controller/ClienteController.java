package io.github.FeeATo.rest.controller;

import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.rest.dto.ClienteDTO;
import io.github.FeeATo.rest.dto.Response;
import io.github.FeeATo.rest.service.imp.ClienteServiceImp;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteServiceImp clienteServiceImp;

    public ClienteController(ClienteServiceImp clienteServiceImp) { //o spring vai injetar automaticamente
        this.clienteServiceImp = clienteServiceImp;
    }


    @GetMapping(value = "/hello/{nome}")
    public String helloCliente(@PathVariable("nome") String nomeCliente) {
        return String.format("Hello %s", nomeCliente);
    }

    @GetMapping(value = "/{id}") //msm coisa que as duas de cima juntas
    public Response<ClienteDTO> getClienteById(@PathVariable("id") Integer id) {  /*se o nome do parametro é o msm do path, não precisa desse PathVariable*/
        return new Response<>(ClienteDTO.convertDTO(clienteServiceImp.getClienteById(id)));
    }

    @PostMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    public Response<ClienteDTO> save(@RequestBody @Valid Cliente cliente) {
        return new Response<>("Cliente cadastrado com sucesso", ClienteDTO.convertDTO(clienteServiceImp.salvar(cliente)));
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Response<ClienteDTO> delete(@PathVariable Integer id) {
        clienteServiceImp.removerCliente(id);
        return new Response<>("Cliente removido com sucesso");
    }

    @PutMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.OK)
    public Response<ClienteDTO> update(@RequestBody Cliente cliente) {
        return new Response<>("Cliente atualizado com sucesso", ClienteDTO.convertDTO(clienteServiceImp.update(cliente)));
    }

    @GetMapping(value = {"/", ""})
    public Response<List<ClienteDTO>> find(Cliente filtro) {
        return new Response<List<ClienteDTO>>(ClienteDTO.convertDTO(clienteServiceImp.find(filtro)));
    }
}
