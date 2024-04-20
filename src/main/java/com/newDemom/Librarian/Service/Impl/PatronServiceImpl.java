package com.newDemom.Librarian.Service.Impl;

import com.newDemom.Librarian.Domain.PatronEntity;
import com.newDemom.Librarian.Repository.PatronRepository;
import com.newDemom.Librarian.Service.PatronService;

import java.util.List;
import java.util.stream.Collectors;

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
        return patronRepository.save(patronEntity);
    }
}
