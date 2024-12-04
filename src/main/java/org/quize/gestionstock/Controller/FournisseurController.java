package org.quize.gestionstock.Controller;

import org.quize.gestionstock.Service.facade.FournisseurService;
import org.quize.gestionstock.entities.Fournisseur;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fournisseurs")
public class FournisseurController {
    private FournisseurService fournisseurService;

    public FournisseurController(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @GetMapping
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurService.getAllFournisseurs();
    }

    @GetMapping("/{id}")
    public Fournisseur getFournisseur(@PathVariable Long id) {
        return fournisseurService.getFournisseur(id);
    }

    @PostMapping
    public Fournisseur createFournisseur(@RequestBody Fournisseur fournisseur) {
        return fournisseurService.createFournisseur(fournisseur);
    }

    @PutMapping("/{id}")
    public Fournisseur updateFournisseur(@PathVariable Long id, @RequestBody Fournisseur fournisseur) {
        return fournisseurService.updateFournisseur(id, fournisseur);
    }

    @DeleteMapping("/{id}")
    public void deleteFournisseur(@PathVariable Long id) {
        fournisseurService.deleteFournisseur(id);
    }

    @GetMapping("/nom/{nom}")
    public List<Fournisseur> getFournisseursByNom(@PathVariable String nom) {
        return fournisseurService.findByNom(nom);
    }

    @GetMapping("/adresse/{adresse}")
    public List<Fournisseur> getFournisseursByAdresse(@PathVariable String adresse) {
        return fournisseurService.findByAdresse(adresse);
    }
}
