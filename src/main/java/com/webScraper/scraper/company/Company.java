package com.webScraper.scraper.company;

import com.webScraper.scraper.Founder;

import java.util.ArrayList;

public class Company {
    private String companyType;
    private String name;
    private String status; //Կարգավիճակ
    private String recordNumber; //ԳրանցմանՀամար
    private String HVHH; //ՀՎՀՀ
    private String CDK; //    ՁԿԴ
    private ArrayList<Founder>  Founders; //Հիմնադիր(ներ)


///////////////////    getters and setters///////////
    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(String recordNumber) {
        this.recordNumber = recordNumber;
    }

    public String getHVHH() {
        return HVHH;
    }

    public void setHVHH(String HVHH) {
        this.HVHH = HVHH;
    }

    public String getCDK() {
        return CDK;
    }

    public void setCDK(String CDK) {
        this.CDK = CDK;
    }

    public ArrayList<Founder> getFounders() {
        return Founders;
    }

    public void setFounders(ArrayList<Founder> founders) {
        Founders = founders;
    }
}
