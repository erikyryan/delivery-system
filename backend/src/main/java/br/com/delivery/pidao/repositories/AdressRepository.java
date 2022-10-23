package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.Adress;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdressRepository extends JpaRepository<Adress, Long> {

    Optional<Adress> findByAdressIdentifier(String adressIdentifier);
}
