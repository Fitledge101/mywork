/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mumsnet;

import java.time.LocalDate;
import java.time.ZoneId;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Date;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Ed
 */
public class Manager extends Application {

    Stage window;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    int year;
    int month;
    int day;
    private ArrayList<House> houses;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        houses = new ArrayList<>();
        window = primaryStage;
        window.setTitle("Mumsnet");
        button1 = new Button("Add House");
        button2 = new Button("Print Houses in List");
        button3 = new Button("Check Expiry Dates");
        button4 = new Button("make record");
        TextField textField1 = new TextField("House text");

        button1.setOnAction(e -> {
            String houseName = textField1.getText();
            houses.add(new House(houseName));

        });

        button2.setOnAction(e -> {
            int i = 0;
            for (House house : houses) {
                System.out.println("House Name is: " + house.getName());
                System.out.println("Record Number " + Integer.toString(i) + " is " + house.getRecord(0).getName()
                        + " Expiry Date is: " + house.getRecord(0).getExpiry());
                i++;
            }

        });

        button3.setOnAction(e -> {
            
                findExpired();
                

            
        });
        //needs to be able to work on other houses
        button4.setOnAction(e -> {
            RecordMaker recordVibe = new RecordMaker("nex vibe", "ayo hold up", houses.get(0));

        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
        layout.getChildren().addAll(button1, button2, textField1, button4, button3);
        Scene scene = new Scene(layout, 400, 500);
        window.setScene(scene);
        window.show();
        initialiseDate();

    }

    //atm just checks if any expired
    //needs to find within range specified by user
    //could put in another method
    
    public void findExpired(){
        boolean anyExpired = false;
        
        int i = 0;
        for (House house: houses){
            if (house.hasExpiredRecord(i, year, month, day)!=null){
                System.out.println("Expired Record: " + house.hasExpiredRecord(i, year, month, day));
                i++;
                anyExpired = true;
            }
        }
        if (anyExpired == false){
            System.out.println("No Records to Show");
        }
        
    }

    public void addHouse(String name, String address, String owners, int remainingMortgage, String manager, int currentRent) {
        House newHouse = new House(name, address, owners, remainingMortgage, manager, currentRent);
        houses.add(newHouse);

    }

    public void addCar(String name, String registration) {
        Car newCar = new Car(name, registration);

    }

    public void removeItem(int index) {

        houses.remove(index);

    }

    public void clearList() {
        houses.removeAll(houses);
    }

    public void initialiseDate() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        year = localDate.getYear();
        month = localDate.getMonthValue();
        day = localDate.getDayOfMonth();
        System.out.println("Day is " +Integer.toString(day)+ " Month is "+Integer.toString(month) + " Year is "+ Integer.toString(year) );
    }
}
