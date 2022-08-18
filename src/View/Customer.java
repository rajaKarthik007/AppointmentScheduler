package View;

/**
 *  Creates a unique View.Customer object with the specified information.
 */
public class Customer {
    public double CID;
    public String Cname;
    public String Caddress;
    public double postalcode;
    public String phonenumber;
    public String country;
    public String division;
    public double created_by;

    /**
     * Constructor that creates a new customer with information passed in from either the database or the Add View.Customer controller.
     */
    public Customer(){
        this.CID = 0;
        this.Cname = "";
        this.postalcode = 0;
        this.Caddress = "";
        this.phonenumber = "8341425621";
        this.country = "";
        this.created_by = 1;
        this.division = "";
    }

    /**
     *
     * @return the View.Customer ID.
     */
    public double getCID() {
        return CID;
    }
    /**
     *
     * @param CID The View.Customer ID to create customer.
     */
    public void setCID(double CID) {
        this.CID = CID;
    }

    /**
     *
     * @return the View.Customer Name.
     */
    public String getCname() {
        return Cname;
    }

    /**
     *
     * @param cname The name of the new customer.
     */
    public void setCname(String cname) {
        Cname = cname;
    }

    /**
     *
     * @return the View.Customer Address.
     */
    public String getCaddress() {
        return Caddress;
    }

    /**
     *
     * @param caddress The address of the new customer.
     */
    public void setCaddress(String caddress) {
        Caddress = caddress;
    }

    /**
     *
     * @return the View.Customer's area PostalCode.
     */
    public double getPostalcode() {
        return postalcode;
    }

    /**
     *
     * @param postalcode The postalcode of the customer.
     */
    public void setPostalcode(double postalcode) {
        this.postalcode = postalcode;
    }

    /**
     *
     * @return the View.Customer phone number.
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     *
     * @param phonenumber The phone number of the customer.
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    /**
     *
     * @return the View.Customer Country.
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country The country of the customer.
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return the View.Customer ID.
     */
    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    /**
     *
     * @return the ID of the user who created the customer.
     */
    public double getCreated_by() {
        return created_by;
    }

    /**
     *
     * @param created_by The D of the user who created the customer.
     */
    public void setCreated_by(double created_by) {
        this.created_by = created_by;
    }

    /**
     *
     * @param CID The View.Customer ID to create customer.
     * @param Cname The name of the customer.
     * @param Caddress The address of the customer.
     * @param postalcode The postalcode of the customer.
     * @param phonenumber The phone number of the customer.
     * @param country The country of the customer.
     * @param division The division of the customer.
     * @param created_by The ID of the user who created the View.Customer.
     */
    public Customer(double CID, String Cname, String Caddress, double postalcode, String phonenumber, String country, String division, double created_by){
        this.CID = CID;
        this.Cname = Cname;
        this.postalcode = postalcode;
        this.Caddress = Caddress;
        this.phonenumber = phonenumber;
        this.country = country;
        this.division = division;
        this.created_by = created_by;
    }

}
