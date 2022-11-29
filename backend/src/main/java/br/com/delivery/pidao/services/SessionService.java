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

    public void validateToken(String token) {
        LoginSession session = this.findSessionByToken(token);
        Date expirationDate = session.getExpirationDate();
        if (new Date().after(expirationDate)) {
            throw new RuntimeException("Sessão expirou!");
        }
    }

    public String generateSession(Users UsersS) {
        Optional<LoginSession> session = loginSessionRepository.findByUserIdentifierAndLogoutDateNull(UsersS.getUserIdentifier());
        if (session.isPresent()) {
            session.get().finish();
        }

        LoginSession UsersSession = new LoginSession(UsersS);
        loginSessionRepository.save(UsersSession);

        return UsersSession.getToken();
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
        Optional<Users> Users = UsersRepository.findByUserIdentifier(session.getUserIdentifier());
        if(!Users.isPresent()){
            throw new RuntimeException("Users não encontrado!");
        }else{}

        return Users.get();
    }

    public UsersDTO findUsersDTOByToken(String token) {
        LoginSession session = this.findSessionByToken(token);
        Optional<Users> Users = UsersRepository.findByUserIdentifier(session.getUserIdentifier());
        if(!Users.isPresent()){
            throw new RuntimeException("Users não encontrado!");
        }else{}

        return new UsersDTO();
        //UsersDTO(Users.get().getEmail(),Users.get().getPassword());
    }

    public LoginSession logout(String token) {
        LoginSession session = this.findSessionByToken(token);
        session.finish();

        loginSessionRepository.save(session);

        return session;
    }
}
