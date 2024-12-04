package org.quize.gestionstock.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int numeroVente;
    @OneToMany(mappedBy = "vente", cascade = CascadeType.ALL)
    private List<VenteProduit> produits;
    private int quantite;
    private double prixUnitaire;
    @Temporal(TemporalType.DATE)
    private Date dateVente;
    private String etat; // En attente, Validée, Expédiée, etc.

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroVente() {
        return numeroVente;
    }

    public void setNumeroVente(int numeroVente) {
        this.numeroVente = numeroVente;
    }



    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Date getDateVente() {
        return dateVente;
    }

    public void setDateVente(Date dateVente) {
        this.dateVente = dateVente;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public List<VenteProduit> getProduits() {
        return produits;
    }

    public void setProduits(List<VenteProduit> produits) {
        this.produits = produits;
    }
}
