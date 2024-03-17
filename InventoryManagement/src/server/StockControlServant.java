package server;

import shared.*;
import utility.User;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Map;

public class StockControlServant extends UnicastRemoteObject implements StockControlServer, Serializable {
    // name is mapped to a messagecallback object
    private Map<String, StockControlCallback> msgCallbacks =
            new Hashtable<>();
    protected StockControlServant() throws RemoteException {}

    @Override
    public void login(StockControlCallback msgCallback) throws RemoteException, AlreadyLoggedInException, UserExistsException {
        User user = msgCallback.getUser();
        // check if callback instance already exists
        if (msgCallbacks.containsValue(msgCallback)) {
            throw new AlreadyLoggedInException("Already logged in... you cannot login using the same client...");
        } else if (msgCallbacks.containsKey(user.getUsername())) {
            // different callback instance but same name
            throw new UserExistsException("User name already exists, use another name...");
        } else {
            // new user, session; add msgCallback to the current collection of online clients
            msgCallbacks.put(user.getUsername(), msgCallback);
            System.out.println("login: " + user.getUsername());
            System.out.print("online: [ ");
            for (String name : msgCallbacks.keySet()) {
                System.out.print(name + " ");
            }
            System.out.println("]");

            // loop to send login notification to all clients
            for (String name : msgCallbacks.keySet()) {
                msgCallbacks.get(name).loginCall(user);
            }
        }
    }

    @Override
    public void broadcast(StockControlCallback msgCallback, String msg) throws RemoteException, NotLoggedInException {
        // check if msgCallback/session is not in the existing callback objects
        if (!msgCallbacks.containsValue(msgCallback)) {
            throw new NotLoggedInException();
        }
        // get user of mc/callback
        User user = msgCallback.getUser();
        // loop to send broadcast to all clients/callbacks
        for (String name : msgCallbacks.keySet()) {
            msgCallbacks.get(name).broadcastCall(user, msg);
        }
    }

    @Override
    public void logout(StockControlCallback msgCallback) throws RemoteException, NotLoggedInException {
        // check if msgCallback/session is not in the table of callbacks/clients
        // if callback is not found, the method is being called without
        // first logging in.
        if (!msgCallbacks.containsValue(msgCallback)) {
            throw new NotLoggedInException();
        }
        // get current user of callback
        User user = msgCallback.getUser();
        // remove session/callback from the table
        msgCallbacks.remove(user.getUsername());
        System.out.println("- logout: " + user.getUsername());
        System.out.print("online: [ ");
        for (String name : msgCallbacks.keySet()) {
            System.out.print(name + " ");
        }
        System.out.println("]");

        // loop to send logout message to all clients
        for (String name : msgCallbacks.keySet()) {
            msgCallbacks.get(name).logoutCall(user);
        }
    }

    @Override
    public void update(StockControlCallback stockControlCallback) throws RemoteException {

    }

    @Override
    public void delete(StockControlCallback stockControlCallback) throws RemoteException {

    }
}
