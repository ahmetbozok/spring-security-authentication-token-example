package com.boza.repository;

import com.boza.entity.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticationTokenRepository extends JpaRepository<AuthenticationToken, Long> {
    public AuthenticationToken findByToken(String authenticationToken);
}
