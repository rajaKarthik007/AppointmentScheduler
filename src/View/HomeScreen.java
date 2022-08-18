package View;

import Model.ADimplimentation;
import Model.AppointmentDao;
import Model.CDimplimentation;
import Model.CustomerDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.util.*;

/**
 * Class to set up the Homescreen of the user who has logged in
 */
public class HomeScreen{
    static Translate d = new Translate();
    static HashMap<String,String> LoginMap = d.getmap();








//    public static void main(String[] args){
//        launch(args);
//    }

//    @Override

    /**
     * This function sets up the screen for the logged in user to view all his appointments and customers
     * @param uid The userid of the logged in user
     * @param translate The variable which tells us whether the user has opted for translation or not
     * @throws Exception  If an object that is being called cannot be found.
     */
    public static void homePage(int uid, Boolean translate) throws Exception {

        AppointmentDao APP = new ADimplimentation();
        CustomerDao CUS = new CDimplimentation();
        Translate d = new Translate();
        HashMap<String,String> LoginMap = d.getmap();
        Label l;
        TableView<Appointment> homeTable;
        TableView<Customer> custTable;
        Stage window = new Stage();
        window.setTitle(translate?"Home":LoginMap.get("Home"));

        TableColumn<Customer, Double> cIDcol = new TableColumn<>(translate?"View.Customer ID":LoginMap.get("View.Customer ID"));
        cIDcol.setMinWidth(150);
        cIDcol.setCellValueFactory(new PropertyValueFactory<>("CID"));

        TableColumn<Customer, String> cnamecol = new TableColumn<>(translate?"View.Customer Name":LoginMap.get("View.Customer Name"));
        cnamecol.setMinWidth(150);
        cnamecol.setCellValueFactory(new PropertyValueFactory<>("Cname"));

        TableColumn<Customer, String> caddrcol = new TableColumn<>(translate?"Address":LoginMap.get("Address"));
        caddrcol.setMinWidth(150);
        caddrcol.setCellValueFactory(new PropertyValueFactory<>("Caddress"));

        TableColumn<Customer, Double> postcol = new TableColumn<>(translate?"Postal Code":LoginMap.get("Postal Code"));
        postcol.setMinWidth(150);
        postcol.setCellValueFactory(new PropertyValueFactory<>("postalcode"));

        TableColumn<Customer, String> phonenocol = new TableColumn<>(translate?"Phone Number":LoginMap.get("Phone Number"));
        phonenocol.setMinWidth(150);
        phonenocol.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));

        TableColumn<Customer, String> countrycol = new TableColumn<>(translate?"Country":LoginMap.get("Country"));
        countrycol.setMinWidth(150);
        countrycol.setCellValueFactory(new PropertyValueFactory<>("country"));

        TableColumn<Customer, String> divisioncol = new TableColumn<>(translate?"State/Province":LoginMap.get("State/Province"));
        divisioncol.setMinWidth(150);
        divisioncol.setCellValueFactory(new PropertyValueFactory<>("division"));

        TableColumn<Customer, Double> createdcol = new TableColumn<>(translate?"Created By":LoginMap.get("Created By"));
        createdcol.setMinWidth(150);
        createdcol.setCellValueFactory(new PropertyValueFactory<>("created_by"));



        TableColumn<Appointment, Double> IDcol = new TableColumn<>(translate?"Appt ID":LoginMap.get("Appt ID"));
        IDcol.setMinWidth(100);
        IDcol.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Appointment, String> titlecol = new TableColumn<>(translate?"Title":LoginMap.get("Title"));
        titlecol.setMinWidth(100);
        titlecol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Appointment, String>descol = new TableColumn<>(translate?"Description":LoginMap.get("Description"));
        descol.setMinWidth(100);
        descol.setCellValueFactory(new PropertyValueFactory<>("Description"));

        TableColumn<Appointment, String> loccol = new TableColumn<>(translate?"Location":LoginMap.get("Location"));
        loccol.setMinWidth(100);
        loccol.setCellValueFactory(new PropertyValueFactory<>("location"));

        TableColumn<Appointment, Double> contactcol = new TableColumn<>(translate?"Contact":LoginMap.get("Contact"));
        contactcol.setMinWidth(100);
        contactcol.setCellValueFactory(new PropertyValueFactory<>("contact"));

        TableColumn<Appointment, String> typecol = new TableColumn<>(translate?"Type":LoginMap.get("Type"));
        typecol.setMinWidth(100);
        typecol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Appointment, java.sql.Time> stimecol = new TableColumn<>(translate?"Start Time":LoginMap.get("Start Time"));
        stimecol.setMinWidth(100);
        stimecol.setCellValueFactory(new PropertyValueFactory<>("Stime"));

        TableColumn<Appointment, java.sql.Time> etimecol = new TableColumn<>(translate?"End Time":LoginMap.get("End Time"));
        etimecol.setMinWidth(100);
        etimecol.setCellValueFactory(new PropertyValueFactory<>("Etime"));

        TableColumn<Appointment, java.sql.Date> sdatecol = new TableColumn<>(translate?"Start Date":LoginMap.get("Start Date"));
        sdatecol.setMinWidth(100);
        sdatecol.setCellValueFactory(new PropertyValueFactory<>("Sdate"));

        TableColumn<Appointment, java.sql.Date> edatecol = new TableColumn<>(translate?"End Date":LoginMap.get("End Date"));
        edatecol.setMinWidth(100);
        edatecol.setCellValueFactory(new PropertyValueFactory<>("Edate"));

        TableColumn<Appointment, Double> cidcol = new TableColumn<>(translate?"View.Customer ID":LoginMap.get("View.Customer ID"));
        cidcol.setMinWidth(100);
        cidcol.setCellValueFactory(new PropertyValueFactory<>("CID"));

        TableColumn<Appointment, Double> uidcol = new TableColumn<>(translate?"User ID":LoginMap.get("User ID"));
        uidcol.setMinWidth(100);
        uidcol.setCellValueFactory(new PropertyValueFactory<>("UID"));

        homeTable = new TableView<>();
        homeTable.setItems(APP.getAppointment(uid));
        custTable = new TableView<>();
        custTable.setItems(CUS.getCustomers());
        custTable.getColumns().addAll(cIDcol, cnamecol, caddrcol, postcol, phonenocol, countrycol, divisioncol, createdcol);
        homeTable.getColumns().addAll(IDcol, titlecol, descol, loccol, contactcol, typecol, stimecol, etimecol, sdatecol, edatecol, cidcol, uidcol);

        BorderPane bp = new BorderPane();
        bp.setCenter(homeTable);

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10, 10, 10, 10));
        l = new Label(translate?"View.Appointment Schedule":LoginMap.get("View.Appointment Schedule"));
        ToggleGroup tg = new ToggleGroup();
        RadioButton r1 = new RadioButton(translate?"View Customers":LoginMap.get("View Customers"));
        RadioButton r2 = new RadioButton(translate?"View By Week":LoginMap.get("View By Week"));
        RadioButton r3 = new RadioButton(translate?"View By Month":LoginMap.get("View By Month"));
        RadioButton r4 = new RadioButton(translate?"View All":LoginMap.get("View All"));
        r4.setSelected(true);



        r1.setToggleGroup(tg);
        r2.setToggleGroup(tg);
        r3.setToggleGroup(tg);
        r4.setToggleGroup(tg);

        GridPane.setConstraints(l, 0, 0);
        GridPane.setConstraints(r1, 1, 0);
        GridPane.setConstraints(r2, 2, 0);
        GridPane.setConstraints(r3, 3, 0);
        GridPane.setConstraints(r4, 4, 0);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(20);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(16);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(16);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(16);
        ColumnConstraints col5 = new ColumnConstraints();
        col5.setPercentWidth(16);
        ColumnConstraints col6 = new ColumnConstraints();
        col6.setPercentWidth(16);

        layout.getChildren().addAll(l, r1, r2, r3, r4);
        layout.getColumnConstraints().addAll(col1, col2, col3, col4, col5, col6);

        GridPane g = new GridPane();
        Button b1 = new Button(translate?"Reports":LoginMap.get("Reports"));
        /**
         * This is a lambda expression which captures the click event of the Button b1 and performs the required action
         */
        b1.setOnAction(e -> {
            try{
                Report.display(translate, uid, window);
            }catch (Exception e1){
                e1.printStackTrace();
            }

        });

        b1.setMinWidth(100);
        Button b2 = new Button(translate?"Add View.Appointment":LoginMap.get("Add View.Appointment"));
        b2.setMinWidth(100);
        Button b3 = new Button(translate?"Modify View.Appointment":LoginMap.get("Modify View.Appointment"));
        b3.setMinWidth(100);
        Button b4 = new Button(translate?"Delete View.Appointment":LoginMap.get("Delete View.Appointment"));
        b4.setMinWidth(100);

        GridPane g1 = new GridPane();
        Button bg1 = new Button(translate?"Reports":LoginMap.get("Reports"));
        bg1.setMinWidth(100);
        Button bg2 = new Button(translate?"Add View.Customer":LoginMap.get("Add View.Customer"));
        bg2.setMinWidth(100);
        /**
         * This is a lambda expression which captures the click event of the Button bg2 and performs the required action
         */
        bg2.setOnAction(e -> {
            try{
                CreateCustomer.display(false, translate, uid, custTable);
            }catch (Exception exc){
                exc.printStackTrace();
            }

        });

        Button bg3 = new Button(translate?"Modify View.Customer":LoginMap.get("Modify View.Customer"));
        bg3.setMinWidth(100);
        /**
         * This is a lambda expression which captures the click event of the Button bg3 and performs the required action
         */
        bg3.setOnAction(e -> {
            try{
                Customer cust = custTable.getSelectionModel().getSelectedItem();
                if(cust == null){
                    Alert.display(translate?"View.Alert!":LoginMap.get("View.Alert!"), translate?"Select a customer to modify!":LoginMap.get("Select a customer to delete!"));
                }else{
                    CreateCustomer.display(true, translate, uid, custTable);
                }

            }catch (Exception exc){
                exc.printStackTrace();
            }

        });
        Button bg4 = new Button(translate?"Delete View.Customer":LoginMap.get("Delete View.Customer"));
        bg4.setMinWidth(100);

        b4.setOnAction(e -> {
            APP.deleteAppointment(translate, homeTable);
        });
        bg4.setOnAction(e ->{
            CUS.deleteCustomer(translate, custTable);
        });
        Button b5 = new Button(translate?"Logout":LoginMap.get("Logout"));
        b5.setOnAction(e ->{
            System.out.println("User logged out");
            window.close();
        });
        b5.setMinWidth(100);
        Button bg5 = new Button(translate?"Logout":LoginMap.get("Logout"));
        bg5.setMinWidth(100);

        bg5.setOnAction(e ->{
            System.out.println("User logged out");
            window.close();
        });
        bg1.setOnAction(e -> {
            try{
                Report.display(translate, uid, window);
            }catch (Exception e1){
                e1.printStackTrace();
            }

        });
        b2.setOnAction(e -> {
            try{
                addAppointment.display(false ,translate, uid, homeTable);
            }catch (Exception exe){
                exe.printStackTrace();
            }

        });
        b3.setOnAction(e->{
            try{
                addAppointment.display(true ,translate, uid, homeTable);
            }catch (Exception e1){
                e1.printStackTrace();
            }
        });

        GridPane.setConstraints(b1, 0, 0);
        GridPane.setConstraints(b2, 1, 0);
        GridPane.setConstraints(b3, 2, 0);
        GridPane.setConstraints(b4, 3, 0);
        GridPane.setConstraints(b5, 4, 0);

        GridPane.setConstraints(bg1, 0, 0);
        GridPane.setConstraints(bg2, 1, 0);
        GridPane.setConstraints(bg3, 2, 0);
        GridPane.setConstraints(bg4, 3, 0);
        GridPane.setConstraints(bg5, 4, 0);

        g.getChildren().addAll(b1, b2, b3, b4, b5);
        g1.getChildren().addAll(bg1, bg2, bg3, bg4, bg5);
        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(20);
        g.getColumnConstraints().addAll(c1, c1, c1, c1, c1);
        g.setPadding(new Insets(15, 15, 15, 15));
        g1.getColumnConstraints().addAll(c1, c1, c1, c1, c1);
        g1.setPadding(new Insets(15, 15, 15, 15));
        bp.setTop(layout);
        bp.setBottom(g);

        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton)tg.getSelectedToggle();
                if (rb != null) {
                    String s = rb.getText();
                    if(s.equals("View Customers") || s.equals(LoginMap.get("View Customers"))){
                        bp.setBottom(g1);
                        bp.setCenter(custTable);
                    }else if(s.equals("View By Week") || s.equals(LoginMap.get("View By Week"))){
                        bp.setBottom(g);
                        bp.setCenter(homeTable);
                    }else if(s.equals("View By Month") || s.equals(LoginMap.get("View By Month"))){
                        bp.setCenter(homeTable);
                        bp.setBottom(g);
                    }else if(s.equals("View All") || s.equals(LoginMap.get("View All"))){
                        bp.setCenter(homeTable);
                        bp.setBottom(g);
                    }
                }
            }
        });

        Scene scene = new Scene(bp);
        window.setScene(scene);

        window.show();
        if(Integer.parseInt(APP.ExecQuery("SELECT count(*) FROM appointments WHERE userid="+ uid+" and stime < NOW() + INTERVAL 15 MINUTE and stime > NOW()")) >= 1){
            Alert.display(translate?"View.Alert!":LoginMap.get("View.Alert!"), translate?"You Have a meeting within the next 15 minuites":LoginMap.get("You Have a meeting within the next 15 minuites"));
        }
    }









}

