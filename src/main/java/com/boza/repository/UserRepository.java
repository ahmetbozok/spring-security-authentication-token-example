package com.boza.repository;

import com.boza.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    public UserModel getUserByUserName(String userName);
}
