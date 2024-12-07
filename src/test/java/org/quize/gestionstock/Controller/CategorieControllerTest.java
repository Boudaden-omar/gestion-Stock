package org.quize.gestionstock.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.quize.gestionstock.Service.facade.CategorieService;
import org.quize.gestionstock.entities.Categorie;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CategorieControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CategorieService categorieService;

    @InjectMocks
    private CategorieController categorieController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(categorieController).build();
    }

    @Test
    void testGetAllCategories() throws Exception {
        List<Categorie> categories = Arrays.asList(
                new Categorie(1L, "Electronique"),
                new Categorie(2L, "Livres")
        );

        when(categorieService.getAllCategories()).thenReturn(categories);

        mockMvc.perform(get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nom").value("Electronique"))
                .andExpect(jsonPath("$[1].nom").value("Livres"));

        verify(categorieService, times(1)).getAllCategories();
    }

    @Test
    void testGetCategorie() throws Exception {
        Categorie categorie = new Categorie(1L, "Electronique");

        when(categorieService.getCategorie(1L)).thenReturn(categorie);

        mockMvc.perform(get("/categories/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.nom").value("Electronique"));

        verify(categorieService, times(1)).getCategorie(1L);
    }

    @Test
    void testCreateCategorie() throws Exception {
        Categorie categorie = new Categorie(1L, "Electronique");

        when(categorieService.createCategorie(any(Categorie.class))).thenReturn(categorie);

        mockMvc.perform(post("/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categorie)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Electronique"));

        verify(categorieService, times(1)).createCategorie(any(Categorie.class));
    }

    @Test
    void testUpdateCategorie() throws Exception {
        Categorie categorie = new Categorie(1L, "Updated");

        when(categorieService.updateCategorie(eq(1L), any(Categorie.class))).thenReturn(categorie);

        mockMvc.perform(put("/categories/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(categorie)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nom").value("Updated"));

        verify(categorieService, times(1)).updateCategorie(eq(1L), any(Categorie.class));
    }

    @Test
    void testDeleteCategorie() throws Exception {
        doNothing().when(categorieService).deleteCategorie(1L);

        mockMvc.perform(delete("/categories/1"))
                .andExpect(status().isOk());

        verify(categorieService, times(1)).deleteCategorie(1L);
    }
}
