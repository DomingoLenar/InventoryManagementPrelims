package utility;


import java.io.Serializable;

public class User implements Serializable {
    private String username;
    private String password;
    private String role;
    private boolean active;

    /**
     * Default constructor initializes the user with empty username and password.
     */
    public User() {
        username = "";
        password = "";
        role = "";
        active = false;
    }

    /**
     * Parameterized constructor to create a user with the given username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password, String role, boolean active) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.active = active;
    }

    public User(String byUserName) {
        this.username = byUserName;
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

    public String getRole(){
        return this.role;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(String role){

        this.role = role;

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
