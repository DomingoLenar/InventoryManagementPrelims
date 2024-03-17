package server;

import client.common.views.IndexView;
import shared.StockControlServer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server extends IndexView {
    protected Server() throws RemoteException {
    }
    public static void main(String[] args) {
        try {
            StockControlServer servant = new StockControlServant();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind("servant",servant);
            System.out.println("server running...");
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
}
