package Controller;
import Model.*;
import View.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    public static void main(String args[]) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Login.display();
    }
}
