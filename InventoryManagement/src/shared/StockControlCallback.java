package shared;

import utility.User;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StockControlCallback extends Remote {

    // method to get the current user of the callback/session
    User getUser() throws RemoteException;

    // method called by server when other clients log in
    // user data is sent as parameter
    void loginCall(User user) throws RemoteException;

    // method called by server when a message is sent by clients
    // user/sender and message sent as parameter
    void broadcastCall(User user, String msg) throws RemoteException;

    // method called by server when a user logs out from the system
    // user logging out sent as parameter
    void logoutCall(User user) throws RemoteException;

    void index(Void o) throws RemoteException, InstantiationException, IllegalAccessException;

}
