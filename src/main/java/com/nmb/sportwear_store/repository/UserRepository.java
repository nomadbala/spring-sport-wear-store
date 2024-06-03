package com.nmb.sportwear_store.repository;

import com.nmb.sportwear_store.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Modifying
    @Query("UPDATE User u SET u.username = :username WHERE u.id = :id")
    int updateUsername(Long id, String username);

    @Modifying
    @Query("UPDATE User u SET u.email = :email WHERE u.id = :id")
    int updateEmail(Long id, String email);

    @Modifying
    @Query("UPDATE User u SET u.password = :password WHERE u.id = :id")
    int updatePassword(Long id, String password);

    @Modifying
    @Query("UPDATE User u SET u.contactNumber = :contactNumber WHERE u.id = :id")
    int updateContactNumber(Long id, String contactNumber);

    @Modifying
    @Query("UPDATE User u SET u.country = :country WHERE u.id = :id")
    int updateCountry(Long id, String country);

    @Modifying
    @Query("UPDATE User u SET u.city = :city WHERE u.id = :id")
    int updateCity(Long id, String city);

    @Modifying
    @Query("UPDATE User u SET u.firstName = :firstName WHERE u.id = :id")
    int updateFirstName(Long id, String firstName);

    @Modifying
    @Query("UPDATE User u SET u.lastName = :lastName WHERE u.id = :id")
    int updateLastName(Long id, String lastName);
}
