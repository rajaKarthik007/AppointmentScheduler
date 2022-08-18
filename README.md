# View.Appointment Scheduler

17 July 2022

Author: Vobugari Raja Karthik

Contact Information

Email: 411992@student.nitandhra.ac.in

View.Appointment scheduler system application.

Scenario:

You are working for a software company that has been contracted to develop a GUI-based scheduling desktop application. The contract is with a global consulting organization that conducts business in multiple languages and has main offices in Phoenix, Arizona; White Plains, New York; Montreal, Canada; and London, England. The consulting organization has provided a MySQL database that the application must pull data from. The database is used for other systems, so its structure cannot be modified.

The organization outlined specific business requirements that must be met as part of the application. From these requirements, a system analyst at your company created solution statements for you to implement in developing the application. These statements are listed in the requirements section.

Your company acquires Country and First-Level-Division data from a third party that is updated once per year. These tables are prepopulated with read-only data. Please use the attachment “Locale Codes for Region and Language” to review division data. Your company also supplies a list of contacts, which are prepopulated in the Contacts table; however, administrative functions such as adding users are beyond the scope of the application and done by your company’s IT support staff. Your application should be organized logically using one or more design patterns and generously commented using Javadoc so your code can be read and maintained by other programmers.

Implementation:

This program uses the Model-View-Controller software design pattern to form the basis of data sharing between the different controllers. It also uses CSS to style each controller, and the program uses styling to alert the users of errors.
The program was built using Java 18.0.1.1, JavaFX 18.0.1 and it used mysql-java-connector Version 8.0.29(jdbc connector) for the database connector. For the submission the program was switched over to MySQL 8.0. for the database connection.
It was written using IntelliJ IDEA 2022.1.3 Ultimate Edition.

The Data Access Object (DAO) pattern which is a structural pattern that allows us to isolate the application/business layer from the persistence layer is followed for implimentation of this project.
Model-View-Controller Pattern which is used to separate application's concerns is also used for implimentation.
 

How to run the program:
1. Download and unzip the file.
2. Open the file as a project in IntelliJ IDE.
3. In the top Navigation bar,Navigate to file -> project structure -> libraries and add the JavaFX and MYSQL Connector libraries.
4. In the top Navigation bar,Navigate to run -> edit configurations. On the right side of the new pane select Modify options and then click add VM Options and enter the following values as the VM Options :
    '''--module-path ${PATH_TO_FX} --add-modules=javafx.controls'''
    replace ${PATH_TO_FX} with the actual path to the JavaFx library in your local system.
5. Set up a local database in your system.
6. Change database credentials in the code to your own credentials.
7. Run the Controller.Main.java file.

functionality for viewing reports based on contact and type of appointments, customers by location and country has been incorporated as part of part A3f.

