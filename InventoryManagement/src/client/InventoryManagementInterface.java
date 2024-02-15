package client;

import client.controllers.IndexController;
import client.controllers.LoginController;
import client.controllers.SignUpController;
import javax.swing.*;

public class InventoryManagementInterface extends JFrame {
    // #TODO: remove instance of controller following mvc pattern
//    IndexController indexController;
//    LoginController loginController;
//    SignUpController signUpController;
    public InventoryManagementInterface(){
        initControllers();

//        add(indexController.getIndexView().getIVpanel());

        setVisible(true);
        setSize(800, 800);
    }

    private void initControllers() {
//        indexController = new IndexController(this);
//        signUpController = new SignUpController(this);
//        loginController = new LoginController(this);
    }

    public void displaySignUpView() {
//        add(signUpController.getSignUpView().getSUpanel());
    }

    public void displayLoginView() {
//        add(loginController.getLoginView().getLVpanel());
    }


}
