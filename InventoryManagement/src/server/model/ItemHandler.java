package server.model;

import utility.Item;

import java.io.*;
import java.net.Socket;


public class ItemHandler implements Runnable {

    Socket socket;
    String operation;

    /**
     * Constructs a new ItemHandler with the specified client socket.
     *
     * @param clientSocket The client socket connected to the server.
     */
    public ItemHandler(Socket clientSocket) {
        socket = clientSocket;
    }

    @Override
    public void run() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
            ObjectInputStream oIS = new ObjectInputStream(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter pWriter = new PrintWriter(outputStream);

            pWriter.println("You are connected to the server");
            pWriter.flush();

            while (true) {
                pWriter.println("Operation: ");
                pWriter.flush();

                switch (bufferedReader.readLine().toLowerCase()) {
                    case "additem":
                        Item submittedItem = (Item) oIS.readObject();
                        itemAddition(submittedItem, outputStream);
                        break;

                    case "removeitem":
                        int submittedID = (int) oIS.readInt();
                        itemRemoval(submittedID, outputStream);
                        break;

                    case "exit":
                        socket.close();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the addition of an item to the server.
     *
     * @param itemObject    The item object to be added.
     * @param outputStream  The output stream for sending responses.
     * @throws IOException  If an I/O error occurs.
     */
    private void itemAddition(Item itemObject, OutputStream outputStream) throws IOException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            boolean success = XMLProcessing.addItem(itemObject);

            objectOutputStream.writeBoolean(success);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the removal of an item from the server.
     *
     * @param id            The ID of the item to be removed.
     * @param outputStream  The output stream for sending responses.
     * @throws IOException  If an I/O error occurs.
     */
    private void itemRemoval(int id, OutputStream outputStream) throws IOException {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            boolean success = XMLProcessing.removeItem(id);

            objectOutputStream.writeBoolean(success);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
