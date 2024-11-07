package com.anushka.fsdproject.repository;

import com.anushka.fsdproject.entity.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    // Additional query methods can be defined here if needed
}
