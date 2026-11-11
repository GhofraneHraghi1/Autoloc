package tn.esprit.autoloc.service;

public interface IGenericService<T, ID> extends
        IConsultationService<T, ID>,
        IEcritureService<T, ID>,
        ISuppressionService<T, ID> {
    // C'est vide ici, tout est hérité des 3 interfaces ci-dessus
}