package org.quize.gestionstock.Service.facade;

import org.quize.gestionstock.entities.Categorie;
import org.quize.gestionstock.entities.Produit;

import java.util.List;

public interface ProduitService {
    List<Produit> getAllProduits();

    Produit getProduit(Long id);

    Produit createProduit(Produit produit);

    Produit updateProduit(Long id, Produit produit);

    void deleteProduit(Long id);

    List<Produit> findByNom(String nom);

    List<Produit> findByCategorie(Categorie categorie);

    List<Produit> findByPrixBetween(double min, double max);

    List<Produit> findByQuantiteEnStockLessThan(int seuil);
}
