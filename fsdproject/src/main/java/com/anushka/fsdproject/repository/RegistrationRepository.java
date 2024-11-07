package com.anushka.fsdproject.repository;

import com.anushka.fsdproject.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    // Additional custom query methods can be added here if needed
}
