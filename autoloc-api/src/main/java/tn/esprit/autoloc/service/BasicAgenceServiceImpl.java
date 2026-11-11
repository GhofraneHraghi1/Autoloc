package tn.esprit.autoloc.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employee; // Import à ajouter
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasicAgenceServiceImpl implements IAgenceService {

    private final IAgenceRepository agenceRepository;

    @Override
    public boolean check(Agence agence, boolean isNew) {
        return Utils.isNotEmpty(agence.getNom())
                && Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    public Agence create(Agence agence) {
        if (agence.getIdAgence() != null) {
            throw new IllegalArgumentException("L'identifiant doit être vide lors de la création");
        }
        if (!check(agence, true)) {
            throw new IllegalArgumentException("Les données de l'agence ne sont pas valides");
        }
        return agenceRepository.save(agence);
    }

    @Override
    public Agence findById(Long id) {
        return agenceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Agence", id.toString()));
    }

    @Override
    public List<Agence> findAll() {
        return null;
    }

    @Override
    public Agence update(Long id, Agence agence) {
        Agence existing = findById(id);

        if (!check(agence, false)) {
            throw new IllegalArgumentException("Les données de l'agence ne sont pas valides");
        }

        existing.setNom(agence.getNom());
        existing.setVille(agence.getVille());
        existing.setAdresse(agence.getAdresse());
        existing.setTelephone(agence.getTelephone());

        return agenceRepository.save(existing);
    }

    @Override
    public void deleteById(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Agence", id.toString());
        }
        agenceRepository.deleteById(id);
    }

    @Override
    public boolean hasVehicules(Long id) {
        return false;
    }

    // --- MÉTHODE AJOUTÉE POUR CORRIGER L'ERREUR DE COMPILATION ---
    @Override
    public String simuler(Long idAgence, int nombreJours, VehiculeOffre offre) {
        throw new UnsupportedOperationException("La méthode simuler n'est pas implémentée dans la version basique.");
    }

    // --- NOUVELLE MÉTHODE À AJOUTER (Étape 12) ---
    @Override
    public Agence ajouterEmployes(Long idAgence, List<Employee> employes) {
        // Pour la version basique, on ne fait rien ou on lance une exception
        throw new UnsupportedOperationException("La méthode ajouterEmployes n'est pas implémentée dans la version basique.");
    }
}