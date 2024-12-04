package org.quize.gestionstock.Service.facade;

import org.quize.gestionstock.entities.Categorie;

import java.util.List;

public interface CategorieService {
    List<Categorie> getAllCategories();

    Categorie getCategorie(Long id);

    Categorie createCategorie(Categorie categorie);

    Categorie updateCategorie(Long id, Categorie categorie);

    void deleteCategorie(Long id);
}
