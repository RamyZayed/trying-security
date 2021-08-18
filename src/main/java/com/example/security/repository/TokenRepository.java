package com.example.security.repository;

import com.example.security.entity.VerificationToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends CrudRepository<VerificationToken,Integer> {

    VerificationToken findByToken(String token);
}
