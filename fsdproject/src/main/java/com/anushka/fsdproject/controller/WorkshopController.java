package com.anushka.fsdproject.controller;

import com.anushka.fsdproject.entity.Workshop;
import com.anushka.fsdproject.service.WorkshopService;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workshops")
public class WorkshopController {

    private static final Logger logger = LoggerFactory.getLogger(WorkshopController.class);
    private final WorkshopService workshopService;

    public WorkshopController(WorkshopService workshopService) {
        this.workshopService = workshopService;
    }

    @GetMapping
    public List<Workshop> getAllWorkshops() {
        logger.info("Fetching all workshops");
        return workshopService.getAllWorkshops();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Workshop> getWorkshopById(@PathVariable Long id) {
        logger.info("Fetching workshop with ID: {}", id);
        return workshopService.getWorkshopById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> {
                    logger.warn("Workshop with ID {} not found", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @PostMapping
    public ResponseEntity<Workshop> createWorkshop(@RequestBody Workshop workshop) {
        logger.info("Creating a new workshop: {}", workshop);
        Workshop createdWorkshop = workshopService.createWorkshop(workshop);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdWorkshop);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Workshop> updateWorkshop(@PathVariable Long id, @RequestBody Workshop workshop) {
        logger.info("Updating workshop with ID: {}", id);
        try {
            Workshop updatedWorkshop = workshopService.updateWorkshop(id, workshop);
            return ResponseEntity.ok(updatedWorkshop);
        } catch (EntityNotFoundException e) {
            logger.warn("Failed to update. Workshop with ID {} not found", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Internal server error while updating workshop with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkshop(@PathVariable Long id) {
        logger.info("Deleting workshop with ID: {}", id);
        try {
            workshopService.deleteWorkshop(id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            logger.warn("Failed to delete. Workshop with ID {} not found", id);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("Internal server error while deleting workshop with ID {}", id, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
