package org.quize.gestionstock.Service.Impl;

import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.quize.gestionstock.Exception.ResourceNotFoundException;
import org.quize.gestionstock.Service.facade.FournisseurService;
import org.quize.gestionstock.entities.Fournisseur;
import org.quize.gestionstock.repositories.FournisseurRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class FournisseurServiceImpl implements FournisseurService {
    private FournisseurRepository fournisseurRepository;

    public FournisseurServiceImpl(FournisseurRepository fournisseurRepository) {
        this.fournisseurRepository = fournisseurRepository;
    }

    @Override
    public List<Fournisseur> getAllFournisseurs() {
        return fournisseurRepository.findAll();
    }
    @Override
    public Fournisseur getFournisseur(Long id) {
        return fournisseurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur non trouvé avec l'ID : " + id));
    }
    @Override
    public Fournisseur createFournisseur(Fournisseur fournisseur) {
        if (StringUtils.isBlank(fournisseur.getNom()) || StringUtils.isBlank(fournisseur.getAdresse()) || StringUtils.isBlank(fournisseur.getTelephone())) {
            throw new IllegalArgumentException("Les informations du fournisseur ne peuvent pas être vides");
        }
        return fournisseurRepository.save(fournisseur);
    }
    @Override
    public Fournisseur updateFournisseur(Long id, Fournisseur fournisseur) {
        Fournisseur existingFournisseur = getFournisseur(id);
        existingFournisseur.setNom(fournisseur.getNom());
        existingFournisseur.setAdresse(fournisseur.getAdresse());
        existingFournisseur.setTelephone(fournisseur.getTelephone());
        return fournisseurRepository.save(existingFournisseur);
    }
    @Override
    public void deleteFournisseur(Long id) {
        Fournisseur fournisseur = getFournisseur(id);
        fournisseurRepository.delete(fournisseur);
    }

    @Override
    public List<Fournisseur> findByNom(String nom) {
        return fournisseurRepository.findByNom(nom);
    }
    @Override
    public List<Fournisseur> findByAdresse(String adresse) {
        return fournisseurRepository.findByAdresse(adresse);
    }

}
