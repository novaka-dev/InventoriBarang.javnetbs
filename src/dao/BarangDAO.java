/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Barang;
import java.util.ArrayList;
import java.util.List;
 
/**
 * DAO — BarangDAO.java (versi ArrayList)
 * Nyimpen data barang di memori (ArrayList static).
 * Dipanggil oleh BarangMasukController untuk cek & update stok.
 */
/**
 *
 * @author Novaka Saputra
 */
public class BarangDAO {
    
    // static = satu list ini dibagi semua yang makenya selama app jalan
    private static List<Barang> dataBarang = new ArrayList<>();
 
    // Saat pertama kali app dibuka, isi data contoh biar combobox ga kosong
    static {
        dataBarang.add(new Barang("B001", "Spidol Whiteboard", "Alat Tulis", 50, 5000, "PT Stationery Indonesia"));
        dataBarang.add(new Barang("B002", "Kertas A4 (Rim)", "Kertas", 30, 45000, "PT Pindo Deli Pulp and Paper Mills"));
        dataBarang.add(new Barang("B003", "Pulpen Pilot", "Alat Tulis", 100, 3000, "PT Pilot Pen Indonesia"));
        dataBarang.add(new Barang("B004", "Tinta Printer Hitam", "Tinta", 20, 85000, "PT Karisa Sukses Abadi"));
    }
 
    public List<Barang> getAll() {
        return dataBarang;
    }
 
    public boolean simpan(Barang barang) {
        dataBarang.add(barang);
        return true;
    }
 
    public boolean update(Barang barangBaru) {
        for (int i = 0; i < dataBarang.size(); i++) {
            if (dataBarang.get(i).getIdBarang().equals(barangBaru.getIdBarang())) {
                dataBarang.set(i, barangBaru);
                return true;
            }
        }
        return false;
    }
 
    public boolean hapus(String idBarang) {
        return dataBarang.removeIf(b -> b.getIdBarang().equals(idBarang));
    }
 
    public Barang findById(String idBarang) {
        return dataBarang.stream()
                .filter(b -> b.getIdBarang().equals(idBarang))
                .findFirst()
                .orElse(null);
    }
 
    // Dipanggil otomatis saat ada transaksi masuk/keluar
    // perubahanStok positif = tambah, negatif = kurang
    public boolean updateStok(String idBarang, int perubahanStok) {
        Barang barang = findById(idBarang);
        if (barang == null) return false;
 
        int stokBaru = barang.getStok() + perubahanStok;
        if (stokBaru < 0) return false; // stok ga boleh minus
 
        barang.setStok(stokBaru);
        return true;
    }
 
    public String generateId() {
        int next = dataBarang.size() + 1;
        return String.format("B%03d", next);
    }
    
}
