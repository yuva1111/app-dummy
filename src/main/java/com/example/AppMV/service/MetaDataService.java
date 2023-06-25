package com.example.AppMV.service;

import com.example.AppMV.model.MetaData;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.NoSuchElementException;

public interface MetaDataService {
    void updateMetadata(Long id, MetaData metaData) throws NoSuchElementException;
    MetaData getMetadata(Long id) throws NoSuchElementException;
    Collection<MetaData> getAllMetadata();
}
