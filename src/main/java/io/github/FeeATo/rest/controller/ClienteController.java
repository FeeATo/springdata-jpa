package io.github.FeeATo.rest.controller;

import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.rest.exception.VendasException;
import io.github.FeeATo.rest.model.Response;
import io.github.FeeATo.rest.service.ClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public Response getClienteById(@PathVariable("id") Integer id) {  /*se o nome do parametro é o msm do path, não precisa desse PathVariable*/
        try {
            return clienteService.getClienteById(id);
        } catch (VendasException ve) {
            if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.BAD_REQ)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ve.getMessage());
            } else if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, ve.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ve.getMessage());
            }
        }
    }

    @PostMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.CREATED)
    public Response save(@RequestBody Cliente cliente) {
        try {
            return clienteService.save(cliente);
        } catch (VendasException ve) {
            if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.BAD_REQ)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ve.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ve.getMessage());
            }
        }
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public Response delete(@PathVariable Integer id) {
        try {
            return clienteService.delete(id);
        } catch (VendasException ve) {
            if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.BAD_REQ)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ve.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ve.getMessage());
            }
        }
    }

    @PutMapping(value = {"/", ""})
    @ResponseStatus(HttpStatus.OK)
    public Response update(@RequestBody Cliente cliente) {
        try {
            return clienteService.update(cliente);
        } catch (VendasException ve) {
            if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.BAD_REQ)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ve.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ve.getMessage());
            }
        }
    }

    @GetMapping(value = {"/", ""})
    public Response find(Cliente filtro) {
        return clienteService.find(filtro);
    }
}
