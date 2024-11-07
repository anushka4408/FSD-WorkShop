package com.anushka.fsdproject.service;

import com.anushka.fsdproject.entity.Participant;
import com.anushka.fsdproject.repository.ParticipantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ParticipantService {

    private final ParticipantRepository participantRepository;

    public ParticipantService(ParticipantRepository participantRepository) {
        this.participantRepository = participantRepository;
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Optional<Participant> getParticipantById(Long id) {
        return participantRepository.findById(id);
    }

    @Transactional
    public Participant createParticipant(Participant participant) {
        participant.setRegDate(new Date()); // Set registration date to current date
        return participantRepository.save(participant);
    }

    @Transactional
    public Participant updateParticipant(Long id, Participant updatedParticipant) {
        return participantRepository.findById(id)
                .map(participant -> {
                    participant.setParticipantName(updatedParticipant.getParticipantName());
                    participant.setParticipantPhone(updatedParticipant.getParticipantPhone());
                    participant.setParticipantEmail(updatedParticipant.getParticipantEmail());
                    return participantRepository.save(participant);
                })
                .orElseThrow(() -> new RuntimeException("Participant not found with id: " + id));
    }

    @Transactional
    public void deleteParticipant(Long id) {
        participantRepository.deleteById(id);
    }
}
