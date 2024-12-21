package io.github.FeeATo.rest.controller;

import io.github.FeeATo.rest.dto.PedidoDTO;
import io.github.FeeATo.rest.exception.VendasException;
import io.github.FeeATo.rest.dto.Response;
import io.github.FeeATo.rest.service.PedidoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping(value = {"/", ""})
    public Response<PedidoDTO> createPedido(@RequestBody PedidoDTO pedidoDTO) throws VendasException {
        return new Response<PedidoDTO>("Pedido criado com sucesso", PedidoDTO.convertDTO(pedidoService.salvar(pedidoDTO)));
    }

    @GetMapping(value = "/{id}")
    public Response<PedidoDTO> getPedidoById(@PathVariable Integer id) {
        return new Response<PedidoDTO>(PedidoDTO.convertDTO(pedidoService.getPedidoById(id)));
    }


    @PatchMapping(value = {"/{id}"})
    public Response<PedidoDTO> cancelarPedido(@PathVariable Integer id) {
        return new Response<PedidoDTO>(PedidoDTO.convertDTO(pedidoService.cancelarPedido(id)));
    }

}
