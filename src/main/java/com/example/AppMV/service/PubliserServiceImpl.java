package com.example.AppMV.service;

import com.example.AppMV.model.MetaData;
import com.example.AppMV.model.Publisher;
import com.example.AppMV.repository.MetaDataRepository;
import com.example.AppMV.repository.PublisherRepository;
import jakarta.transaction.Transactional;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.NoSuchElementException;

@Transactional
@Service
public class PubliserServiceImpl implements PublisherService{
    @Autowired
    private PublisherRepository publisherRepository;
    @Autowired
    private MetaDataRepository metaDataRepository;

    public PubliserServiceImpl() {
        super();
    }

    @Override
    public ResponseEntity<String> register(Publisher pub) {
        String url = "https://play.google.com/store/apps/details?id=" + pub.getBundleId();
        try{
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            System.out.println("done connecting");
            con.setRequestProperty("User-Agent","Mozilla/5.0");
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type","application/json");
            int rescode = con.getResponseCode();
            if(rescode==200)
            {
                System.out.println("Application Validated");
                BufferedReader bufin = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder res = new StringBuilder();
                String line;
                while((line = bufin.readLine())!=null) {
                    res.append(line);
                }
                String html = res.toString();
                Document doc = Jsoup.parse(html);
                Elements content1 = doc.getElementsByClass("ClM7O");
                Elements content2 = doc.getElementsByClass("g1rdde");
                MetaData metaData = new MetaData();
                for(int i =0;i<content1.size();i++)
                {
                    String val = content1.get(i).text();
                    String key = content2.get(i).text();
                    if(key.contains("reviews")){
                        metaData.setRating(metadatautil.getRating(val));
                        metaData.setNoOfReviews(metadatautil.getReviews(key));
                    }else if(key.contains("Download")){
                        metaData.setNoOfDownloads(metadatautil.getDownloads(val));
                    }else if(key.contains("Editors' Choice")){
                        metaData.setEditorsChoice(Boolean.TRUE);
                    }else if(key.contains("Rated")){
                        metaData.setAge(metadatautil.getAge(key));
                    }else {
                        System.out.println(key);
                        System.out.println(val);
                    }
                }
                metaDataRepository.save(metaData);
                pub.setMetaData(metaData);
                publisherRepository.save(pub);
                return ResponseEntity.ok().body("Thank you for Registering");
            }else {
                return ResponseEntity.badRequest().body("There is a mistake in Bundle Id");
            }
        }catch (IOException e){
            System.out.println("URL is Malformed");
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("There is a mistake in Bundle Id");
        }
    }

    @Override
    public ResponseEntity<String> updateBundleId(Long id,Publisher pub) throws NoSuchElementException {
        String url = "https://play.google.com/store/apps/details?id=" + pub.getBundleId();
        try{
            URL obj = new URL(url);
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            System.out.println("done connecting");
            con.setRequestProperty("User-Agent","Mozilla/5.0");
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type","application/json");
            int rescode = con.getResponseCode();
            if(rescode==200)
            {
                Publisher publisher = publisherRepository.findById(id).get();
                publisher.setBundleId(pub.getBundleId());
                publisherRepository.save(publisher);
                return ResponseEntity.ok().body("BundleId is updated");
            }else {
                return ResponseEntity.badRequest().body("There is a mistake in Bundle Id");
            }
        }catch (IOException e){
            System.out.println("URL is Malformed");
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().body("There is a mistake in Bundle Id");
        }
    }

    @Override
    public void updateAppName(Long id,Publisher pub) throws NoSuchElementException{
        Publisher publisher = publisherRepository.findById(id).get();
        publisher.setAppName(pub.getAppName());
        publisherRepository.save(publisher);
    }

    @Override
    public Collection<Publisher> getPublisher() {
        return publisherRepository.findAll();
    }

    @Override
    public Publisher getPublisherbyId(Long pub_id) throws NoSuchElementException {
        return publisherRepository.findById(pub_id).get();
    }

    @Override
    public void derigester(Long pub_id) {
        publisherRepository.deleteById(pub_id);
    }
}
