package org.quize.gestionstock.repositories;

import org.junit.jupiter.api.Test;
import org.quize.gestionstock.entities.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategorieRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategorieRepository categorieRepository;

    @Test
    public void testSaveCategorie() {
        // Créer une nouvelle catégorie
        Categorie categorie = new Categorie();
        categorie.setNom("Electronique");

        // Sauvegarder la catégorie
        Categorie savedCategorie = categorieRepository.save(categorie);
        System.out.println("test categorieRepo");

        // Vérifier que la catégorie a été sauvegardée
        //test test
        assertThat(savedCategorie).isNotNull();
        assertThat(savedCategorie.getId()).isNotNull();
        assertThat(savedCategorie.getNom()).isEqualTo("Electronique");
    }

    @Test
    public void testFindByNom() {
        // Créer et persister une catégorie
        Categorie categorie = new Categorie();
        categorie.setNom("Livres");
        entityManager.persist(categorie);
        entityManager.flush();

        // Rechercher la catégorie par son nom
        List<Categorie> categories = categorieRepository.findByNom("Livres");

        // Vérifier les résultats
        assertThat(categories).hasSize(1);
        assertThat(categories.get(0).getNom()).isEqualTo("Livres");
    }
}
