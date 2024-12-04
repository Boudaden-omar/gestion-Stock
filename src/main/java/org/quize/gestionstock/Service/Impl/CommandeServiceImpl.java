package org.quize.gestionstock.Service.Impl;

import lombok.AllArgsConstructor;
import org.quize.gestionstock.Exception.ResourceNotFoundException;
import org.quize.gestionstock.Service.facade.CommandeService;
import org.quize.gestionstock.entities.Commande;
import org.quize.gestionstock.entities.CommandeProduit;
import org.quize.gestionstock.entities.Produit;
import org.quize.gestionstock.repositories.CommandeRepository;
import org.quize.gestionstock.repositories.ProduitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional

public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository commandeRepository;
    private final ProduitRepository produitRepository;

    public CommandeServiceImpl(CommandeRepository commandeRepository, ProduitRepository produitRepository) {
        this.commandeRepository = commandeRepository;
        this.produitRepository = produitRepository;
    }

    @Override
    public List<Commande> getAllCommandes() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande getCommande(Long id) {
        return commandeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée avec l'ID : " + id));
    }

    @Override
    public Commande createCommande(Commande commande) {
        for (CommandeProduit produit : commande.getProduits()) {
            Produit existingProduit = produitRepository.findById(produit.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + produit.getId()));
            if (commande.getQuantite() > existingProduit.getQuantiteEnStock()) {
                throw new IllegalArgumentException("La quantité commandée est supérieure au stock disponible");
            }
            existingProduit.setQuantiteEnStock(existingProduit.getQuantiteEnStock() - commande.getQuantite());
            produitRepository.save(existingProduit);
        }
        return commandeRepository.save(commande);
    }

    @Override
    public void deleteCommande(Long id) {
        Commande commande = getCommande(id);
        for (CommandeProduit produit : commande.getProduits()) {
            Produit existingProduit = produitRepository.findById(produit.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + produit.getId()));
            existingProduit.setQuantiteEnStock(existingProduit.getQuantiteEnStock() + commande.getQuantite());
            produitRepository.save(existingProduit);
        }
        commandeRepository.delete(commande);
    }

    @Override
    public List<Commande> findByNumeroCommande(int numeroCommande) {
        return commandeRepository.findByNumeroCommande(numeroCommande);
    }

    @Override
    public List<Commande> findByDateCreationBetween(Date start, Date end) {
        return commandeRepository.findByDateCreationBetween(start, end);
    }
}
