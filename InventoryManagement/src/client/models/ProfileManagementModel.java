package client.models;
import utility.User;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Represents the model for handling user login and signup operations on the client side.
 */
public class ProfileManagementModel {
    private final Socket socket;
    private final ObjectOutputStream outputStream;

    /**
     * Constructs a new LoginModel with the specified socket and output stream.
     *
     * @param socket        The socket connected to the server.
     * @param outputStream  The output stream used for communication with the server.
     */
    public ProfileManagementModel(Socket socket, ObjectOutputStream outputStream) {
        this.socket = socket;
        this.outputStream = outputStream;
    }

    /**
     * Sends an action to the server.
     *
     * @param action The action to be sent.
     */
    public void sendAction(String action) {
        try {
            outputStream.writeObject(action);
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
   
    public boolean handleLogin(String username, String password) {
        try {
            // Create a new User object with provided credentials
            User currentUser = new User(username, password, null, false);

            // Send the action for user authentication
            sendAction("userAuthentication");

            // Send the User object to the server for login
            outputStream.writeObject(currentUser);
            System.out.println(currentUser.getUsername() + " sent to the server for login");

            // Receive authentication response from the server
            try (ObjectInputStream ios = new ObjectInputStream(socket.getInputStream())) {
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
            User newUser = new User(username, password,role,false);

            sendAction("createUser");
            // Send the User object to the server for sign-up
            outputStream.writeObject(newUser);
            System.out.println(newUser.getUsername() + " sent to the server for sign-up");

            // Receive account creation response from the server
            try (ObjectInputStream ios = new ObjectInputStream(socket.getInputStream())) {
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
    public boolean changePassword(String userName, String newPassword) {
        try {
            sendAction("changePassword");
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeUTF(userName); // Send the user's name
            oos.writeUTF(newPassword);
            outputStream.flush();

            System.out.println("Password change request has been sent to the server...");

            try (ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
                boolean changeSuccess = ois.readBoolean();
                System.out.println("Server response: " + changeSuccess);
                return changeSuccess;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error changing password", e);
        }
    }

}
