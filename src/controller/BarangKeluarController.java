package controller;

import dao.BarangDAO;
import dao.BarangKeliar; // Menggunakan nama kelas DAO sesuai file fisik BarangKeliar.java kamu
import model.Barang;
import model.BarangKeluar;
import java.util.List;

/**
 * Controller — BarangKeluarController.java
 * Jembatan antara BarangKeluarFrame (View) dengan Data Access Object (DAO).
 * Berisi semua logika bisnis dan validasi transaksi barang keluar.
 * * @author Novaka Saputra
 */
public class BarangKeluarController {
    
    private final BarangKeliar bkDAO; 
    private final BarangDAO barangDAO;

    public BarangKeluarController() {
        this.bkDAO = new BarangKeliar(); // Instansiasi DAO transaksi keluar
        this.barangDAO = new BarangDAO(); // Instansiasi DAO master barang untuk cek & update stok
    }

    /**
     * Mengambil seluruh data riwayat transaksi barang keluar.
     * Dipanggil oleh JTable pada View untuk memuat baris data.
     */
    // KODE BARU YANG SUDAH DIPERBAIKI (BarangKeluar = Model)
    public List<BarangKeluar> ambilSemuaRiwayat() {
    return bkDAO.getAll();
}

    /**
     * Membuat ID Transaksi otomatis untuk dipasang di form input awal (Contoh: BK001, BK002)
     */
    public String ambilIdOtomatis() {
        return bkDAO.generateId();
    }

    
    public String catatBarangKeluar(String idBarang, String jumlahStr, String tujuan, String tanggal, String keterangan) {
        
        // 1. Validasi Input Kosong / Belum Diisi
        if (idBarang == null || idBarang.trim().isEmpty()) {
            return "Pilih barang terlebih dahulu!";
        }
        if (jumlahStr == null || jumlahStr.trim().isEmpty()) {
            return "Jumlah keluar tidak boleh kosong!";
        }
        if (tujuan == null || tujuan.trim().isEmpty()) {
            return "Tujuan pengiriman tidak boleh kosong!";
        }
        if (tanggal == null || tanggal.trim().isEmpty()) {
            return "Tanggal tidak boleh kosong!";
        }

        // 2. Validasi Format Angka dan Batas Minimum Jumlah Keluar
        int jumlahKeluar;
        try {
            jumlahKeluar = Integer.parseInt(jumlahStr.trim());
            if (jumlahKeluar <= 0) {
                return "Jumlah keluar harus lebih besar dari 0!";
            }
        } catch (NumberFormatException e) {
            return "Jumlah keluar harus berupa angka bulat!";
        }

        // 3. Cek Keberadaan Barang di Database/Memori Master Barang
        Barang barang = barangDAO.findById(idBarang);
        if (barang == null) {
            return "Barang dengan ID tersebut tidak ditemukan di sistem!";
        }

        // 4. Validasi Kecukupan Stok (Logika Bisnis Utama)
        if (barang.getStok() < jumlahKeluar) { 
            return "Stok tidak cukup! Stok saat ini: " + barang.getStok() + ", Anda meminta: " + jumlahKeluar; 
        }

        // 5. Jika Semua Validasi Lolos, Buat Objek Transaksi Baru
        String idTransaksiBaru = bkDAO.generateId();
        int stokSebelum = barang.getStok();
        int stokSesudah = stokSebelum - jumlahKeluar;

        BarangKeluar bk = new BarangKeluar(
            idTransaksiBaru,
            idBarang,
            barang.getNama(),
            jumlahKeluar,
            tanggal,
            keterangan,
            tujuan,
            stokSebelum,
            stokSesudah
        );

        // 6. Eksekusi Penyimpanan Transaksi & Pemotongan Stok
        bkDAO.simpan(bk);                       // Menyimpan ke riwayat transaksi keluar
        barangDAO.updateStok(idBarang, -jumlahKeluar); // Memotong stok di master barang (menggunakan nilai negatif)

        return "Sukses mencatat transaksi barang keluar dengan ID " + idTransaksiBaru;
    }
}