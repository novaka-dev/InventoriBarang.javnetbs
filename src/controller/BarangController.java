/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import dao.BarangDAO;
import java.util.ArrayList;
import model.Barang;
import java.util.List;
/**
 *
 * @author Novaka Saputra
 */
public class BarangController {
    private final BarangDAO dao = new BarangDAO();
 
    public List<Barang> getAllBarang() {
        return dao.getAll();
    }
 
    public Barang findById(String idBarang) {
        return dao.findById(idBarang);
    }
 
    public boolean tambahBarang(String id, String nama, String kategori,
                                 int stok, int harga, String supplier) {
        if (dao.findById(id) != null) return false; // ID duplikat
        return dao.simpan(new Barang(id, nama, kategori, stok, harga, supplier));
    }
 
    public boolean updateBarang(String id, String nama, String kategori,
                                 int stok, int harga, String supplier) {
        return dao.update(new Barang(id, nama, kategori, stok, harga, supplier));
    }
 
    public boolean hapusBarang(String idBarang) {
        return dao.hapus(idBarang);
    }
 
    public String generateId() {
        return dao.generateId();
    }
    
    public List<Barang> cariBarang(String keyword) {
        List<Barang> hasil = new ArrayList<>();

        for (Barang b : dao.getAll()) {

            if (b.getNama().toLowerCase()
                    .contains(keyword.toLowerCase())) {
                hasil.add(b);
            }
        }
        return hasil;
}
 
    // Hitung total nilai stok semua barang (stok x harga)
    public int hitungTotalNilaiStok() {
        int total = 0;
        for (Barang b : dao.getAll()) {
            total += b.getStok() * b.getHarga();
        }
        return total;
    }
 
    public int hitungTotalUnit() {
        int total = 0;
        for (Barang b : dao.getAll()) total += b.getStok();
        return total;
    }
 
    public String validasiInput(String id, String nama, String stokStr, String hargaStr, String supplier) {
        if (id.isBlank())   return "ID barang tidak boleh kosong!";
        if (nama.isBlank()) return "Nama barang tidak boleh kosong!";
        if (supplier.isBlank()) return "Supplier tidak boleh kosong!";
        try {
            if (Integer.parseInt(stokStr) < 0) return "Stok tidak boleh negatif!";
        } catch (NumberFormatException e) { return "Stok harus berupa angka!"; }
        try {
            if (Integer.parseInt(hargaStr) < 0) return "Harga tidak boleh negatif!";
        } catch (NumberFormatException e) { return "Harga harus berupa angka!"; }
        return null;
    }


    
}
