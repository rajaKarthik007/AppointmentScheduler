package View;

import java.util.HashMap;

/**
 * Class which converts helps in translation.
 */
public class Translate {
    HashMap<String,String> LanguageMap=new HashMap<String,String>();

    /**
     * Maps english words to the corresponding French words.
     */
    public Translate(){
        LanguageMap.put("Username","Nom d’utilisateur");
        LanguageMap.put("Password","Mot de passe");
        LanguageMap.put("Log In","Connexion");
        LanguageMap.put("Reset","Réinitialiser");
        LanguageMap.put("Language","Langue");
        LanguageMap.put("Timezone","Fuseau horaire");
        LanguageMap.put("View.Appointment Schedule","Horaire de rendez-vous");
        LanguageMap.put("View Customers","Voir les clients");
        LanguageMap.put("View By Week","Afficher par semaine");
        LanguageMap.put("View By Month","Afficher par mois");
        LanguageMap.put("View All","Voir tout");
        LanguageMap.put("Appt ID","ID de rendez-vous");
        LanguageMap.put("Title","Titre");
        LanguageMap.put("Description","La description");
        LanguageMap.put("Location","Emplacement");
        LanguageMap.put("Contact","Contact");
        LanguageMap.put("Type","Taper");
        LanguageMap.put("Start Time","Heure de début");
        LanguageMap.put("End Time","Heure de fin");
        LanguageMap.put("Start Date","Date de début");
        LanguageMap.put("End Date","Date de fin");
        LanguageMap.put("View.Customer ID","N ° de client");
        LanguageMap.put("User ID","Identifiant d'utilisateur");
        LanguageMap.put("Reports","Rapports");
        LanguageMap.put("Add View.Appointment","Ajouter un rendez-vous");
        LanguageMap.put("Modify View.Appointment","Modifier le rendez-vous");
        LanguageMap.put("Delete View.Appointment","Supprimer le rendez-vous");
        LanguageMap.put("Logout","Se déconnecter");
        LanguageMap.put("View.Customer Name","Nom du client");
        LanguageMap.put("Address","Adresse");
        LanguageMap.put("Postal Code","code postal");
        LanguageMap.put("Phone Number","Numéro de téléphone");
        LanguageMap.put("Countries","Des pays");
        LanguageMap.put("Divisions","Divisions");
        LanguageMap.put("Save","sauvegarder");
        LanguageMap.put("Cancel","Annuler");
        LanguageMap.put("View.Appointment ID","ID de rendez-vous");
        LanguageMap.put("Contact ID","ID de contact");
        LanguageMap.put("View.Login","Connexion");
        LanguageMap.put("Home","Maison");
        LanguageMap.put("Add View.Customer","Ajouter un client");
        LanguageMap.put("Contact Schedule","Horaire des contacts");
        LanguageMap.put("Total Appointments by Type","Total des rendez-vous par heure");
        LanguageMap.put("Total Customers by Location","Nombre total de clients par emplacement");
        LanguageMap.put("Total Customers by Country","Nombre total de clients par pays");
        LanguageMap.put("Month","Mois");
        LanguageMap.put("Country","Pays");
        LanguageMap.put("Created Date","Date de création");
        LanguageMap.put("Created By","Créé par");
        LanguageMap.put("Last Update","Dernière mise à jour");
        LanguageMap.put("Last Updated By","Dernière mise à jour par");
        LanguageMap.put("State/Province","État/Province");
        LanguageMap.put("Total Customers","Nombre total de clients");
        LanguageMap.put("Modify View.Customer","Modifier le client");
        LanguageMap.put("Delete View.Customer","Supprimer le client");
        LanguageMap.put("user ID has to be an Integer","l'ID utilisateur doit être un entier");
        LanguageMap.put("View.Alert!","Alerte");
        LanguageMap.put("Password is incorrect","Mot de passe incorrect");
        LanguageMap.put("The entered userID does not exist. please try again.","L'ID utilisateur saisi n'existe pas. Veuillez réessayer.");
        LanguageMap.put("Please enter Start Date, End Date correctly","Veuillez saisir correctement la date de début et la date de fin");
        LanguageMap.put("Please Select an View.Appointment to modify","Veuillez sélectionner un rendez-vous à modifier");
        LanguageMap.put("was Added!","était ajouté");
        LanguageMap.put("was Modified!","a été modifié");
        LanguageMap.put("Sorry we could not Add the New appointment","Désolé, nous n'avons pas pu ajouter le nouveau rendez-vous");
        LanguageMap.put("Sorry we could not Add the New View.Customer. Please check and try again","Désolé, nous n'avons pas pu ajouter le nouveau client. S'il vous plaît, vérifiez et essayez à nouveau");
        LanguageMap.put("We could not modify the required customer details at the moment. Try again","Nous ne pouvons pas modifier les informations client requises pour le moment. Réessayer");
        LanguageMap.put("Error!","Erreur!");
        LanguageMap.put("details have been updated","les détails ont été mis à jour");
        LanguageMap.put("Please select a row to delete","Veuillez sélectionner une ligne à supprimer");
        LanguageMap.put("Select a customer to delete!","Sélectionnez un client à supprimer !");
        LanguageMap.put("You Have a meeting within the next 15 minuites","Vous avez une réunion dans les 15 prochaines minutes");
        LanguageMap.put("was deleted","A été supprimée");
        LanguageMap.put("Sorry we could not delete the appointment","Désolé, nous n'avons pas pu supprimer le rendez-vous");
        LanguageMap.put("Sorry we could not delete the View.Customer. please try again","Désolé, nous n'avons pas pu supprimer le client. Veuillez réessayer");
        LanguageMap.put("was deleted!","A été supprimée!");
        LanguageMap.put("Please select a View.Customer to delete","Veuillez sélectionner un client à supprimer");
    };

    public HashMap<String,String> getmap(){
        return this.LanguageMap;
    }
};