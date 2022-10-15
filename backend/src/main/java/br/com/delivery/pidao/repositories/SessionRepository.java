package br.com.delivery.pidao.repositories;

import br.com.delivery.pidao.entities.LoginSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<LoginSession,Long> {
    Optional<LoginSession> findByToken(String token);

    Optional<LoginSession> findByUserIdentifierAndLogoutDateNull(String userIdentifier);
}
