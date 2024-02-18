package client.models;

import utility.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Represents the model for handling user login and signup operations on the client side.
 */
// TODO: Refactor the whole class and its method so that it is accessed statically
public class ProfileManagementModel {


    public ProfileManagementModel() {

    }

    /**
     * Sends an action to the server.
     *
     * @param action The action to be sent.
     */

    public static void sendAction(String action, ObjectOutputStream oos) throws IOException {
        try {
            oos.writeUTF(action);
            oos.flush();
            System.out.println(action + " sent to server");
        } catch (IOException e) {
            throw new RuntimeException("Error sending action to server", e);
        }
    }

    /**
     * Handles the login process by sending user credentials to the server.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @return True if login is successful, false otherwise.
     */

    public static boolean handleLogin(String username, String password, ObjectOutputStream oOs, ObjectInputStream oIs) {
        // Create a new User object with provided credentials
        User currentUser = new User(username, password, null, false);

        try {


            sendAction("userVerification", oOs);

            // Send the User object to the server for login
            oOs.writeObject(currentUser);
            System.out.println(currentUser.getUsername() + " sent to the server for login");

            try {
                boolean loginSuccess = oIs.readBoolean();
                System.out.println("Authentication response: " + loginSuccess);
                return loginSuccess;
            } finally {

            }

        } catch (IOException e){
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Handles the signup process by sending user credentials to the server.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @param role The user's role
     * @return True if account creation is successful, false otherwise.
     */

    @Deprecated
    public static boolean handleSignup(String username, String password, String role,ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {
            // Create a new User object with provided credentials
            User newUser = new User(username, password,role,false);

            sendAction("createUser", oOs);

            // Send the User object to the server for sign-up
            oOs.writeObject(newUser);
            oOs.flush();
            System.out.println(newUser.getUsername() + " sent to the server for sign-up");

            // Receive account creation response from the server
            try {
                boolean createAccountSuccess = (boolean) oIs.readObject();
                System.out.println("Account Creation Response: " + createAccountSuccess);
                return createAccountSuccess;
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error handling signup", e);
        }
    }

    /**
     * Method for changing a password of a user
     *
     * @param userName    The username of the user whose password is to be changed.
     * @param newPassword The new password to set for the user.
     * @return True if the password change was successful, false otherwise.
     * @throws RuntimeException If an error occurs while changing the password.
     */
    public static boolean changePassword(String userName, String newPassword, ObjectOutputStream oOs, ObjectInputStream oIs) {
        try {


            sendAction("changePassword", oOs);

            oOs.writeUTF(userName);
            oOs.writeUTF(newPassword);
            oOs.flush();

            System.out.println("Password change request has been sent to the server...");

            try {
                boolean changeSuccess = oIs.readBoolean();
                System.out.println("Server response: " + changeSuccess);
                return changeSuccess;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException("Error changing password", e);
        }
    }

    public static ArrayList<User> fetchListOfUsers (ObjectOutputStream oOs, ObjectInputStream oIs){

        try {

            sendAction("fetchListOfUsers", oOs);

            try  {
                ArrayList<User> listOfUsers = (ArrayList<User>) oIs.readObject();
                System.out.println("List of users have been fetched.");
                return listOfUsers;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
