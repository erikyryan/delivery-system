package br.com.delivery.pidao.controller;

import br.com.delivery.pidao.domain.order.dto.OrderDTO;
import br.com.delivery.pidao.domain.order.CustomerOrderService;
import br.com.delivery.pidao.domain.login.SessionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/order")
public class CustomerOrderController {

    private SessionService sessionService;

    private CustomerOrderService customerOrderService;

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestBody final OrderDTO orderDTO, @RequestHeader("token") final String token) {
        try {
            sessionService.validateToken(token);
            return ResponseEntity.ok(customerOrderService.addCustomerOrder(orderDTO));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/customer/{customerIdentifier}")
    public ResponseEntity<?> getAllOrders(@PathVariable final String customerIdentifier, @RequestHeader("token") final String token) {

        try {
            sessionService.validateToken(token);
            return ResponseEntity.ok(customerOrderService.findAllByCustomerIdentifier(customerIdentifier));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }

    @GetMapping("/{orderIdentifier}")
    public ResponseEntity<?> getOrderByIdentifier(@PathVariable final String orderIdentifier, @RequestHeader final String token) {
        try {
            sessionService.validateToken(token);
            return ResponseEntity.ok(customerOrderService.getClientOrderByCustomerIdentifier(orderIdentifier));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body((ex.getMessage()));
        }
    }

    @DeleteMapping("/{orderIdentifier}")
    public ResponseEntity<?> removeOrder(@PathVariable final String orderIdentifier, @RequestHeader final String token) {
        try {
            sessionService.validateToken(token);
            customerOrderService.removeCustomerOrder(orderIdentifier);
            return ResponseEntity.ok().build();
        } catch(Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PutMapping("{orderIdentifier}")
    public ResponseEntity<?> updateOrder(@PathVariable final String orderIdentifier, @RequestHeader final String token, @RequestBody final OrderDTO orderDTO){
        try {
            sessionService.validateToken(token);
            return ResponseEntity.ok(customerOrderService.updateCustomerOrder(orderDTO, orderIdentifier));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
