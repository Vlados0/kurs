package com.example.cyber.service;

import com.example.cyber.exceptions.UsernameAlreadyExistsException;
import com.example.cyber.model.User;
import com.example.cyber.repository.UserRepository;
import com.example.cyber.repository.UserRolesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserServiceImplTest {

    private UserRepository userRepository;
    private UserRolesRepository userRolesRepository;
    private PasswordEncoder passwordEncoder;
    private UserServiceimpl userService;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userRolesRepository = Mockito.mock(UserRolesRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService = new UserServiceimpl(userRepository, userRolesRepository, passwordEncoder);
    }

    @DisplayName("Test registration method with new user")
    @Test
    void registrationNewUser() {
        String username = "username";
        String password = "password";
        User user = new User();
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.empty());
        Mockito.when(passwordEncoder.encode(password)).thenReturn("encodedPassword");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);

        userService.registration(username, password);

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(username);
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @DisplayName("Test registration method with existing user")
    @Test
    void registrationExistingUser() {
        String username = "username";
        String password = "password";
        User existingUser = new User();
        Mockito.when(userRepository.findByUsername(username)).thenReturn(Optional.of(existingUser));

        assertThrows(UsernameAlreadyExistsException.class, () -> userService.registration(username, password));

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(username);
        Mockito.verify(userRepository, Mockito.never()).save(Mockito.any(User.class));
    }

    @DisplayName("Test getUserById method")
    @Test
    void getUserById() {
        User user = new User();
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertEquals(user, userService.getUserById(1L));
    }

    @DisplayName("Test getAllUsers method")
    @Test
    void getAllUsers() {
        User user = new User();
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));
        assertEquals(List.of(user), userService.getAllUsers());
    }

    @DisplayName("Test deleteUser method")
    @Test
    void deleteUser() {
        userService.deleteUser(1L);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1L);
    }
}
