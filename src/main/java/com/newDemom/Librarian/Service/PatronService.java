package com.newDemom.Librarian.Service;

import com.newDemom.Librarian.Domain.PatronEntity;

import java.util.List;

public interface PatronService {

    List<PatronEntity> getAllPatrons();

    PatronEntity createPatron(PatronEntity patronEntity);

    PatronEntity getPatronDetails(Long id);

    PatronEntity updatePatron(PatronEntity patron, Long id);

    void deletePatron(Long id);

    PatronEntity getPatronDetails(Long id);
}
