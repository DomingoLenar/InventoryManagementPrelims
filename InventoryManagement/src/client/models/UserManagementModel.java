package client.models;

import utility.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Client-side model for handling user-related operations (creation/deletion) on the server
 */
public class UserManagementModel {
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public UserManagementModel(ObjectOutputStream oos, ObjectInputStream ois) {
        this.oos = oos;
        this.ois = ois;
    }

    /**
     * Sends an action to the server.
     *
     * @param action The action to be sent.
     */
    private void sendAction(String action) {
        try {
            oos.writeUTF(action);
            oos.flush();
            System.out.println(action + " sent to server");
        } catch (IOException e) {
            throw new RuntimeException("Error sending action to server", e);
        }
    }

    /**
     * Creates a new user.
     *
     * @param user The user object to be created.
     * @return True if the user is successfully created; false otherwise.
     */
    public boolean createUser(User user) {
        try {
            sendAction("createUser");
            oos.writeObject(user);
            oos.flush();
            System.out.println("User creation request has been sent to the server.");

            try {
                boolean creationSuccess = ois.readBoolean();
                System.out.println("Server response: " + creationSuccess);
                return creationSuccess;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error creating user", e);
        }
    }

    /**
     * Deletes a user.
     *
     * @param username The username of the user to be deleted.
     * @return True if the user is successfully deleted; false otherwise.
     */
    public boolean deleteUser(String username) {
        try {
            sendAction("deleteUser");
            oos.writeUTF(username);
            oos.flush();
            System.out.println("User deletion request has been sent to the server.");

            try {
                boolean deletionSuccess = ois.readBoolean();
                System.out.println("Server response: " + deletionSuccess);
                return deletionSuccess;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }

    /**
     * Sets the role of a user.
     *
     * @param username The username of the user whose role is to be set.
     * @param role     The role to set for the user.
     * @return True if the user role is successfully set; false otherwise.
     */
    public boolean setUserRole(String username, String role) {
        try {
            sendAction("setUserRole");
            oos.writeUTF(username);
            oos.writeUTF(role);
            oos.flush();
            System.out.println("User role update request has been sent to the server.");

            try {
                boolean updateSuccess = ois.readBoolean();
                System.out.println("Server response: " + updateSuccess);
                return updateSuccess;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (IOException e) {
            throw new RuntimeException("Error setting user role", e);
        }
    }
}