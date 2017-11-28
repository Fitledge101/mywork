/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafx;

import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

/**
 *
 * @author Ed
 */
public class NewConfirmBox {
    
    static boolean answer;
    
    public static boolean display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);
        Button yesButton = new Button("yes");
        Button noButton = new Button("no");
        
        yesButton.setOnAction(e->{
            answer = true;
            window.close();
        });
        
        noButton.setOnAction(e->{
            answer = false;
            window.close();
        });
        
        VBox layout = new VBox(10);
        
        layout.getChildren().addAll(label, yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        
        return answer;
        
        
    }
    
    
    
}
