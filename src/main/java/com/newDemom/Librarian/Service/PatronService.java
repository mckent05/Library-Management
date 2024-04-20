package com.newDemom.Librarian.Service;

import com.newDemom.Librarian.Domain.PatronEntity;

import java.util.List;

public interface PatronService {

    List<PatronEntity> getAllPatrons();

    PatronEntity createPatron(PatronEntity patronEntity);

    PatronEntity updatePatron(PatronEntity patron, Long id);
}
