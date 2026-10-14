package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Paiement;
import tn.esprit.autoloc.repository.IPaiementRepository;

@Service
public class PaiementServiceImpl extends GenericServiceImpl<Paiement, Long> implements IPaiementService {

    public PaiementServiceImpl(IPaiementRepository repository) {
        super(repository);
    }

    @Override
    protected String getEntityName() {
        return "Paiement";
    }

    @Override
    protected Long getId(Paiement p) {
        return p.getIdPaiement();
    }

    @Override
    protected void setFields(Paiement source, Paiement target) {
        // jamais appelée : la mise à jour est interdite
    }

    @Override
    public Paiement create(Paiement paiement) {
        throw new UnsupportedOperationException("Paiement est en lecture seule");
    }

    @Override
    public Paiement update(Long id, Paiement paiement) {
        throw new UnsupportedOperationException("Paiement est en lecture seule");
    }

    @Override
    public void deleteById(Long id) {
        throw new UnsupportedOperationException("Paiement est en lecture seule");
    }
}