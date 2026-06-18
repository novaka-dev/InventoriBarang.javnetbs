/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;

/**
 *
 * @author Novaka Saputra
 */
public class BarangMasuk extends Transaksi {
    private String supplier;
 
    public BarangMasuk() {}
 
    public BarangMasuk(String idTransaksi, String idBarang, String namaBarang,
                       int jumlah, String tanggal, String keterangan, String supplier) {
        super(idTransaksi, idBarang, namaBarang, jumlah, tanggal, keterangan);
        this.supplier = supplier;
    }
 
    public String getSupplier()         { return supplier; }
    public void   setSupplier(String s) { this.supplier = s; }
 
    @Override
    public String getTipeTransaksi() { return "MASUK"; }
}
