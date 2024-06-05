package com.nmb.sportwear_store.service;

import com.nmb.sportwear_store.dto.UserDTO;
import com.nmb.sportwear_store.dto.requests.*;
import com.nmb.sportwear_store.entity.User;
import com.nmb.sportwear_store.exception.FailedUserUpdateException;
import com.nmb.sportwear_store.exception.UserNotFoundException;
import com.nmb.sportwear_store.mapper.UserMapper;
import com.nmb.sportwear_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserManipulationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Modifying
    public void updateUsername(UpdateUsernameRequest request) throws FailedUserUpdateException {
        int updates = userRepository.updateUsername(request.id(), request.username());

        if (updates == 0) {
            throw new FailedUserUpdateException("Failed update username for user with id %d".formatted(request.id()));
        }
    }

    @Transactional
    @Modifying
    public void updateEmail(UpdateEmailRequest request) throws FailedUserUpdateException {
        int updates = userRepository.updateEmail(request.id(), request.email());

        if (updates == 0) {
            throw new FailedUserUpdateException("Failed update email for user with id %d".formatted(request.id()));
        }
    }

    @Transactional
    @Modifying
    public void updatePassword(UpdatePasswordRequest request) throws FailedUserUpdateException {
        String encodedPassword = passwordEncoder.encode(request.password());

        int updates = userRepository.updatePassword(request.id(), encodedPassword);

        if (updates == 0) {
            throw new FailedUserUpdateException("Failed update password for user with id %d".formatted(request.id()));
        }
    }

    @Transactional
    @Modifying
    public void updateContactNumber(UpdateContactNumberRequest request) throws FailedUserUpdateException {
        int updates = userRepository.updateContactNumber(request.id(), request.contactNumber());

        if (updates == 0) {
            throw new FailedUserUpdateException("Failed update contactNumber for user with id %d".formatted(request.id()));
        }
    }

    @Transactional
    @Modifying
    public void updateCountry(UpdateCountryRequest request) throws FailedUserUpdateException {
        int updates = userRepository.updateCountry(request.id(), request.country());

        if (updates == 0) {
            throw new FailedUserUpdateException("Failed update country for user with id %d".formatted(request.id()));
        }
    }

    @Transactional
    @Modifying
    public void updateCity(UpdateCityRequest request) throws FailedUserUpdateException {
        int updates = userRepository.updateCity(request.id(), request.city());

        if (updates == 0) {
            throw new FailedUserUpdateException("Failed update city for user with id %d".formatted(request.id()));
        }
    }

    @Transactional
    @Modifying
    public void updateFirstName(UpdateFirstNameRequest request) throws FailedUserUpdateException {
        int updates = userRepository.updateFirstName(request.id(), request.firstName());

        if (updates == 0) {
            throw new FailedUserUpdateException("Failed update firstName for user with id %d".formatted(request.id()));
        }
    }

    @Transactional
    @Modifying
    public void updateLastName(UpdateLastNameRequest request) throws FailedUserUpdateException {
        int updates = userRepository.updateLastName(request.id(), request.lastName());

        if (updates == 0) {
            throw new FailedUserUpdateException("Failed update lastName for user with id %d".formatted(request.id()));
        }
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) throws UserNotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id %d".formatted(id)));

        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    @Transactional(readOnly = true)
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<UserDTO> findAll() {
        return UserMapper.INSTANCE.usersToUsersDTO(userRepository.findAll());
    }
}
