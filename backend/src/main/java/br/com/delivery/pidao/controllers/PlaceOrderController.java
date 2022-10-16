package br.com.delivery.pidao.controllers;

import br.com.delivery.pidao.entities.ClientOrder;
import br.com.delivery.pidao.repositories.PlaceOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/realizarpedido")
public class PlaceOrderController {

    private final ClientOrder clientOrder;
    private final PlaceOrderRepository placeOrderRepository;
        

    @Autowired
    public PlaceOrderController(ClientOrder clientOrder){
        this.clientOrder = clientOrder;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientOrder saveOrder(@RequestBody ClientOrder order){
        return placeOrderRepository.save(clientOrder);
    }

    @GetMapping
    public List<ClientOrder> getAllOrders(){
        return placeOrderRepository.findAll();
    }

    @GetMapping("{Id_Pedido}")
    public ClientOrder getOrderById(@PathVariable Long OrderId){
        return placeOrderRepository
                .findById(OrderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado! :("));
    }

    @DeleteMapping("{Ìd_Pedido)")
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
                    return placeOrderRepository.save(orderAtualizacao);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado! :("));
    }

}
