package com.nmb.sportwear_store.service;

import com.nmb.sportwear_store.entity.LoginAttempt;
import com.nmb.sportwear_store.repository.LoginAttemptRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
public class LoginAttemptService {
    private final LoginAttemptRepository repository;

    private final int loginAttemptLimit = 3;

    private final int blockTimeMinutes = 10;

    public void loginSucceeded(String username) {
        List<LoginAttempt> attempts = repository.findByUsernameAndAttemptTimeAfter(
                username, LocalDateTime.now().minusMinutes(blockTimeMinutes)
        );

        attempts.forEach(attempt -> {
            if (!attempt.isSuccessful()) {
                attempt.setSuccessful(true);
                repository.save(attempt);
            }
        });
    }

    public void loginFailed(String username) {
        LoginAttempt attempt = LoginAttempt.builder()
                .username(username)
                .attemptTime(LocalDateTime.now())
                .successful(false)
                .build();

        repository.save(attempt);
    }

    public boolean isBlocked(String username) {
        List<LoginAttempt> attempts = repository.findByUsernameAndAttemptTimeAfter(
                username, LocalDateTime.now().minusMinutes(blockTimeMinutes)
        );

        long failedAttempts = attempts.stream()
                .filter(attempt -> !attempt.isSuccessful())
                .count();

        return failedAttempts >= loginAttemptLimit;
    }
}
