Feature: User Management

  # Scénario 1 : Créer un nouvel utilisateur
  Scenario: Create a new user
    Given I am an admin user
    When I create a user with username "Bilel" and email "bilel@example.com"
    Then the user should be saved successfully

  # Scénario 2 : Récupérer un utilisateur par ID
  Scenario: Get user by ID
    Given a user with ID "1" exists
    When I retrieve the user by ID "1"
    Then I should receive a user with username "Bilel"

  # Scénario 3 : Mettre à jour un utilisateur
  Scenario: Update user information
    Given a user with ID "1" exists
    When I update the user with ID "1" to have username "BilelUpdated" and email "bilel.updated@example.com"
    Then the user should be updated successfully

  # Scénario 4 : Supprimer un utilisateur
  Scenario: Delete a user
    Given a user with ID "1" exists
    When I delete the user with ID "1"
    Then the user should be deleted successfully
