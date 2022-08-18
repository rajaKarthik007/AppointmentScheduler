package Model;
import View.*;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;

public interface AppointmentDao {
    public void modifyAppointment(Appointment app, Spinner St1, Spinner St2, Spinner Et1, Spinner Et2, TextField Appid, TextField TitleInput, TextField DescriptionInput, TextField LocationInput, TextField TypeInput, TextField ContactInput, ComboBox CustomerBox, ComboBox UserIDBox, DatePicker StartDateInput, DatePicker EndDateInput);
    public void ValidateAddAppointment(Boolean translate, Appointment app, TextField AppointmentIdInput, Boolean modApp, TableView<Appointment> homeTable, int uid, Stage window, String newAptId, Spinner St1, Spinner St2, Spinner Et1, Spinner Et2, TextField TitleInput, TextField DescriptionInput, TextField LocationInput, TextField TypeInput, TextField ContactInput,
                                       ComboBox CustomerBox, ComboBox UserIDBox, DatePicker StartDateInput, DatePicker EndDateInput);
    public String ExecQuery(String query) throws SQLException;
    public ObservableList<Appointment> getAppointment(int uname) throws SQLException;
    public void deleteAppointment(Boolean translate, TableView<Appointment> homeTable);
}
