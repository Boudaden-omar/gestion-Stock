package org.quize.gestionstock.Service.facade;

import org.quize.gestionstock.entities.Fournisseur;

import java.util.List;

public interface FournisseurService {
    List<Fournisseur> getAllFournisseurs();

    Fournisseur getFournisseur(Long id);

    Fournisseur createFournisseur(Fournisseur fournisseur);

    Fournisseur updateFournisseur(Long id, Fournisseur fournisseur);

    void deleteFournisseur(Long id);

    List<Fournisseur> findByNom(String nom);

    List<Fournisseur> findByAdresse(String adresse);
}
