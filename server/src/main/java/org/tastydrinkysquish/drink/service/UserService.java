package org.tastydrinkysquish.drink.service;

import jakarta.transaction.Transactional;
import org.tastydrinkysquish.drink.entity.DrinkUser;
import org.tastydrinkysquish.drink.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    public List<DrinkUser> getUsers() {
        return userRepository.findAll();
    }

    public boolean addUser(DrinkUser drinkUser) {
        if (!userRepository.existsUserName(drinkUser.getUserName())) {
            try {
                String password = drinkUser.getUserPassword();
                String encodedPassword = bCryptPasswordEncoder.encode(password);
                drinkUser.setUserPassword(encodedPassword);

                userRepository.save(drinkUser);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        else {
            return false;

        }
    }
    public boolean addDrink(String name,String drink){
        if(userRepository.existsUserName(name)){
            try {
                DrinkUser drinkUser = userRepository.findByUserName(name);
                drinkUser.getDrinks().add(drink);
                userRepository.save(drinkUser);
                return true;
            }
            catch (Exception e){
                System.out.println(e);
                return false;
            }
        }
        else{
            return false;
        }
    }
    @Transactional
    public boolean changePassword(Long id, String value) {

        try {
            DrinkUser drinkUser = userRepository.findById(id).
                    orElseThrow(() -> new IllegalStateException(
                            "User with ID " + id + " is not found"
                    ));
            if (value != null && value.length() > 0 && !Objects.equals(drinkUser.getUserPassword(), value)) {
                drinkUser.setUserPassword(bCryptPasswordEncoder.encode(value));
                return true;
            }
            else {
                return false;
            }
        }
        catch(Exception e) {
            return false;
        }
    }

    public Long getLoginUserID(String username) {
        DrinkUser user = userRepository.findByUserName(username);
        return user.getUserID();
    }
    public String getUserNameByID(Long id) {
        DrinkUser user =  userRepository.findById(id).orElseGet(DrinkUser::new);
        return user.getUserName();
    }
}
