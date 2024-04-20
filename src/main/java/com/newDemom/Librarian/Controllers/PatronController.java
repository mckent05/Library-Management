package com.newDemom.Librarian.Controllers;


import com.newDemom.Librarian.Domain.Dto.PatronDto;
import com.newDemom.Librarian.Domain.PatronEntity;
import com.newDemom.Librarian.Mappers.Impl.PatronMapper;
import com.newDemom.Librarian.Service.PatronService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
@RequestMapping("/api/v1/patrons")
public class PatronController {

    private PatronService patronService;
    private PatronMapper patronMapper;

    public PatronController(PatronService patronService,
                            PatronMapper patronMapper) {
        this.patronService = patronService;
        this.patronMapper = patronMapper;

    }

    @GetMapping
    public ResponseEntity<List<PatronDto>> getPatrons() {
        List<PatronEntity> getPatrons = patronService.getAllPatrons();
        return new ResponseEntity<>(getPatrons.stream().
                map(patronMapper::mapFrom).collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatronDto> createPatron(
           @Valid @RequestBody PatronDto patronDto) {
        PatronEntity patron = patronMapper.MapTo(patronDto);
        PatronEntity savedPatron = patronService.createPatron(patron);
        return new ResponseEntity<>(patronMapper.mapFrom(savedPatron),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatronDto> updatePatron(
            @Valid @RequestBody PatronDto patronDto,
            @PathVariable("id") Long id) {
        PatronEntity patron = patronMapper.MapTo(patronDto);
        PatronEntity savedUpdatedPatron = patronService.updatePatron(patron, id);
        return new ResponseEntity<>(patronMapper.mapFrom(savedUpdatedPatron),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatron(
            @PathVariable("id") Long id) {
        patronService.deletePatron(id);
        return new ResponseEntity<>("Patron Deleted", HttpStatus.OK);
    }
}
