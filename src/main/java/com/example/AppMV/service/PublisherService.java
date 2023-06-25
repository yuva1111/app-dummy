package com.example.AppMV.service;

import com.example.AppMV.model.Publisher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.NoSuchElementException;


public interface PublisherService {
    ResponseEntity<String> register(Publisher pub);
    ResponseEntity<String> updateBundleId(Long id,Publisher pub) throws NoSuchElementException;
    void updateAppName(Long id,Publisher pub) throws NoSuchElementException;
    Collection<Publisher> getPublisher();
    Publisher getPublisherbyId(Long pub_id) throws NoSuchElementException;
    void derigester(Long pub_id);
}
