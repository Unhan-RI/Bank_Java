import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankImpl extends UnicastRemoteObject implements Bank {

    private Map<String, Double> accounts;
    private Map<String, String> pins;  // Menyimpan PIN pengguna
    private Map<String, List<String>> transactionHistory;

    protected BankImpl() throws RemoteException {
        accounts = new HashMap<>();
        pins = new HashMap<>();
        transactionHistory = new HashMap<>();

        // Inisialisasi akun dengan saldo awal dan PIN
        accounts.put("ACC123", 5000.0);
        accounts.put("ACC456", 3000.0);
        pins.put("ACC123", "1234");  // PIN untuk ACC123
        pins.put("ACC456", "4567");  // PIN untuk ACC456
        
        transactionHistory.put("ACC123", new ArrayList<>());
        transactionHistory.put("ACC456", new ArrayList<>());
    }

    // Validasi login menggunakan PIN
    @Override
    public boolean login(String accountNo, String pin) throws RemoteException {
        return pins.get(accountNo).equals(pin);
    }

    @Override
    public double checkBalance(String accountNo, String pin) throws RemoteException {
        if (login(accountNo, pin)) {
            Double balance = accounts.get(accountNo);
            if (balance != null) {
                return balance;
            } else {
                System.out.println("Akun tidak ditemukan: " + accountNo);
                return 0;
            }
        } else {
            System.out.println("PIN salah.");
            return -1;
        }
    }

    @Override
    public boolean transferFunds(String fromAccount, String toAccount, double amount, String pin) throws RemoteException {
        if (login(fromAccount, pin)) {
            Double fromBalance = accounts.get(fromAccount);
            Double toBalance = accounts.get(toAccount);

            if (fromBalance != null && toBalance != null) {
                if (fromBalance >= amount) {
                    accounts.put(fromAccount, fromBalance - amount);
                    accounts.put(toAccount, toBalance + amount);

                    String transferRecord = "Transfer $" + amount + " ke " + toAccount;
                    transactionHistory.get(fromAccount).add(transferRecord);
                    transactionHistory.get(toAccount).add("Menerima $" + amount + " dari " + fromAccount);
                    return true;
                } else {
                    System.out.println("Saldo tidak cukup untuk akun: " + fromAccount);
                    return false;
                }
            } else {
                System.out.println("Akun tidak ditemukan.");
                return false;
            }
        } else {
            System.out.println("PIN salah.");
            return false;
        }
    }

    @Override
    public List<String> getTransactionHistory(String accountNo, String pin) throws RemoteException {
        if (login(accountNo, pin)) {
            List<String> history = transactionHistory.get(accountNo);
            if (history != null) {
                return history;
            } else {
                return new ArrayList<>();  // Jika tidak ada riwayat, kembalikan list kosong
            }
        } else {
            System.out.println("PIN salah.");
            return new ArrayList<>();
        }
    }
}
