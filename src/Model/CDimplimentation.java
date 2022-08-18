package Model;
import View.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CDimplimentation implements CustomerDao{

    /**
     * Function which enables the user to modify a customer's details
     * @param homeTable Table which displays all the details of the customers
     * @param saveButton Button which saves all changes made
     * @param customerIdInput Text field which enables user to enter input of customer ID
     * @param customerNameInput Text field which enables user to enter input of customer Name
     * @param addressInput Text field which enables user to enter input of customer's Address
     * @param postalCodeInput Text field which enables user to enter input of customer's area postal code
     * @param phoneNumberInput Text field which enables user to enter input of customer's phone number
     * @param choiceBoxCountries choice box which enables user to select the customer's country
     * @param choiceBoxStates choice box which enables user to select the customer's Division
     */
    @Override
    public void ModifyCustomer(TableView<Customer> homeTable, Button saveButton, TextField customerIdInput, TextField customerNameInput, TextField addressInput, TextField postalCodeInput, TextField phoneNumberInput, ComboBox choiceBoxCountries, ComboBox choiceBoxStates) {
        Customer cust = homeTable.getSelectionModel().getSelectedItem();
        String CustomerIdValue = String.valueOf(cust.getCID());
        String CustomerNameValue = cust.getCname();
        String AddressValue = cust.getCaddress();
        String PostalCodeValue = String.valueOf(cust.getPostalcode());
        String PhoneNumberValue = String.valueOf(cust.getPhonenumber());
        Object CountryValue = cust.getCountry();
        Object StateValue = cust.getDivision();

        customerIdInput.setText(CustomerIdValue);
        customerNameInput.setText(CustomerNameValue);
        addressInput.setText(AddressValue);
        postalCodeInput.setText(PostalCodeValue);
        phoneNumberInput.setText(PhoneNumberValue);
        choiceBoxCountries.getSelectionModel().select(CountryValue);
        choiceBoxStates.getSelectionModel().select(StateValue);
        saveButton.setText("Modify");
    }

    /**
     *
     * @param translate Indicates whether the user has opted for translation or not
     * @param modCus Indicates whether the user has selected to modify or add a customer
     * @param homeTable Table which displays all the details of the customers
     * @param uid The id of the logged-in user
     * @param window the stage where all the components are displayed
     * @param customerIdInput Text field which enables user to enter input of customer ID
     * @param customerNameInput Text field which enables user to enter input of customer Name
     * @param addressInput Text field which enables user to enter input of customer's Address
     * @param postalCodeInput Text field which enables user to enter input of customer's area postal code
     * @param phoneNumberInput Text field which enables user to enter input of customer's phone number
     * @param choiceBoxCountries choice box which enables user to select the customer's country
     * @param choiceBoxStates choice box which enables user to select the customer's Division
     */
    @Override
    public void ValidateCreateCustomer(Boolean translate, Boolean modCus, TableView<Customer> homeTable, int uid, Stage window, TextField customerIdInput, TextField customerNameInput, TextField addressInput, TextField postalCodeInput, TextField phoneNumberInput, ComboBox choiceBoxCountries, ComboBox choiceBoxStates) {
        Translate d = new Translate();
        HashMap<String,String> LoginMap = d.getmap();
        Boolean b = true;
        String CustomerIdValue = customerIdInput.getText();
        String CustomerNameValue = customerNameInput.getText();
        String AddressValue = addressInput.getText();
        String PostalCodeValue = postalCodeInput.getText();
        String PhoneNumberValue = phoneNumberInput.getText();
        Object CountryValue = choiceBoxCountries.getValue();
        Object StateValue = choiceBoxStates.getValue();

        if(!CustomerNameValue.equals("")){  customerNameInput.setStyle(null);}
        else {
            customerNameInput.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
            b = false;
        }

        if (!AddressValue.equals("")){  addressInput.setStyle(null);}
        else {
            addressInput.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
            b = false;
        }

        if (!PostalCodeValue.equals("")){ postalCodeInput.setStyle(null);}
        else {
            postalCodeInput.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
            b = false;
        }

        if (!PhoneNumberValue.equals("")){  phoneNumberInput.setStyle(null);}
        else {
            phoneNumberInput.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
            b = false;
        }

        if (CountryValue != null) { choiceBoxCountries.setStyle(null);}
        else {
            b = false;
            choiceBoxCountries.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
        }

        if (StateValue != null){ choiceBoxStates.setStyle(null);}
        else {
            b = false;
            choiceBoxStates.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
        }
        if(b){
            String url = "jdbc:mysql://localhost:3306/appointments";
            String dbuname = "root";
            String dbpass = "root";
            String query;
            if(!modCus){
                query = "insert into customer values ("+ CustomerIdValue+",'"+ CustomerNameValue+"','"+ AddressValue+"','"+PostalCodeValue+"','"+PhoneNumberValue+"', '"+CountryValue+"','"+ StateValue+"','"+ uid+"');";
            }else{
                query = "update customer set cname='"+CustomerNameValue+"', address='"+AddressValue+"', postalcode="+PostalCodeValue+", phonenumber='"+PhoneNumberValue+"', country='"+CountryValue+"', division='"+StateValue+"' where cid="+CustomerIdValue;
            }
//            System.out.println(query);
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            try{
                Connection con = DriverManager.getConnection(url, dbuname, dbpass);
                Statement stmt = con.prepareStatement(query);
                stmt.executeUpdate(query);
                if(!modCus){
                    Alert.display(translate?"Success":LoginMap.get("Success!"), "New View.Customer "+CustomerIdValue + (translate?" was Added!":" "+LoginMap.get("was Added!")));
                }else{
                    Alert.display(translate?"Success":LoginMap.get("Success!"), "View.Customer "+CustomerIdValue+ (translate?" details have been updated!":" " + LoginMap.get("details have been updated!")));
                }
                window.close();
                Customer cst = new Customer(Double.parseDouble(CustomerIdValue),CustomerNameValue, AddressValue, Double.parseDouble(PostalCodeValue), PhoneNumberValue, CountryValue.toString(), StateValue.toString(), uid);
                homeTable.getItems().add(cst);


            }catch(SQLException e){
                e.printStackTrace();
                if(!modCus){
                    Alert.display(translate?"Error!":LoginMap.get("Error!"), translate?"Sorry we could not Add the New View.Customer. Please check and try again":LoginMap.get("Sorry we could not Add the New View.Customer. Please check and try again"));
                }else{
                    Alert.display(translate?"Error!":LoginMap.get("Error!"), translate?"We could not modify the required customer details at the moment. Try again":LoginMap.get("We could not modify the required customer details at the moment. Try again"));
                }

            }
        }
    }

    /**
     * Function which returns the list containing all countries
     * @return List of all countries in the database
     */
    @Override
    public List<String> Countries() {
        CustomerDao CUS = new CDimplimentation();
        List<String> countriesList = Arrays.asList(CUS.fun("select country from countries").split("#"));
        return countriesList;
    }


    /**
     * Function returns the states of a particular country
     * @param country returns the divisions of the country given as input
     * @return List of divisions
     */
    @Override
    public List<String> States(String country) {
        Map<String, List<String>> stateList = new HashMap<String, List<String>>();
        stateList.put("Australia", Arrays.asList(fun("select division from divisions where country=(select country_id from countries where country='Australia')").split("#")));
        stateList.put("England", Arrays.asList(fun("select division from divisions where country=(select country_id from countries where country='England')").split("#")));
        stateList.put("India", Arrays.asList(fun("select division from divisions where country=(select country_id from countries where country='India')").split("#")));
        stateList.put("USA", Arrays.asList(fun("select division from divisions where country=(select country_id from countries where country='USA')").split("#")));
        stateList.put("South Korea", Arrays.asList(fun("select division from divisions where country=(select country_id from countries where country='South Korea')").split("#")));

        return stateList.get(country);
    }


    /**
     * This function takes in MYSQL query and returns the result after executing it. Returns the results of exeution as a List.
     * @param query The query to be executed.
     * @return List of results returned.
     */

    //Works only for queries returning single value after execution.
    @Override
    public String fun(String query) {
        String ret = "";
        String url = "jdbc:mysql://localhost:3306/appointments";
        String dbuname = "root";
        String dbpass = "root";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            Connection con = DriverManager.getConnection(url, dbuname, dbpass);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

//            System.out.println(result);
            while(result.next()){
                ret = ret +result.getString(1)+"#";
            }


        }catch(SQLException e){
            e.printStackTrace();
        }
        return ret;
    }


    /**
     * function to return the details of all the customers in the database
     * @return Customers contains the details of all the customers
     * @throws SQLException If a SQL database error occurs.
     */
    @Override
    public ObservableList<Customer> getCustomers() throws SQLException {
        ObservableList<Customer> Customers = FXCollections.observableArrayList();
        String url = "jdbc:mysql://localhost:3306/appointments";
        String dbuname = "root";
        String dbpass = "root";
        String query = "select * from customer";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            Connection con = DriverManager.getConnection(url, dbuname, dbpass);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);
//            System.out.println(result);
            while(result.next()){
                String data = "";
                for(int i=1; i<=8; i++){
                    data+=result.getString(i) +":";
                }
                Customers.add(new Customer(Double.parseDouble(result.getString(1)), result.getString(2),result.getString(3),Double.parseDouble(result.getString(4)),result.getString(5),result.getString(6),result.getString(7),Double.parseDouble(result.getString(8))));
//                System.out.println(data);
//                System.out.println(Double.parseDouble(result.getString(5)));
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return Customers;
    }


    /**
     * function which aids the user to delete any customer from the database
     * @param translate The variable which tells us whether the user has opted for translation or not
     * @param custTable The table which contains the details of all the customers
     */
    @Override
    public void deleteCustomer(Boolean translate, TableView<Customer> custTable) {
        Translate d = new Translate();
        HashMap<String,String> LoginMap = d.getmap();
        Customer cust = custTable.getSelectionModel().getSelectedItem();
        if(cust == null){
            Alert.display(translate?"View.Alert!":LoginMap.get("View.Alert!"), translate?"Please select a View.Customer to delete!":LoginMap.get("Please select a View.Customer to delete"));

        }else{

            String url = "jdbc:mysql://localhost:3306/appointments";
            String dbuname = "root";
            String dbpass = "root";
            String query1 = "delete from customer where cid=" + cust.getCID();
            String query2 = "delete from appointments where custid="+ cust.getCID();
//            System.out.println(query1);
//            System.out.println(query2);
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            try{
                Connection con = DriverManager.getConnection(url, dbuname, dbpass);
                Statement stmt1 = con.prepareStatement(query2);
                Statement stmt2 = con.prepareStatement(query1);
                stmt1.executeUpdate(query2);
                stmt2.executeUpdate(query1);
//                    System.out.println(result);
                //            System.out.println(result);
                custTable.getItems().remove(cust);
                Alert.display(translate?"Success!":LoginMap.get("Success!"), "View.Appointment "+cust.getCID() + (translate?" was deleted!":" "+LoginMap.get("was deleted!")));


            }catch(SQLException e){
                e.printStackTrace();
                Alert.display(translate?"Error!":LoginMap.get("Error!"), translate?"Sorry we could not delete the View.Customer. please try again":LoginMap.get("Sorry we could not delete the View.Customer. please try again"));
            }
        }
    }
}
