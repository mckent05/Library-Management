package com.newDemom.Librarian.Repository;

import com.newDemom.Librarian.Domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Long> {

    @Query("""
            select t from token t inner join user u on t.user.id = u.id
            where u.id = :userId and (t.expired = false or t.revoked = false
            """)
    List<Token> findAllUserTokens(long userId);

    Optional<Token> findByToken(String token);
}
