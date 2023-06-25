package com.example.AppMV.model;

import jakarta.persistence.*;

@Entity
@Table(name = "MetaData")
public class MetaData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Downloads")
    private Long noOfDownloads;
    @Column(name = "Rating")
    private Double rating;
    @Column(name = "Reviews")
    private Long noOfReviews;
    @Column(name = "Age")
    private Integer age;

    @Column(name = "EditorsChoice")
    private Boolean editorsChoice;
    //@OneToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "publisher_id")
    //private Publisher publisher;

    public MetaData() {
    }

    public MetaData(Long id, Long noOfDownloads, Double rating, Long noOfReviews, Integer age, Boolean editorsChoice) {
        this.id = id;
        this.noOfDownloads = noOfDownloads;
        this.rating = rating;
        this.noOfReviews = noOfReviews;
        this.age = age;
        this.editorsChoice = editorsChoice;
    }

    public Long getNoOfDownloads() {
        return noOfDownloads;
    }

    public void setNoOfDownloads(Long noOfDownloads) {
        this.noOfDownloads = noOfDownloads;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getNoOfReviews() {
        return noOfReviews;
    }

    public void setNoOfReviews(Long noOfReviews) {
        this.noOfReviews = noOfReviews;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getEditorsChoice() {
        return editorsChoice;
    }

    public void setEditorsChoice(Boolean editorsChoice) {
        this.editorsChoice = editorsChoice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
