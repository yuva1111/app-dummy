package com.example.AppMV.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Publisher")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "AppName")
    private String appName;
    @Column(name = "BundleId",unique = true)
    private String bundleId;
    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "metadata_id")
    private MetaData metaData;

    public Publisher(){

    }

    public Publisher(Long id, String appName, String bundleId, MetaData metaData) {
        this.id = id;
        this.appName = appName;
        this.bundleId = bundleId;
        this.metaData = metaData;
    }
    public Long getId() {
        return id;
    }

    public String getAppName() {
        return appName;
    }

    public String getBundleId() {
        return bundleId;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public void setBundleId(String bundleId) {
        this.bundleId = bundleId;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }
}
