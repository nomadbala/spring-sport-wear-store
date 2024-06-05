package com.nmb.sportwear_store.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
@Builder
public class Role implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "name")
    private String name;

//    @ManyToMany(mappedBy = "roles", cascade = CascadeType.ALL)
//    private Set<User> users = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }
}