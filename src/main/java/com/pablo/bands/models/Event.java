package com.pablo.bands.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern ="yyyy-MM-dd")
    @NotEmpty
    private Date date;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "event_has_band",
            joinColumns = @JoinColumn(name= "event_id"),
            inverseJoinColumns = @JoinColumn(name = "band_id")
    )
    List<Band> bands;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="venue_id")
    private Venue venue;

    @Column(updatable = false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;

    public Event () {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Band> getBands() {
        return bands;
    }

    public void setBands(List<Band> bands) {
        this.bands = bands;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
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
