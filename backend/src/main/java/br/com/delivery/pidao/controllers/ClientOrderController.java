package br.com.delivery.pidao.controllers;

import br.com.delivery.pidao.entities.ClientOrder;
import br.com.delivery.pidao.repositories.ClientOrderRepository;
import br.com.delivery.pidao.services.ClientOrderService;
import br.com.delivery.pidao.services.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class ClientOrderController {
    private ClientOrderRepository clientOrderRepository;

    private SessionService sessionService;

    private ClientOrderService clientOrderService;

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestHeader String token, @RequestBody ClientOrder order){
        sessionService.validateToken(token);
        clientOrderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public ResponseEntity<?> getAllOrders(@RequestHeader String token, @RequestHeader String clientIdentifier){

        try {
            //sessionService.validateToken(token);
            return ResponseEntity.ok(clientOrderService.findAllByClientIdentifier(clientIdentifier));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }



    }

    @GetMapping("/{OrderId}")
    public ClientOrder getOrderById(@PathVariable Long OrderId,@RequestHeader String token){
        sessionService.validateToken(token);
        return clientOrderRepository
                .findById(OrderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado! :("));
    }

    @DeleteMapping("/{OrderId}")
    public void removeOrder(@PathVariable Long OrderId, @RequestHeader String token){
        sessionService.validateToken(token);
        clientOrderRepository
                .findById(OrderId)
                .map( order -> {
                    clientOrderRepository.delete(order);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado! :("));
    }

    @PutMapping("{OrderId}")
    public void updateOrder(@PathVariable Long OrderId,@RequestHeader String token, @RequestBody ClientOrder clientOrder){
        sessionService.validateToken(token);
        clientOrderRepository
                .findById(OrderId)
                .map(order -> {
                    order.setName(clientOrder.getName());
                    order.setValue(clientOrder.getValue());
                    order.setStatus(clientOrder.getStatus());
                    order.setComment(clientOrder.getComment());
                    return clientOrderRepository.save(order);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado! :("));
    }

}
