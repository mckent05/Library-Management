package com.newDemom.Librarian.Service;

import com.newDemom.Librarian.Domain.BookEntity;
import com.newDemom.Librarian.Domain.PatronEntity;

import java.util.List;

public interface BookService {

    List<BookEntity> getBooks();

    BookEntity createBook(BookEntity bookEntity);

    BookEntity getBookDetails(Long id);

    BookEntity updateBook(BookEntity bookEntity, Long id);

    void deleteBook(Long id);
}
