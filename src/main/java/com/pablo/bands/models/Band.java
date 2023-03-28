package com.pablo.bands.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bands")
public class Band {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Band name is required")
    @Size(min = 3, max = 12, message = "Band name must be 3 and 12 characters long")
    private String bandName;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "band_has_user",
            joinColumns = @JoinColumn(name = "band_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    // users can join and become members of a band
    List<User> members;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_has_band",
            joinColumns = @JoinColumn(name= "band_id"),
            inverseJoinColumns = @JoinColumn(name = "event_id")
    )
    List<Event> events;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    public Band() {}

    public Band(String bandName) {
        this.bandName = bandName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }


    public Date getCreatedAt() {
        return createdAt;
    }

    @PrePersist
    // is this protected or public
    public void onCreate(){
        this.createdAt = new Date();
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    @PreUpdate
    // is this protected or public
    public void onUpdate(){
        this.updatedAt = new Date();
    }
}
