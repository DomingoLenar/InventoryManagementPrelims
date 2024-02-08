package utility;


import java.io.Serializable;

public class User implements Serializable, Comparable<User> {
    private String username;
    private String password;
    private String role;

    /**
     * Default constructor initializes the user with empty username and password.
     */
    public User() {
        username = "";
        password = "";
        role = "";
    }

    /**
     * Parameterized constructor to create a user with the given username and password.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     * @param role the role of the user (admin, sales, purchase)
     */
    public User(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
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

    public void setRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }

    /**
     * Calculates the hash code of the user's password.
     *
     * @return The hash code of the user's password, or 0 if the password is null.
     */
    public int hashPassword() {
        return password != null ? password.hashCode() : 0;
    }

    @Override
    public int compareTo(User other) {
        if(this.username.equals(other.getUsername()) && this.password.equals(other.getPassword())){
            return 0;
        }
        return 1;
    }
}
