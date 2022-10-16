package br.com.delivery.pidao.controllers;

import br.com.delivery.pidao.entities.ClientOrder;
import br.com.delivery.pidao.repositories.PlaceOrderRepository;
import br.com.delivery.pidao.services.SessionService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class PlaceOrderController {

    private final PlaceOrderRepository placeOrderRepository;

    private SessionService sessionService;

    @PostMapping
    public ResponseEntity<?> saveOrder(@RequestHeader String token, @RequestBody ClientOrder order){
        sessionService.validateToken(token);
        placeOrderRepository.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @GetMapping
    public List<ClientOrder> getAllOrders(@RequestHeader String token){
        sessionService.validateToken(token);
        return placeOrderRepository.findAll();
    }

    @GetMapping("/{OrderId}")
    public ClientOrder getOrderById(@PathVariable Long OrderId,@RequestHeader String token){
        sessionService.validateToken(token);
        return placeOrderRepository
                .findById(OrderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado! :("));
    }

    @DeleteMapping("/{OrderId}")
    public void removeOrder(@PathVariable Long OrderId, @RequestHeader String token){
        sessionService.validateToken(token);
        placeOrderRepository
                .findById(OrderId)
                .map( order -> {
                    placeOrderRepository.delete(order);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado! :("));
    }

    @PutMapping("{OrderId}")
    public void updateOrder(@PathVariable Long OrderId,@RequestHeader String token, @RequestBody ClientOrder clientOrder){
        sessionService.validateToken(token);
        placeOrderRepository
                .findById(OrderId)
                .map(order -> {
                    order.setName(clientOrder.getName());
                    order.setValue(clientOrder.getValue());
                    order.setStatus(clientOrder.getStatus());
                    order.setComment(clientOrder.getComment());
                    return placeOrderRepository.save(order);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado! :("));
    }

}
