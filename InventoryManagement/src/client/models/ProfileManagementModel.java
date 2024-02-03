package client.models;
import utility.User;
public class ProfileManagementModel {
    private User currentUser;

    public ProfileManagementModel (){
        currentUser = null;
    }

    public ProfileManagementModel(User currentUser) {
        this.currentUser = currentUser;
    }

    public void changeProfileDetails (String username, String password){
      try {
          this.currentUser.setPassword(password);
          this.currentUser.setUsername(username);

          System.out.println("You've successfully changed the details of your account...");
      } catch (Exception e){
          System.err.println("Error changing profile details: " + e.getMessage());
      }

    }

    /**
     * TODO:
     * Modify the methods into accessing an manipulating a database
     */
}
