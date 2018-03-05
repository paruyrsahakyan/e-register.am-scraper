package com.webScraper.scraper;


import com.webScraper.scraper.company.Company;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Scraper {
    public static void main(String[] args)  {
        List<Company> companyList = new ArrayList<Company>();

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
            if (document.getElementsByClass("compName").size() == 1) {
                Company company = new Company();
                company.setValues(document);
                companyList.add(company);
                    }

        }
    }
}