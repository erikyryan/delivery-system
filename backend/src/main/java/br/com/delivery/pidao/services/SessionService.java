package br.com.delivery.pidao.services;

import br.com.delivery.pidao.entities.Client;
import br.com.delivery.pidao.entities.LoginSession;
import br.com.delivery.pidao.entities.Manager;
import br.com.delivery.pidao.entities.User;
import br.com.delivery.pidao.entities.dto.UserDTO;
import br.com.delivery.pidao.repositories.ClientRepository;
import br.com.delivery.pidao.repositories.ManagerRepository;
import br.com.delivery.pidao.repositories.SessionRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SessionService {

    private SessionRepository loginSessionRepository;

    private ManagerRepository managerRepository;

    private ClientRepository clientRepository;

    public void validateToken(String token) {
        LoginSession session = this.findSessionByToken(token);
        Date expirationDate = session.getExpirationDate();
        if (new Date().after(expirationDate)) {
            throw new RuntimeException("Sessão expirou!");
        }
    }

    public String generateSession(User userS) {
        Optional<LoginSession> session = loginSessionRepository.findByUserIdentifierAndLogoutDateNull(userS.getUserIdentifier());
        if (session.isPresent()) {
            session.get().finish();
        }

        LoginSession userSession = new LoginSession(userS);
        loginSessionRepository.save(userSession);

        return userSession.getToken();
    }

    public LoginSession findSessionByToken(String token) {
        Optional<LoginSession> session = loginSessionRepository.findByToken(token);
        if (session.isPresent()) {
            return session.get();
        }else {
            throw new RuntimeException("não existe essa sessão");
        }
    }

    public User findUser(String token) {
        LoginSession session = this.findSessionByToken(token);
        Optional<Manager> manager = managerRepository.findByUserIdentifier(session.getUserIdentifier());
        if(!manager.isPresent()){
            Optional<Client> client = clientRepository.findByUserIdentifier(session.getUserIdentifier());
            if(!client.isPresent()){
                throw new RuntimeException("User não encontrado!");
            }
            return client.get();
        }

        return manager.get();
    }

    public UserDTO findUserDTOByToken(String token) {
        LoginSession session = this.findSessionByToken(token);
        Optional<Manager> manager = managerRepository.findByUserIdentifier(session.getUserIdentifier());
        if(!manager.isPresent()){
            Optional<Client> client = clientRepository.findByUserIdentifier(session.getUserIdentifier());
            if(!client.isPresent()){
                throw new RuntimeException("User não encontrado!");
            }
            return new UserDTO(client.get().getEmail(),client.get().getPassword());
        }

        return new UserDTO(manager.get().getEmail(),manager.get().getPassword());
    }

    public LoginSession logout(String token) {
        LoginSession session = this.findSessionByToken(token);
        session.finish();

        loginSessionRepository.save(session);

        return session;
    }
}
