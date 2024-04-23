package com.newDemom.Librarian.Repository;

import com.newDemom.Librarian.Domain.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {
}
