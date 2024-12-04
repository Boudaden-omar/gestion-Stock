package org.quize.gestionstock.Service.facade;

import org.quize.gestionstock.entities.Commande;

import java.util.Date;
import java.util.List;

public interface CommandeService {
    List<Commande> getAllCommandes();

    Commande getCommande(Long id);

    Commande createCommande(Commande commande);

    void deleteCommande(Long id);

    List<Commande> findByNumeroCommande(int numeroCommande);

    List<Commande> findByDateCreationBetween(Date start, Date end);
}
