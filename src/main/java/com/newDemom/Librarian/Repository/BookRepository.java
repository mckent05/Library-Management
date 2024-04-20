package com.newDemom.Librarian.Repository;

import com.newDemom.Librarian.Domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
