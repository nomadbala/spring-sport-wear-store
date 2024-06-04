package com.nmb.sportwear_store.repository;

import com.nmb.sportwear_store.dto.LoginAttemptDTO;
import com.nmb.sportwear_store.entity.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {
    List<LoginAttempt> findByUsernameAndAttemptTimeAfter(String username, LocalDateTime after);
}
