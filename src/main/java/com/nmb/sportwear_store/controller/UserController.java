package com.nmb.sportwear_store.controller;

import com.nmb.sportwear_store.dto.requests.*;
import com.nmb.sportwear_store.exception.FailedUserUpdateException;
import com.nmb.sportwear_store.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService service;

    @PutMapping("/update_username")
    @ResponseStatus(HttpStatus.OK)
    public void updateUsername(@RequestBody UpdateUsernameRequest request) throws FailedUserUpdateException {
        service.updateUsername(request);
    }

    @PutMapping("/update_email")
    @ResponseStatus(HttpStatus.OK)
    public void updateEmail(@RequestBody UpdateEmailRequest request) throws FailedUserUpdateException {
        service.updateEmail(request);
    }

    @PutMapping("/update_password")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(@RequestBody UpdatePasswordRequest request) throws FailedUserUpdateException {
        service.updatePassword(request);
    }

    @PutMapping("/update_contactnumber")
    @ResponseStatus(HttpStatus.OK)
    public void updateContactNumber(@RequestBody UpdateContactNumberRequest request) throws FailedUserUpdateException {
        service.updateContactNumber(request);
    }

    @PutMapping("/update_country")
    @ResponseStatus(HttpStatus.OK)
    public void updateCountry(@RequestBody UpdateCountryRequest request) throws FailedUserUpdateException {
        service.updateCountry(request);
    }

    @PutMapping("/update_city")
    @ResponseStatus(HttpStatus.OK)
    public void updateCity(@RequestBody UpdateCityRequest request) throws FailedUserUpdateException {
        service.updateCity(request);
    }

    @PutMapping("/update_firstname")
    @ResponseStatus(HttpStatus.OK)
    public void updateFirstName(@RequestBody UpdateFirstNameRequest request) throws FailedUserUpdateException {
        service.updateFirstName(request);
    }

    @PutMapping("/update_lastname")
    @ResponseStatus(HttpStatus.OK)
    public void updateLastName(@RequestBody UpdateLastNameRequest request) throws FailedUserUpdateException {
        service.updateLastName(request);
    }
}
