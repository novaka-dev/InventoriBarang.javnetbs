/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import dao.BarangDAO;
import dao.BarangMasukDAO;
import model.BarangMasuk;
import java.util.List;
import model.Barang;
/**
 *
 * @author Novaka Saputra
 */
public class BarangMasukController {
    
    private final BarangMasukDAO masukDAO  = new BarangMasukDAO();
    private final BarangDAO      barangDAO = new BarangDAO();
 
    public boolean catatBarangMasuk(String idBarang, String namaBarang,
                                     int jumlah, String tanggal,
                                     String keterangan, String supplier) {
        String idTransaksi = masukDAO.generateId();
 
        Barang barang = barangDAO.findById(idBarang);

        int stokSebelum = barang.getStok();
        int stokSesudah = stokSebelum + jumlah;

        BarangMasuk transaksi = new BarangMasuk(
            idTransaksi,
            idBarang,
            namaBarang,
            jumlah,
            tanggal,
            keterangan,
            supplier,
            stokSebelum,
            stokSesudah
        );
 
        boolean berhasil = masukDAO.simpan(transaksi);
 
        // Stok otomatis bertambah setelah transaksi tersimpan
        if (berhasil) {
            barangDAO.updateStok(idBarang, +jumlah);
        }
 
        return berhasil;
    }
 
    public List<BarangMasuk> getAllBarangMasuk() {
        return masukDAO.getAll();
    }
 
    // Hitung total unit masuk hari ini
    public int hitungTotalMasukHariIni(String tanggalHariIni) {
        int total = 0;
        for (BarangMasuk bm : masukDAO.getAll()) {
            if (bm.getTanggal().equals(tanggalHariIni)) total += bm.getJumlah();
        }
        return total;
    }
 
    // Validasi input dari form sebelum disimpan
    public String validasiInput(String idBarang, String jumlahStr) {
        if (idBarang == null || idBarang.isBlank()) return "Pilih barang dulu!";
        if (barangDAO.findById(idBarang) == null)   return "Barang tidak ditemukan!";
        try {
            int jumlah = Integer.parseInt(jumlahStr);
            if (jumlah <= 0) return "Jumlah harus lebih dari 0!";
        } catch (NumberFormatException e) {
            return "Jumlah harus berupa angka!";
        }
        return null; // null = valid
    }

    
}
