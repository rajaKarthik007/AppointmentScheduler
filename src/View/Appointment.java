package View;

import java.sql.Date;

/**
 * Creates a unique appointment object with the specified information.
 */
public class Appointment {
    private Double ID;
    private String title;
    private String Description;
    private String location;
    private Double contact;
    private String type;
    private java.sql.Time Stime;
    private java.sql.Time Etime;
    private java.sql.Date Sdate;
    private java.sql.Date Edate;
    private Double CID;
    private Double UID;

    /**
     * Constructor that creates a new appointment with information passed in from either the database or the Add View.Appointment controller.
     */
    public Appointment(){
        this.ID = 0.;
        this.title = "";
        this.Description = "";
        this.location = "";
        this.contact = 0.;
        this.type = "";
        this.Stime = new java.sql.Time((new java.util.Date()).getTime());
        this.Etime = new java.sql.Time((new java.util.Date()).getTime());
        this.Sdate = new java.sql.Date(System.currentTimeMillis());
        this.Edate = new java.sql.Date(System.currentTimeMillis());
        this.UID = 0.;
        this.CID = 0.;

    }

    /**
     *
     * @return the appointment ID.
     */
    public Double getID() {
        return ID;
    }

    /**
     *
     * @param ID The appointment ID to set for this appointment.
     */
    public void setID(Double ID) {
        this.ID = ID;
    }

    /**
     *
     * @return the title of the appointment.
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title the title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return the description of the appointment.
     */
    public String getDescription() {
        return Description;
    }

    /**
     *
     * @param description the appointment description to set.
     */
    public void setDescription(String description) {
        Description = description;
    }

    /**
     *
     * @return the location of the appointment.
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location location the location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return The ID of the contact for this appointment.
     */
    public Double getContact() {
        return contact;
    }

    /**
     *
     * @param contact the contact information of the appointment to be set.
     */
    public void setContact(Double contact) {
        this.contact = contact;
    }

    /**
     *
     * @return  the appointment type to set
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type the View.Appointment type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    public java.sql.Time getStime() {
        return Stime;
    }

    public void setStime(java.sql.Time stime) {
        Stime = stime;
    }

    public java.sql.Time getEtime() {
        return Etime;
    }

    public void setEtime(java.sql.Time etime) {
        Etime = etime;
    }

    /**
     *
     * @return the start date of the appointment.
     */
    public java.sql.Date getSdate() {
        return Sdate;
    }

    /**
     *
     * @param sdate sets the start date of the appointment
     */
    public void setSdate(java.sql.Date sdate) {
        Sdate = sdate;
    }

    /**
     *
     * @return the end date of the appointment.
     */
    public java.sql.Date getEdate() {
        return Edate;
    }

    /**
     *
     * @param edate sets the end date of the appointment
     */
    public void setEdate(java.sql.Date edate) {
        Edate = edate;
    }

    /**
     *
     * @return The ID of the customer for this appointment.
     */
    public Double getCID() {
        return CID;
    }

    /**
     *
     * @param CID The customer ID to set for this appointment.
     */
    public void setCID(Double CID) {
        this.CID = CID;
    }

    /**
     *
     * @return the use ID of the appointment.
     */
    public Double getUID() {
        return UID;
    }

    /**
     *
     * @param UID the user ID to be set.
     */
    public void setUID(Double UID) {
        this.UID = UID;
    }

    /**
     *
     * @param UID The ID for the user that created this appointment
     * @param CID The ID for the customer that is associated with this appointment.
     * @param Edate Data for the end of the appointment.
     * @param Sdate Data for the start of the appointment.
     * @param ID The ID of the contact that is associated with this appointment.
     * @param title The title of the appointment
     * @param Description A description of the appointment's purpose.
     * @param location The location of the appointment
     * @param contact The ID of the contact that is associated with this appointment.
     * @param type A description of what type of appointment this is.
     * @param Stime The start time of the appointment
     * @param Etime The end time of the appointment
     */
    public Appointment(Double UID, Double CID, java.sql.Date Edate, java.sql.Date Sdate, Double ID, String title, String Description, String location, Double contact, String type, java.sql.Time Stime, java.sql.Time Etime){
        this.ID = ID;
        this.title = title;
        this.Description = Description;
        this.location = location;
        this.contact = contact;
        this.type = type;
        this.Stime = Stime;
        this.Etime = Etime;
        this.Sdate = Sdate;
        this.Edate = Edate;
        this.UID = UID;
        this.CID = CID;
    }
}
