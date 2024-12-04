package com.tms.security.domain;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String username;
        private String password;
        @ElementCollection(fetch = FetchType.EAGER)
        private Set<String> roles = new HashSet<>();
}
