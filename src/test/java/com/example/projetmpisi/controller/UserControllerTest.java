package com.example.projetmpisi.controller;

import com.example.projetmpisi.entity.User;
import com.example.projetmpisi.service.IUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class) // Test uniquement le contrôleur
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private IUserService userService; // Mock du service

    @InjectMocks
    private UserController userController; // Injection du contrôleur

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1, "Bilel", "bilel@example.com");
    }

    @Test
    void testCreateUser() throws Exception {
        when(userService.saveUser(any(User.class))).thenReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType("application/json")
                        .content("{\"username\": \"Bilel\", \"email\": \"bilel@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Bilel"))
                .andExpect(jsonPath("$.email").value("bilel@example.com"));
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("Bilel"))
                .andExpect(jsonPath("$[0].email").value("bilel@example.com"));
    }

    @Test
    void testGetUserById() throws Exception {
        when(userService.getUserById(1)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Bilel"))
                .andExpect(jsonPath("$.email").value("bilel@example.com"));
    }

    @Test
    void testUpdateUser() throws Exception {
        when(userService.updateUser(eq(1), any(User.class))).thenReturn(user);

        mockMvc.perform(put("/api/users/1")
                        .contentType("application/json")
                        .content("{\"username\": \"Bilel\", \"email\": \"bilel@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Bilel"))
                .andExpect(jsonPath("$.email").value("bilel@example.com"));
    }

    @Test
    void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isNoContent());
    }
}
