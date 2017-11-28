/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mumsnet;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.scene.control.TextField;

/**
 *
 * @author Ed
 */
public class RecordMaker {

   // ArrayList<Record> records;
    Button button1;
    private House house;
    private Record record;

    public RecordMaker(String title, String message, House house) {
       // records = new ArrayList();
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        TextField textField1 = new TextField("Name of record");
        TextField textField2 = new TextField("expiry date");
        button1 = new Button("make record");
        Button button2 = new Button("unused");
        button1.setOnAction(e -> {
            

            try {
                String expiry = textField2.getText();
                record = new Record(textField1.getText(), expiry);
            } catch (NumberFormatException ex) {
                System.out.println("Enter an Integer Please");

            }

           // records.add(record);

            house.addRecord(record);
        });
        this.house = house;

        VBox layout = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(button1, button2, textField1, textField2);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

    }
    //old record maker that doesn't require a House in its constructor
//    public RecordMaker(String title, String message){
//        records = new ArrayList();
//        Stage window = new Stage();
//        window.initModality(Modality.APPLICATION_MODAL);
//        window.setTitle(title);
//        window.setMinWidth(250);
//        button1 = new Button("hey cutie");
//        button1.setOnAction(e->{
//            String nameOfRecord = "holder";
//            Record record = new Record(nameOfRecord, 10);
//            records.add(record);
//            System.out.println("Record added: " + nameOfRecord);
//            window.close();
//        });
//        
//        VBox layout = new VBox(10);
//        house = null;
//        record = null;
//
//        //Add buttons
//        layout.getChildren().add(button1);
//        layout.setAlignment(Pos.CENTER);
//        Scene scene = new Scene(layout);
//        window.setScene(scene);
//        window.showAndWait();
//        
//    }

}
