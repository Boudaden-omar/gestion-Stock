package org.quize.gestionstock.repositories;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.quize.gestionstock.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UtilisateurRepositoryTest {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Utilisateur utilisateur;

    @BeforeEach
    public void setUp() {
        utilisateur = new Utilisateur();
        utilisateur.setNom("Test User");
        utilisateur.setEmail("test@example.com");
        utilisateur.setMotDePasse("password123");
        utilisateur.setRole("ADMIN");
        entityManager.persist(utilisateur);
        entityManager.flush();
    }

    @Test
    public void testFindByEmailAndMotDePasse() {
        Optional<Utilisateur> found = utilisateurRepository.findByEmailAndMotDePasse(
                "test@example.com", "password123"
        );

        assertThat(found).isPresent();
        assertThat(found.get().getNom()).isEqualTo("Test User");
    }

    @Test
    public void testFindByRole() {
        List<Utilisateur> adminUsers = utilisateurRepository.findByRole("ADMIN");

        assertThat(adminUsers).isNotEmpty();
        assertThat(adminUsers.get(0).getRole()).isEqualTo("ADMIN");
    }

    @Test
    public void testFindByEmailAndMotDePasseNotFound() {
        Optional<Utilisateur> found = utilisateurRepository.findByEmailAndMotDePasse(
                "wrong@example.com", "wrongpassword"
        );

        assertThat(found).isEmpty();
    }
}