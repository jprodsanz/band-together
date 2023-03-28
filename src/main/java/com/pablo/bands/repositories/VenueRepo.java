package com.pablo.bands.repositories;


import com.pablo.bands.models.Venue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VenueRepo extends CrudRepository<Venue, Long> {
    List<Venue> findAll();
}
