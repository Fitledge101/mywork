
package javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Ed
 */
public class NewDisplay extends Application{
    Stage window;
    Button button;
    
    public static void main(String[] args){
        launch(args);
    }
    
    @Override
    public void start(Stage primarystage){
        window = primarystage;
        window.setTitle("test");
        button = new Button("click me");
        button.setOnAction(e-> {
            boolean result = NewConfirmBox.display("newpopup", "heyyy");
            System.out.println(result);
        });
        StackPane layout = new StackPane();
        layout.getChildren().add(button);
        Scene scene = new Scene(layout, 400, 300);
        window.setScene(scene);
        window.show();
    }
    
    
    
    
}
