package tn.esprit.autoloc.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "agence")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 50)
    private String ville;

    @Column(nullable = false, length = 150)
    private String adresse;

    @Column(nullable = false, length = 20)
    private String telephone;

    @Column(name = "effectif")
    private Integer effectif;

    @JsonIgnore
    // Relation OneToMany : une agence possède plusieurs véhicules
    @OneToMany(mappedBy = "agence", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Vehicule> vehicules = new HashSet<>();

    // LAZY : charger l'agence ne charge pas les employés
    // pas de cascade : supprimer l'agence ne supprime pas les employés
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    private Set<Employee> employes = new HashSet<>();

    // --- MÉTHODES DE SYMÉTRISATION (Étape 10) ---

    // Pour les véhicules
    public void addVehicule(Vehicule v) {
        if (vehicules == null) {
            vehicules = new HashSet<>();
        }
        vehicules.add(v);
        v.setAgence(this); // On lie le véhicule à l'agence
    }

    public void removeVehicule(Vehicule v) {
        if (vehicules != null) {
            vehicules.remove(v);
            v.setAgence(null);
        }
    }

    // Pour les employés (Très important pour l'étape 12)
    public void addEmploye(Employee e) {
        if (employes == null) {
            employes = new HashSet<>();
        }
        employes.add(e);
        e.setAgence(this); // On lie l'employé à l'agence
    }

    public void removeEmploye(Employee e) {
        if (employes != null) {
            employes.remove(e);
            e.setAgence(null);
        }
    }
}