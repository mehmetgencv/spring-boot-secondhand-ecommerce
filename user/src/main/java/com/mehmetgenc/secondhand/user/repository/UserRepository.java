package com.mehmetgenc.secondhand.user.repository;

import com.mehmetgenc.secondhand.user.model.User;
import com.mehmetgenc.secondhand.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByMail(String mail);
}
