package org.quize.gestionstock.Service.Impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quize.gestionstock.Exception.ResourceNotFoundException;
import org.quize.gestionstock.entities.Categorie;
import org.quize.gestionstock.repositories.CategorieRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
public class CategorieServiceImplTest {

    @Mock
    private CategorieRepository categorieRepository;

    @InjectMocks
    private CategorieServiceImpl categorieService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllCategories() {
        Categorie categorie1 = new Categorie();
        categorie1.setNom("Electronique");
        Categorie categorie2 = new Categorie();
        categorie2.setNom("Livres");

        when(categorieRepository.findAll()).thenReturn(Arrays.asList(categorie1, categorie2));

        List<Categorie> categories = categorieService.getAllCategories();

        assertEquals(2, categories.size());
        verify(categorieRepository, times(1)).findAll();
    }

    @Test
    public void testGetCategorie() {
        Categorie categorie = new Categorie();
        categorie.setId(1L);
        categorie.setNom("Electronique");

        when(categorieRepository.findById(1L)).thenReturn(Optional.of(categorie));

        Categorie found = categorieService.getCategorie(1L);

        assertEquals("Electronique", found.getNom());
    }

    @Test
    public void testCreateCategorie() {
        Categorie categorie = new Categorie();
        categorie.setNom("Nouveau");

        when(categorieRepository.save(categorie)).thenReturn(categorie);

        Categorie created = categorieService.createCategorie(categorie);

        assertNotNull(created);
        assertEquals("Nouveau", created.getNom());
    }

    @Test
    public void testUpdateCategorie() {
        Categorie existingCategorie = new Categorie();
        existingCategorie.setId(1L);
        existingCategorie.setNom("Ancien");

        Categorie updatedInfo = new Categorie();
        updatedInfo.setNom("Nouveau");

        when(categorieRepository.findById(1L)).thenReturn(Optional.of(existingCategorie));
        when(categorieRepository.save(existingCategorie)).thenReturn(existingCategorie);

        Categorie updated = categorieService.updateCategorie(1L, updatedInfo);

        assertEquals("Nouveau", updated.getNom());
    }

    @Test
    public void testDeleteCategorie() {
        Categorie categorie = new Categorie();
        categorie.setId(1L);
        categorie.setNom("A Supprimer");

        when(categorieRepository.findById(1L)).thenReturn(Optional.of(categorie));

        assertDoesNotThrow(() -> categorieService.deleteCategorie(1L));
        verify(categorieRepository, times(1)).delete(categorie);
    }

    @Test
    public void testGetCategorieNotFound() {
        when(categorieRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            categorieService.getCategorie(999L);
        });
    }
}