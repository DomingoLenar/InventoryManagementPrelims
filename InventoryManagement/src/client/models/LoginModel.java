package client.models;

import utility.User;

public class LoginModel {
    private User currentUser;

    public LoginModel(User user) {
        this.currentUser = user;
    }

    public User getUser() {
        return currentUser;
    }

    public void setUser(User user) {
        this.currentUser = user;
    }

    public void submitUserAccountDetails(String username, String password){
        try {
            User newUser = new User(username,password);
            if (currentUser.equals(newUser)){
                System.out.println("Login Successful");
            }else {
                System.out.println("Invalid username or password");
            }
        }catch (Exception e){
            System.err.println("Failed to submit user account details: "+ e.getMessage());
        }
    }

    /**
     * TODO:
     * Modify the methods into accessing and manipulating a database
     */
}
