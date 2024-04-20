package com.newDemom.Librarian.Domain;

import jakarta.persistence.*;
import lombok.*;


@Data
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "patrons")
public class PatronEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String contactAddress;

    private String phoneNumber;


}
