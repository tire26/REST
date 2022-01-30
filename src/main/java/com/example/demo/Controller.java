package com.example.demo;

import com.example.demo.model.Employer;
import com.example.demo.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
public class Controller {

    private final Service service;

    @Autowired
    public Controller(Service service) {
        this.service = service;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody Employer employer) {
        service.create(employer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping(value = "/employers")
    public ResponseEntity<List<Employer>> read() {
        final List<Employer> employers = service.readAll();

        return employers != null &&  !employers.isEmpty()
                ? new ResponseEntity<>(employers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/employersFromDepartment/{department}")
    public ResponseEntity<List<Employer>> readFromDepartment(@PathVariable(name = "department") String department) {
        List<Employer> employers = new LinkedList<>();

        for (Employer employer:
             service.readAll()) {
            if(employer.getDepartment().equals(department)) {
                employers.add(employer);
            }
        }
        return !employers.isEmpty()
                ? new ResponseEntity<>(employers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/employer/{id}")
    public ResponseEntity<Employer> read(@PathVariable(name = "id") int id) {
        final Employer employer = service.read(id);

        return employer != null
                ? new ResponseEntity<>(employer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Employer employer) {
        final boolean updated = service.update(employer, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @PutMapping(value = "/updateDepartment/{id}/{department}")
    public ResponseEntity<?> updateDepartment(@PathVariable(name = "id") int id, @PathVariable(name = "department") String  department) {
        Employer currentEmployer = service.read(id);
        currentEmployer.setDepartment(department);
        final boolean updated = service.update(currentEmployer, id);

        return updated
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = service.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value = "/deleteFromDepartment/{id}")
    public ResponseEntity<?> deleteFromDepartment(@PathVariable(name = "id") int id) {
        Employer employer = service.read(id);
        if (employer != null) {
            employer.setDepartment("");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
