package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Address;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    Optional<Address> findByAdressIdentifier(String adressIdentifier);
}
