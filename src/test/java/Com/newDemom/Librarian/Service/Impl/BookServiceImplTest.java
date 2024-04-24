package Com.newDemom.Librarian.Service.Impl;

import com.newDemom.Librarian.Domain.BookEntity;
import com.newDemom.Librarian.Repository.BookRepository;
import com.newDemom.Librarian.Service.BookService;
import com.newDemom.Librarian.Service.Impl.BookServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_get_a_list_of_books() {

        List<BookEntity> books = new ArrayList<>();
        books.add( new BookEntity(1,
                "12345-9898-2456",
                "2008",
                "Akinlade Tope",
                "Isakaba",
                false,
                new ArrayList<>()));
        books.add( new BookEntity(2,
                "12345-9898-2456",
                "2020",
                "JK Rowlings",
                "Game of Thrones",
                false,
                new ArrayList<>()));

        when(bookRepository.findAll()).thenReturn(books);
        List<BookEntity> allbooks = bookService.getBooks();
        assertEquals(books.size(), allbooks.size());
    }

    @Test
    void should_create_book() {
        BookEntity bookEntity = new BookEntity(1,
                "12345-9898-2456",
                "2008",
                "Akinlade Tope",
                "Isakaba",
                false,
                new ArrayList<>());

        when(bookRepository.save(bookEntity)).thenReturn(bookEntity);

        BookEntity responseEntity = bookService.createBook(bookEntity);

        assertEquals(bookEntity.getIsbn(), responseEntity.getIsbn());
        assertEquals(bookEntity.getPublicationYear(), responseEntity.getPublicationYear());
        assertEquals(bookEntity.getAuthor(), responseEntity.getAuthor());
        assertEquals(bookEntity.getTitle(), responseEntity.getTitle());
        verify(bookRepository, times(1)).save(bookEntity);
    }

    @Test
    void should_return_student_details() {
        long bookId = 1;
        BookEntity bookEntity = new BookEntity(1,
                "12345-9898-2456",
                "2008",
                "Akinlade Tope",
                "Isakaba",
                false,
                new ArrayList<>());
        when(bookRepository.findById(bookId)).thenReturn(Optional.of(bookEntity));

        BookEntity responseEntity = bookService.getBookDetails(bookId);
        assertEquals(bookEntity.getIsbn(), responseEntity.getIsbn());
        assertEquals(bookEntity.getPublicationYear(), responseEntity.getPublicationYear());
        assertEquals(bookEntity.getAuthor(), responseEntity.getAuthor());
        assertEquals(bookEntity.getTitle(), responseEntity.getTitle());


    }


    @Test
    void should_return_an_updated_book() {
        long bookId = 1;
        BookEntity getBookEntity = new BookEntity(1,
                "12345-9898-2456",
                "2008",
                "Akinlade Tope",
                "Isakaba",
                false,
                new ArrayList<>());

        BookEntity updateBookEntity = new BookEntity(1,
                "123-898-456",
                "2023",
                "JK Rowlings",
                "Game of Thrones",
                false,
                new ArrayList<>());
        when(bookRepository.findById(1L)).thenReturn(Optional.of(getBookEntity));
        getBookEntity.setPublicationYear(updateBookEntity.getPublicationYear());
        getBookEntity.setIsbn(updateBookEntity.getIsbn());
        getBookEntity.setAuthor(updateBookEntity.getAuthor());
        getBookEntity.setTitle(updateBookEntity.getTitle());
        when(bookRepository.save(getBookEntity)).thenReturn(getBookEntity);

        BookEntity responseEntity = bookService.updateBook(updateBookEntity,bookId);

        assertEquals(updateBookEntity.getIsbn(), responseEntity.getIsbn());
        assertEquals(updateBookEntity.getPublicationYear(), responseEntity.getPublicationYear());
        assertEquals(updateBookEntity.getAuthor(), responseEntity.getAuthor());
        assertEquals(updateBookEntity.getTitle(), responseEntity.getTitle());
    }

    @Test
    void deleteBook() {
    }
}