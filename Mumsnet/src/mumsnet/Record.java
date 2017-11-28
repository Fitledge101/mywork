/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mumsnet;

/**
 *
 * @author Ed
 */
public class Record {

    private final String recordType;
    private String expiryDate;
    private final String company;
    private int expiryMonth;
    private int expiryDay;
    private int expiryYear;
    

    public Record(String recordName, String expiryDate) {
        this.recordType = recordName;
        this.expiryDate = expiryDate;
        expiryMonth = 0;
        expiryDay = 0;
        expiryYear = 0;
        parseExpiry(expiryDate);
        company = "testcompany";

    }
    
    public void parseExpiry(String expiryDate){
        
           //return Integer.toString(expiryDate);
           //char[] chars = expiryDate.toCharArray();
           if (expiryDate.length()==8){
               
           
           try{
           expiryDay =Integer.parseInt(expiryDate.substring(0,1));
           expiryMonth = Integer.parseInt(expiryDate.substring(2,3));
           expiryYear = Integer.parseInt(expiryDate.substring(4,7));}
           catch(NumberFormatException ex){
               System.out.println("Date Should Be in Following Format DDMMYYYY");
           }
           }
           else{
               System.out.println("Too Short: Date should be in following format: DDMMYYYY");
           }
           
        
    }
    //this needs sorting really, if statements need to be more thorough as currenty don't cover things
    //like same month but wrong year etc and also new method that can search for within a range
    public boolean isExpired(int year, int month, int day){
        if (year>expiryYear){
            return true;
        }
        if (month>expiryMonth){
            return true;
        }
        if(day > expiryDay){
            return true;
        }
        return false;
    }
    
    public int getExpiryDay(){
        return expiryDay;
        
    }
    
    public int getExpiryMonth(){
        return expiryMonth;
        
    }
    
    public int getExpiryYear(){
        return expiryYear;
        
    }
    
    

    public void updateExpiry(String expiryDate) {
        this.expiryDate = expiryDate;
        parseExpiry(this.expiryDate);
    }

    public String getExpiry() {
        return expiryDate;
    }

    public String getName() {
        return recordType;
    }
    
    public String getNameAndExpiry(){
        
        return recordType + " expires on " + expiryDate;
    }

    public String getCompany() {

        return company;
    }

}
