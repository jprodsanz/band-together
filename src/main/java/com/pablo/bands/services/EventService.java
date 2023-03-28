package com.pablo.bands.services;

import com.pablo.bands.models.Band;
import com.pablo.bands.models.Event;
import com.pablo.bands.repositories.BandRepo;
import com.pablo.bands.repositories.EventRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    @Autowired
    private EventRepo repo;

    public Event createOrUpdate(Event e) {
        return repo.save(e);
    }

    public Event getOne(Long id) {
        // i for instance
        Optional<Event> i = repo.findById(id);

        if(i.isPresent()) {
            return i.get();
        } else {
            return null;
        }
    }
    public List<Event> getAll() {
        return repo.findAll();
    }
    public boolean deleteOne(Long id) {
        Optional<Event> i = repo.findById(id);
        if(i.isPresent()) {
            repo.delete(i.get());
            return true;
        } else {
            return false;
        }
    }
}
