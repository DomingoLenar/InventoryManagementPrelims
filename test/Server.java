import client.api.ClientApi;
import org.junit.jupiter.api.Test;
import utility.revision.Item;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Server {
    @Test
    public void testAddNewItemRequest(){
        Item item1 = new Item("test", -1, null);
//        Item item2 = new Item("test", -1, 0, null, null);
        try {
            Socket cSocket = new Socket("localhost", 2020);
            ObjectOutputStream oOS = new ObjectOutputStream(cSocket.getOutputStream());
            ObjectInputStream oIS = new ObjectInputStream(cSocket.getInputStream());

            ClientApi.sendAction("addNewItem", oOS);
            oOS.writeObject(item1);

            try {
                boolean success = oIS.readBoolean();
                System.out.println(success);
            }catch (IOException e){
                e.printStackTrace();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
