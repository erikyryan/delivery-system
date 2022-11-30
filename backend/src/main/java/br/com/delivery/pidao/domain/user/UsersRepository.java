package br.com.delivery.pidao.domain.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.delivery.pidao.domain.user.Users;
import br.com.delivery.pidao.domain.user.UserTypeEnum;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long>{

    Optional<Users> findBySocialsSecurity(String socialSecurity);

    Optional<Users> findByEmail(String email);

    Optional<Users> findByUuid(UUID uuid);

    Optional<Users> findByEmailAndType(String email, UserTypeEnum type);

}
