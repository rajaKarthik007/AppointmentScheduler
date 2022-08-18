package Model;
import View.*;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDao {

    public void ModifyCustomer(TableView<Customer> homeTable, Button saveButton, TextField customerIdInput, TextField customerNameInput, TextField addressInput, TextField postalCodeInput,
                               TextField phoneNumberInput, ComboBox choiceBoxCountries, ComboBox choiceBoxStates);
    public void ValidateCreateCustomer(Boolean translate, Boolean modCus, TableView<Customer> homeTable, int uid, Stage window, TextField customerIdInput, TextField customerNameInput, TextField addressInput, TextField postalCodeInput,
                                       TextField phoneNumberInput, ComboBox choiceBoxCountries, ComboBox choiceBoxStates);
    public  List<String> Countries();
    public List<String> States(String country);
    public String fun(String query);
    public ObservableList<Customer> getCustomers() throws SQLException;
    public void deleteCustomer(Boolean translate, TableView<Customer> custTable);

}
