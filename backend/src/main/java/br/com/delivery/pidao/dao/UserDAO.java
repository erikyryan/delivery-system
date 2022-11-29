package br.com.delivery.pidao.dao;

import br.com.delivery.pidao.entities.Customer;
import br.com.delivery.pidao.entities.Delivery;
import br.com.delivery.pidao.entities.Manager;
import br.com.delivery.pidao.entities.Users;
import br.com.delivery.pidao.entities.dto.UsersDTO;
import br.com.delivery.pidao.enums.UserTypeEnum;
import br.com.delivery.pidao.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
@AllArgsConstructor
public class UserDAO {

   private UsersRepository usersRepository;

    public boolean isManager(UsersDTO userDTO){
        Optional<Users> user = usersRepository.findByEmailAndType(userDTO.getEmail(), userDTO.getTypeUser());
        Users userManager = user.get();
        return userManager.getType() == UserTypeEnum.MANAGER ? true : false;
    }

    public boolean isCustomer(UsersDTO userDTO){
        Optional<Users> user = usersRepository.findByEmailAndType(userDTO.getEmail(), userDTO.getTypeUser());
        Users userCustomer = user.get();
        return userCustomer.getType() == UserTypeEnum.CUSTOMER ? true : false;
    }

    public boolean isDeliveryman(UsersDTO userDTO){
        Optional<Users> user = usersRepository.findByEmailAndType(userDTO.getEmail(), userDTO.getTypeUser());
        Users userDeliveryman = user.get();
        return userDeliveryman.getType() == UserTypeEnum.DELIVERYMAN ? true : false;
    }

    public Optional<Users> isPresent(String socialSecurity){
        return usersRepository.findBySocialsSecurity(socialSecurity);
    }

    public Object valitadeUserEmail(String email){
        Optional<Users> user = usersRepository.findByEmail(email);
        if(user.isPresent()){
            return user;
        }
        return null;
    }
}








