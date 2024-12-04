package org.quize.gestionstock.Service.Impl;

import org.quize.gestionstock.Exception.ResourceNotFoundException;
import org.quize.gestionstock.Service.facade.CategorieService;
import org.quize.gestionstock.entities.Categorie;
import org.quize.gestionstock.repositories.CategorieRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepository categorieRepository;

    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

    @Override
    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie getCategorie(Long id) {
        return categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie non trouvée avec l'ID : " + id));
    }

    @Override
    public Categorie createCategorie(Categorie categorie) {
        if (categorie == null) {
            throw new RuntimeException("La catégorie ne peut pas être null. Il faut remplir les paramètres.");
        } else if (categorie.getId() != null && categorieRepository.existsById(categorie.getId())) {
            throw new RuntimeException("La catégorie existe déjà.");
        }
        return categorieRepository.save(categorie);
    }

    @Override
    public Categorie updateCategorie(Long id, Categorie categorie) {
        Categorie existingCategorie = getCategorie(id);
        existingCategorie.setNom(categorie.getNom());
        return categorieRepository.save(existingCategorie);
    }

    @Override
    public void deleteCategorie(Long id) {
        Categorie categorie = getCategorie(id);
        categorieRepository.delete(categorie);
    }
}
