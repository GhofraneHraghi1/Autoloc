package tn.esprit.autoloc.service;

public interface IGenericService<T, K> extends
        IConsultationService<T, K>,
        IEcritureService<T, K>,
        ISuppressionService<K> {
    // C'est vide ici, tout est hérité des 3 interfaces ci-dessus
}