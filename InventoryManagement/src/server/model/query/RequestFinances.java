package server.model.query;

import server.model.XMLProcessing;
import utility.revision.ItemOrder;
import utility.revision.OrderDetails;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * This class contains methods for Finances
 * Panel (Gross Sales, Average Order Value, Gross Profit)
 */
public class RequestFinances {

    public static synchronized void process(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            float yearlyGrossSales = calculateYearlyGrossSales();
            float averageOrderSales = calculateAverageOrderValue();
            float grossProfit = calculateGrossProfit();

            objectOutputStream.writeObject(yearlyGrossSales);
            objectOutputStream.writeObject(averageOrderSales);
            objectOutputStream.writeObject(grossProfit);
        } catch (Exception e) {
            e.printStackTrace();
            objectOutputStream.writeObject("Error: Unable to fetch financial data.");
        } finally {
            objectOutputStream.flush();
        }
    }

    /**
     * This method returns a float value (yearlyGrossSales) that
     * contains the calculated annual gross sales
     * @return yearlyGrossSales
     */
    public static synchronized float calculateYearlyGrossSales() {
        float yearlyGrossSales = 0;

        try {
            String currentYear = getCurrentYear();
            ArrayList<ItemOrder> allSales = XMLProcessing.fetchItemOrders("sales");

            for (ItemOrder order : allSales) {
                String orderYear = order.getDate().split("-")[0];
                if (orderYear.equals(currentYear)) {
                    ArrayList<OrderDetails> orderDetails = XMLProcessing.fetchOrderDetails(order.getOrderId());
                    for (OrderDetails detail : orderDetails) {
                        float unitPrice = detail.getUnitPrice();
                        int units = detail.getUnits();
                        yearlyGrossSales += unitPrice * units;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return yearlyGrossSales;
    }//end of calculateYearlyGrossSales

    /**
     * This method returns an int value (totalNumberOfOrder) that
     * contains the annual total of orders
     * @return totalNumberOfOrders
     */
    public static synchronized int getTotalNumberOfOrders() {
        int totalNumberOfOrders = 0;

        try {
            String currentYear = getCurrentYear();
            ArrayList<ItemOrder> allSales = XMLProcessing.fetchItemOrders("sales");

            for (ItemOrder order : allSales) {
                String orderYear = order.getDate().split("-")[0];
                if (orderYear.equals(currentYear)) {
                    totalNumberOfOrders++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalNumberOfOrders;
    }//end of getTotalNumberOfOrders

    /**
     * This method returns a float value that contains
     * the Average Order Value annually
     */
    public static synchronized float calculateAverageOrderValue() {
        float yearlyGrossSales = calculateYearlyGrossSales();
        int totalNumberOfOrders = getTotalNumberOfOrders();

        if (totalNumberOfOrders == 0) {
            return 0;
        }

        return yearlyGrossSales / totalNumberOfOrders;
    }//end of calculateAverageOrderSales

    /**
     * This method returns a float value that contains the
     * Gross Profit annually
     */
    public static synchronized float calculateGrossProfit() {
        float totalRevenue = 0;
        float totalCostOfGoodsSold = 0;

        try {
            ArrayList<String> revenueNCosts = RequestSalesDashboard.getYearlyRevenueNCosts();
            for (String revenueNCost : revenueNCosts) {
                String[] values = revenueNCost.split(",");
                totalRevenue += Float.parseFloat(values[0]);
                totalCostOfGoodsSold += Float.parseFloat(values[1]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return totalRevenue - totalCostOfGoodsSold;
    }//end of calculateGrossProfit

    /**
     * This method returns the current year in a string value
     */
    private static String getCurrentYear() {
        LocalDate localDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return localDate.format(formatter);
    }//end of getCurrentYear

}//end of class
