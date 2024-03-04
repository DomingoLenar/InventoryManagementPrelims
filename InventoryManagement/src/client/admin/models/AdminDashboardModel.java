package client.admin.models;

import client.api.ClientApi;
import utility.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

public class AdminDashboardModel {
    public static Stack<User> fetchListOfUsers (ObjectOutputStream oOs, ObjectInputStream oIs){
        try {
            ClientApi.sendAction("fetchListOfUsers", oOs);

            try  {
                Stack<User> listOfUsers = (Stack<User>) oIs.readObject();
                System.out.println("List of users have been fetched.");
                return listOfUsers;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
