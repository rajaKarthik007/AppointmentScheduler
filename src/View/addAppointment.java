package View;

import Model.AppointmentDao;
import Model.CustomerDao;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Spinner;

import java.time.LocalDate;

import java.util.*;

/**
 * Class which enables the user to add a new View.Appointment for him
 */
public class addAppointment {
    static Translate d = new Translate();
    static HashMap<String,String> LoginMap = d.getmap();




    /**
     *
     * @param modApp tells us whether the user has opted for adding or modifying an appointment
     * @param translate The variable which tells us whether the user has opted for translation or not
     * @param uid The user id of the logged in user
     * @param homeTable The table where the Appointments of the user are displayed
     * @throws Exception If a SQL database error occurs.
     */
    public static void display(Boolean modApp, Boolean translate, int uid, TableView<Appointment> homeTable) throws Exception {

        Boolean disp = true;
        AppointmentDao APP = new Model.ADimplimentation();
        CustomerDao CUS = new Model.CDimplimentation();
        String newAptId = APP.ExecQuery("select max(id) from appointments");
        String noOfUsers = APP.ExecQuery("select max(userid) from users");
        String noOfCustomers = APP.ExecQuery("select max(cid) from customer");

//        System.out.println(noOfCustomers+" "+noOfUsers+" "+newAptId);

        Stage window = new Stage();
        Scene scene;
        window.setTitle(translate?"Add View.Appointment":LoginMap.get("Add View.Appointment"));
        window.initModality(Modality.APPLICATION_MODAL);

        GridPane grid = new GridPane();
        grid.setHgap(50);
        grid.setVgap(25);
        grid.setPadding(new Insets(50,50,50,50));

        Label AppointmentIdLabel = new Label(translate?"View.Appointment ID":LoginMap.get("View.Appointment ID"));
        GridPane.setConstraints(AppointmentIdLabel,0,1);

        Label TitleLabel = new Label(translate?"Title":LoginMap.get("Title"));
        GridPane.setConstraints(TitleLabel,0,2);

        Label DescriptionLabel = new Label(translate?"Description":LoginMap.get("Description"));
        GridPane.setConstraints(DescriptionLabel,0,3);

        Label LocationLabel = new Label(translate?"Location":LoginMap.get("Location"));
        GridPane.setConstraints(LocationLabel,0,4);

        Label TypeLabel = new Label(translate?"Type":LoginMap.get("Type"));
        GridPane.setConstraints(TypeLabel,0,5);

        Label ContactIDLabel = new Label(translate?"Contact ID":LoginMap.get("Contact ID"));
        GridPane.setConstraints(ContactIDLabel,0,6);

        Label CustomerIdLabel = new Label(translate?"View.Customer ID":LoginMap.get("View.Customer ID"));
        GridPane.setConstraints(CustomerIdLabel,0,7);

        Label UserIDLabel = new Label(translate?"User ID":LoginMap.get("User ID"));
        GridPane.setConstraints(UserIDLabel,0,8);

        Label StartDateLabel = new Label(translate?"Start Date":LoginMap.get("Start Date"));
        GridPane.setConstraints(StartDateLabel,0,9);

        Label EndDateLabel = new Label(translate?"End Date":LoginMap.get("End Date"));
        GridPane.setConstraints(EndDateLabel,2,9);

        Label StartTimeLabel = new Label(translate?"Start Time":LoginMap.get("Start Time"));
        GridPane.setConstraints(StartTimeLabel,0,10);

        Label EndTimeLabel = new Label(translate?"End Time":LoginMap.get("End Time"));
        GridPane.setConstraints(EndTimeLabel,2,10);

        Button saveButton = new Button(modApp?(translate?"Modify":LoginMap.get("Modify")):(translate?"Save":LoginMap.get("Save")));

        GridPane.setConstraints(saveButton,1,11);
        saveButton.setMinWidth(200);

        Button cancelButton = new Button(translate?"Cancel":LoginMap.get("Cancel"));
        cancelButton.setOnAction(e -> {
            window.close();
        });
        GridPane.setConstraints(cancelButton,3,11);
        cancelButton.setMinWidth(200);

        TextField AppointmentIdInput = new TextField();
        AppointmentIdInput.setText(String.valueOf(Double.parseDouble(newAptId)+1));
        AppointmentIdInput.setDisable(true);
        GridPane.setConstraints(AppointmentIdInput,1,1);

        TextField TitleInput = new TextField();
        GridPane.setConstraints(TitleInput,1,2);

        TextField DescriptionInput = new TextField();
        GridPane.setConstraints(DescriptionInput,1,3);

        TextField LocationInput = new TextField();
        GridPane.setConstraints(LocationInput,1,4);

        TextField TypeInput = new TextField();
        GridPane.setConstraints(TypeInput,1,5);

        TextField ContactInput = new TextField();
        GridPane.setConstraints(ContactInput,1,6);

        ComboBox<Integer> CustomerBox = new ComboBox(FXCollections.observableArrayList(CUS.fun("select distinct(cid) from customer").split("#")));
        GridPane.setConstraints(CustomerBox,1,7);
        CustomerBox.setMinWidth(200);

        ComboBox<Integer> UserIDBox = new ComboBox(FXCollections.observableArrayList(CUS.fun("select distinct(userid) from users").split("#")));
        GridPane.setConstraints(UserIDBox,1,8);
        UserIDBox.setMinWidth(200);

        DatePicker StartDateInput = new DatePicker();
        GridPane.setConstraints(StartDateInput,1,9);
        StartDateInput.setMinWidth(200);
        StartDateInput.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

        DatePicker EndDateInput = new DatePicker();
        GridPane.setConstraints(EndDateInput,3,9);
        EndDateInput.setMinWidth(200);
        EndDateInput.setDayCellFactory(picker -> new DateCell() {
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                LocalDate today = LocalDate.now();

                setDisable(empty || date.compareTo(today) < 0 );
            }
        });

        GridPane g1 = new GridPane();
        GridPane g2 = new GridPane();

        Spinner StartTimeFirst = new Spinner(1,24,1);
        Spinner StartTimeSecond = new Spinner(1,60,1);
        Spinner EndTimeFirst = new Spinner (1,24,1);
        Spinner EndTimeSecond = new Spinner(1,60,1);

        saveButton.setOnAction(e->{
            if(!modApp){
                APP.ValidateAddAppointment(translate, null, AppointmentIdInput, false, homeTable, uid ,window, newAptId, StartTimeFirst, StartTimeSecond, EndTimeFirst, EndTimeSecond, TitleInput,DescriptionInput,LocationInput,TypeInput,ContactInput,CustomerBox,UserIDBox,StartDateInput,EndDateInput);
            }else {
                Appointment app = homeTable.getSelectionModel().getSelectedItem();
                APP.ValidateAddAppointment(translate, app, AppointmentIdInput,true, homeTable, uid ,window, newAptId, StartTimeFirst, StartTimeSecond, EndTimeFirst, EndTimeSecond, TitleInput,DescriptionInput,LocationInput,TypeInput,ContactInput,CustomerBox,UserIDBox,StartDateInput,EndDateInput);
            }
        });

        StartTimeFirst.setPromptText("8");
        StartTimeSecond.setPromptText("40");
        EndTimeFirst.setPromptText("10");
        EndTimeSecond.setPromptText("10");

        StartTimeFirst.setMaxWidth(100);
        StartTimeSecond.setMaxWidth(100);
        EndTimeFirst.setMaxWidth(100);
        EndTimeSecond.setMaxWidth(100);

        GridPane.setConstraints(StartTimeFirst,0,0);
        GridPane.setConstraints(StartTimeSecond,1,0);
        GridPane.setConstraints(EndTimeFirst,0,0);
        GridPane.setConstraints(EndTimeSecond,1,0);

        g1.getChildren().addAll(StartTimeFirst,StartTimeSecond);
        g2.getChildren().addAll(EndTimeFirst,EndTimeSecond);

        GridPane.setConstraints(g1,1,10);
        GridPane.setConstraints(g2,3,10);

        grid.getChildren().addAll(AppointmentIdLabel,TitleLabel,DescriptionLabel,LocationLabel,TypeLabel,ContactIDLabel,CustomerIdLabel,
                UserIDLabel,StartDateLabel,EndDateLabel,StartTimeLabel,EndTimeLabel,saveButton,cancelButton,
                AppointmentIdInput,TitleInput,DescriptionInput,LocationInput,TypeInput,ContactInput,CustomerBox,
                UserIDBox,StartDateInput,EndDateInput,g1,g2);

        if(modApp){
            Appointment app = homeTable.getSelectionModel().getSelectedItem();
            if(app == null){
                Alert.display(translate?"View.Alert!":LoginMap.get("View.Alert!"), translate?"Please Select an View.Appointment to modify":LoginMap.get("Please Select an View.Appointment to modify"));
                disp = false;
            }else{
                APP.modifyAppointment(app,StartTimeFirst,StartTimeSecond,EndTimeFirst,EndTimeSecond, AppointmentIdInput,TitleInput,DescriptionInput,LocationInput,TypeInput,ContactInput,CustomerBox,
                        UserIDBox,StartDateInput,EndDateInput);
            }
        }

        scene = new Scene(grid,800,700);
        window.setScene(scene);
        if(disp){
            window.show();
        }

    }
}