package com.example.projetmpisi.steps;

import com.example.projetmpisi.entity.User;
import com.example.projetmpisi.service.IUserService;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.springframework.stereotype.Component;

import static org.junit.jupiter.api.Assertions.*;

@Component
public class UserSteps {

    private final IUserService userService;

    private User createdUser;

    public UserSteps(IUserService userService) {
        this.userService = userService;
    }

    @Given("I am an admin user")
    public void iAmAnAdminUser() {
    }

    @When("I create a user with username {string} and email {string}")
    public void iCreateAUser(String username, String email) {
        User user = new User(1, username, email);
        createdUser = userService.saveUser(user);
    }

    @Then("the user should be saved successfully")
    public void theUserShouldBeSavedSuccessfully() {
        assertNotNull(createdUser);
        assertEquals("Bilel", createdUser.getUsername());
    }

    @Given("a user with ID {int} exists")
    public void aUserWithIdExists(int id) {
        User user = new User(id, "Bilel", "bilel@example.com");
        userService.saveUser(user);
    }

    @When("I retrieve the user by ID {int}")
    public void iRetrieveTheUserById(int id) {
        createdUser = userService.getUserById(id).orElse(null);
    }

    @Then("I should receive a user with username {string}")
    public void iShouldReceiveAUserWithUsername(String username) {
        assertNotNull(createdUser);
        assertEquals(username, createdUser.getUsername());
    }

    @When("I update the user with ID {int} to have username {string} and email {string}")
    public void iUpdateTheUserWithId(int id, String username, String email) {
        User user = new User(id, username, email);
        createdUser = userService.updateUser(id, user);
    }

    @Then("the user should be updated successfully")
    public void theUserShouldBeUpdatedSuccessfully() {
        assertNotNull(createdUser);
        assertEquals("BilelUpdated", createdUser.getUsername());
        assertEquals("bilel.updated@example.com", createdUser.getEmail());
    }

    @When("I delete the user with ID {int}")
    public void iDeleteTheUserWithId(int id) {
        userService.deleteUser(id);
        createdUser = userService.getUserById(id).orElse(null); // Vérifie si l'utilisateur a été supprimé
    }

    @Then("the user should be deleted successfully")
    public void theUserShouldBeDeletedSuccessfully() {
        assertNull(createdUser);
    }
}
