package tn.esprit.autoloc.service;

import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Agence;
import tn.esprit.autoloc.domain.Employee;

import java.util.List;

public interface IAgenceService extends IGenericService<Agence, Long> {
    // ... autres méthodes
    String simuler(Long idAgence, int nombreJours, VehiculeOffre offre);

    Agence ajouterEmployes(Long idAgence, List<Employee> employes);
}