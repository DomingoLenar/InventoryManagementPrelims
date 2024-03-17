package client;

import shared.StockControlCallback;
import utility.User;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class StockControlCallbackClass extends UnicastRemoteObject
        implements StockControlCallback, Serializable {
    private User user;
    public StockControlCallbackClass() throws RemoteException {}
    @Override
    public User getUser() throws RemoteException {
        return user;
    }

    @Override
    public void loginCall(User user) throws RemoteException {

    }

    @Override
    public void broadcastCall(User user, String msg) throws RemoteException {

    }

    @Override
    public void logoutCall(User user) throws RemoteException {

    }

    @Override
    public void index(Void o) throws RemoteException {

    }

    public void authenticate(User user) {
        this.user = user;
    }
}
