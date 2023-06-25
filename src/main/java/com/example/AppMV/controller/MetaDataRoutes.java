package com.example.AppMV.controller;

import com.example.AppMV.model.MetaData;
import com.example.AppMV.service.MetaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/metadata")
public class MetaDataRoutes {
    @Autowired
    private MetaDataService metaDataService;

    @GetMapping()
    public ResponseEntity<Collection<MetaData>> getAll(){
        Collection<MetaData> md = metaDataService.getAllMetadata();
        return ResponseEntity.ok(md);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MetaData> get(@PathVariable("id") Long id){
        MetaData md;
        try {
            md = metaDataService.getMetadata(id);
        }catch (NoSuchElementException e){
            md = null;
        }
        return ResponseEntity.ok(md);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id,@RequestBody MetaData metaData){
        try {
            metaDataService.updateMetadata(id,metaData);
        }catch (NoSuchElementException e){
            return ResponseEntity.ok("No Publisher is registered with this id");
        }
        return ResponseEntity.ok("Meta Data is updated");
    }
}
