package com.newDemom.Librarian.Service.Impl;

import com.newDemom.Librarian.Domain.BookEntity;
import com.newDemom.Librarian.Domain.PatronEntity;
import com.newDemom.Librarian.Exception.ResourceNotFoundException;
import com.newDemom.Librarian.Repository.BookRepository;
import com.newDemom.Librarian.Service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookEntity> getBooks() {
        return bookRepository.findAll().
                stream().
                collect(Collectors.toList());
    }

    @Override
    public BookEntity createBook(BookEntity bookEntity) {
        bookRepository.save(bookEntity);
        return bookEntity;
    }

    @Override
    public BookEntity getBookDetails(Long id) {
        return findBook(id);
    }

    @Override
    public BookEntity updateBook(BookEntity bookEntity, Long id) {
        BookEntity getBook = findBook(id);
        getBook.setAuthor(bookEntity.getAuthor());
        getBook.setIsbn(bookEntity.getIsbn());
        getBook.setTitle(bookEntity.getTitle());
        getBook.setPublicationYear(bookEntity.getPublicationYear());
        bookRepository.save(getBook);
        return getBook;
    }

    @Override
    public void deleteBook(Long id) {
        BookEntity getBook = findBook(id);
        bookRepository.delete(getBook);

    }

    private BookEntity findBook(long id) {
        return bookRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException(id, "bookId", "Book"));
    }
}
