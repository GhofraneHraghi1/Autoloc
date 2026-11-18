package tn.esprit.autoloc.service;

public interface IEcritureService<T, K> {
    T create(T entity);
    T update(K id, T entity);
}