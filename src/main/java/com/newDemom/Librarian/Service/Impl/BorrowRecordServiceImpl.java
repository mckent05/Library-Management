package com.newDemom.Librarian.Service.Impl;


import com.newDemom.Librarian.Domain.BookEntity;
import com.newDemom.Librarian.Domain.Dto.BorrowRecordDto;
import com.newDemom.Librarian.Domain.PatronEntity;
import com.newDemom.Librarian.Exception.LibrarianAPIException;
import com.newDemom.Librarian.Exception.ResourceNotFoundException;
import com.newDemom.Librarian.Repository.BookRepository;
import com.newDemom.Librarian.Repository.BorrowRecordRepository;
import com.newDemom.Librarian.Repository.PatronRepository;
import com.newDemom.Librarian.Service.BorrowRecordService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.newDemom.Librarian.Domain.BorrowRecord;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {
    
    private BookRepository bookRepository;
    
    private PatronRepository patronRepository;
    
    private BorrowRecordRepository borrowRecordRepository;

    public BorrowRecordServiceImpl(BookRepository bookRepository, 
                                   PatronRepository patronRepository, 
                                   BorrowRecordRepository borrowRecordRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.borrowRecordRepository = borrowRecordRepository;
    }

    @Override
    @Transactional
    public BorrowRecord createBorrowRecord(Long bookId, Long patronId, BorrowRecord borrowRecord) throws ParseException {
        BorrowRecord newborrowRecord = new BorrowRecord();
        SimpleDateFormat formatter = new SimpleDateFormat("yy/MM/dd");
        Date fBorrowDate = formatter.parse(borrowRecord.getBorrowDate());
        Date currentDate = formatter.parse(formatter.format(new Date()));
        BookEntity bookEntity = findBook(bookId);
        PatronEntity patronEntity = findPatron(patronId);
        if(fBorrowDate.before(currentDate)) {
            throw new LibrarianAPIException("Borrow date cannot be before today",
                    HttpStatus.BAD_REQUEST);
        }
        if(bookEntity.getBorrowedStatus()) {
            throw new LibrarianAPIException("This Book has already been borrowed",
                    HttpStatus.BAD_REQUEST);
        }
        bookEntity.setBorrowedStatus(true);
        bookRepository.save(bookEntity);
        newborrowRecord.setBook(bookEntity);
        newborrowRecord.setPatron(patronEntity);
        newborrowRecord.setBorrowDate(borrowRecord.getBorrowDate());
        newborrowRecord.setTransactionStatus("In Progress");
        borrowRecordRepository.save(newborrowRecord);

        return newborrowRecord;
    }

    @Override
    @Transactional
    public BorrowRecord updateBorrowRecord(Long borrowRecordId, BorrowRecord borrowRecord) throws ParseException {
        Date fReturnDate = new SimpleDateFormat("MM/dd/yy").parse(borrowRecord.getReturnDate());
        BorrowRecord getBorrowRecord = borrowRecordRepository.findById(borrowRecordId).orElseThrow(
                () -> new ResourceNotFoundException(borrowRecordId, "borrowId", "BorrowRecord")
        );
        Date fborrowDate = new SimpleDateFormat("MM/dd/yy").parse(getBorrowRecord.getBorrowDate());
        BookEntity bookEntity = getBorrowRecord.getBook();
        System.out.println(bookEntity.getBorrowedStatus());
        if (!getBorrowRecord.getTransactionStatus().equals("Completed")) {
            bookEntity.setBorrowedStatus(false);
            bookRepository.save(bookEntity);
            if (fReturnDate.before(fborrowDate)) {
                throw new LibrarianAPIException("Return date cannot be before the borrowed date",
                        HttpStatus.BAD_REQUEST);
            }
            getBorrowRecord.setReturnDate(borrowRecord.getReturnDate());
            getBorrowRecord.setTransactionStatus("Completed");
            borrowRecordRepository.save(getBorrowRecord);
            return getBorrowRecord;
        } else {
            throw new LibrarianAPIException("Transaction is Completed!!",
                    HttpStatus.BAD_REQUEST);
        }
    }

    private PatronEntity findPatron(Long patronId) {
        return patronRepository.findById(patronId).orElseThrow(() ->
                new ResourceNotFoundException(patronId, "patronId", "Patron"));
    }

    private BookEntity findBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() ->
                new ResourceNotFoundException(bookId, "bookId", "Book"));
    }


}
