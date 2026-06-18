/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

import dao.BarangDAO;
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
                                 int stok, int harga) {
        if (dao.findById(id) != null) return false; // ID duplikat
        return dao.simpan(new Barang(id, nama, kategori, stok, harga));
    }
 
    public boolean updateBarang(String id, String nama, String kategori,
                                 int stok, int harga) {
        return dao.update(new Barang(id, nama, kategori, stok, harga));
    }
 
    public boolean hapusBarang(String idBarang) {
        return dao.hapus(idBarang);
    }
 
    public String generateId() {
        return dao.generateId();
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
 
    public String validasiInput(String id, String nama, String stokStr, String hargaStr) {
        if (id.isBlank())   return "ID barang tidak boleh kosong!";
        if (nama.isBlank()) return "Nama barang tidak boleh kosong!";
        try {
            if (Integer.parseInt(stokStr) < 0) return "Stok tidak boleh negatif!";
        } catch (NumberFormatException e) { return "Stok harus berupa angka!"; }
        try {
            if (Integer.parseInt(hargaStr) < 0) return "Harga tidak boleh negatif!";
        } catch (NumberFormatException e) { return "Harga harus berupa angka!"; }
        return null;
    }


    
}
