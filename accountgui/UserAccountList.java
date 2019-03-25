/**
 * This implements the UserAccountList class to store user data and automatically save it for
 * future use by the study spot recommendation program.
 *
 * Last modified: February 28, 2019
 *
 */

 package accountgui;

import java.util.HashMap;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import logic.IdealStudySpot;

public class UserAccountList {
    private HashMap<String, String> credentialsMap;
    private HashMap<String, IdealStudySpot> userDataMap;

    /**
     * Default constructor for UserAccountList object
     * This constructor will end the program if it is unable to read the user data file.
     * NOTE: THE USERACCOUNTS.DAT FILE MUST BE INITIALIZED AFTER ANY UPDATES TO STUDYSPOT OR IDEALSTUDYSPOT
     * BEFORE THIS IS RUN TO AVOID UNPREDICTABLE RESULTS AND WARRANT SUPPRESSING THE UNCHECKED COMPILER WARNINGS
     */
    @SuppressWarnings("unchecked")
    public UserAccountList() {

        try {
            ObjectInputStream savedData = new ObjectInputStream(new FileInputStream("UserAccounts.dat"));
            credentialsMap = (HashMap<String, String>)savedData.readObject();
            userDataMap = (HashMap<String, IdealStudySpot>)savedData.readObject();
            savedData.close();
        }
        catch(Exception e) {
            System.out.println("There was an error attempting to read saved user data.");
            System.exit(0);
        }
    }

    /**
     * Saves the user data to the UserAccounts.dat file for future use or writes a message indicating that the
     * save process failed but allows the program to continue if there is an error writing to the file
     */
    private void saveData() {

        try {
            ObjectOutputStream fileToWrite = new ObjectOutputStream(new FileOutputStream("UserAccounts.dat"));
            fileToWrite.writeObject(credentialsMap);
            fileToWrite.writeObject(userDataMap);
            fileToWrite.close();
        }
        catch(Exception e) {
            System.out.println("Failed to save user data to file");
        }
    }

    /**
     * Returns true if there is a saved account with the given username or false otherwise
     * @param username: String containing the username to check for
     */
    public boolean hasAccount(String username) {
        return credentialsMap.containsKey(username);
    }

    /**
     * Returns true if the given username and password match a saved account or false otherwise
     * @param username: String containing the username to check for
     * @param password: String containing the password to check for
     */
    public boolean credentialsValid(String username, String password) {

        boolean valid = false;
        if(this.hasAccount(username)) {
            valid = credentialsMap.get(username).equals(password);
        }

        return valid;
    }

    /**
     * Creates an account with the given username and password if there is not already an account with the given
     * username, or does nothing otherwise
     * The data file is automatically updated unless there is an error doing so.
     * @param username: String containing the username for the new account
     * @param password: String containing the password for the new account
     */
    public void addAccount(String username, String password) {
        this.addAccount(username, password, new IdealStudySpot());
    }

    /**
     * Creates an account with the given username, password, and user data if there is not already an account
     * with the given username, or does nothing otherwise
     * The data file is automatically updated unless there is an error doing so.
     * @param username: String containing the username for the new account
     * @param password: String containing the password for the new account
     * @param userData: IdealStudySpot containing the preferences (survey answers) for the user
     */
    public void addAccount(String username, String password, IdealStudySpot userData) {

        if(!this.hasAccount(username)) {
            credentialsMap.put(username, password);
            userDataMap.put(username, new IdealStudySpot(userData));
            this.saveData();
        }
    }

    /**
     * Sets the user data for the account with the given username and password to the given IdealStudySpot
     * object or does nothing if the username and password are not valid
     * The data file is automatically updated unless there is an error doing so.
     * @param username: String containing the username for the account to change
     * @param password: String containing the password for the account to change
     * @param userData: IdealStudySpot to use for the updated account data
     */
    public void setUserData(String username, String password, IdealStudySpot userData) {

        if(credentialsValid(username, password)) {
            userDataMap.put(username, new IdealStudySpot(userData));
            this.saveData();
        }
    }

    /**
     * Returns an IdealStudySpot containing the user data for the account with the given username and
     * password or returns null if the username and password are invalid
     * @param username: String containing the username for the account to check
     * @param password: String containing the password for the account to check
     */
    public IdealStudySpot getUserData(String username, String password) {

        IdealStudySpot userData = null;
        if(credentialsValid(username, password)) {
            userData = new IdealStudySpot(userDataMap.get(username));
        }

        return userData;
    }

    /**
     * Tests the UserAccountList class
     */
    public static void main(String[] args) {
        UserAccountList userList = new UserAccountList();
        System.out.println(userList.hasAccount("testUsername"));
        userList.addAccount("testUsername", "password");
        System.out.println(userList.hasAccount("testUsername"));
        System.out.println(userList.credentialsValid("testUsername", "password"));
        System.out.println(userList.credentialsValid("testUSername", "wrongPassword"));
        IdealStudySpot someData = new IdealStudySpot(), moreData;
        someData.setNoiseLevel(5.0);
        userList.setUserData("testUsername", "password",someData);
        moreData = userList.getUserData("testUsername", "password");
        System.out.println(moreData.getNoiseLevel());
    }
}