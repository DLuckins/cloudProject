package org.tastydrinkysquish.drink.controller;

import org.tastydrinkysquish.drink.entity.DrinkUser;
import org.tastydrinkysquish.drink.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<DrinkUser> getUsers() {
        return userService.getUsers();
    }
    @GetMapping("getLoginID")
    public Long getLoginUserID(String username) {
        return userService.getLoginUserID(username);
    }

    @GetMapping("name")
    public String getUserNameByID(@RequestParam Long id) {
        return userService.getUserNameByID(id);
    }

    @PostMapping(path = "register")
    public ResponseEntity<String> addUser(@RequestBody DrinkUser drinkUser) {
        boolean result = userService.addUser(drinkUser);
        if (result) {
            return new ResponseEntity<>("User successfully registered", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to register user", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping(path = "addDrinkToCart")
    public ResponseEntity<String> addDrink(@RequestParam String name,String drink){
        boolean result = userService.addDrink(name,drink);
        if (result) {
            return new ResponseEntity<>("Drink added to user cart", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to add drink to cart", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("password")
    public ResponseEntity<String> changePassword(
            @RequestParam Long id,
            @RequestParam String value) {
        boolean result = userService.changePassword(id, value);
        if (result) {
            return new ResponseEntity<>("Password successfully changed", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to change password", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
