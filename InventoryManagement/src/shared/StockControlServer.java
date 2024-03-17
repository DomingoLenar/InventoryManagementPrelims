package shared;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StockControlServer extends Remote {
    void login(StockControlCallback stockControlCallback) throws RemoteException, AlreadyLoggedInException, UserExistsException;
    void broadcast(StockControlCallback stockControlCallback, String msg) throws RemoteException, NotLoggedInException;
    void logout(StockControlCallback stockControlCallback) throws RemoteException, NotLoggedInException;
    void update(StockControlCallback stockControlCallback) throws RemoteException;
    void delete(StockControlCallback stockControlCallback) throws RemoteException;
}
