package com.newDemom.Librarian.Domain.Dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class BorrowRecordDto {

    private long id;

    private long bookId;

    private  long patronId;

    private String returnDate;

    private String borrowDate;

    private String transactionStatus;
}
