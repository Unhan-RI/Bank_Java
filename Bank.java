import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Bank extends Remote {
    double checkBalance(String accountNo, String pin) throws RemoteException;
    boolean transferFunds(String fromAccount, String toAccount, double amount, String pin) throws RemoteException;
    List<String> getTransactionHistory(String accountNo, String pin) throws RemoteException;  // Tambahkan method baru
    boolean login(String accountNo, String pin) throws RemoteException;  // Method login baru
}
