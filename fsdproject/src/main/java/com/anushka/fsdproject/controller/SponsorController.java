package com.anushka.fsdproject.controller;

import com.anushka.fsdproject.entity.Sponsor;
import com.anushka.fsdproject.service.SponsorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sponsors")
public class SponsorController {

    @Autowired
    private SponsorService sponsorService;

    @GetMapping
    public List<Sponsor> getAllSponsors() {
        return sponsorService.getAllSponsors();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sponsor> getSponsorById(@PathVariable("id") Long sponsId) {
        return sponsorService.getSponsorById(sponsId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Sponsor createSponsor(@RequestBody Sponsor sponsor) {
        return sponsorService.addSponsor(sponsor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sponsor> updateSponsor(@PathVariable("id") Long sponsId,
            @RequestBody Sponsor sponsorDetails) {
        try {
            Sponsor updatedSponsor = sponsorService.updateSponsor(sponsId, sponsorDetails);
            return ResponseEntity.ok(updatedSponsor);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSponsor(@PathVariable("id") Long sponsId) {
        try {
            sponsorService.deleteSponsor(sponsId);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
