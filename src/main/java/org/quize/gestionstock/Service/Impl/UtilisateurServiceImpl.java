package org.quize.gestionstock.Service.Impl;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.quize.gestionstock.Exception.ResourceNotFoundException;
import org.quize.gestionstock.Service.facade.UtilisateurService;
import org.quize.gestionstock.entities.Utilisateur;
import org.quize.gestionstock.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UtilisateurServiceImpl implements UtilisateurService {
    private UtilisateurRepository utilisateurRepository;

    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }


    @Override
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurRepository.findAll();
    }

    @Override
    public Utilisateur getUtilisateur(Long id) {
        return utilisateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'ID : " + id));
    }

    @Override
    public Utilisateur createUtilisateur(Utilisateur utilisateur) {
        if (StringUtils.isBlank(utilisateur.getNom()) || StringUtils.isBlank(utilisateur.getEmail()) || StringUtils.isBlank(utilisateur.getMotDePasse()) || StringUtils.isBlank(utilisateur.getRole())) {
            throw new IllegalArgumentException("Les informations de l'utilisateur ne peuvent pas être vides");
        }
        return utilisateurRepository.save(utilisateur);
    }

    @Override
    public Utilisateur updateUtilisateur(Long id, Utilisateur utilisateur) {
        Utilisateur existingUtilisateur = getUtilisateur(id);
        existingUtilisateur.setNom(utilisateur.getNom());
        existingUtilisateur.setEmail(utilisateur.getEmail());
        existingUtilisateur.setMotDePasse(utilisateur.getMotDePasse());
        existingUtilisateur.setRole(utilisateur.getRole());
        return utilisateurRepository.save(existingUtilisateur);
    }

    @Override
    public void deleteUtilisateur(Long id) {
        Utilisateur utilisateur = getUtilisateur(id);
        utilisateurRepository.delete(utilisateur);
    }

    @Override
    public Optional<Utilisateur> authenticateUtilisateur(String email, String motDePasse) {
        return utilisateurRepository.findByEmailAndMotDePasse(email, motDePasse);
    }

    @Override
    public List<Utilisateur> findByRole(String role) {
        return utilisateurRepository.findByRole(role);
    }
}
