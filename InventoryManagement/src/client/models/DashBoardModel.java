package client.models;

import utility.Item;
import utility.ItemOrder;

import java.util.ArrayList;

public class DashBoardModel {
    private ArrayList <Item> itemList;
    private ArrayList <ItemOrder> buyList;
    private ArrayList <ItemOrder> sellList;
    float income;
    float taxPaid;

    public DashBoardModel(ArrayList<Item> itemList, ArrayList<ItemOrder> buyList, ArrayList<ItemOrder> sellList, float income, float taxPaid) {
        this.itemList = itemList;
        this.buyList = buyList;
        this.sellList = sellList;
        this.income = income;
        this.taxPaid = taxPaid;
    }

    /**
     * To Do:
     * Method for calculating income
     * Method for calculating Products sold
     * Method for calculating the tax
     * Method for calculating the amount of items deposited
     * Method for calculating the total value of the deposited items
     */

}
