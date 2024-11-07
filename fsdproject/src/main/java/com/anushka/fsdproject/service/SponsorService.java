package com.anushka.fsdproject.service;

import com.anushka.fsdproject.entity.Sponsor;
import com.anushka.fsdproject.repository.SponsorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SponsorService {

    @Autowired
    private SponsorRepository sponsorRepository;

    public List<Sponsor> getAllSponsors() {
        return sponsorRepository.findAll();
    }

    public Optional<Sponsor> getSponsorById(Long sponsId) {
        return sponsorRepository.findById(sponsId);
    }

    public Sponsor addSponsor(Sponsor sponsor) {
        return sponsorRepository.save(sponsor);
    }

    public Sponsor updateSponsor(Long sponsId, Sponsor sponsorDetails) {
        Sponsor sponsor = sponsorRepository.findById(sponsId)
                .orElseThrow(() -> new RuntimeException("Sponsor not found"));
        sponsor.setSponsAmount(sponsorDetails.getSponsAmount());
        sponsor.setContactPerson(sponsorDetails.getContactPerson());
        sponsor.setSponsEmail(sponsorDetails.getSponsEmail());
        return sponsorRepository.save(sponsor);
    }

    public void deleteSponsor(Long sponsId) {
        Sponsor sponsor = sponsorRepository.findById(sponsId)
                .orElseThrow(() -> new RuntimeException("Sponsor not found"));
        sponsorRepository.delete(sponsor);
    }
}
