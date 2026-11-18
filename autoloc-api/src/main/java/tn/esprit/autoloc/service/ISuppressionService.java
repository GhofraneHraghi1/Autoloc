package tn.esprit.autoloc.service;

public interface ISuppressionService<K> {
    void deleteById(K id);
}