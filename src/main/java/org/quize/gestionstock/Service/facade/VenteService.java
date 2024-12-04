package org.quize.gestionstock.Service.facade;

import org.quize.gestionstock.entities.Vente;

import java.util.Date;
import java.util.List;

public interface VenteService {
    List<Vente> getAllVentes();

    Vente getVente(Long id);

    Vente createVente(Vente vente);

    void deleteVente(Long id);

    List<Vente> findByNumeroVente(int numeroVente);


    List<Vente> findByDateVenteBetween(Date start, Date end);

    List<Vente> findByEtat(String etat);
}
