package org.quize.gestionstock.Controller;

import org.quize.gestionstock.Service.facade.CategorieService;
import org.quize.gestionstock.Service.facade.ProduitService;
import org.quize.gestionstock.entities.Categorie;
import org.quize.gestionstock.entities.Produit;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produits")
public class ProduitController {
    private ProduitService produitService;
    private CategorieService categorieService;

    public ProduitController(ProduitService produitService, CategorieService categorieService) {
        this.produitService = produitService;
        this.categorieService = categorieService;
    }

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    public Produit getProduit(@PathVariable Long id) {
        return produitService.getProduit(id);
    }

    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        return produitService.createProduit(produit);
    }

    @PutMapping("/{id}")
    public Produit updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        return produitService.updateProduit(id, produit);
    }

    @DeleteMapping("/{id}")
    public void deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
    }

    @GetMapping("/nom/{nom}")
    public List<Produit> getProduitsByNom(@PathVariable String nom) {
        return produitService.findByNom(nom);
    }

    @GetMapping("/categorie/{categorieId}")
    public List<Produit> getProduitsByCategorie(@PathVariable Long categorieId) {
        Categorie categorie = categorieService.getCategorie(categorieId);
        return produitService.findByCategorie(categorie);
    }

    @GetMapping("/prix")
    public List<Produit> getProduitsByPrix(@RequestParam double min, @RequestParam double max) {
        return produitService.findByPrixBetween(min, max);
    }

    @GetMapping("/stock/faible")
    public List<Produit> getProduitsFaibleStock(@RequestParam int seuil) {
        return produitService.findByQuantiteEnStockLessThan(seuil);
    }
}
