package tn.esprit.autoloc.service;

import java.util.List;

public interface IConsultationService<T, K> {
    T findById(K id);
    List<T> findAll();
    boolean check(T entity, boolean isNew);
    boolean hasVehicules(K id);
}