package com.pablo.bands.services;

import com.pablo.bands.models.Event;
import com.pablo.bands.models.Venue;
import com.pablo.bands.repositories.EventRepo;
import com.pablo.bands.repositories.VenueRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VenueService {
    @Autowired
    private VenueRepo repo;

    public Venue createOrUpdate(Venue v) {
        return repo.save(v);
    }

    public Venue getOne(Long id) {
        // i for instance
        Optional<Venue> i = repo.findById(id);

        if(i.isPresent()) {
            return i.get();
        } else {
            return null;
        }
    }
    public List<Venue> getAll() {
        return repo.findAll();
    }
    public boolean deleteOne(Long id) {
        Optional<Venue> i = repo.findById(id);
        if(i.isPresent()) {
            repo.delete(i.get());
            return true;
        } else {
            return false;
        }
    }
}
