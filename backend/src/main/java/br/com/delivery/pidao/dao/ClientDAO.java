package br.com.delivery.pidao.dao;

import java.util.Optional;
import br.com.delivery.pidao.repositories.ClientRepository;
import br.com.delivery.pidao.entities.Client;

public class ClientDAO {

    private ClientRepository clientRepository;

    public Optional<Client> IsPresent(String socialSecurity){
        return clientRepository.findBySocialSecurity(socialSecurity);
    }
    
}
