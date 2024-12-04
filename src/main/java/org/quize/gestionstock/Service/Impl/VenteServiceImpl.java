package org.quize.gestionstock.Service.Impl;

import lombok.AllArgsConstructor;
import org.quize.gestionstock.Exception.ResourceNotFoundException;
import org.quize.gestionstock.Service.facade.VenteService;
import org.quize.gestionstock.entities.Produit;
import org.quize.gestionstock.entities.Vente;
import org.quize.gestionstock.entities.VenteProduit;
import org.quize.gestionstock.repositories.ProduitRepository;
import org.quize.gestionstock.repositories.VenteRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service

public class VenteServiceImpl implements VenteService {

    private final VenteRepository venteRepository;
    private final ProduitRepository produitRepository;

    public VenteServiceImpl(VenteRepository venteRepository, ProduitRepository produitRepository) {
        this.venteRepository = venteRepository;
        this.produitRepository = produitRepository;
    }

    @Override
    public List<Vente> getAllVentes() {
        return venteRepository.findAll();
    }

    @Override
    public Vente getVente(Long id) {
        return venteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Vente non trouvée avec l'ID : " + id));
    }

    @Override
    public Vente createVente(Vente vente) {
        for (VenteProduit produit : vente.getProduits()) {
            Produit existingProduit = produitRepository.findById(produit.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + produit.getId()));
            if (vente.getQuantite() > existingProduit.getQuantiteEnStock()) {
                throw new IllegalArgumentException("La quantité vendue est supérieure au stock disponible pour le produit " + produit.getId());
            }
            existingProduit.setQuantiteEnStock(existingProduit.getQuantiteEnStock() - vente.getQuantite());
            produitRepository.save(existingProduit);
        }
        return venteRepository.save(vente);
    }

    @Override
    public void deleteVente(Long id) {
        Vente vente = getVente(id);
        for (VenteProduit  produit : vente.getProduits()) {
            Produit existingProduit = produitRepository.findById(produit.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Produit non trouvé avec l'ID : " + produit.getId()));
            existingProduit.setQuantiteEnStock(existingProduit.getQuantiteEnStock() + vente.getQuantite());
            produitRepository.save(existingProduit);
        }
        venteRepository.delete(vente);
    }

    @Override
    public List<Vente> findByNumeroVente(int numeroVente) {
        return venteRepository.findByNumeroVente(numeroVente);
    }

    @Override
    public List<Vente> findByDateVenteBetween(Date start, Date end) {
        return venteRepository.findByDateVenteBetween(start, end);
    }

    @Override
    public List<Vente> findByEtat(String etat) {
        return venteRepository.findByEtat(etat);
    }
}
