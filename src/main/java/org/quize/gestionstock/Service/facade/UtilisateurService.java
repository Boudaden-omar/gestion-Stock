package org.quize.gestionstock.Service.facade;

import org.quize.gestionstock.entities.Utilisateur;

import java.util.List;
import java.util.Optional;

public interface UtilisateurService {
    List<Utilisateur> getAllUtilisateurs();

    Utilisateur getUtilisateur(Long id);

    Utilisateur createUtilisateur(Utilisateur utilisateur);

    Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur);

    void deleteUtilisateur(Long id);

    Optional<Utilisateur> authenticateUtilisateur(String email, String motDePasse);

    List<Utilisateur> findByRole(String role);
}
