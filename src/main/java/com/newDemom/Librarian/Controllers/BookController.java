package com.newDemom.Librarian.Controllers;


import com.newDemom.Librarian.Domain.BookEntity;
import com.newDemom.Librarian.Domain.Dto.BookDto;
import com.newDemom.Librarian.Mappers.Impl.BookMapper;
import com.newDemom.Librarian.Service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/api/books")
public class BookController {

    private BookService bookService;
    private BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks() {
        List<BookEntity> fetchBooks = bookService.getBooks();
        return new ResponseEntity<>(fetchBooks.stream().
                map(bookMapper::mapFrom).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getAllBookDetail(
            @PathVariable("id") Long id
    ) {
        BookEntity fetchBook = bookService.getBookDetails(id);
        BookDto bookDto = bookMapper.mapFrom(fetchBook);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BookDto> createPatron(
            @Valid @RequestBody BookDto bookDto) {
        BookEntity bookEntity = bookMapper.MapTo(bookDto);
        BookEntity savedBook = bookService.createBook(bookEntity);
        return new ResponseEntity<>(bookMapper.mapFrom(savedBook),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> updateBook(
            @Valid @RequestBody BookDto bookDto,
            @PathVariable("id") Long id) {
        BookEntity bookEntity = bookMapper.MapTo(bookDto);
        BookEntity savedUpdatedBook = bookService.updateBook(bookEntity, id);
        return new ResponseEntity<>(bookMapper.mapFrom(savedUpdatedBook),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(
            @PathVariable("id") Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>("Book Deleted!!!", HttpStatus.OK);
    }
}
