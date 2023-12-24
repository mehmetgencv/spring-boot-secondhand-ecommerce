package com.mehmetgenc.secondhand.user.repository;

import com.mehmetgenc.secondhand.user.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
}
