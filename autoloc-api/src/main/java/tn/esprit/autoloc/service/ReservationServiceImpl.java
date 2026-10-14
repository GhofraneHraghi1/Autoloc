package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.domain.Reservation;
import tn.esprit.autoloc.repository.IReservationRepository;

@Service
public class ReservationServiceImpl extends GenericServiceImpl<Reservation, Long> implements IReservationService {

    public ReservationServiceImpl(IReservationRepository repository) {
        super(repository);
    }

    @Override
    protected String getEntityName() {
        return "Reservation";
    }

    @Override
    protected Long getId(Reservation r) {
        return r.getIdReservation();
    }

    @Override
    protected void setFields(Reservation source, Reservation target) {
        target.setDateDebut(source.getDateDebut());
        target.setDateFin(source.getDateFin());
        target.setStatut(source.getStatut());
    }

    @Override
    public boolean check(Reservation r, boolean isNew) {
        return r != null
                && r.getDateDebut() != null
                && r.getDateFin() != null
                && !r.getDateFin().isBefore(r.getDateDebut())
                && r.getStatut() != null;
    }
}