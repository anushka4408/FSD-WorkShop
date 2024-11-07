package com.anushka.fsdproject.service;

import com.anushka.fsdproject.entity.Registration;
import com.anushka.fsdproject.repository.RegistrationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    public Optional<Registration> getRegistrationById(Long id) {
        return registrationRepository.findById(id);
    }

    @Transactional
    public Registration createRegistration(Registration registration) {
        registration.setRegDate(new Date()); // Set registration date to current date
        return registrationRepository.save(registration);
    }

    @Transactional
    public Registration updateRegistration(Long id, Registration updatedRegistration) {
        return registrationRepository.findById(id)
                .map(registration -> {
                    registration.setPaymentAmount(updatedRegistration.getPaymentAmount());
                    registration.setPaymentStatus(updatedRegistration.getPaymentStatus());
                    registration.setWorkshopId(updatedRegistration.getWorkshopId());
                    registration.setParticipantId(updatedRegistration.getParticipantId());
                    return registrationRepository.save(registration);
                })
                .orElseThrow(() -> new RuntimeException("Registration not found with id: " + id));
    }

    @Transactional
    public void deleteRegistration(Long id) {
        registrationRepository.deleteById(id);
    }
}
