package org.quize.gestionstock.repositories;

import org.quize.gestionstock.entities.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface VenteRepository extends JpaRepository<Vente, Long> {
    List<Vente> findByNumeroVente(int numeroVente);
    List<Vente> findByDateVenteBetween(Date start, Date end);
    List<Vente> findByEtat(String etat);
}
