package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;
import tn.esprit.autoloc.repository.IAgenceRepository;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class AgenceTests {
    @Autowired
    private AgenceRepositoryMock basicAgenceRepository;

    @Autowired
    private IAgenceRepository fullAgenceRepository;

    private void addAgence(CrudRepository<Agence, Long> repository) {

        int suffix = (int) System.currentTimeMillis();

        Agence agence = new Agence();
        agence.setNom("Agence Ariana");
        agence.setVille("Tunis");
        agence.setAdresse("1 Rue Hedi");
        agence.setTelephone("71585874");

        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("78414TUN96" + suffix);
        v1.setMarque("Isuzu");
        v1.setModele("DMax");
        v1.setCategorie(CategorieVehicule.CITADINE);
        v1.setTarifJournalier(new BigDecimal("100"));
        v1.setStatut(StatutVehicule.EN_MAINTENANCE);
        v1.setAgence(agence);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("785414TUN95" + suffix);
        v2.setMarque("Toyata");
        v2.setModele("Yaris");
        v2.setCategorie(CategorieVehicule.BERLINE);
        v2.setTarifJournalier(new BigDecimal("80"));
        v2.setStatut(StatutVehicule.DISPONIBLE);
        v2.setAgence(agence);

        Set<Vehicule> vehicules = new HashSet<>();
        vehicules.add(v1);
        vehicules.add(v2);
        agence.setVehicules(vehicules);

        repository.save(agence);
    }

    @Test
    void basicAddAgence() {
        addAgence(basicAgenceRepository);
    }

    @Test
    void fullAddAgence() {
        addAgence(fullAgenceRepository);
    }

    private void loadAgence(CrudRepository<Agence, Long> repository, String type) {
        Iterable<Agence> agences = repository.findAll();

        StringBuilder sb = new StringBuilder();
        sb.append("\nDépôt utilisé : ").append(type);

        for (Agence a : agences) {
            sb.append("\n").append(a.getIdAgence())
                    .append(" | ").append(a.getNom());
            sb.append("\nVehicules Count : ").append(a.getVehicules().size());

            for (Vehicule v : a.getVehicules()) {
                sb.append("\n=== ").append(v.getIdVehicule())
                        .append("|").append(v.getImmatriculation());
            }
        }

        fail(sb.toString());
    }

    @Test
    void basicLoadAgence() {
        loadAgence(basicAgenceRepository, "basicAgenceRepository (CrudRepository)");
    }

    @Test
    void fullLoadAgence() {
        loadAgence(fullAgenceRepository, "fullAgenceRepository (JpaRepository)");
    }

    @Test
    void loadSortedAgences() {
        List<Agence> agences = fullAgenceRepository.findAll(Sort.by(Sort.Direction.DESC, "idAgence"));

        StringBuilder sb = new StringBuilder();
        sb.append("\nAgences triées par id décroissant :");

        for (Agence a : agences) {
            sb.append("\n").append(a.getIdAgence())
                    .append(" | ").append(a.getNom())
                    .append(" | ").append(a.getVille());
        }

        fail(sb.toString());
    }

    @Test
    void loadPagedAgences() {
        Pageable pageable = PageRequest.of(0, 2, Sort.by(Sort.Direction.DESC, "idAgence"));
        Page<Agence> page = fullAgenceRepository.findAll(pageable);

        StringBuilder sb = new StringBuilder();
        sb.append("\nTotal pages : ").append(page.getTotalPages());
        sb.append("\nPage en cours : ").append(page.getNumber());
        sb.append("\nAgences de la page :");

        for (Agence a : page.getContent()) {
            sb.append("\n").append(a.getIdAgence())
                    .append(" | ").append(a.getNom())
                    .append(" | ").append(a.getVille());
        }

        fail(sb.toString());
    }
}

interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
}