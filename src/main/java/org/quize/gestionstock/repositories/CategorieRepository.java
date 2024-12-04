package org.quize.gestionstock.repositories;

import org.quize.gestionstock.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
    List<Categorie> findByNom(String nom);
}
