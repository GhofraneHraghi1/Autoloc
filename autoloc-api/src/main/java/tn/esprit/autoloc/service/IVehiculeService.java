package tn.esprit.autoloc.service;

import tn.esprit.autoloc.calcul.VehiculeOffre;
import tn.esprit.autoloc.domain.Vehicule;

public interface IVehiculeService extends IGenericService<Vehicule, Long> {

    double calculTarif(Long idVehicule, int nombreJours, VehiculeOffre offre);

    Vehicule desaffecte(Long id);

    Vehicule affecte(Long id, Long idAgence);

    Vehicule affecteEquipement(Long id, Long idEquipement);
}