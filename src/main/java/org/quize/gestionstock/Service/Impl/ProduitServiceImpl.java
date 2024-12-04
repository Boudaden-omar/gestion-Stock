package org.quize.gestionstock.Service.Impl;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.quize.gestionstock.Exception.ResourceNotFoundException;
import org.quize.gestionstock.Service.facade.ProduitService;
import org.quize.gestionstock.entities.Categorie;
import org.quize.gestionstock.entities.Produit;
import org.quize.gestionstock.repositories.CategorieRepository;
import org.quize.gestionstock.repositories.ProduitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {
    private ProduitRepository produitRepository;
    private CategorieRepository categorieRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository, CategorieRepository categorieRepository) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
    }


    @Override
    public List<Produit> getAllProduits() {
        return produitRepository.findAll();
    }

    @Override
    public Produit getProduit(Long id) {
        return produitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + id));
    }

    @Override
    public Produit createProduit(Produit produit) {
        if (StringUtils.isBlank(produit.getNom()) || StringUtils.isBlank(produit.getDescription()) || produit.getPrix() <= 0 || produit.getQuantiteEnStock() < 0) {
            throw new IllegalArgumentException("Les informations du produit ne peuvent pas être vides ou négatives");
        }
        Categorie categorie = categorieRepository.findById(produit.getCategorie().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID : " + produit.getCategorie().getId()));
        produit.setCategorie(categorie);
        return produitRepository.save(produit);
    }

    @Override
    public Produit updateProduit(Long id, Produit produit) {
        Produit existingProduit = getProduit(id);
        existingProduit.setNom(produit.getNom());
        existingProduit.setDescription(produit.getDescription());
        existingProduit.setPrix(produit.getPrix());
        existingProduit.setQuantiteEnStock(produit.getQuantiteEnStock());
        existingProduit.setDateAjout(produit.getDateAjout());
        Categorie categorie = categorieRepository.findById(produit.getCategorie().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID : " + produit.getCategorie().getId()));
        existingProduit.setCategorie(categorie);
        return produitRepository.save(existingProduit);
    }

    @Override
    public void deleteProduit(Long id) {
        Produit produit = getProduit(id);
        produitRepository.delete(produit);
    }

    @Override
    public List<Produit> findByNom(String nom) {
        return produitRepository.findByNom(nom);
    }

    @Override
    public List<Produit> findByCategorie(Categorie categorie) {
        return produitRepository.findByCategorie(categorie);
    }

    @Override
    public List<Produit> findByPrixBetween(double min, double max) {
        return produitRepository.findByPrixBetween(min, max);
    }

    @Override
    public List<Produit> findByQuantiteEnStockLessThan(int seuil) {
        return produitRepository.findByQuantiteEnStockLessThan(seuil);
    }
}
