package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Maintenance;
import tn.esprit.autoloc.repository.IMaintenanceRepository;

@Service
public class MaintenanceServiceImpl extends GenericServiceImpl<Maintenance, Long> implements IMaintenanceService {

    public MaintenanceServiceImpl(IMaintenanceRepository repository) {
        super(repository);
    }

    @Override
    protected String getEntityName() {
        return "Maintenance";
    }

    @Override
    protected Long getId(Maintenance m) {
        return m.getIdMaintenance();
    }

    @Override
    protected void setFields(Maintenance source, Maintenance target) {
        target.setDateDebut(source.getDateDebut());
        target.setDateFin(source.getDateFin());
        target.setDescription(source.getDescription());
    }

    @Override
    public boolean check(Maintenance m, boolean isNew) {
        return m != null
                && m.getDateDebut() != null
                && Utils.isNotEmpty(m.getDescription());
    }
}