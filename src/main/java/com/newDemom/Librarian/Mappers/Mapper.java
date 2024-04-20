package com.newDemom.Librarian.Mappers;

public interface Mapper <A, B>{

    B mapFrom(A a);

    A MapTo(B b);


}
