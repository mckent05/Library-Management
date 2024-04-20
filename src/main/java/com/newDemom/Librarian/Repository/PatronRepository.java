package com.newDemom.Librarian.Repository;

import com.newDemom.Librarian.Domain.PatronEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface PatronRepository extends JpaRepository<PatronEntity, Long> {
}
