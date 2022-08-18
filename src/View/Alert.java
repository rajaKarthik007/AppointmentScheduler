package View;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Class which generates the alert boxes.
 */
public class Alert {
    /**
     *
     * @param title Title of the alert box.
     * @param message Message of the alert box.
     */
    public static void display(String title, String message){
        Stage window = new Stage();
        window.setTitle(title);
        window.initModality(Modality.APPLICATION_MODAL); // this line makes sure that all screens below this stage are not in use
        window.setMinWidth(300);

        Label label = new Label(message);
        Button proceed = new Button("OK");
        proceed.setOnAction(e -> {
            window.close();
        });
        VBox layout = new VBox(25);
        layout.getChildren().addAll(label, proceed);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
