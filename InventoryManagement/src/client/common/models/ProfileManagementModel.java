package client.common.models;
import utility.User;

import java.io.*;
import java.net.Socket;

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

    public static String handleLogin(String username, String password, Socket clientSocket) {
        // Create a new User object with provided credentials
        User currentUser = new User(username, password, null, false);

        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
            InputStream iS = clientSocket.getInputStream();
            ObjectInputStream ios = new ObjectInputStream(iS);

            sendAction("userVerification", oos);

            // Send the User object to the server for login
            oos.writeObject(currentUser);
            System.out.println(currentUser.getUsername() + " sent to the server for login");

            try {
                User user = (User) ios.readObject();
                if (user != null) {
                    System.out.println("Authentication response: success");
                    return user.getRole();
                } else {
                    System.out.println("Authentication response: failed");
                    return null;
                }
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {

            }

        } catch (IOException e){
            e.printStackTrace();
        }
        // Check if the socket is open and connected
//            if (socket == null || socket.isClosed() || !socket.isConnected()) {
//                throw new IOException("Socket is not open or connected");
//            }

//            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
//            ObjectInputStream ois = new ObjectInputStream(inputStream);


//            try (ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
//                // Send the action for user authentication
//
//                oos.writeObject(currentUser);
//                System.out.println(currentUser.getUsername() + " sent to the server for login");
//
//                // Receive authentication response from the server

//            }
//            boolean loginSuccess = (boolean) ois.readObject();
//            System.out.println("Authentication response: " + loginSuccess);
//            return loginSuccess;
        return null;
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
    public static boolean handleSignup(String username, String password, String role, Socket clientSocket) {
        try {
            // Create a new User object with provided credentials
            User newUser = new User(username, password,role,false);
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

            sendAction("createUser", oos);

            // Send the User object to the server for sign-up
            oos.writeObject(newUser);
            oos.flush();
            System.out.println(newUser.getUsername() + " sent to the server for sign-up");

            // Receive account creation response from the server
            try (ObjectInputStream ios = new ObjectInputStream(clientSocket.getInputStream())) {
                boolean createAccountSuccess = (boolean) ios.readObject();
                System.out.println("Account Creation Response: " + createAccountSuccess);
                return createAccountSuccess;
            }
        } catch (IOException | ClassNotFoundException e) {
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
    public boolean changePassword(String userName, String newPassword, Socket clientSocket) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

            sendAction("changePassword", oos);

            oos.writeUTF(userName);
            oos.writeUTF(newPassword);
            oos.flush();

            System.out.println("Password change request has been sent to the server...");

            try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream())) {
                boolean changeSuccess = ois.readBoolean();
                System.out.println("Server response: " + changeSuccess);
                return changeSuccess;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error changing password", e);
        }
    }

}
