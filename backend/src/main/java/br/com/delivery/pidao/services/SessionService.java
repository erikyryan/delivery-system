package br.com.delivery.pidao.services;

import br.com.delivery.pidao.entities.LoginSession;
import br.com.delivery.pidao.entities.Users;
import br.com.delivery.pidao.entities.dto.UsersDTO;
import br.com.delivery.pidao.repositories.SessionRepository;
import br.com.delivery.pidao.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionService {

    private SessionRepository loginSessionRepository;

    private UsersRepository UsersRepository;

    public String validateToken(String token) {
        LoginSession session = this.findSessionByToken(token);
        Date expirationDate = session.getExpirationDate();
        if (new Date().after(expirationDate)) {
            throw new RuntimeException("Sessão expirou!");
        }

        return session.getUserIdentifier();

    }

    public String generateSession(Users UsersS) {
        Optional<LoginSession> session = loginSessionRepository.findByUserIdentifierAndLogoutDateNull(UsersS.getUserIdentifier());
        if (session.isPresent()) {
            session.get().finish();
        }

        LoginSession usersSession = new LoginSession(UsersS);
        loginSessionRepository.save(usersSession);

        return usersSession.getToken();
    }

    public LoginSession findSessionByToken(String token) {
        Optional<LoginSession> session = loginSessionRepository.findByToken(token);
        if (session.isPresent()) {
            return session.get();
        }else {
            throw new RuntimeException("não existe essa sessão");
        }
    }

    public Users findUsers(String token) {
        LoginSession session = this.findSessionByToken(token);
        Optional<Users> users = UsersRepository.findByUserIdentifier(session.getUserIdentifier());
        if(!users.isPresent()){
            throw new RuntimeException("Users não encontrado!");
        }else{}

        return users.get();
    }

    public UsersDTO findUsersDTOByToken(String token) {
        LoginSession session = this.findSessionByToken(token);
        Optional<Users> users = UsersRepository.findByUserIdentifier(session.getUserIdentifier());
        if(!users.isPresent()){
            throw new RuntimeException("Users não encontrado!");
        }

        return new UsersDTO();
    }

    public LoginSession logout(String token) {
        LoginSession session = this.findSessionByToken(token);
        session.finish();

        loginSessionRepository.save(session);

        return session;
    }
}
