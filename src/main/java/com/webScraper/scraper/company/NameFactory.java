package com.webScraper.scraper.company;

public class NameFactory {

        private StringBuilder name;
        private char lastLetter;

        private boolean hasMoreCases;
        public NameFactory(){
            name = new StringBuilder("ա");
            lastLetter='ա';
            hasMoreCases = true;
        }

        public void addLetter(){

            this.name =name.append('ա');
            lastLetter ='ա';

                    }
                    public void changLastLetter(){
            if (lastLetter =='և') {
                name.setCharAt((name.length() - 1), ' ');
                lastLetter=' ';
            }
            else if (lastLetter ==' ') {
                name.setCharAt((name.length() - 1), '.');
                lastLetter='.';
            }
                       else if (lastLetter =='.') {
                            name.setCharAt((name.length() - 1), '-');
                lastLetter='-';
                            }

            else if (lastLetter =='-') {
                if (name.length() == 1) {
                    hasMoreCases = false;
                } else {
                    name.deleteCharAt(name.length() - 1);
                    lastLetter = name.charAt(name.length() - 1);
                    changLastLetter();
                }
            }
            else {
                name.setCharAt((name.length() - 1), (char)(lastLetter+1));
                lastLetter= (char)(lastLetter+1);
            }

                    }
                    public boolean hasMoreCases(){
                        return hasMoreCases;
                    }

        public String getName(){
            return name.toString();
        }


}
