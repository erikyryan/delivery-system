package br.com.delivery.pidao.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.delivery.pidao.entities.Users;
import br.com.delivery.pidao.enums.UserTypeEnum;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long>{
    
    Optional<Users> findByEmailAndPassword(String email, String password);

    Optional<Users> findBySocialsSecurity(String socialSecurity);

    Optional<Users> findByEmail(String email);

    Optional<Users> findByUsersdentifier(String Usersdentifier);

    Optional<Users> findByEmailAndType(String email, UserTypeEnum type);

    Optional<Users> findByEmailAndTypeAndIsAdmin(String email, UserTypeEnum type, Boolean isAdmin);
}
