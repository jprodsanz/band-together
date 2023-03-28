package com.pablo.bands.repositories;

import com.pablo.bands.models.Band;
import com.pablo.bands.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BandRepo extends CrudRepository<Band, Long> {
    List<Band> findAll();
    // make a function that returns all bands a user is not a part of

    List<Band>findByMembersNotContaining(User u);
}
