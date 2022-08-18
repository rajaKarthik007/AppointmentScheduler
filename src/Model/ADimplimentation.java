package Model;

import View.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;
import View.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.TimeZone;

public class ADimplimentation implements AppointmentDao{

    /**
     * function to convert time from one zone to another
     * @param Time the time which has to be converted
     * @param sourceZoneID the zone in which the entered time is in
     * @param DestZoneID the zone to which the entered time has to be converted to
     * @return
     */
    public static String convert(String Time,String sourceZoneID,String DestZoneID){

        String[] time1 = Time.split(":");
        Calendar localTime = Calendar.getInstance();
        localTime.setTimeZone(TimeZone.getTimeZone(sourceZoneID));
        localTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time1[0]));
        localTime.set(Calendar.MINUTE, Integer.parseInt(time1[1]));
        localTime.set(Calendar.SECOND, Integer.parseInt(time1[2]));

        Calendar ConvertTime = new GregorianCalendar(TimeZone.getTimeZone(DestZoneID));
        ConvertTime.setTimeInMillis(localTime.getTimeInMillis());

        String hour = String.valueOf(ConvertTime.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(ConvertTime.get(Calendar.MINUTE));
        String second = String.valueOf(ConvertTime.get(Calendar.SECOND));
        if(hour.length()!=2) hour="0"+hour;
        if(minute.length()!=2) minute="0"+minute;
        if(second.length()!=2) second="0"+second;
        String res =  hour+":"+minute+":"+second;
        return res;
    }

    /**
     * Function which aids the user to modify an appointment
     * @param app The appointment the user has selected
     * @param St1 The Spinner where user enters the start hour of appointment
     * @param St2 The Spinner where user enters the start minuites of appointment
     * @param Et1 The Spinner where user enters the end hour of appointment
     * @param Et2 The Spinner where user enters the end minuites of appointment
     * @param Appid The Input field which has the appointment id
     * @param TitleInput The input field for giving the title of appointment
     * @param DescriptionInput The input field for giving the description of appointment
     * @param LocationInput The input field for giving the Location of appointment
     * @param TypeInput The input field for giving the type of appointment
     * @param ContactInput The input field for giving the contact ID of appointment
     * @param CustomerBox The input field for giving the View.Customer ID of customer in the appointment
     * @param UserIDBox The input field for giving the user ID of user in the appointment
     * @param StartDateInput The input field for giving the Start date of appointment
     * @param EndDateInput The input field for giving the End date of appointment
     */
    @Override
    public void modifyAppointment(Appointment app, Spinner St1, Spinner St2, Spinner Et1, Spinner Et2, TextField Appid, TextField TitleInput, TextField DescriptionInput, TextField LocationInput, TextField TypeInput, TextField ContactInput, ComboBox CustomerBox, ComboBox UserIDBox, DatePicker StartDateInput, DatePicker EndDateInput) {
        String TitleValue = app.getTitle();
        String DescriptionValue = app.getDescription();
        String LocationValue = app.getLocation();
        String TypeValue = app.getType();
        String ContactValue = app.getContact().toString();
        Double CustomerValue = app.getUID();
        Double UserIDValue = app.getUID();
        LocalDate StartDateValue = app.getSdate().toLocalDate();
        LocalDate EndDateValue = app.getEdate().toLocalDate();
        Object st1 = app.getStime().getHours();
        Object st2 = app.getStime().getMinutes();
        Object st3 = app.getEtime().getHours();
        Object st4 = app.getEtime().getMinutes();

        St1.getValueFactory().setValue(st1);
        St2.getValueFactory().setValue(st2);
        Et1.getValueFactory().setValue(st3);
        Et2.getValueFactory().setValue(st4);
        Appid.setText(app.getID().toString());
        TitleInput.setText(TitleValue);
        DescriptionInput.setText(DescriptionValue);
        LocationInput.setText(LocationValue);
        TypeInput.setText(TypeValue);
        ContactInput.setText(ContactValue);
        CustomerBox.getSelectionModel().select(CustomerValue);
        UserIDBox.getSelectionModel().select(UserIDValue);
        StartDateInput.setValue(StartDateValue);
        EndDateInput.setValue(EndDateValue);
    }

    /**
     * Function aiding the user to add new appointments
     * @param translate The variable which tells us whether the user has opted for translation or not
     * @param app The appointment the user has selected
     * @param AppointmentIdInput The Input field which has the appointment id
     * @param modApp The variable indicating whether the user has opted for modifying or creating new appointment.
     * @param homeTable The table containing all appointments of the logged-in user
     * @param uid User ID of the logged-in user
     * @param window Stage where the screen has been set up
     * @param newAptId View.Appointment ID of the newly added appointment.
     * @param St1 The Spinner where user enters the start hour of appointment
     * @param St2 The Spinner where user enters the start minuites of appointment
     * @param Et1 The Spinner where user enters the end hour of appointment
     * @param Et2 The Spinner where user enters the end minuites of appointment
     * @param TitleInput The input field for giving the title of appointment
     * @param DescriptionInput The input field for giving the description of appointment
     * @param LocationInput The input field for giving the Location of appointment
     * @param TypeInput The input field for giving the type of appointment
     * @param ContactInput The input field for giving the contact ID of appointment
     * @param CustomerBox The input field for giving the View.Customer ID of customer in the appointment
     * @param UserIDBox The input field for giving the user ID of user in the appointment
     * @param StartDateInput The input field for giving the Start date of appointment
     * @param EndDateInput The input field for giving the End date of appointment
     */
    @Override
    public void ValidateAddAppointment(Boolean translate, Appointment app, TextField AppointmentIdInput, Boolean modApp, TableView<Appointment> homeTable, int uid, Stage window, String newAptId, Spinner St1, Spinner St2, Spinner Et1, Spinner Et2, TextField TitleInput, TextField DescriptionInput, TextField LocationInput, TextField TypeInput, TextField ContactInput, ComboBox CustomerBox, ComboBox UserIDBox, DatePicker StartDateInput, DatePicker EndDateInput) {
        String TitleValue = TitleInput.getText();
        String DescriptionValue = DescriptionInput.getText();
        String LocationValue = LocationInput.getText();
        String TypeValue = TypeInput.getText();
        String ContactValue = ContactInput.getText();
        Object CustomerValue = CustomerBox.getValue();
        Object UserIDValue = UserIDBox.getValue();
        Object StartDateValue = StartDateInput.getValue();
        Object EndDateValue = EndDateInput.getValue();
        String st = St1.getValue().toString()+":"+St2.getValue().toString()+":"+"00";
        String et = Et1.getValue().toString()+":"+Et2.getValue().toString()+":"+"00";
        String startTime = convert(st, "Asia/Calcutta", ZoneId.systemDefault().toString());
        String endTime = convert(et, "Asia/Calcutta", ZoneId.systemDefault().toString());

        Translate D = new Translate();
        HashMap<String,String> LoginMap = D.getmap();
        Boolean validate = true;
        if(!TitleValue.equals("")){  TitleInput.setStyle(null);}
        else {
            validate = false;
            TitleInput.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
        }
        if (!DescriptionValue.equals("")){  DescriptionInput.setStyle(null);}
        else {
            validate = false;
            DescriptionInput.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
        }
        if (!LocationValue.equals("")){  LocationInput.setStyle(null);}
        else {
            validate = false;
            LocationInput.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
        }
        if (!TypeValue.equals("")){  TypeInput.setStyle(null);}
        else {
            validate = false;
            TypeInput.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
        }
        if (!ContactValue.equals("")){
            try {
                double d = Double.parseDouble(ContactValue);
            }catch (NumberFormatException e){
                ContactInput.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
                validate = false;
                e.printStackTrace();

            }
            ContactInput.setStyle(null);
        }
        else {
            validate = false;
            ContactInput.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
        }
        if (CustomerValue != null) { CustomerBox.setStyle(null);}
        else {
            validate = false;
            CustomerBox.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
        }
        if (UserIDValue != null){  UserIDBox.setStyle(null);}
        else {
            validate = false;
            UserIDBox.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
        }
        if (StartDateValue != null) { StartDateInput.setStyle(null);}
        else {
            validate = false;
            StartDateInput.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
        }

        if (EndDateValue != null) { EndDateInput.setStyle(null);}
        else {
            validate = false;
            EndDateInput.setStyle("-fx-border-color:red ; -fx-border-width: 2px ;");
        }
        if(StartDateValue.toString().compareTo(EndDateValue.toString()) >= 1){
            View.Alert.display(translate?"View.Alert!":LoginMap.get("View.Alert!"),translate?"Please enter Start Date, End Date correctly":LoginMap.get("Please enter Start Date, End Date correctly"));
        }else{
            if (validate){
                String url = "jdbc:mysql://localhost:3306/appointments";
                String dbuname = "root";
                String dbpass = "root";
                String query;
                if(!modApp){
                    query = "insert into appointments values ("+ String.valueOf(Integer.parseInt(newAptId.substring(0, newAptId.length()-2))+1)+",'"+ TitleValue+"','"+ DescriptionValue+"','"+LocationValue+"',"+ContactValue+", '"+TypeValue+"','"+ Date.valueOf(StartDateValue.toString())+"','"+Date.valueOf(EndDateValue.toString())+"', "+CustomerValue.toString()+", "+UserIDValue.toString()+", '"+ Time.valueOf(startTime)+"','"+Time.valueOf(endTime) +"');";
                }else{
                    query = "update appointments set title='"+TitleValue+"',descrption='"+DescriptionValue+"',location='"+LocationValue+"',contact="+ContactValue+",tpe='"+TypeValue+"',startdate='"+Date.valueOf(StartDateValue.toString())+"', enddate='"+Date.valueOf(EndDateValue.toString())+"', custid="+CustomerValue.toString()+",userid="+UserIDValue.toString()+", stime='"+Time.valueOf(startTime)+"', etime='"+Time.valueOf(endTime)+"' where id="+String.valueOf(Double.parseDouble(AppointmentIdInput.getText()));
                }
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                }catch(ClassNotFoundException e){
                    e.printStackTrace();
                }
                try{
                    Connection con = DriverManager.getConnection(url, dbuname, dbpass);
                    Statement stmt = con.prepareStatement(query);
                    stmt.executeUpdate(query);
//                    System.out.println(query);
                    if(!modApp){
                        Alert.display(translate?"Success":LoginMap.get("Success"), "View.Appointment "+String.valueOf(Integer.parseInt(newAptId.substring(0, newAptId.length()-2))+1) + (translate?" was Added!":" "+LoginMap.get("was Added")));
                    }else{

                        Alert.display(translate?"Success":LoginMap.get("Success"), "View.Appointment "+String.valueOf(Double.parseDouble(AppointmentIdInput.getText())) + (translate?" was Modified!":" "+LoginMap.get("was Modified!")));
                    }


//                    View.Appointment apt = new View.Appointment(Double.valueOf(UserIDValue.toString()), Double.valueOf(CustomerValue.toString()), Date.valueOf(String.valueOf(EndDateValue)), Date.valueOf(StartDateValue.toString()), Double.valueOf(String.valueOf(AppointmentIdInput.getText())), TitleValue, DescriptionValue, LocationValue, Double.valueOf(ContactValue), TypeValue, Time.valueOf(startTime), Time.valueOf(endTime));
                    Appointment apt = new Appointment(Double.valueOf(UserIDValue.toString()), Double.valueOf(CustomerValue.toString()), Date.valueOf(EndDateValue.toString()), Date.valueOf(StartDateValue.toString()), Double.parseDouble(AppointmentIdInput.getText()), TitleValue, DescriptionValue, LocationValue, Double.parseDouble(ContactValue), TypeValue, Time.valueOf(st), Time.valueOf(et));
                    if(!modApp && uid != Integer.parseInt(UserIDValue.toString().substring(0, UserIDValue.toString().length()-2))){
                        homeTable.getItems().add(apt);
                    }else{
                        homeTable.getItems().remove(app);
                        homeTable.getItems().add(apt);
                    }
                    window.close();

                }catch(SQLException e){
                    e.printStackTrace();
                    Alert.display(translate?"Error!":LoginMap.get("Error!"), translate?"Sorry we could not Add the New appointment":LoginMap.get("Sorry we could not Add the New appointment"));
                }
            }
        }
    }


    /**
     * function to execute a particular query and return the results
     * @param query The query to be executed
     * @return the results after query execution
     * @throws SQLException If a SQL database error occurs.
     */
    @Override
    public String ExecQuery(String query) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/appointments";
        String dbuname = "root";
        String dbpass = "root";
        String ret = "-1";
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
                ret = result.getString(1);
            }

//            System.out.println(ret);

        }catch(SQLException e){
            e.printStackTrace();
        }
        return ret;
    }

    /**
     *
     * @param uname the user id of the logged in user
     * @return Appointments contains all the appointments of the user who has logged in
     * @throws SQLException  If an object that is being called cannot be found.
     */
    @Override
    public ObservableList<Appointment> getAppointment(int uname) throws SQLException {
        ObservableList<Appointment> Appointments = FXCollections.observableArrayList();
        String url = "jdbc:mysql://localhost:3306/appointments";
        String dbuname = "root";
        String dbpass = "root";
        String query = "select * from appointments where userid="+String.valueOf(uname);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            Connection con = DriverManager.getConnection(url, dbuname, dbpass);
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while(result.next()){
                String data = "";
                for(int i=1; i<=12; i++){
                    data+=result.getString(i) +":";
                }
                Appointments.add(new Appointment(Double.parseDouble(result.getString(10)), Double.parseDouble(result.getString(9)),Date.valueOf(result.getString(7)),Date.valueOf(result.getString(8)),Double.parseDouble(result.getString(1)),result.getString(2),result.getString(3),result.getString(4),Double.parseDouble(result.getString(5)),result.getString(6),Time.valueOf(convert(result.getString(11), ZoneId.systemDefault().toString(), "Asia/Calcutta")), Time.valueOf(convert(result.getString(12), ZoneId.systemDefault().toString(), "Asia/Calcutta"))));
//                System.out.println(data);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }


        return Appointments;
    }


    /**
     * function to delete any appointment of the user who has logged in
     * @param translate The variable which tells us whether the user has opted for translation or not
     * @param homeTable The table which contains the appointments of the user
     */
    @Override
    public void deleteAppointment(Boolean translate, TableView<Appointment> homeTable) {

        Translate D = new Translate();
        HashMap<String,String> LoginMap = D.getmap();
        Appointment app = homeTable.getSelectionModel().getSelectedItem();
        if(app == null){
            Alert.display(translate?"View.Alert!": LoginMap.get("View.Alert!"), translate?"Please select a row to delete":LoginMap.get("Please select a row to delete"));

        }else{

            String url = "jdbc:mysql://localhost:3306/appointments";
            String dbuname = "root";
            String dbpass = "root";
            String query = "delete from appointments where id=" + app.getID();
//                System.out.println(query);
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
            }catch(ClassNotFoundException e){
                e.printStackTrace();
            }
            try{
                Connection con = DriverManager.getConnection(url, dbuname, dbpass);
                Statement stmt = con.prepareStatement(query);
                stmt.executeUpdate(query);
//                    System.out.println(result);
                //            System.out.println(result);
                homeTable.getItems().remove(app);
                Alert.display(translate?"Success":LoginMap.get("Success"), "View.Appointment "+app.getID() + (translate?" was deleted!":" "+LoginMap.get("was deleted!")));


            }catch(SQLException e){
                e.printStackTrace();
                Alert.display(translate?"Error!":LoginMap.get("Error!"), translate?"Sorry we could not delete the appointment":LoginMap.get("Sorry we could not delete the appointment"));
            }
        }
    }
}
