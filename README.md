Berikut adalah file README yang lebih spesifik, termasuk contoh penggunaan langsung dan detail terkait struktur data, fungsi, serta skenario kompilasi.

---

# BANK JAVA

**BANK JAVA** adalah aplikasi layanan perbankan sederhana berbasis **Java RMI (Remote Method Invocation)** yang memungkinkan klien mengakses layanan perbankan dari jarak jauh melalui koneksi server. Aplikasi ini mencakup fitur login, pengecekan saldo, transfer dana antar rekening, dan pemantauan riwayat transaksi.

## Fitur Utama
- **Login Otentikasi**: Autentikasi berbasis nomor akun dan PIN.
- **Cek Saldo**: Menampilkan saldo akun pengguna.
- **Transfer Dana**: Fitur transfer antar rekening dengan validasi saldo.
- **Riwayat Transaksi**: Menampilkan riwayat transaksi berdasarkan akun pengguna.

## Struktur Proyek
Proyek terdiri dari empat file utama berikut:
1. **Bank.java** - Antarmuka layanan perbankan.
2. **BankImpl.java** - Implementasi antarmuka `Bank` yang menjalankan logika layanan.
3. **BankServer.java** - Server RMI yang mengelola dan menyediakan layanan `Bank`.
4. **BankClient.java** - Klien yang digunakan pengguna untuk berinteraksi dengan server.

## Struktur dan Fungsi Masing-Masing File
### 1. `Bank.java`
   - **Deskripsi**: Mendefinisikan metode layanan perbankan utama.
   - **Metode**:
      - `login(int accountNumber, int pin)`: Validasi nomor akun dan PIN.
      - `checkBalance(int accountNumber, int pin)`: Menampilkan saldo akun.
      - `transferFunds(int sourceAccount, int destinationAccount, double amount, int pin)`: Transfer dana antar rekening dengan autentikasi PIN.
      - `getTransactionHistory(int accountNumber, int pin)`: Mendapatkan riwayat transaksi.

### 2. `BankImpl.java`
   - **Deskripsi**: Implementasi utama antarmuka `Bank`.
   - **Struktur Data**:
      - `Map<Integer, Double> accountBalances`: Menyimpan saldo masing-masing akun.
      - `Map<Integer, Integer> accountPins`: Menyimpan PIN masing-masing akun.
      - `Map<Integer, List<String>> transactionHistory`: Menyimpan riwayat transaksi setiap akun.
   - **Metode Implementasi**:
      - `login()`: Mengautentikasi akun berdasarkan PIN.
      - `checkBalance()`: Memeriksa saldo dengan autentikasi PIN.
      - `transferFunds()`: Melakukan transfer dana, memverifikasi saldo dan akun tujuan.
      - `getTransactionHistory()`: Mengambil riwayat transaksi berdasarkan akun.

### 3. `BankServer.java`
   - **Deskripsi**: Mengelola server dan registry RMI.
   - **Fungsi Utama**:
      - Membuat instance `BankImpl`.
      - Memulai registry RMI pada port default 1099.
      - Mendaftarkan layanan `BankService` ke registry untuk diakses klien.

### 4. `BankClient.java`
   - **Deskripsi**: Klien berbasis konsol yang menyediakan antarmuka interaktif bagi pengguna.
   - **Fungsi Utama**:
      - Menghubungkan ke server RMI dan mengakses layanan `Bank`.
      - Memungkinkan pengguna login, cek saldo, transfer, dan cek riwayat transaksi.
   - **Alur Interaksi**:
      - Pengguna memasukkan nomor akun dan PIN untuk login.
      - Setelah login berhasil, pengguna dapat memilih opsi layanan perbankan.

## Persyaratan Sistem
- **Java Development Kit (JDK)** versi 8 atau lebih tinggi.
- Pastikan `CLASSPATH` mendukung Java RMI dan akses ke semua file dalam proyek.

## Cara Menjalankan
### 1. Kompilasi File Java
   ```bash
   javac *.java
   ```

### 2. Membuat Stub RMI
   ```bash
   rmic BankImpl
   ```

### 3. Memulai Server
   - Jalankan registry RMI di port 1099:
     ```bash
     rmiregistry 1099 &
     ```
   - Jalankan server RMI:
     ```bash
     java BankServer
     ```

### 4. Memulai Klien
   - Setelah server aktif, buka terminal baru dan jalankan klien:
     ```bash
     java BankClient
     ```

## Contoh Penggunaan
1. **Login**:
   - Masukkan nomor akun dan PIN.
   - Jika berhasil, Anda akan menerima pesan login sukses.

2. **Cek Saldo**:
   - Pilih opsi "1. Cek Saldo".
   - Masukkan PIN untuk verifikasi.
   - Sistem akan menampilkan saldo akun.

3. **Transfer Dana**:
   - Pilih opsi "2. Transfer Dana".
   - Masukkan nomor akun tujuan, jumlah transfer, dan PIN untuk verifikasi.
   - Jika transfer berhasil, pesan sukses akan ditampilkan, dan riwayat transaksi diperbarui.

4. **Riwayat Transaksi**:
   - Pilih opsi "3. Lihat Riwayat Transaksi".
   - Masukkan PIN untuk autentikasi.
   - Sistem menampilkan semua transaksi yang terkait dengan akun tersebut.

## Catatan Keamanan
- Aplikasi ini menggunakan autentikasi berbasis PIN, tetapi tidak menyertakan enkripsi data dan fitur keamanan lanjutan lainnya. Pastikan penanganan data pada sistem produksi lebih aman.
  
## Pengembangan Lebih Lanjut
- Menambahkan enkripsi data pada proses autentikasi dan transaksi.
- Mengintegrasikan fitur notifikasi transaksi.
- Menambahkan dukungan multi-threading untuk klien yang lebih besar.

--- 