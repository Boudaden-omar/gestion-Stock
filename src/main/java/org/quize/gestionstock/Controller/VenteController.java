package org.quize.gestionstock.Controller;

import org.quize.gestionstock.Service.facade.VenteService;
import org.quize.gestionstock.entities.Vente;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/ventes")
public class VenteController {
    private  VenteService venteService;

    public VenteController(VenteService venteService) {
        this.venteService = venteService;
    }

    @GetMapping
    public List<Vente> getAllVentes() {
        return venteService.getAllVentes();
    }

    @GetMapping("/{id}")
    public Vente getVente(@PathVariable Long id) {
        return venteService.getVente(id);
    }

    @PostMapping
    public Vente createVente(@RequestBody Vente vente) {
        return venteService.createVente(vente);
    }

    @DeleteMapping("/{id}")
    public void deleteVente(@PathVariable Long id) {
        venteService.deleteVente(id);
    }

    @GetMapping("/numero/{numeroVente}")
    public List<Vente> getVentesByNumero(@PathVariable int numeroVente) {
        return venteService.findByNumeroVente(numeroVente);
    }

    @GetMapping("/date")
    public List<Vente> getVentesByDateVente(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return venteService.findByDateVenteBetween(start, end);
    }

    @GetMapping("/etat/{etat}")
    public List<Vente> getVentesByEtat(@PathVariable String etat) {
        return venteService.findByEtat(etat);
    }
}