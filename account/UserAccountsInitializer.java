/**
 * This implements the UserAccountsInitializer class, which is meant to initialize the UserAccounts.dat file for
 * use by the UserAccountList class.
 * This class should be run after any updates to StudySpot or IdealStudySpot before the main program is run, and
 * will erase any stored user data. It should not be used in the main program.
 *
 * Last modified: March 25, 2019
 *
 */

package account;

import java.util.Scanner;
import java.util.HashMap;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import logic.IdealStudySpot;

public class UserAccountsInitializer {

    public static void main(String[] args) {

        HashMap<String, String> credentialsMap = new HashMap<String, String>();
        HashMap<String, IdealStudySpot> userDataMap = new HashMap<String, IdealStudySpot>();

        // Confirms that the user wants to initialize the file.
        Scanner keyboard = new Scanner(System.in);
        String choice = "";
        boolean validInput = false;

        System.out.println("WARNING: This process will erase any available stored user data.");
        System.out.println("Enter c if you wish to continue or q to quit.");

        while(!validInput) {
            choice = keyboard.next();
            if(choice.equalsIgnoreCase("c") || choice.equalsIgnoreCase("q")) {
                validInput = true;
            }
        }

        // Writes the necessary objects to the file and tells the user or indicates that there was an error
        if(choice.equalsIgnoreCase("c")) {
            try {
                ObjectOutputStream fileToInitialize = new ObjectOutputStream(new FileOutputStream("UserAccounts.dat"));
                fileToInitialize.writeObject(credentialsMap);
                fileToInitialize.writeObject(userDataMap);
                fileToInitialize.close();
                System.out.println("The file \"UserAccounts.dat\" was initialized successfully.");
            }
            catch(Exception e) {
                System.out.println("There was an error initializing the file.");
            }
        }

    }
}
