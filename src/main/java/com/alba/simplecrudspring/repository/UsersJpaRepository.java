package com.alba.simplecrudspring.repository;

import com.alba.simplecrudspring.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UsersJpaRepository extends JpaRepository<Users, Long> {

    Users findByName(String name);
}
