package com.nmb.sportwear_store.service;

import com.nmb.sportwear_store.dto.UserDTO;
import com.nmb.sportwear_store.dto.requests.RegistrationRequest;
import com.nmb.sportwear_store.entity.Role;
import com.nmb.sportwear_store.entity.User;
import com.nmb.sportwear_store.mapper.UserMapper;
import com.nmb.sportwear_store.repository.RoleRepository;
import com.nmb.sportwear_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class RegistrationService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDTO register(RegistrationRequest request) {
        Role role = roleRepository.findByName("ROLE_USER")
                .orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));

        User requestUser = UserMapper.INSTANCE.registerRequestToUser(request, passwordEncoder, role);

        User savedUser = userRepository.save(requestUser);
        return UserMapper.INSTANCE.userToUserDTO(savedUser);
    }
}
