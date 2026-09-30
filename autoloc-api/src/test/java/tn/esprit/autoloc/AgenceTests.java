package tn.esprit.autoloc;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.CategorieVehicule;
import tn.esprit.autoloc.domain.StatutVehicule;
import tn.esprit.autoloc.domain.Vehicule;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class AgenceTests {
    @Autowired
    private AgenceRepositoryMock agenceRepository;

    @Test
    void addAgence() {
        Agence agence = new Agence();
        agence.setNom("Agence Ariana");
        agence.setVille("Tunis");
        agence.setAdresse("1 Rue Hedi");
        agence.setTelephone("71585874");

        Vehicule v1 = new Vehicule();
        v1.setImmatriculation("78414TUN96");
        v1.setMarque("Isuzu");
        v1.setModele("DMax");
        v1.setCategorie(CategorieVehicule.CITADINE);
        v1.setTarifJournalier(new BigDecimal("100"));
        v1.setStatut(StatutVehicule.EN_MAINTENANCE);
        v1.setAgence(agence);

        Vehicule v2 = new Vehicule();
        v2.setImmatriculation("785414TUN95");
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

        agenceRepository.save(agence);
    }


    @Test
    void loadAgence() {
        Iterable<Agence> agences = agenceRepository.findAll();

        StringBuilder sb = new StringBuilder();

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

}

    interface AgenceRepositoryMock extends CrudRepository<Agence, Long> {
    }
