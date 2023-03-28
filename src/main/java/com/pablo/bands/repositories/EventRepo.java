package com.pablo.bands.repositories;

import com.pablo.bands.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepo extends CrudRepository<Event, Long> {
    List<Event> findAll();
}
