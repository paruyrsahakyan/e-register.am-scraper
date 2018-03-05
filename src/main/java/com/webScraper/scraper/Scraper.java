package com.webScraper.scraper;


import com.webScraper.scraper.company.NameFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Scraper {
    public static void main(String[] args) throws IOException {

        Set<String> allCompaniesRefs = new HashSet<String>();
        NameFactory nameFactory = new NameFactory();
        int resultQuantity;
//        for(int i= 1; i<100;i++){
//            nameFactory.changLastLetter();
//            System.out.println(nameFactory.getName());
//        }

           do {
               Elements compRefElements = extractRefElements(nameFactory.getName());
               System.out.println(nameFactory.getName());////////////////////////////////////////////////

               resultQuantity = compRefElements.size();
                   for (Element href : compRefElements) {
                       System.out.println(href.toString());
                       String relativUrl = href.getElementsByAttribute("href").attr("href");
//                       System.out.println(href.getElementsByAttribute("href").attr("href"));
                       allCompaniesRefs.add(relativUrl);
                   }
                   if (resultQuantity==200){
                       nameFactory.addLetter();
                       System.out.println("letter added");
                   }
                   else {nameFactory.changLastLetter();
                       System.out.println("letter changed");
                                      }

//               nameFactory.changLastLetter();
           }
        while(nameFactory.hasMoreCases());
           for (String ref : allCompaniesRefs){
               System.out.println(ref);

           }

//              for(Element element: compRefElements){
//            Elements hrefs= element.getElementsByAttribute("href");
//            for(Element href: hrefs){
//                ArrayList<String> links =new ArrayList<String>();
//                String relativUrl = href.attr("href");
//                Document documents = Jsoup.
//                        connect("https://www.e-register.am"+relativUrl).
//                        userAgent("Mozilla")
//                        .cookie("auth", "token")
//                        .timeout(3000)
//                        .get();
//            System.out.println(documents.body().text());


//            }
//
//
//        }

//        System.out.println(element.text());

    }
   private static Elements extractRefElements(String compName) throws IOException {

       Document searchPage = Jsoup.
               connect("https://www.e-register.am/am/search").
               data("q_comp", compName).
               data("search_type", "exact_word").
               userAgent("Mozilla")
               .cookie("auth", "token")
               .timeout(3000)
               .post();
       Element searchPageBody = searchPage.body();
       return searchPageBody.getElementsByClass("datarow");
   }
}
