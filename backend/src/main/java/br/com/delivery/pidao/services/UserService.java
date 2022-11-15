package br.com.delivery.pidao.services;

import br.com.delivery.pidao.dao.UserDAO;
import br.com.delivery.pidao.entities.Client;
import br.com.delivery.pidao.entities.LoginSession;
import br.com.delivery.pidao.entities.Manager;
import br.com.delivery.pidao.entities.User;
import br.com.delivery.pidao.entities.dto.UserDTO;
import br.com.delivery.pidao.repositories.ClientRepository;
import br.com.delivery.pidao.repositories.ManagerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private ManagerRepository managerRepository;

    private ClientRepository clientRepository;

    private SessionService loginSessionService;

    private UserDAO userDAO;

    public String login(UserDTO userDTO) {
        User currentUser = validateLogin(userDTO);
        return loginSessionService.generateSession(currentUser);
    }

    public LoginSession logout(String token) {
        try {
            LoginSession session = loginSessionService.logout(token);
            return session;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private User validateLogin(UserDTO userS) {
        Optional<Manager> manager = managerRepository.findByEmail(userS.getEmail());
        if (!manager.isPresent()) {
            Optional<Client> client = clientRepository.findByEmail(userS.getEmail());
            if(!client.isPresent()) {
                throw new RuntimeException("Usuário não foi encontrado.");
            }
            if(!Objects.equals(client.get().getPassword(),userS.getPassword())){
                throw new RuntimeException("Senha inválida!");
            }
            return client.get();
        }

        if(!Objects.equals(manager.get().getPassword(),userS.getPassword())){
            throw new RuntimeException("Senha inválida!");
        }

        return manager.get();
    }

    public User findByToken(String token) {
        User user = this.loginSessionService.findUser(token);
        return user;
    }

    public Optional<Manager> isManager(String identifier) {
        return userDAO.isManager(identifier);
    }
}
