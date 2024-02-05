package client.models;

import utility.User;

public class SignupModel {
    private User user;

    public SignupModel(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void submitAccountDetails(String username, String password){
        try {
            User newUser = new User(username, password);
            this.user = newUser;
            System.out.println("You have successfully made an account!");
        } catch (Exception e){
            System.err.println("Failed to submit user account details: "+ e.getMessage());
        }
    }

    /**
     * TODO:
     * Modify the methods into accessing and manipulating a database
     */

}
