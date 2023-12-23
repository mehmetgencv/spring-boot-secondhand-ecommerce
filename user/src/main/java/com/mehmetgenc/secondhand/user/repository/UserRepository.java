package com.mehmetgenc.secondhand.user.repository;

import com.mehmetgenc.secondhand.user.model.User;
import com.mehmetgenc.secondhand.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByMail(String mail);
}
