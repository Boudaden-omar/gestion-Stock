package org.quize.gestionstock.repositories;

import org.quize.gestionstock.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

// Utilisateur Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmailAndMotDePasse(String email, String motDePasse);
    List<Utilisateur> findByRole(String role);
}
