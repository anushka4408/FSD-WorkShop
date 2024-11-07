package com.anushka.fsdproject.service;

import com.anushka.fsdproject.entity.Workshop;
import com.anushka.fsdproject.repository.WorkshopRepository;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkshopService {

    private static final Logger logger = LoggerFactory.getLogger(WorkshopService.class);
    private final WorkshopRepository workshopRepository;

    public WorkshopService(WorkshopRepository workshopRepository) {
        this.workshopRepository = workshopRepository;
    }

    public List<Workshop> getAllWorkshops() {
        logger.info("Retrieving all workshops from the database");
        return workshopRepository.findAll();
    }

    public Optional<Workshop> getWorkshopById(Long id) {
        logger.info("Retrieving workshop with ID: {}", id);
        return workshopRepository.findById(id);
    }

    public Workshop createWorkshop(Workshop workshop) {
        logger.info("Saving new workshop: {}", workshop);
        return workshopRepository.save(workshop);
    }

    public Workshop updateWorkshop(Long id, Workshop updatedWorkshop) {
        logger.info("Updating workshop with ID: {}", id);
        return workshopRepository.findById(id)
                .map(workshop -> {
                    workshop.setWorkshopName(updatedWorkshop.getWorkshopName());
                    workshop.setEventId(updatedWorkshop.getEventId());
                    workshop.setDate(updatedWorkshop.getDate());
                    workshop.setTime(updatedWorkshop.getTime());
                    return workshopRepository.save(workshop);
                })
                .orElseThrow(() -> {
                    logger.warn("Workshop with ID {} not found for update", id);
                    return new EntityNotFoundException("Workshop not found with id: " + id);
                });
    }

    public void deleteWorkshop(Long id) {
        logger.info("Attempting to delete workshop with ID: {}", id);
        if (!workshopRepository.existsById(id)) {
            logger.warn("Workshop with ID {} not found for deletion", id);
            throw new EntityNotFoundException("Workshop not found with id: " + id);
        }
        workshopRepository.deleteById(id);
        logger.info("Deleted workshop with ID: {}", id);
    }
}
