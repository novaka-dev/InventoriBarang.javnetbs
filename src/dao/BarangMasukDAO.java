/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.BarangMasuk;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Novaka Saputra
 */
public class BarangMasukDAO {
    private static List<BarangMasuk> dataBarangMasuk = new ArrayList<>();
 
    public List<BarangMasuk> getAll() {
        return dataBarangMasuk;
    }
 
    public boolean simpan(BarangMasuk transaksi) {
        dataBarangMasuk.add(transaksi);
        return true;
    }
 
    // Generate ID otomatis: BM001, BM002, dst
    public String generateId() {
        int next = dataBarangMasuk.size() + 1;
        return String.format("BM%03d", next);
    }
}
