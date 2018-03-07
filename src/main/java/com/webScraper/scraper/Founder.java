package com.webScraper.scraper;

// Հիմնադիր

public class Founder {
    private String  name;
    private String cityzenship;

    public Founder(String name, String cityzenship) {
        this.name = name;
        this.cityzenship = cityzenship;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityzenship() {
        return cityzenship;
    }

    public void setCityzenship(String cityzenship) {
        this.cityzenship = cityzenship;
    }


}
