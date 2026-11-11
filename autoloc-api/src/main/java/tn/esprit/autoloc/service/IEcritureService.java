package tn.esprit.autoloc.service;

public interface IEcritureService<T, ID> {
    T create(T entity);
    T update(ID id, T entity);
}