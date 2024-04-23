package com.newDemom.Librarian.Service;

import com.newDemom.Librarian.Domain.BorrowRecord;
import com.newDemom.Librarian.Domain.Dto.BorrowRecordDto;

import java.text.ParseException;

public interface BorrowRecordService {

    BorrowRecord createBorrowRecord(Long bookId, Long patronId, BorrowRecord borrowRecord) throws ParseException;
    BorrowRecord updateBorrowRecord(Long borrowTransactionId, BorrowRecord borrowRecord) throws ParseException;
}
