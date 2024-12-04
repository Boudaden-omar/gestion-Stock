package org.quize.gestionstock.repositories;

import org.quize.gestionstock.entities.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    List<Fournisseur> findByNom(String nom);
    List<Fournisseur> findByAdresse(String adresse);
}