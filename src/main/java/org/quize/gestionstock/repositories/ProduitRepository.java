package org.quize.gestionstock.repositories;

import org.quize.gestionstock.entities.Categorie;
import org.quize.gestionstock.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findByNom(String nom);
    List<Produit> findByCategorie(Categorie categorie);
    List<Produit> findByPrixBetween(double min, double max);
    List<Produit> findByQuantiteEnStockLessThan(int seuil);
}
