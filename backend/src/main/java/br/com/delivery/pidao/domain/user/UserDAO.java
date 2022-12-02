package br.com.delivery.pidao.domain.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@AllArgsConstructor
public class UserDAO {

   private UsersRepository usersRepository;

    public boolean isManager(Users.UsersDTO userDTO){
        Optional<Users> user = usersRepository.findByEmailAndType(userDTO.getEmail(), userDTO.getTypeUser());
        Users userManager = user.get();
        return userManager.getTypeUser() == UserTypeEnum.MANAGER ? true : false;
    }

    public boolean isCustomer(Users.UsersDTO userDTO){
        Optional<Users> user = usersRepository.findByEmailAndType(userDTO.getEmail(), userDTO.getTypeUser());
        Users userCustomer = user.get();
        return userCustomer.getTypeUser() == UserTypeEnum.CUSTOMER ? true : false;
    }

    public boolean isDeliveryman(Users.UsersDTO userDTO){
        Optional<Users> user = usersRepository.findByEmailAndType(userDTO.getEmail(), userDTO.getTypeUser());
        Users userDeliveryman = user.get();
        return userDeliveryman.getTypeUser() == UserTypeEnum.DELIVERYMAN ? true : false;
    }

    public Optional<Users> isPresent(String socialSecurity){
        return usersRepository.findBySocialsSecurity(socialSecurity);
    }

    public Users valitadeUserEmail(String email){
        Optional<Users> user = usersRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get();
        }
        return null;
    }

    public Users save(Users userCustomer) {
        return usersRepository.save(userCustomer);
    }

    public Users findByEmail(String email) {
        Optional<Users> user = usersRepository.findByEmail(email);
        if(!user.isPresent()){
            throw new RuntimeException("Usuario não encontrado.");
        }else {
            return user.get();
        }


    }
}








