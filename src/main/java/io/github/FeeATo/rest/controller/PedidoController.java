package io.github.FeeATo.rest.controller;

import io.github.FeeATo.domain.entity.Pedido;
import io.github.FeeATo.rest.dto.PedidoDTO;
import io.github.FeeATo.rest.exception.VendasEnumException;
import io.github.FeeATo.rest.exception.VendasException;
import io.github.FeeATo.rest.exception.VendasRuntimeException;
import io.github.FeeATo.rest.model.Response;
import io.github.FeeATo.rest.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "/api/pedidos")
public class PedidoController {

    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping(value = {"/", ""})
    public Response<PedidoDTO> createPedido(@RequestBody PedidoDTO pedido) {
        try {
            return new Response<PedidoDTO>("Pedido criado com sucesso", pedidoService.salvar(pedido));
        }  catch (VendasRuntimeException vne) {
            if (vne.getExceptionEnum().equals(VendasEnumException.NOT_FOUND)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, vne.getMessage());
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, vne.getMessage());
            }
        } catch (VendasException ve) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ve.getMessage());
        }
    }


}
