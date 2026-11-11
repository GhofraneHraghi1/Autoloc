package tn.esprit.autoloc.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.domain.Employee;
import tn.esprit.autoloc.service.IEmployeService;

import java.util.List;

@Tag(name = "Employes", description = "Gestion des employés")
@RestController
@RequestMapping("/employes")
@RequiredArgsConstructor
public class EmployeController {

    private final IEmployeService employeService;

    @Operation(summary = "Lister les employés")
    @GetMapping
    public List<Employee> findAll() {
        return employeService.findAll();
    }

    @Operation(summary = "Consulter un employé")
    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id) {
        return employeService.findById(id);
    }

    @Operation(summary = "Créer un employé")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employe) {
        return employeService.create(employe);
    }

    @Operation(summary = "Modifier un employé")
    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @RequestBody Employee employe) {
        return employeService.update(id, employe);
    }

    @Operation(summary = "Supprimer un employé")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        employeService.deleteById(id);
    }
}