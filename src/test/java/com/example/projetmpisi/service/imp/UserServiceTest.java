package com.example.projetmpisi.service.imp;


import com.example.projetmpisi.entity.User;
import com.example.projetmpisi.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserService userService;

    public UserServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveUser() {
        User user = new User(1, "Bilel", "bilel@example.com");
        when(userRepository.save(user)).thenReturn(user);
        User saved = userService.saveUser(user);
        assertEquals("Bilel", saved.getUsername());
    }

    @Test
    void testGetUserById() {
        User user = new User(1, "Bilel", "bilel@example.com");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        Optional<User> result = userService.getUserById(1);
        assertTrue(result.isPresent());
        assertEquals("bilel@example.com", result.get().getEmail());
    }

    @Test
    void testGetAllUsers() {
        when(userRepository.findAll()).thenReturn(List.of(new User(1, "A", "a@a.com")));
        List<User> users = userService.getAllUsers();
        assertEquals(1, users.size());
    }

    @Test
    void testDeleteUser() {
        userService.deleteUser(1);
        verify(userRepository, times(1)).deleteById(1);
    }

    @Test
    void testGetUserById_notFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());
        Optional<User> result = userService.getUserById(1);
        assertFalse(result.isPresent());
    }

}
