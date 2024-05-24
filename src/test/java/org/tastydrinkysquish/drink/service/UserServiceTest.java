package org.tastydrinkysquish.drink.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.tastydrinkysquish.drink.entity.DrinkUser;
import org.tastydrinkysquish.drink.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @InjectMocks
    UserService userService;
    DrinkUser drinkUser;
    private AutoCloseable closeable;
    @BeforeEach
    public void init(){
        drinkUser=new DrinkUser("John","Doe", Arrays.asList(new String[]{"water"}));
        userService=new UserService(userRepository,bCryptPasswordEncoder);
    }
    @Test
    void getUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(drinkUser));
        List<DrinkUser> userList= userService.getUsers();
        verify(userRepository, Mockito.times(1)).findAll();
        verifyNoMoreInteractions(userRepository);
        assertEquals(userList,Arrays.asList(drinkUser));
    }

    @Test
    void addUserCorrect() {
        when(userRepository.existsByUserName("John")).thenReturn(false);
        assertTrue(userService.addUser(drinkUser));
    }
    @Test
    void addUserExists() {
        when(userRepository.existsByUserName("John")).thenReturn(true);
        assertFalse(userService.addUser(drinkUser));
    }
    @Test
    void addUserException() {
        when(userRepository.save(drinkUser)).thenThrow(new RuntimeException());
        assertFalse(userService.addUser(drinkUser));
    }

    @Test
    void addDrink() {
    }

    @Test
    void changePassword() {
    }

    @Test
    void getLoginUserID() {
    }

    @Test
    void getUserNameByID() {
    }
}