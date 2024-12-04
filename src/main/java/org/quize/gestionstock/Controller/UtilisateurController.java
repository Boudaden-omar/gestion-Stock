package org.quize.gestionstock.Controller;

import org.quize.gestionstock.Exception.ResourceNotFoundException;
import org.quize.gestionstock.Service.facade.UtilisateurService;
import org.quize.gestionstock.entities.Utilisateur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/users")
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateur(@PathVariable Long id) {
        try {
            Utilisateur utilisateur = utilisateurService.getUtilisateur(id);
            return ResponseEntity.ok(utilisateur);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("")
    public ResponseEntity<Utilisateur> createUtilisateur(@RequestBody Utilisateur utilisateur) {
        Utilisateur createdUtilisateur = utilisateurService.createUtilisateur(utilisateur);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUtilisateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        try {
            Utilisateur updatedUtilisateur = utilisateurService.updateUtilisateur(id, utilisateur);
            return ResponseEntity.ok(updatedUtilisateur);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable Long id) {
        try {
            utilisateurService.deleteUtilisateur(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // Méthode d'authentification (décommentée)
    @PostMapping("/login")
    public ResponseEntity<Utilisateur> authenticateUtilisateur(@RequestBody Utilisateur loginRequest) {
        Optional<Utilisateur> utilisateur = utilisateurService.authenticateUtilisateur(loginRequest.getEmail(), loginRequest.getMotDePasse());
        return utilisateur.map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @GetMapping("/role/{role}")
    public List<Utilisateur> getUtilisateursByRole(@PathVariable String role) {
        return utilisateurService.findByRole(role);
    }
}
