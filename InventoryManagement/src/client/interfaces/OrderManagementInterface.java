package client.interfaces;

public interface OrderManagementInterface {
    public void OnGenerate(String[] cxOrderDetails);
    public void OnUpdate(String[] cxOrderDetails);

    public void OnDelete(String[] cxOrderDetails);
}
