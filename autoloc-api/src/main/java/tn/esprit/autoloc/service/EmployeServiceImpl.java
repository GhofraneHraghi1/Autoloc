package tn.esprit.autoloc.service;

import org.springframework.stereotype.Service;
import tn.esprit.autoloc.Utils;
import tn.esprit.autoloc.domain.Employee;
import tn.esprit.autoloc.repository.IEmployeRepository;

@Service
public class EmployeServiceImpl extends GenericServiceImpl<Employee, Long> implements IEmployeService {

    public EmployeServiceImpl(IEmployeRepository repository) {
        super(repository);
    }

    @Override
    protected String getEntityName() {
        return "Employe";
    }

    @Override
    protected Long getId(Employee e) {
        return e.getIdEmployee();
    }

    @Override
    protected void setFields(Employee source, Employee target) {
        target.setNom(source.getNom());
        target.setPrenom(source.getPrenom());
        target.setRole(source.getRole());
    }

    @Override
    public boolean check(Employee e, boolean isNew) {
        return e != null
                && Utils.isNotEmpty(e.getNom())
                && Utils.isNotEmpty(e.getPrenom())
                && e.getRole() != null;
    }
}