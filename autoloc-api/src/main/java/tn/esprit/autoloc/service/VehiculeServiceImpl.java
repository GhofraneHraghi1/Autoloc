package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.IStrategieTarif;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.exception.ResourceNotFoundException;
import tn.esprit.autoloc.repository.IAgenceRepository;
import tn.esprit.autoloc.repository.IVehiculeRepository;

import java.util.Map;

@Service
public class VehiculeServiceImpl extends GenericServiceImpl<Vehicule, Long> implements IVehiculeService {

    private final Map<String, IStrategieTarif> strategies;
    private final IEquipementService equipementService;
    private final IAgenceRepository agenceRepository;

    public VehiculeServiceImpl(IVehiculeRepository repository,
                               Map<String, IStrategieTarif> strategies,
                               IAgenceRepository agenceRepository,
                               IEquipementService equipementService) {
        super(repository);
        this.strategies = strategies;
        this.agenceRepository = agenceRepository;
        this.equipementService = equipementService;
    }

    @Override
    protected String getEntityName() {
        return "Vehicule";
    }

    @Override
    protected Long getId(Vehicule v) {
        return v.getIdVehicule();
    }

    @Override
    protected void setFields(Vehicule source, Vehicule target) {
        target.setImmatriculation(source.getImmatriculation());
        target.setMarque(source.getMarque());
        target.setModele(source.getModele());
        target.setCategorie(source.getCategorie());
        target.setTarifJournalier(source.getTarifJournalier());
        target.setStatut(source.getStatut());
    }

    @Override
    public boolean check(Vehicule v, boolean isNew) {
        return v != null
                && Utils.isNotEmpty(v.getImmatriculation())
                && Utils.isNotEmpty(v.getMarque())
                && Utils.isNotEmpty(v.getModele())
                && v.getCategorie() != null
                && v.getStatut() != null
                && v.getTarifJournalier() != null;
    }

    @Override
    public double calculTarif(Long idVehicule, int nombreJours, VehiculeOffre offre) {
        Vehicule vehicule = this.findById(idVehicule);

        IStrategieTarif strategie = strategies.get(offre.name());
        if (strategie == null) {
            strategie = strategies.get("STANDARD");
        }

        return strategie.calculer(vehicule.getTarifJournalier().doubleValue(), nombreJours);
    }

    @Override
    public Vehicule desaffecte(Long id) {
        var vehicule = findById(id);
        vehicule.setAgence(null);
        return repository.save(vehicule);
    }

    @Override
    public Vehicule affecte(Long id, Long idAgence) {
        var vehicule = findById(id);
        var agence = agenceRepository.findById(idAgence)
                .orElseThrow(() -> new ResourceNotFoundException("Agence", idAgence.toString()));

        vehicule.setAgence(agence);
        return repository.save(vehicule);
    }

    @Override
    @Transactional
    public Vehicule affecteEquipement(Long id, Long idEquipement) {
        // 1. Récupérer le véhicule et l'équipement
        var vehicule = findById(id);
        var equipement = equipementService.findById(idEquipement);

        // 2. Ajouter l'équipement au véhicule (côté propriétaire ou les deux)
        vehicule.getEquipements().add(equipement);

        // 3. Symétriser la relation (ajouter le véhicule à l'équipement)
        equipement.getVehicules().add(vehicule);

        // 4. Sauvegarder le véhicule pour mettre à jour la table de jointure
        return repository.save(vehicule);
    }
}