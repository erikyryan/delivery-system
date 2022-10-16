package br.com.delivery.pidao.controllers;

import br.com.delivery.pidao.entities.ClientOrder;
import br.com.delivery.pidao.repositories.PlaceOrderRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/realizarpedido")
public class PlaceOrderController {

    private final PlaceOrderRepository placeOrderRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientOrder saveOrder(@RequestHeader String token, @RequestBody ClientOrder order){
        return placeOrderRepository.save(order);
    }

    @GetMapping
    public List<ClientOrder> getAllOrders(@RequestHeader String token){
        return placeOrderRepository.findAll();
    }

    @GetMapping("{OrderId}")
    public ClientOrder getOrderById(@PathVariable Long OrderId,@RequestHeader String token){
        return placeOrderRepository
                .findById(OrderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado! :("));
    }

    @DeleteMapping("/{OrderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOrder(@PathVariable Long OrderId){
        placeOrderRepository
                .findById(OrderId)
                .map( order -> {
                    placeOrderRepository.delete(order);
                    return Void.TYPE;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado! :("));
    }

    @PutMapping("{Id_Pedido}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateOrder(@PathVariable Long OrderId, @RequestBody ClientOrder clientOrder){
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
