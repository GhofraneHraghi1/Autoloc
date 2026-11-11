package tn.esprit.autoloc.service;

public interface ISuppressionService<T, ID> {
    void deleteById(ID id);
}