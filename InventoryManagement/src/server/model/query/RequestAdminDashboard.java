package server.model.query;

import server.model.XMLProcessing;
import utility.User;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static server.model.query.RequestPurchaseDashboard.getUnitsSold;
import static server.model.query.RequestSalesDashboard.getYearlyRevenueNCosts;

public class RequestAdminDashboard {

    public static void process(ObjectOutputStream objectOutputStream)throws IOException {

        objectOutputStream.writeObject(getActiveUsers());
        objectOutputStream.flush();

        objectOutputStream.writeObject(getUnitsSold());
        objectOutputStream.flush();

        objectOutputStream.writeObject(getYearlyRevenueNCosts());
        objectOutputStream.flush();


    }

    private static ArrayList<String> getActiveUsers(){
        ArrayList<String> users = new ArrayList<>();

        Stack<User> activeUsers = XMLProcessing.fetchListOfUsers();
        int maxIndex = Math.min(5, activeUsers.size());

        List<User> topNUser = activeUsers.subList(0, maxIndex);

        for(int x = 0; x<topNUser.size(); x++){
            User cUser = topNUser.get(x);
            users.add(cUser.getUsername()+","+cUser.getRole());
        }

        return users;
    }



}
