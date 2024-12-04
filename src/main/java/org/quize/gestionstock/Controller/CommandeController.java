package org.quize.gestionstock.Controller;

import org.quize.gestionstock.Service.facade.CommandeService;
import org.quize.gestionstock.entities.Commande;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/commandes")
public class CommandeController {
    private CommandeService commandeService;

    public CommandeController(CommandeService commandeService) {
        this.commandeService = commandeService;
    }

    @GetMapping
    public List<Commande> getAllCommandes() {
        return commandeService.getAllCommandes();
    }

    @GetMapping("/{id}")
    public Commande getCommande(@PathVariable Long id) {
        return commandeService.getCommande(id);
    }

    @PostMapping
    public Commande createCommande(@RequestBody Commande commande) {
        return commandeService.createCommande(commande);
    }

    @DeleteMapping("/{id}")
    public void deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
    }


    @GetMapping("/numero/{numeroCommande}")
    public List<Commande> getCommandesByNumero(@PathVariable int numeroCommande) {
        return commandeService.findByNumeroCommande(numeroCommande);
    }


    @GetMapping("/date")
    public List<Commande> getCommandesByDateCreation(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
                                                     @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end) {
        return commandeService.findByDateCreationBetween(start, end);
    }
}