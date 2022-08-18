package View;

import Model.ADimplimentation;
import Model.AppointmentDao;
import Model.CDimplimentation;
import Model.CustomerDao;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;

/**
 * Class which enables the users to create new customers
 */
public class CreateCustomer{
    static Translate d = new Translate();
    static HashMap<String,String> LoginMap = d.getmap();


    /**
     * function which displays details of all customers
     * @param modCus gives information about whether the user has opted for creating or modifying a View.Customer
     * @param translate Indicates whether the user has opted for translation or not
     * @param uid The user ID of the logged in user
     * @param homeTable The table which displays all the customers in the database
     * @throws Exception If a SQL database error occurs or If an object that is being called cannot be found.
     */
    public static void display(Boolean modCus, Boolean translate, int uid, TableView<Customer> homeTable) throws Exception {

        AppointmentDao APP = new ADimplimentation();
        CustomerDao CUS = new CDimplimentation();
        String newCustId = APP.ExecQuery("select max(cid) from customer");
        Stage window = new Stage();
        Scene scene;
        window.setTitle(modCus?translate?"Modify View.Customer":LoginMap.get("Modify View.Customer"):translate?"Add View.Customer":LoginMap.get("Add View.Customer"));
        window.initModality(Modality.APPLICATION_MODAL);
        GridPane grid = new GridPane();
        grid.setHgap(50);
        grid.setVgap(35);
        grid.setPadding(new Insets(50,50,50,50));

        Label customerIdLabel = new Label(translate?"View.Customer ID":LoginMap.get("View.Customer ID"));
        GridPane.setConstraints(customerIdLabel,0,1);

        Label customerNameLabel = new Label(translate?"View.Customer Name":LoginMap.get("View.Customer Name"));
        GridPane.setConstraints(customerNameLabel,0,2);

        Label addressLabel = new Label(translate?"Address":LoginMap.get("Address"));
        GridPane.setConstraints(addressLabel,0,3);

        Label postalCodeLabel = new Label(translate?"Postal Code":LoginMap.get("Postal Code"));
        GridPane.setConstraints(postalCodeLabel,0,4);

        Label phoneNumberLabel = new Label(translate?"Phone Number":LoginMap.get("Phone Number"));
        GridPane.setConstraints(phoneNumberLabel,0,5);

        Label countriesLabel = new Label(translate?"Countries":LoginMap.get("Countries"));
        GridPane.setConstraints(countriesLabel,0,6);

        Label divisionsLabel = new Label(translate?"Divisions":LoginMap.get("Divisions"));
        GridPane.setConstraints(divisionsLabel,3,6);

        TextField customerIdInput = new TextField();
        customerIdInput.setText(String.valueOf(Integer.parseInt(newCustId.substring(0, newCustId.length()-2))+1));
        customerIdInput.setDisable(true);
        GridPane.setConstraints(customerIdInput,2,1);

        TextField customerNameInput = new TextField();
        GridPane.setConstraints(customerNameInput,2,2);

        TextField addressInput = new TextField();
        GridPane.setConstraints(addressInput,2,3);

        TextField postalCodeInput = new TextField();
        GridPane.setConstraints(postalCodeInput,2,4);

        TextField phoneNumberInput = new TextField();
        GridPane.setConstraints(phoneNumberInput,2,5);

        ComboBox<String> choiceBoxCountries = new ComboBox<>(FXCollections.observableArrayList(CUS.Countries()));
        GridPane.setConstraints(choiceBoxCountries,2,6);
        choiceBoxCountries.setMinWidth(150);

        ComboBox<String> choiceBoxStates = new ComboBox<>(FXCollections.observableArrayList(Arrays.asList("Bangalore","Hyderabad","Mumbai","Chennai")));
        GridPane.setConstraints(choiceBoxStates,4,6);
        choiceBoxStates.setMinWidth(150);

        choiceBoxCountries.setOnAction(e->{
            choiceBoxStates.getItems().removeAll();
            choiceBoxStates.getItems().setAll(FXCollections.observableArrayList(CUS.States(choiceBoxCountries.getValue())));
        });

        Button saveButton = new Button(translate?"Save":"Save");
        GridPane.setConstraints(saveButton,2,7);
        saveButton.setMinWidth(150);

        /**
         * This is a lambda expression which captures the click event of the saveButton and performs the required action
         */
        saveButton.setOnAction(e->{
            CUS.ValidateCreateCustomer(translate, modCus, homeTable, uid, window, customerIdInput,customerNameInput,addressInput,postalCodeInput,phoneNumberInput,choiceBoxCountries,choiceBoxStates);
        });

        Button cancelButton = new Button(translate?"Cancel":LoginMap.get("Cancel"));
        cancelButton.setOnAction(e->window.close());
        GridPane.setConstraints(cancelButton,4,7);
        cancelButton.setMinWidth(150);


        grid.getChildren().addAll(customerIdLabel,customerNameLabel,addressLabel,postalCodeLabel,phoneNumberLabel,countriesLabel,divisionsLabel,
                customerIdInput,customerNameInput,addressInput,postalCodeInput,phoneNumberInput,choiceBoxCountries,choiceBoxStates,
                saveButton,cancelButton);
        if(modCus){
            CUS.ModifyCustomer(homeTable,saveButton, customerIdInput,customerNameInput,addressInput,postalCodeInput,phoneNumberInput,choiceBoxCountries,choiceBoxStates);
        }
        scene = new Scene(grid,800,600);
        window.setScene(scene);
        window.show();
    }
}