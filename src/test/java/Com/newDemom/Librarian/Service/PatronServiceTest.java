package Com.newDemom.Librarian.Service;

import com.newDemom.Librarian.Domain.BookEntity;
import com.newDemom.Librarian.Domain.PatronEntity;
import com.newDemom.Librarian.Repository.BookRepository;
import com.newDemom.Librarian.Repository.PatronRepository;
import com.newDemom.Librarian.Service.Impl.BookServiceImpl;
import com.newDemom.Librarian.Service.Impl.PatronServiceImpl;
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
import static org.mockito.Mockito.when;

class PatronServiceImplTest {

    @InjectMocks
    private PatronServiceImpl patronService;

    @Mock
    private PatronRepository patronRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void should_get_a_list_of_patrons() {

        List<PatronEntity> patrons = new ArrayList<>();
        patrons.add( new PatronEntity(1,
                "Akinwande Sunami",
                "10 Joseph Haarrison Street Iwaya Yaba Lagos",
                "080987654234",
                new ArrayList<>()));
        patrons.add( new PatronEntity(2,
                "Akinwande Werghost",
                "10 hoseph Sabo Street Iwaya Yaba Lagos",
                "091237904234",
                new ArrayList<>()));

        when(patronRepository.findAll()).thenReturn(patrons);
        List<PatronEntity> allPatrons = patronService.getAllPatrons();
        assertEquals(patrons.size(), allPatrons.size());
    }

    @Test
    void should_create_patron() {
        PatronEntity patronEntity = new PatronEntity(1,
                "Akinwande Sunami",
                "10 Joseph Haarrison Street Iwaya Yaba Lagos",
                "080987654234",
                new ArrayList<>());

        when(patronRepository.save(patronEntity)).thenReturn(patronEntity);

        PatronEntity responseEntity = patronService.createPatron(patronEntity);

        assertEquals(patronEntity.getName(), responseEntity.getName());
        assertEquals(patronEntity.getContactAddress(), responseEntity.getContactAddress());
        assertEquals(patronEntity.getPhoneNumber(), responseEntity.getPhoneNumber());
        verify(patronRepository, times(1)).save(patronEntity);
    }

    @Test
    void should_return_patron_details() {
        long patronId = 1;
        PatronEntity patronEntity = new PatronEntity(1,
                "Akinwande Sunami",
                "10 Joseph Haarrison Street Iwaya Yaba Lagos",
                "080987654234",
                new ArrayList<>());
        when(patronRepository.findById(1L)).thenReturn(Optional.of(patronEntity));

        PatronEntity responseEntity = patronService.getPatronDetails(patronId);
        assertEquals(patronEntity.getName(), responseEntity.getName());
        assertEquals(patronEntity.getContactAddress(), responseEntity.getContactAddress());
        assertEquals(patronEntity.getPhoneNumber(), responseEntity.getPhoneNumber());


    }


    @Test
    void should_return_an_updated_patron() {
        long patronId = 1;
        PatronEntity getPatronEntity = new PatronEntity(1,
                "Akinwande Werghost",
                "10 hoseph Sabo Street Iwaya Yaba Lagos",
                "091237904234",
                new ArrayList<>());

        PatronEntity updatePatronEntity = new PatronEntity(1,
                "Yemine Lamal",
                "20 hoseph Sabo Street Iwaya Yaba Lagos",
                "08133746125",
                new ArrayList<>());
        when(patronRepository.findById(patronId)).thenReturn(Optional.of(getPatronEntity));
        getPatronEntity.setName(updatePatronEntity.getName());
        getPatronEntity.setContactAddress(updatePatronEntity.getContactAddress());
        getPatronEntity.setPhoneNumber(updatePatronEntity.getPhoneNumber());
        when(patronRepository.save(getPatronEntity)).thenReturn(getPatronEntity);

        PatronEntity responseEntity = patronService.updatePatron(updatePatronEntity,patronId);

        assertEquals(updatePatronEntity.getName(), responseEntity.getName());
        assertEquals(updatePatronEntity.getContactAddress(), responseEntity.getContactAddress());
        assertEquals(updatePatronEntity.getPhoneNumber(), responseEntity.getPhoneNumber());
    }

    @Test
    void deleteBook() {
    }
}