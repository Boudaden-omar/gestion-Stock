package org.quize.gestionstock.repositories;

import org.quize.gestionstock.entities.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findByNumeroCommande(int numeroCommande);
    List<Commande> findByDateCreationBetween(Date start, Date end);
}
