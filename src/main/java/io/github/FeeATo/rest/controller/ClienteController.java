package io.github.FeeATo.rest.controller;

import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.rest.dto.Response;
import io.github.FeeATo.rest.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) { //o spring vai injetar automaticamente
        this.clienteService = clienteService;
    }

    //    @RequestMapping(
//            value = {"/hello/{nome}", "/hell/{nome}"}, //pode ser mais de um endpoint aqui
//            method = RequestMethod.GET,
//            consumes = {"application/json", "application/xml"}, //dá pra fazer isso; O padrão é json. Pra GET, não precisa disso.
//            produces = {"application/json", "application/xml"} //dá pra fazer isso tbm
//    )
    @GetMapping(value = "/hello/{nome}") //msm coisa que as duas de cima juntas
    public String helloCliente(@PathVariable("nome") String nomeCliente) {
        return String.format("Hello %s", nomeCliente);
    }

    @GetMapping(value = "/{id}") //msm coisa que as duas de cima juntas
    public Response<Cliente> getClienteById(@PathVariable("id") Integer id) {  /*se o nome do parametro é o msm do path, não precisa desse PathVariable*/
        return new Response<>(clienteService.getClienteById(id));
    }

    @PostMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    public Response<Cliente> save(@RequestBody @Valid Cliente cliente) {
        return new Response<>("Cliente cadastrado com sucesso", clienteService.save(cliente));
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Response<Cliente> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return new Response<>("Cliente deletado com sucesso");
    }

    @PutMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.OK)
    public Response<Cliente> update(@RequestBody Cliente cliente) {
        return new Response<>("Cliente atualizado com sucesso", clienteService.update(cliente));
    }

    @GetMapping(value = {"/", ""})
    public Response<List<Cliente>> find(Cliente filtro) {
        return new Response<List<Cliente>>(clienteService.find(filtro));
    }
}
