package tn.esprit.autoloc.service;

import java.util.List;

public interface IConsultationService<T, ID> {
    T findById(ID id);
    List<T> findAll();
    boolean check(T entity, boolean isNew);
    boolean hasVehicules(ID id);
}