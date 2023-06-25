package com.example.AppMV.service;

import com.example.AppMV.model.MetaData;
import com.example.AppMV.repository.MetaDataRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.NoSuchElementException;

@Transactional
@Service
public class MetaDataServiceImpl implements MetaDataService{

    @Autowired
    private MetaDataRepository metaDataRepository;
    public MetaDataServiceImpl() {
        super();
    }

    @Override
    public void updateMetadata(Long id, MetaData metaData) throws NoSuchElementException {
        MetaData md = metaDataRepository.findById(id).get();
        md.setNoOfReviews(metaData.getNoOfReviews());
        md.setRating(metaData.getRating());
        md.setNoOfDownloads(metaData.getNoOfDownloads());
        md.setAge(metaData.getAge());
        metaDataRepository.save(md);
    }

    @Override
    public MetaData getMetadata(Long id) throws NoSuchElementException {
        return metaDataRepository.findById(id).get();
    }

    @Override
    public Collection<MetaData> getAllMetadata() {
        return metaDataRepository.findAll();
    }
}
