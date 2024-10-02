package io.github.FeeATo.rest.controller;

import io.github.FeeATo.domain.entity.Cliente;
import io.github.FeeATo.domain.repository.ClientesRepository;
import io.github.FeeATo.rest.controller.exception.VendasException;
import io.github.FeeATo.rest.controller.model.Response;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteService clienteService;
    private ClientesRepository clientesRepository;

    public ClienteController(ClientesRepository clientesRepository, ClienteService clienteService) { //o spring vai injetar automaticamente
        this.clientesRepository = clientesRepository;
        this.clienteService = clienteService;
    }

    //    @RequestMapping(
//            value = {"/hello/{nome}", "/hell/{nome}"}, //pode ser mais de um endpoint aqui
//            method = RequestMethod.GET,
//            consumes = {"application/json", "application/xml"}, //dá pra fazer isso; O padrão é json. Pra GET, não precisa disso.
//            produces = {"application/json", "application/xml"} //dá pra fazer isso tbm
//    )
    @GetMapping(value = "/hello/{nome}") //msm coisa que as duas de cima juntas
    @ResponseBody //indica que o resultado é o corpo da resposta
    public String helloCliente(@PathVariable("nome") String nomeCliente) {
        return String.format("Hello %s", nomeCliente);
    }

    @GetMapping(value = "/{id}") //msm coisa que as duas de cima juntas
    @ResponseBody //indica que o resultado é o corpo da resposta
    public ResponseEntity<Response> getClienteById(@PathVariable("id") Integer id) {  /*se o nome do parametro é o msm do path, não precisa desse PathVariable*/
        try {
            return ResponseEntity.ok(clienteService.getClienteById(id));
        } catch (VendasException ve) {
            if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.BAD_REQ)) {
                return new ResponseEntity<>(new Response(ve.getMessage()), HttpStatus.BAD_REQUEST);
            } else if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.NOT_FOUND)) {
                return new ResponseEntity<>(new Response(ve.getMessage()), HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<>(new Response(ve.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping(value = {"/", ""})
    @ResponseBody
    public ResponseEntity<Response> save(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(clienteService.save(cliente));
        } catch (VendasException ve) {
            if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.BAD_REQ)) {
                return new ResponseEntity<>(new Response(ve.getMessage()), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(new Response(ve.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @DeleteMapping(value = {"/{id}"})
    @ResponseBody
    public ResponseEntity<Response> delete(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(clienteService.delete(id));
        } catch (VendasException ve) {
            if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.BAD_REQ)) {
                return new ResponseEntity<>(new Response(ve.getMessage()), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(new Response(ve.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PutMapping(value = {"/", ""})
    @ResponseBody
    public ResponseEntity<Response> update(@RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(clienteService.update(cliente));
        } catch (VendasException ve) {
            if (ve.getExceptionEnum().equals(VendasException.VendasExceptionEnum.BAD_REQ)) {
                return new ResponseEntity<>(new Response(ve.getMessage()), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(new Response(ve.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @GetMapping("")
    public ResponseEntity<Response> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example example = Example.of(filtro, matcher);
        List<Cliente> clienteList = clientesRepository.findAll(example);
        return ResponseEntity.ok(new Response(clienteList));
    }
}
