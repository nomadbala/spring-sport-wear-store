package com.nmb.sportwear_store.mapper;

import com.nmb.sportwear_store.dto.UserDTO;
import com.nmb.sportwear_store.dto.requests.RegistrationRequest;
import com.nmb.sportwear_store.entity.Role;
import com.nmb.sportwear_store.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "email", expression = "java(request.email())")
    @Mapping(target = "password", expression = "java(passwordEncoder.encode(request.password()))")
    @Mapping(target = "roles", expression = "java(java.util.Collections.singleton(role))")
    User registerRequestToUser(RegistrationRequest request, PasswordEncoder passwordEncoder, Role role);

    UserDTO userToUserDTO(User user);

    List<UserDTO> usersToUsersDTO(List<User> users);
}
