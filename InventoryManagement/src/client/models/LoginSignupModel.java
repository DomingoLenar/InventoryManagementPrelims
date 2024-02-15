package client.models;
import utility.User;

import java.io.*;
import java.net.Socket;

/**
 * Represents the model for handling user login and signup operations on the client side.
 */
public class LoginSignupModel {
    private final Socket sSocket;
    private final ObjectOutputStream oos;
    private final PrintWriter pWriter;

    /**
     * Constructs a new LoginModel with the specified sSocket and output stream.
     *
     * @param sSocket        The sSocket connected to the server.
     * @param oos  The output stream used for communication with the server.
     */
    public LoginSignupModel(Socket sSocket, ObjectOutputStream oos) {
        this.sSocket = sSocket;
        this.oos = oos;
        try {
            this.pWriter = new PrintWriter(sSocket.getOutputStream(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends an action to the server.
     *
     * @param action The action to be sent.
     */
    public void sendAction(String action) {
        pWriter.println(action);
        System.out.println(action + " sent to server");
    }

    /**
     * Handles the login process by sending user credentials to the server.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @param role = The user's role
     * @return True if login is successful, false otherwise.
     */
    public boolean handleLogin(String username, String password, String role) {
        try {
            // Create a new User object with provided credentials
            User currentUser = new User(username, password, role);

            // Send the action for user authentication
            sendAction("userVerification");

            // Send the User object to the server for login
            oos.writeObject(currentUser);
            System.out.println(currentUser.getUsername() + " sent to the server for login");

            // Receive authentication response from the server
            try (ObjectInputStream ios = new ObjectInputStream(sSocket.getInputStream())) {
                boolean loginSuccess = (boolean) ios.readObject();
                System.out.println("Authentication response: " + loginSuccess);
                return loginSuccess;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error handling login", e);
        }
    }

    /**
     * Handles the signup process by sending user credentials to the server.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @param role The user's role
     * @return True if account creation is successful, false otherwise.
     */
    public boolean handleSignup(String username, String password, String role) {
        try {
            // Create a new User object with provided credentials
            User newUser = new User(username, password, role);

            // Send the User object to the server for sign-up
            oos.writeObject(newUser);
            System.out.println(newUser.getUsername() + " sent to the server for sign-up");

            // Receive account creation response from the server
            try (ObjectInputStream ios = new ObjectInputStream(sSocket.getInputStream())) {
                boolean createAccountSuccess = (boolean) ios.readObject();
                System.out.println("Account Creation Response: " + createAccountSuccess);
                return createAccountSuccess;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error handling signup", e);
        }
    }
}
