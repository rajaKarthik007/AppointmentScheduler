package View;

import Model.ADimplimentation;
import Model.AppointmentDao;
import Model.CDimplimentation;
import Model.CustomerDao;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * The class which enables users to see the reports.
 */
public class Report{





    /**
     *
     * @param translate The variable which tells us whether the user has opted for translation or not
     * @param uid The user id of the logged-in user
     * @param wind Stage for displaying all the components.
     * @throws Exception If a SQL database error occurs.
     */
    public static void display(Boolean translate, int uid, Stage wind) throws Exception {

        AppointmentDao APP = new ADimplimentation();
        CustomerDao CUS = new CDimplimentation();
        Translate d = new Translate();
        HashMap<String,String> LoginMap = d.getmap();
        TableView<Appointment> rtable1;
        TableView<Customer> rtable2;

        TableColumn<Customer, String> CIDcol = new TableColumn<>(translate?"View.Customer ID":LoginMap.get("View.Customer ID"));
        CIDcol.setMinWidth(165);
        CIDcol.setCellValueFactory(new PropertyValueFactory<>("CID"));

        TableColumn<Customer, String> cnamecol = new TableColumn<>(translate?"View.Customer Name":LoginMap.get("View.Customer Name"));
        cnamecol.setMinWidth(165);
        cnamecol.setCellValueFactory(new PropertyValueFactory<>("Cname"));

        TableColumn<Customer, String> caddrcol = new TableColumn<>(translate?"Address":LoginMap.get("Address"));
        caddrcol.setMinWidth(165);
        caddrcol.setCellValueFactory(new PropertyValueFactory<>("Caddress"));

        TableColumn<Customer, String> pcodecol = new TableColumn<>(translate?"Postal Code":LoginMap.get("Postal Code"));
        pcodecol.setMinWidth(165);
        pcodecol.setCellValueFactory(new PropertyValueFactory<>("postalcode"));

        TableColumn<Customer, String> pnocol = new TableColumn<>(translate?"Phone Number":LoginMap.get("Phone Number"));
        pnocol.setMinWidth(165);
        pnocol.setCellValueFactory(new PropertyValueFactory<>("phonenumber"));

        TableColumn<Customer, String> cbycol = new TableColumn<>(translate?"Created By":LoginMap.get("Created By"));
        cbycol.setMinWidth(165);
        cbycol.setCellValueFactory(new PropertyValueFactory<>("created_by"));

        rtable2 = new TableView<>();
        rtable2.getColumns().addAll(CIDcol, cnamecol, caddrcol, pcodecol, pnocol, cbycol);

        TableColumn<Appointment, Double> IDcol = new TableColumn<>(translate?"Appt ID":LoginMap.get("Appt ID"));
        IDcol.setMinWidth(100);
        IDcol.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Customer, String> divcol = new TableColumn<>(translate?"Division":LoginMap.get("Division"));
        divcol.setMinWidth(150);
        divcol.setCellValueFactory(new PropertyValueFactory<>("division"));

        TableColumn<Appointment, Double> contactcol = new TableColumn<>(translate?"Contact":LoginMap.get("Contact"));
        contactcol.setMinWidth(100);
        contactcol.setCellValueFactory(new PropertyValueFactory<>("contact"));

        TableColumn<Appointment, String> titlecol = new TableColumn<>(translate?"Title":LoginMap.get("Title"));
        titlecol.setMinWidth(100);
        titlecol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Appointment, String>descol = new TableColumn<>(translate?"Description":LoginMap.get("Description"));
        descol.setMinWidth(100);
        descol.setCellValueFactory(new PropertyValueFactory<>("Description"));

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

        rtable1 = new TableView<>();
        ObservableList<Customer> custs = CUS.getCustomers();
        ObservableList<Appointment> appts = APP.getAppointment(uid);

        rtable1.getColumns().addAll(IDcol, titlecol, descol, typecol, stimecol, etimecol, sdatecol, edatecol, cidcol, contactcol);


        Stage window = new Stage();
        window.setTitle("Reports");
        window.initModality(Modality.APPLICATION_MODAL);

        GridPane layout = new GridPane();
        BorderPane bp = new BorderPane();
        BorderPane mainbp = new BorderPane();

        layout.setPadding(new Insets(20, 20, 20, 20));

        ToggleGroup tg = new ToggleGroup();
        RadioButton r1 = new RadioButton(translate?"Contact Schedule":LoginMap.get("Contact Schedule"));
        RadioButton r2 = new RadioButton(translate?"Total Appointments by Type":LoginMap.get("Total Appointments by Type"));
        RadioButton r3 = new RadioButton(translate?"Total Customers by Location":LoginMap.get("Total Customers by Location"));
        RadioButton r4 = new RadioButton(translate?"Total Customers by Country":LoginMap.get("Total Customers by Country"));
        r1.setSelected(true);

        r1.setToggleGroup(tg);
        r2.setToggleGroup(tg);
        r3.setToggleGroup(tg);
        r4.setToggleGroup(tg);

        GridPane.setConstraints(r1, 0, 0);
        GridPane.setConstraints(r2, 1, 0);
        GridPane.setConstraints(r3, 2, 0);
        GridPane.setConstraints(r4, 3, 0);

        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        ColumnConstraints col4 = new ColumnConstraints();
        col4.setPercentWidth(25);

        layout.getChildren().addAll(r1, r2, r3, r4);
        layout.getColumnConstraints().addAll(col1, col2, col3, col4);

        GridPane g1 = new GridPane();
        GridPane g2 = new GridPane();
        GridPane g3 = new GridPane();
        GridPane g4 = new GridPane();

        Label ContactLabel = new Label("Contact");
        ComboBox ContactBox = new ComboBox(FXCollections.observableArrayList(CUS.fun("select distinct(contact) from appointments where userid="+uid).split("#")));
        ContactBox.setMinWidth(200);
        ContactBox.getSelectionModel().selectFirst();
        GridPane.setConstraints(ContactLabel,0,0);
        GridPane.setConstraints(ContactBox,2,0);
        g1.setAlignment(Pos.TOP_CENTER);
        g1.setHgap(100);
        g1.getChildren().addAll(ContactLabel,ContactBox);
        ContactBox.setOnAction(e->{
            FilteredList<Appointment> fappts = new FilteredList<>(appts, i->i.getContact().toString().equals(ContactBox.getValue()));
            rtable1.setItems(fappts);
        });

        Label TypeLabel = new Label(translate?"Type":LoginMap.get("Type"));
        ComboBox TypeBox = new ComboBox(FXCollections.observableArrayList(CUS.fun("select distinct(tpe) from appointments where userid="+uid).split("#")));
        TypeBox.setMinWidth(200);
        TypeBox.getSelectionModel().selectFirst();
        GridPane.setConstraints(TypeLabel,0,0);
        GridPane.setConstraints(TypeBox,2,0);
        g2.setAlignment(Pos.TOP_CENTER);
        g2.setHgap(100);
        g2.getChildren().addAll(TypeLabel,TypeBox);
        TypeBox.setOnAction(e->{
            FilteredList<Appointment> fappts = new FilteredList<>(appts, i->i.getType().equals(TypeBox.getValue().toString()));
            rtable1.setItems(fappts);
        });

        Label LocationLabel = new Label(translate?"Location":LoginMap.get("Location"));
        ComboBox LocationBox = new ComboBox(FXCollections.observableArrayList(CUS.fun("select distinct(division) from customer").split("#")));
        LocationBox.setMinWidth(200);
        LocationBox.getSelectionModel().selectFirst();
        GridPane.setConstraints(LocationLabel,0,0);
        GridPane.setConstraints(LocationBox,2,0);
        g3.setAlignment(Pos.TOP_CENTER);
        g3.setHgap(100);
        g3.getChildren().addAll(LocationLabel,LocationBox);
        LocationBox.setOnAction(e->{
            FilteredList<Customer> fcusts = new FilteredList<>(custs, i->i.getDivision().equals(LocationBox.getValue().toString()));
            rtable2.setItems(fcusts);
        });


        Label CountryLabel = new Label(translate?"Country":LoginMap.get("Country"));

        ComboBox CountryBox = new ComboBox(FXCollections.observableArrayList(CUS.fun("select distinct(country) from customer").split("#")));
        CountryBox.setMinWidth(200);
        CountryBox.getSelectionModel().selectFirst();
        GridPane.setConstraints(CountryLabel,0,0);
        GridPane.setConstraints(CountryBox,2,0);
        g4.setAlignment(Pos.TOP_CENTER);
        g4.setHgap(100);
        g4.getChildren().addAll(CountryLabel,CountryBox);
        CountryBox.setOnAction(e->{
            FilteredList<Customer> fcusts = new FilteredList<>(custs, i->i.getCountry().equals(CountryBox.getValue().toString()));
            rtable2.setItems(fcusts);
        });
        GridPane lp = new GridPane();
        Button CancelButton = new Button(translate?"Cancel":LoginMap.get("Cancel"));
        CancelButton.setOnAction(e->{
            window.close();
        });
        CancelButton.setMinWidth(200);

        Button LogoutButton = new Button(
                translate?"Logout":LoginMap.get("Logout"));
        LogoutButton.setMinWidth(200);
        LogoutButton.setOnAction(e->{
            window.close();
            wind.close();
            System.out.println("User logged out");
        });

        GridPane.setConstraints(CancelButton,0,0);
        GridPane.setConstraints(LogoutButton,2,0);

        lp.getChildren().addAll(CancelButton,LogoutButton);
        lp.setAlignment(Pos.BOTTOM_CENTER);
        lp.setPadding(new Insets(30,30,30,30));
        lp.setHgap(100);

        bp.setTop(layout);
        bp.setCenter(g1);
        mainbp.setTop(bp);
        mainbp.setBottom(lp);
        bp.setTop(layout);
        bp.setCenter(g1);
        mainbp.setTop(bp);
        mainbp.setCenter(rtable1);

        FilteredList<Customer> fcusts;
        tg.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            public void changed(ObservableValue<? extends Toggle> ob,
                                Toggle o, Toggle n)
            {
                RadioButton rb = (RadioButton)tg.getSelectedToggle();
                if (rb != null) {
                    String s = rb.getText();
                    if(s.equals("Contact Schedule") || s.equals(LoginMap.get("Contact Schedule"))){
                        mainbp.setCenter(rtable1);
                        FilteredList<Appointment> fappts = new FilteredList<>(appts, i->i.getContact().toString().equals(ContactBox.getValue()));
                        bp.setCenter(g1);
                        rtable1.setItems(fappts);

                    }else if(s.equals("Total Appointments by Type") || s.equals(LoginMap.get("Total Appointments by Type"))){
                        bp.setCenter(g2);
                        FilteredList<Appointment> fappts = new FilteredList<>(appts, i->i.getType().equals(TypeBox.getValue().toString()));
                        rtable1.setItems(fappts);
                        mainbp.setCenter(rtable1);

                    }else if(s.equals("Total Customers by Location") || s.equals(LoginMap.get("Total Customers by Location"))){
                        bp.setCenter(g3);
                        FilteredList<Customer> fcusts = new FilteredList<>(custs, i->i.getDivision().equals(LocationBox.getValue().toString()));
                        rtable2.setItems(fcusts);
                        mainbp.setCenter(rtable2);

                    }else if(s.equals("Total Customers by Country") || s.equals(LoginMap.get("Total Customers by Country"))){
                        bp.setCenter(g4);
                        FilteredList<Customer> fcusts = new FilteredList<>(custs, i->i.getCountry().equals(CountryBox.getValue().toString()));
                        rtable2.setItems(fcusts);
                        mainbp.setCenter(rtable2);

                    }
                }
            }
        });

        FilteredList<Appointment> fappts = new FilteredList<>(appts, i->i.getContact().toString().equals(ContactBox.getValue()));
        rtable1.setItems(fappts);
        g1.setPadding(new Insets(10, 10, 10, 10));
        g2.setPadding(new Insets(10, 10, 10, 10));
        g3.setPadding(new Insets(10, 10, 10, 10));
        g4.setPadding(new Insets(10, 10, 10, 10));

        Scene scene = new Scene(mainbp,1000,600);
        window.setScene(scene);

        window.show();

    }
}