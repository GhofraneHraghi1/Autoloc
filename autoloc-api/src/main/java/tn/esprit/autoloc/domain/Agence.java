package tn.esprit.autoloc.domain;

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

    // Relation OneToMany : une agence possède plusieurs véhicules
    @OneToMany(mappedBy = "agence", cascade =CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Set<Vehicule> vehicules;

    // LAZY : charger l'agence ne charge pas les employés
// pas de cascade : supprimer l'agence ne supprime pas les employés
    @OneToMany(mappedBy = "agence", fetch = FetchType.LAZY)
    private Set<Employee> employes = new HashSet<>();

    public void addVehicule(Vehicule v2) {
    }
}