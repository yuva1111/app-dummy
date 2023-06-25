package com.example.AppMV.controller;

import com.example.AppMV.model.Publisher;
import com.example.AppMV.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RequestMapping("/api/v1/publisher")
@RestController
public class PublisherRoutes {
    @Autowired
    private PublisherService publisherService;

    @PostMapping()
    public ResponseEntity<String> register(@RequestBody Publisher pub) {
        System.out.println(pub.getBundleId());
        System.out.println(pub.getAppName());
        return publisherService.register(pub);
    }
    @GetMapping()
    public ResponseEntity<Collection<Publisher>> getAll(){
        Collection<Publisher> pubList = publisherService.getPublisher();
        return ResponseEntity.ok(pubList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publisher> getbyId(@PathVariable("id") Long id) {
        Publisher pub;
        try{
            pub = publisherService.getPublisherbyId(id);
        }catch(NoSuchElementException e){
            pub = null;
        }
        return ResponseEntity.ok(pub);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id,@RequestBody Publisher pub){
        try {
            return publisherService.updateBundleId(id,pub);
        }catch (NoSuchElementException e){
            return ResponseEntity.ok("No publisher with that id is registered");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        publisherService.derigester(id);
        return ResponseEntity.ok("Application is deregistered");
    }
}
