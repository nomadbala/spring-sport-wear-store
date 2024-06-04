package com.nmb.sportwear_store.service;

import com.nmb.sportwear_store.dto.JwtToken;
import com.nmb.sportwear_store.dto.UserDTO;
import com.nmb.sportwear_store.dto.requests.*;
import com.nmb.sportwear_store.exception.FailedUserUpdateException;
import com.nmb.sportwear_store.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
    private final RegistrationService registrationService;

    private final AuthenticationService authenticationService;

    private final UserManipulationService userManipulationService;

    public UserDTO register(RegistrationRequest request) {
        return registrationService.register(request);
    }

    public JwtToken authenticate(LoginRequest request) {
        return authenticationService.authenticate(request);
    }

    public void updateUsername(UpdateUsernameRequest request) throws FailedUserUpdateException {
        userManipulationService.updateUsername(request);
    }

    public void updateEmail(UpdateEmailRequest request) throws FailedUserUpdateException {
        userManipulationService.updateEmail(request);
    }

    public void updatePassword(UpdatePasswordRequest request) throws FailedUserUpdateException {
        userManipulationService.updatePassword(request);
    }

    public void updateContactNumber(UpdateContactNumberRequest request) throws FailedUserUpdateException {
        userManipulationService.updateContactNumber(request);
    }

    public void updateCountry(UpdateCountryRequest request) throws FailedUserUpdateException {
        userManipulationService.updateCountry(request);
    }

    public void updateCity(UpdateCityRequest request) throws FailedUserUpdateException {
        userManipulationService.updateCity(request);
    }

    public void updateFirstName(UpdateFirstNameRequest request) throws FailedUserUpdateException {
        userManipulationService.updateFirstName(request);
    }

    public void updateLastName(UpdateLastNameRequest request) throws FailedUserUpdateException {
        userManipulationService.updateLastName(request);
    }

    public UserDTO findById(Long id) throws UserNotFoundException {
        return userManipulationService.findById(id);
    }

    public void deleteById(Long id) {
        userManipulationService.deleteById(id);
    }

    public List<UserDTO> findAll() {
        return userManipulationService.findAll();
    }

}
