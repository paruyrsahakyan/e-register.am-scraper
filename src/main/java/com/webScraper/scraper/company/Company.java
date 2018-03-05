package com.webScraper.scraper.company;

import com.sun.xml.internal.ws.developer.MemberSubmissionEndpointReference;
import com.webScraper.scraper.Founder;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String companyType;
    private String name;
    private String status; //Կարգավիճակ
    private String recordNumber; //ԳրանցմանՀամար
    private String HVHH; //ՀՎՀՀ
    private String CDK; //    ՁԿԴ
    private List<Founder> founders = new ArrayList<Founder>(); //Հիմնադիր(ներ)

    public void setValues(Document document) {
        Elements tilteElement = document.getElementsByClass("compname");
        String  title = tilteElement.text();
        this.extractAndSetComponyType(title);
        this.setName(title);
//        System.out.println("company type:  " + companyType);
         Elements compinfo = document.getElementsByClass("fnam");
        for (Element element : compinfo) {
            switch (element.text().charAt(0)) {
                case 'Կ': {
                    this.setStatus(element.nextElementSibling().text());
                    break;
                }
                case 'Գ': {
                    this.setRecordNumber(element.nextElementSibling().text());
                    break;
                }
                case 'Հ': {
                    this.setHVHH(element.nextElementSibling().text());
                    break;
                }
                case 'Ձ': {
                    this.setCDK(element.nextElementSibling().text());
                    break;
                }
            }
        }
        System.out.println("status: " + status + "Record Number:  "+ recordNumber + "HVHH:  "+ HVHH+ "CDK:  "+ CDK);
        Elements founders = document.getElementsByAttributeValue("align", "left");
            if (founders!=null) {
            for (int i = 0; i < founders.size(); i = i+2) {
//                System.out.println(i);
                String founderName = founders.get(i).text();
//                System.out.println("founder name: " +founderName);
                String citizenship = founders.get(i + 1).text();
//                System.out.println("citizenship: " +citizenship);
                this.addFounder(new Founder(founderName, citizenship));
            }
        }

    }

    ///////////////////////////////////////////////////////////////

        @Override
    public String toString() {
        return "Company{" +
                "companyType='" + companyType + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", recordNumber='" + recordNumber + '\'' +
                ", HVHH='" + HVHH + '\'' +
                ", CDK='" + CDK + '\'' +
                ", Founders=" + founders +
                '}';
    }



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

    public List<Founder> getFounders() {
        return founders;
    }

    public void addFounder(Founder founders) {
        this.founders.add(founders);
    }

    //////////////////////////////////////////////////////////////////

    public void extractAndSetComponyType(String title){
        if (title.contains("Անհատ ձեռնարկատեր (Ա/Ձ)")){
            this.setCompanyType("Ա/Ձ");
        }
        else if (title.contains("ՍՊԸ")){
            this.setCompanyType("Սահմանափակ պատասխանատվությամբ ընկերություն (ՍՊԸ)");
        }
        else if (title.contains("ՓԲԸ")){
            this.setCompanyType("Փակ բաժնետիրական ընկերություն (ՓԲԸ)");
        }
        else if (title.contains("ԲԲԸ")){
            this.setCompanyType("Բաց բաժնետիրական ընկերություն (ԲԲԸ)");
        }
        else if (title.contains("ԳԱՁ")){
            this.setCompanyType("Գյուղացիական անհատական ձեռնարկություն (ԳԱՁ)");
        }
        else if (title.contains("Ը/Ձ")){
            this.setCompanyType("Ընտանեկան ձեռնարկություն (Ը/Ձ)");
        }
        else if (title.contains("ԳԸՁ")){
            this.setCompanyType("Գյուղացիական ընտանեկան ձեռնարկություն (ԳԸՁ)");
        }
        else if (title.contains("Լ(Տ)Ը")){
            this.setCompanyType("Լիակատար (տնտեսական) ընկերակցություն (Լ(Տ)Ը)");
        }
        else if (title.contains("(Կ(Տ)Ը)")){
            this.setCompanyType("Կոմանդիտային տնտեսական ընկերակցություն (Կ(Տ)Ը)");
        }
        else if (title.contains("ԱՍ")){
            this.setCompanyType("Առանձնացված ստորաբաժանում  (Ոչ առեւտրային) (ԱՍ)");
        }
        else if (title.contains("Դ/Ձ")){
            this.setCompanyType("Դուստր ձեռնարկություն (Դ/Ձ)");
        }
        else if (title.contains("Ա/Կ")){
            this.setCompanyType("Արտադրական կոոպերատիվ (Ա/Կ)");
        }
        else if (title.contains("Ս/Կ")){
            this.setCompanyType("Սպառողական կոոպերատիվ (Ս/Կ)");
        }
        else if (title.contains("Գ/ԿՏ")){
            this.setCompanyType("Գյուղացիական կոլեկտիվ տնտեսություն (Գ/ԿՏ)");
        }

        else if (title.contains("Պ/Ձ")){
            this.setCompanyType("Պետական (իշխանության տեղական մարմնի) ձեռնարկություն (Պ/Ձ)");
        }

        else if (title.contains("ԻԱՄ")){
            this.setCompanyType("Իրավաբանական անձանց միություն(ոչ առեւտրային) (ԻԱՄ)");
        }
        else if (title.contains("ԼՊԸ")){
            this.setCompanyType("Լրացուցիչ պատասխանատվությամբ ընկերություն (ԼՊԸ)");
        }
        else if (title.contains("ԻԿ")){
            this.setCompanyType("ԻՆՔՆԱԿԱՐԳԱՎՈՐՎՈՂ ԿԱԶՄԱԿԵՐՊՈՒԹՅՈՒՆ (ԻԿ)");
        }
        else if (title.contains("ՀՄԴ")){
            this.setCompanyType("ՀԻՄՆԱԴՐԱՄ (ՀՄԴ)");
        }
        else if (title.contains("ՀԿ")){
            this.setCompanyType("Հասարակական կազմակերպություն (ՀԿ)");
        }
        else if (title.contains("ԿՍ")){
            this.setCompanyType("Կուսակցություն (ԿՍ)");
        }
        else if (title.contains("ԿԿ")){
            this.setCompanyType("Կրոնական կազմակերպություն (ԿԿ)");
        }
        else if (title.contains("ՀԻՄՆԱՐԿ")){
            this.setCompanyType("Պետական կառավարչական հիմնարկ (ՀԻՄՆԱՐԿ)");
        }
        else if (title.contains("ՀՄՏ")){
            this.setCompanyType("Համատիրություն (ՀՄՏ)");
        }
        else if (title.contains("ԼՄ")){
            this.setCompanyType("Լրատվության միջոց (ԼՄ)");
        }
        else if (title.contains("ԱՐՀԿ")){
            this.setCompanyType("Արհեստակցական կազմակերպություն (ԱՐՀԿ)");
        }
        else if (title.contains("ՀՈԱԿ")){
            this.setCompanyType("Համայնքային ոչ առեւտրային կազմակերպություն (ՀՈԱԿ)");
        }
        else if (title.contains("ԱԱՊ")){
            this.setCompanyType("Առեւտրա-արդյունաբերական պալատ (ԱԱՊ)");
        }
        else if (title.contains("ՋԸ")){
            this.setCompanyType("Ջրօգտագործողների ընկերություն (ՋԸ)");
        }
        else if (title.contains("ԱՀ")){
            this.setCompanyType("Ակտիվների համախումբ (ԱՀ)");
        }
        else if (title.contains("ՋՄ")){
            this.setCompanyType("Ջրօգտագործողների ընկերությունների միություն (ՋՄ)");
        }
        else if (title.contains("ՆՊ")){
            this.setCompanyType("(ՆՊ)");
        }
        else if (title.contains("ՀԻՄՆԱՐԿ")){
            this.setCompanyType("Նախկին գործկոմներում գրանցված իրավաբանական անձ (ՀԻՄՆԱՐԿ)");
        }
        else if (title.contains("Գ/ՎԱ")){
            this.setCompanyType("Գյուղատնտեսական վարկային ակումբ (Գ/ՎԱ)");
        }
        else if (title.contains("ՓՊ")){
            this.setCompanyType("Փաստաբանների պալատ (ՓՊ)");
        }
        else if (title.contains("ԳՄ")){
            this.setCompanyType("Գործատուների միություն (ԳՄ)");
        }
        else if (title.contains("ՀՄ")){
            this.setCompanyType("ՀԻՄՆԱՐԿ (ՀՄ)");
        }
        else if (title.contains("ՊՈԱԿ")){
            this.setCompanyType("Պետական ոչ առեւտրային կազմակերպություն (ՊՈԱԿ)");
        }
        else if (title.contains("Հիմնարկ (*)")){
            this.setCompanyType("Պետռեգիստրից դուրս գրանցված հիմնարկ (Հիմնարկ (*))");
        }
        else if (title.contains("Համայնքային կառավարչական հիմնարկ")){
            this.setCompanyType("Համայնքային կառավարչական հիմնարկ (Համայնքային կառավարչական հիմնարկ)");
        }
        else if (title.contains("ՀՄ")){
            this.setCompanyType("ՀԻՄՆԱՐԿ (հիմնադիր` ոչ առեվտրային) (ՀՄ)");
        }
        else if (title.contains("ԻԱՄ")){
            this.setCompanyType("Իրավաբանական անձանց միություն ( առեւտրային ) (ԻԱՄ)");
        }

        else if (title.contains("ԱԲՆ")){
            this.setCompanyType("Արտասահմանյան բանկի ներկայացուցչություն ( ԱԲՆ)");
        }
        else if (title.contains("ՄՀ")){
            this.setCompanyType("Միջկառավարական համաձայնագիր ( ՄՀ)");
        }
        else if (title.contains("ոչ առեւտրային կազմակերպություն")){
            this.setCompanyType("ոչ առեւտրային կազմակերպություն ()");
        }
        else if (title.contains("ՖՀ")){
            this.setCompanyType("Ֆինանսական հաստատություն (ՖՀ)");
        }
        else if (title.contains("ՓՁ")){
            this.setCompanyType("Փոքր ձեռնարկություն (ՓՁ)");
        }
        else if (title.contains("ՀԿՔ")){
            this.setCompanyType("Հասարակական–քաղաքական կազմակերպություն (ՀԿՔ)");
        }
        else if (title.contains("ՄՈԱԿ")){
            this.setCompanyType("Միջկառավարական ոչ առեվտրային կազմակերպություն (ՄՈԱԿ)");
        }
        else if (title.contains("Գ/Կ")){
            this.setCompanyType("Գյուղատնտեսական կոոպերատիվ (Գ/Կ)");
        }
        else if (title.contains("ԱԿՀՄԴ")){
            this.setCompanyType("Անձեռնմխելի Կապիտալով Հիմնադրամ (ԱԿՀՄԴ)");
        }
        else if (title.contains("ՊՄՏ")){
            this.setCompanyType("Պետական մասնագիտացված տնտեսություն (ՊՄՏ)");
        }
       else {this.setCompanyType("not specified");}

    }
}
