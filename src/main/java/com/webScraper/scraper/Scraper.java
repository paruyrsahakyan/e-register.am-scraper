package com.webScraper.scraper;


import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.webScraper.scraper.company.Company;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Scraper {
    public static void main(String[] args)  {

        MongoClient mongoClient= new MongoClient(new MongoClientURI("mongodb+srv://paruyr:729326@cluster0-n3ktz.mongodb.net/test"));
        MongoDatabase mongoDatabase = mongoClient.getDatabase("E-register");
        MongoCollection mongoCollection = mongoDatabase.getCollection("Arm-Componies");

        // connecting to the web pages by iteration  of URL. Sends GET requests.
        for (int i = 1; i < 1000000; i++) {
            System.out.println("searching by: " + i);
            Document document = null;
            try {
                document = Jsoup.
                        connect("https://www.e-register.am/am/companies/" + Integer.toString(i) /*String.valueOf(i)*/)
                        .userAgent("Mozilla")
                        .cookie("auth", "token")
                        .timeout(3000)
                        .timeout(5000)
                        .get();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //checks if connected web page contains information of a company
            if (document.getElementsByClass("compName").size() == 1) {
                Company company = new Company();
                System.out.println("found a company");

                // sets information an sets the into the field of Company object
                company.setValues(document);

                // gets Json Document for a Company object for further inserting into db
                org.bson.Document JsonDocument = company.getMongoDoc();
                System.out.println(JsonDocument);

                mongoCollection.insertOne(JsonDocument);
                                    }

        }
    }
}