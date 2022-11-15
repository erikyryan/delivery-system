package br.com.delivery.pidao.controllers;

import br.com.delivery.pidao.entities.dto.OrderDTO;
import br.com.delivery.pidao.services.ClientOrderService;
import br.com.delivery.pidao.services.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class ClientOrderController {

    private SessionService sessionService;

    private ClientOrderService clientOrderService;

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody OrderDTO orderDTO, @RequestHeader("token") final String token) {
        try {
            sessionService.validateToken(token);
            return ResponseEntity.ok(clientOrderService.addClientOrder(orderDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/client/{clientIdentifier}")
    public ResponseEntity<?> getAllOrders(@PathVariable String clientIdentifier, @RequestHeader("token") final String token) {

        try {
            sessionService.validateToken(token);
            return ResponseEntity.ok(clientOrderService.findAllByClientIdentifier(clientIdentifier));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/{orderIdentifier}")
    public ResponseEntity<?> getOrderByIdentifier(@PathVariable String orderIdentifier, @RequestHeader String token) {
        try {
            sessionService.validateToken(token);
            return ResponseEntity.ok(clientOrderService.getClientOrderByClientIdentifier(orderIdentifier));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body((ex.getMessage()));
        }
    }

    @DeleteMapping("/{orderIdentifier}")
    public ResponseEntity<?> removeOrder(@PathVariable String orderIdentifier, @RequestHeader String token) {
        try {
            sessionService.validateToken(token);
            clientOrderService.removeClientOrder(orderIdentifier);
            return ResponseEntity.ok().build();
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("{orderIdentifier}")
    public ResponseEntity<?> updateOrder(@PathVariable String orderIdentifier, @RequestHeader String token, @RequestBody OrderDTO orderDTO){
        try { 
            sessionService.validateToken(token);
            return ResponseEntity.ok(clientOrderService.updateClientOrder(orderDTO, orderIdentifier));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
