package client.common.models;

/**
 * Represents the model for handling user login and signup operations on the client side.
 */
@Deprecated
public class ProfileManagementModel {
    public ProfileManagementModel() {}

    /**
     * Sends an action to the server.
     *
     * @param action The action to be sent.
     */



    /**
     * Handles the login process by sending user credentials to the server.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @return True if login is successful, false otherwise.
     */



    /**
     * Handles the signup process by sending user credentials to the server.
     *
     * @param username The user's username.
     * @param password The user's password.
     * @param role The user's role
     * @return True if account creation is successful, false otherwise.
     */

//    public static User handleSignup(String username, String password, String role,ObjectOutputStream oOs, ObjectInputStream oIs) {
//        try {
//            // Create a new User object with provided credentials
//            User newUser = new User(username, password,role,false);
//
//            sendAction("createUser", oOs);
//
//            // Send the User object to the server for sign-up
//            oOs.writeObject(newUser);
//            oOs.flush();
//            System.out.println(newUser.getUsername() + " sent to the server for sign-up");
//
//            try {
//                User user = (User) oIs.readObject();
//                if (user != null) {
//                    System.out.println("Authentication response: success");
//                    return user;
//                } else {
//                    System.out.println("Authentication response: failed");
//                    return null;
//                }
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//        return null;
//    }

    /**
     * Method for changing a password of a user
     *
     * @param userName    The username of the user whose password is to be changed.
     * @param newPassword The new password to set for the user.
     * @return True if the password change was successful, false otherwise.
     * @throws RuntimeException If an error occurs while changing the password.
     */

}
