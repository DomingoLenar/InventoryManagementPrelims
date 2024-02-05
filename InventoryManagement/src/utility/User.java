package utility;


public class User {
    private String username;
    private String password;

    /**
     * Default constructor initializes the user with empty username and password.
     */
    public User() {
        username = "";
        password = "";
    }

    /**
     * Parameterized constructor to create a user with the given username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Gets the username of the user.
     *
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }


    /**
     * Gets the password of the user.
     *
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Calculates the hash code of the user's password.
     *
     * @return The hash code of the user's password, or 0 if the password is null.
     */
    public int hashPassword() {
        return password != null ? password.hashCode() : 0;
    }
}
