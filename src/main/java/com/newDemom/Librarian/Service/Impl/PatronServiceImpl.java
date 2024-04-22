package com.newDemom.Librarian.Service.Impl;

import com.newDemom.Librarian.Domain.PatronEntity;
import com.newDemom.Librarian.Exception.ResourceNotFoundException;
import com.newDemom.Librarian.Repository.PatronRepository;
import com.newDemom.Librarian.Service.PatronService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatronServiceImpl implements PatronService {
    private PatronRepository patronRepository;

    public PatronServiceImpl(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    @Override
    public List<PatronEntity> getAllPatrons() {
        return patronRepository.findAll().stream().collect(Collectors.toList());
    }

    @Override
    public PatronEntity createPatron(PatronEntity patronEntity) {
        patronRepository.save(patronEntity);
        return patronEntity;
    }

    @Override
    public PatronEntity getPatronDetails(Long id) {
        return findPatron(id);
    }

    @Override
    public PatronEntity updatePatron(PatronEntity patron, Long id) {
        PatronEntity getPatron = findPatron(id);
        getPatron.setContactAddress(patron.getContactAddress());
        getPatron.setName(patron.getName());
        getPatron.setPhoneNumber(patron.getPhoneNumber());
        patronRepository.save(getPatron);
        return getPatron;
    }

    @Override
    public void deletePatron(Long id) {
        PatronEntity getPatron = findPatron(id);
        patronRepository.delete(getPatron);
    }

    private PatronEntity findPatron(long id) {
        return patronRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(id, "patronId", "Patron"));
    }
}
