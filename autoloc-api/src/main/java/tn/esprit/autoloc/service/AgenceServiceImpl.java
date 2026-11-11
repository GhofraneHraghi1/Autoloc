package tn.esprit.autoloc.service;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employee;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.io.IRapport;
import tn.esprit.autoloc.io.RapportFactory; // IMPORT AJOUTÉ
import tn.esprit.autoloc.repository.IAgenceRepository;

import java.util.List;

@Primary
@Service
public class AgenceServiceImpl extends GenericServiceImpl<Agence, Long> implements IAgenceService {

    // 1. Déclaration des dépendances
    private final IVehiculeService vehiculeService;
    private final RapportFactory rapportFactory; // LA VARIABLE AJOUTÉE ICI
    private final IEmployeService employeeService;

    // 2. Modification du constructeur pour injecter les deux services
    public AgenceServiceImpl(IAgenceRepository agenceRepository,
                             IVehiculeService vehiculeService,
                             RapportFactory rapportFactory,
                             IEmployeService employeeService) { // Ajout ici
        super(agenceRepository);
        this.vehiculeService = vehiculeService;
        this.rapportFactory = rapportFactory;
        this.employeeService = employeeService; // Initialisation
    }

    @Override
    protected String getEntityName() {
        return "Agence";
    }

    @Override
    protected Long getId(Agence agence) {
        return agence.getIdAgence();
    }

    @Override
    protected void setFields(Agence source, Agence target) {
        target.setNom(source.getNom());
        target.setVille(source.getVille());
        target.setAdresse(source.getAdresse());
        target.setTelephone(source.getTelephone());
    }

    @Override
    public boolean check(Agence agence, boolean isNew) {
        return agence != null
                && Utils.isNotEmpty(agence.getNom())
                && Utils.isNotEmpty(agence.getTelephone());
    }

    @Override
    public boolean hasVehicules(Long id) {
        Agence agence = findById(id);
        return agence.getVehicules() != null && !agence.getVehicules().isEmpty();
    }

    @Override
    public String simuler(Long idAgence, int nombreJours, VehiculeOffre offre) {
        Agence agence = this.findById(idAgence);

        StringBuilder contenu = new StringBuilder();
        contenu.append("=== RAPPORT AGENCE ===\n");
        contenu.append("ID : ").append(agence.getIdAgence()).append("\n");
        contenu.append("Nom : ").append(agence.getNom()).append("\n");
        contenu.append("Téléphone : ").append(agence.getTelephone()).append("\n");
        contenu.append("----------------------\n");

        double total = 0;
        if (agence.getVehicules() != null && !agence.getVehicules().isEmpty()) {
            for (Vehicule v : agence.getVehicules()) {
                total += vehiculeService.calculTarif(v.getIdVehicule(), nombreJours, offre);
            }
        } else {
            contenu.append("Aucun véhicule dans cette agence.\n");
        }

        contenu.append("Total des tarifs pour ").append(nombreJours).append(" jours (Offre: ").append(offre).append(") : ").append(total).append(" DT\n");

        // Utilisation de la Factory
        IRapport rapport = rapportFactory.creerRapport(contenu.toString());

        return rapport.genererRapport();
    }

    @Override
    @Transactional // L'annotation magique pour garantir l'atomicité !
    public Agence ajouterEmployes(Long idAgence, List<Employee> employes) {
        // 1. Récupérer (et mettre à jour) l'agence
        Agence agence = this.findById(idAgence);

        // 2. Utiliser le service des employés pour insérer les employés
        for (Employee e : employes) {
            // On utilise la méthode de symétrisation de l'entité Agence
            agence.addEmploye(e);

            // On demande au service Employé de sauvegarder (qui fera un INSERT)
            employeeService.create(e);
        }

        // 3. Mettre à jour le champ effectif avec la taille de la liste
        agence.setEffectif(agence.getEmployes().size());

        // On sauvegarde l'agence mise à jour
        return this.update(idAgence, agence);
    }
}