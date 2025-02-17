import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class BankClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            Bank bank = (Bank) registry.lookup("BankService");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Masukkan nomor akun: ");
            String accountNo = scanner.nextLine();
            System.out.print("Masukkan PIN: ");
            String pin = scanner.nextLine();

            if (bank.login(accountNo, pin)) {
                String choice;

                do {
                    System.out.println("\n=== MENU BANK ===");
                    System.out.println("1. Cek Saldo");
                    System.out.println("2. Transfer Dana");
                    System.out.println("3. Lihat Riwayat Transaksi");
                    System.out.println("4. Keluar");
                    System.out.print("Pilih menu: ");
                    choice = scanner.nextLine();

                    switch (choice) {
                        case "1":
                            double balance = bank.checkBalance(accountNo, pin);
                            if (balance >= 0) {
                                System.out.println("Saldo: $" + balance);
                            }
                            break;

                        case "2":
                            System.out.print("Masukkan nomor akun penerima: ");
                            String toAccount = scanner.nextLine();
                            System.out.print("Masukkan jumlah: ");
                            double amount = scanner.nextDouble();
                            scanner.nextLine();  // Membuang karakter newline

                            boolean success = bank.transferFunds(accountNo, toAccount, amount, pin);
                            if (success) {
                                System.out.println("Transfer berhasil.");
                            } else {
                                System.out.println("Transfer gagal.");
                            }
                            break;

                        case "3":
                            List<String> history = bank.getTransactionHistory(accountNo, pin);
                            if (!history.isEmpty()) {
                                System.out.println("Riwayat Transaksi:");
                                for (String record : history) {
                                    System.out.println(record);
                                }
                            } else {
                                System.out.println("Tidak ada riwayat transaksi.");
                            }
                            break;

                        case "4":
                            System.out.println("Keluar...");
                            break;

                        default:
                            System.out.println("Pilihan tidak valid.");
                    }
                } while (!choice.equals("4"));
            } else {
                System.out.println("PIN salah. Akses ditolak.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
