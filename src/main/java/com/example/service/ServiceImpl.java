package com.example.service;

import com.example.model.Employer;
import com.example.repository.Repository;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    private final Repository clientRepository;

    public ServiceImpl(Repository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public void create(Employer employer) {
        clientRepository.save(employer);
    }

    @Override
    public List<Employer>  readAll() {
        return clientRepository.findAll();
    }

    @Override
    public Employer read(int id) {
        return clientRepository.getById(id);
    }

    @Override
    public boolean update(Employer employer, int id) {
        if (clientRepository.existsById(id)) {
            employer.setId(id);
            clientRepository.save(employer);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
