/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mumsnet;

import java.util.ArrayList;

/**
 *
 * @author Ed
 */
public class House {
    
    private final String name;
    private final String address;
    private String owners;
    private int remainingMortgage;
    private String manager;
    private int currentRent;
    private ArrayList<Record> records;
    
    
    public House (String name, String address, String owners, int remainingMortgage, String manager, int currentRent){
        this.name = name;
        this.address = address;
        this.owners = owners;
        this.remainingMortgage = remainingMortgage;
        this.manager = manager;
        this.currentRent= currentRent;
        records = new ArrayList();
    }
    
    public House(String name){
        this.name = name;
        this.address = "test";
        this.owners = "test";
        this.remainingMortgage = 100;
        this.manager = "test";
        this.currentRent= 100;
        records = new ArrayList();
    }
    
    
    
     public void addRecord(Record record){
        records.add(record);
        
    }
     public void makeRecord(){
         Record record = new Record("name", "");
         records.add(record);
     }
     
     public ArrayList<Record> getList(){
         return records;
     }
     
     
    
    public Record getRecord(int index){
        return records.get(index);
        
    }
    
    public String hasExpiredRecord(int position, int year, int month, int day){
        
        
        
        if (records.get(position).isExpired(year, month, day)){
            //return record;
            return records.get(position).getNameAndExpiry();
            }
        return "none expired";
    }
    
    
    
    
    
    public String getName(){
        return name;
    }
    
    public String getAddress(){
        return address;
    }
    public String getOwner(){
        return owners;
    }
    
    public int getMortgage(){
        return remainingMortgage;
    }
    public String getManager(){
        return manager;
    }
    public int getCurrentRent(){
        return currentRent;
    }
    public void changeRent(int rent){
        currentRent = rent;
        
    }
    public void changeMortgage(int mortgage){
        remainingMortgage = mortgage;
        
    }
    
    public void changeOwner(String owner){
        this.owners = owner;
        
    }
    
    public void changeManager(String manager){
        this.manager = manager;
        
    }
    
    
    
    
    
}

