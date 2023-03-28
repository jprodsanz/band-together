package com.pablo.bands.services;

import com.pablo.bands.models.Band;
import com.pablo.bands.models.User;
import com.pablo.bands.repositories.BandRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BandService {
    @Autowired
    private BandRepo repo;

    public Band createOrUpdate(Band b) {
        return repo.save(b);
    }

    public Band getOne(Long id) {
        // i for instance
        Optional<Band> i = repo.findById(id);

        if(i.isPresent()) {
            return i.get();
        } else {
            return null;
        }
    }
    public List<Band> getAll() {
        return repo.findAll();
    }
    public List<Band> getAllWithoutUser(User u) {
        return repo.findByMembersNotContaining(u);
    }
    public boolean deleteOne(Long id) {
        Optional<Band> i = repo.findById(id);
        if(i.isPresent()) {
            repo.delete(i.get());
            return true;
        } else {
            return false;
        }
    }
}
