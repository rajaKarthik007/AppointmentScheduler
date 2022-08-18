package View;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;

import java.sql.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;

/**
 * The main class that starts the View.Appointment scheduler application
 */
public class Login {
    static Boolean translate = true;

    static String  url = "jdbc:mysql://localhost:3306/appointments";
    static String dbuname = "root";
    static String dbpass = "root";



    static Translate d = new Translate();
    static HashMap<String,String> LoginMap = d.getmap();

    /**
     *
     * This method sets the stage for the home page of the application.
     * @throws Exception  If an object that is being called cannot be found.
     */
    public static void display() throws Exception {
        Stage window;
        Scene scene;
        Stage stage = new Stage();
        Translate d = new Translate();
        HashMap<String,String> LoginMap = d.getmap();
        window = stage;
        window.setTitle("View.Login Page");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        Label nameLabel = new Label("User ID");
        GridPane.setConstraints(nameLabel,0,0);

        TextField nameInput = new TextField();
        nameInput.setPromptText("User ID");
        GridPane.setConstraints(nameInput,1,0);

        Label passLabel = new Label("Password");
        GridPane.setConstraints(passLabel,0,1);

        PasswordField passInput = new PasswordField();
        passInput.setPromptText("Password");
        GridPane.setConstraints(passInput,1,1);

        Button loginButton = new Button("Log In");
        loginButton.setOnAction(e -> {
            try{
                validate(passInput, nameInput, window);
            }catch (Exception ex){
                ex.printStackTrace();
            }

        });
        GridPane.setConstraints(loginButton,1,2);
        loginButton.setMinWidth(150);

        Button resetButton = new Button(" Reset");
        GridPane.setConstraints(resetButton,2,2);
        resetButton.setMinWidth(150);
        resetButton.setOnAction(e->{
            nameInput.clear();
            passInput.clear();
        });

        Label languageLabel = new Label("Language");
        GridPane.setConstraints(languageLabel,0,3);

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.setValue("English");
        choiceBox.getItems().add("English");
        choiceBox.getItems().add("French");
        GridPane.setConstraints(choiceBox,1,3);
        choiceBox.setMinWidth(150);

        Label timezone = new Label("Timezone");
        GridPane.setConstraints(timezone,0,4);

        Label zoneInput = new Label(ZoneId.systemDefault().toString());
        GridPane.setConstraints(zoneInput,1,4);
        grid.setHgap(30);
        grid.setVgap(30);

        choiceBox.setOnAction(e->{
            if(choiceBox.getValue()=="French"){
                translate = false;
                nameLabel.setText(LoginMap.get("Username"));
                passLabel.setText(LoginMap.get("Password"));
                loginButton.setText(LoginMap.get("Log In"));
                resetButton.setText(LoginMap.get("Reset"));
                languageLabel.setText(LoginMap.get("Language"));
                timezone.setText(LoginMap.get("Timezone"));
            }
            if(choiceBox.getValue()=="English"){
                translate = true;
                nameLabel.setText("Username");
                passLabel.setText("Password");
                loginButton.setText("Log In");
                resetButton.setText("Reset");
                languageLabel.setText("Language");
                timezone.setText("Timezone");
            }
        });

        grid.getChildren().addAll(nameLabel,nameInput,passLabel,passInput,loginButton,resetButton,languageLabel,choiceBox,timezone,zoneInput);
        scene = new Scene(grid,600,400);
        window.setScene(scene);
        window.show();
    }

    /**
     * function to validate the userID and password entered by the User
     * @param p password field where the user enters the password
     * @param t text field where the user enters the user id
     * @throws Exception  If an object that is being called cannot be found.
     */
    private static void validate(PasswordField p, TextField t, Stage window) throws Exception{

        String pass = p.getText();
        if(t.getText() == "" || pass == ""){
            if(t.getText() == ""){
                t.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
            }else {
                t.setStyle(null);
            }if(pass == ""){
                    p.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
                }else{
                p.setStyle(null);
            }
        }else{
            t.setStyle(null);
            p.setStyle(null);
            try{
                int uname = Integer.parseInt(t.getText());
                Path file = Paths.get("login_activity.txt");
//            PrintWriter writer = new PrintWriter("login_activity.txt", "UTF-8");
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Calendar date = Calendar.getInstance();

                String query = "select password from users where userid="+uname;
                try{
                    Connection con = DriverManager.getConnection(url, dbuname, dbpass);
                    Statement stmt = con.createStatement();
                    ResultSet result = stmt.executeQuery(query);
//                System.out.println(result);
                    if(result.next() == false){
                        Alert.display(translate?"View.Alert!":LoginMap.get("View.Alert!"),translate?"The entered userID does not exist. please try again.":LoginMap.get("The entered userID does not exist. please try again."));
                        Files.write(file, Arrays.asList("View.Login Attempted. TimeStamp: "+ dateFormat.format(date.getTime()) +" userid:"+ uname +" Status: Failed. UserID not found "), StandardCharsets.UTF_8, StandardOpenOption.APPEND);
//                    System.out.println("user id does not exist");
                    }else{
//                    System.out.println(result.getString(1));
//                    System.out.println(pass);
                        if(result.getString(1).equals(pass)){
//                        View.Alert.display("View.Login success", "HI");
                            window.close();
                            HomeScreen.homePage(uname, translate);
                            Files.write(file, Arrays.asList("View.Login Attempted. TimeStamp: "+ dateFormat.format(date.getTime()) +" userid:"+ uname +" Status: Successful "), StandardCharsets.UTF_8, StandardOpenOption.APPEND);

                            System.out.println("user logged in");
                        }else{
                            Alert.display(translate?"View.Alert!":LoginMap.get("View.Alert!"), translate?"Password is incorrect":LoginMap.get("Password is incorrect"));
                            Files.write(file, Arrays.asList("View.Login Attempted. TimeStamp:"+ dateFormat.format(date.getTime()) +" userid:"+ uname +" Status: Failed. Incorrect Password "), StandardCharsets.UTF_8, StandardOpenOption.APPEND);

//                        System.out.println("wrong password");
                        }
                    }
                }catch(SQLException e){
                    e.printStackTrace();
                }

            }catch(NumberFormatException e){
                Alert.display(translate?"View.Alert!":LoginMap.get("View.Alert!"), translate?"user ID has to be an Integer":LoginMap.get("user ID has to be an Integer"));
//            System.out.println("user ID has to be an Integer");
            }
        }


    }

}