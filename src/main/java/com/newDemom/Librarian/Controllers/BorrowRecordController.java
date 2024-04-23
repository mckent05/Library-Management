package com.newDemom.Librarian.Controllers;

import com.newDemom.Librarian.Domain.BookEntity;
import com.newDemom.Librarian.Domain.BorrowRecord;
import com.newDemom.Librarian.Domain.Dto.BookDto;
import com.newDemom.Librarian.Domain.Dto.BorrowRecordDto;
import com.newDemom.Librarian.Mappers.Impl.BorrowRecordMapper;
import com.newDemom.Librarian.Service.BorrowRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@Controller
public class BorrowRecordController {

    private BorrowRecordService borrowRecordService;

    private BorrowRecordMapper borrowRecordMapper;

    public BorrowRecordController(BorrowRecordService borrowRecordService,
                                  BorrowRecordMapper borrowRecordMapper) {
        this.borrowRecordService = borrowRecordService;
        this.borrowRecordMapper = borrowRecordMapper;
    }

    @PostMapping("api/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<BorrowRecordDto> borrowBook(
            @RequestBody BorrowRecordDto borrowRecordDto,
            @PathVariable("bookId") Long bookId,
            @PathVariable("patronId") Long patronId

    ) throws ParseException {
        BorrowRecord borrowRecord = borrowRecordMapper.MapTo(borrowRecordDto);
        BorrowRecord createBorrowRecord = borrowRecordService.createBorrowRecord(bookId, patronId, borrowRecord);
        return new ResponseEntity<>(borrowRecordMapper.mapFrom(createBorrowRecord),
                HttpStatus.CREATED);
    }

    @PutMapping("api/return/{id}")
    public ResponseEntity<BorrowRecordDto> returnBook(
            @RequestBody BorrowRecordDto borrowRecordDto,
            @PathVariable("id") Long borrowRecordId

    ) throws ParseException {
        BorrowRecord borrowRecord = borrowRecordMapper.MapTo(borrowRecordDto);
        BorrowRecord updateBorrowRecord = borrowRecordService.updateBorrowRecord(borrowRecordId, borrowRecord);
        return new ResponseEntity<>(borrowRecordMapper.mapFrom(updateBorrowRecord),
                HttpStatus.OK);
    }
}
