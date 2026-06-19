/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;

/**
 *
 * @author Novaka Saputra
 */
public class Barang {
    
    private String idBarang;
    private String nama;
    private String kategori;
    private int    stok;
    private int    harga;
    private String supplier;
 
    public Barang() {}
 
    public Barang(String idBarang, String nama, String kategori, int stok, int harga , String supplier) {
        this.idBarang  = idBarang;
        this.nama      = nama;
        this.kategori  = kategori;
        this.stok      = stok;
        this.harga     = harga;
        this.supplier = supplier;
    }
 
    public String getIdBarang()  { return idBarang; }
    public String getNama()      { return nama; }
    public String getKategori()  { return kategori; }
    public int    getStok()      { return stok; }
    public int    getHarga()     { return harga; }  
    public String getSupplier()  { return supplier; }
 
    public void setIdBarang(String idBarang)  { this.idBarang = idBarang; }
    public void setNama(String nama)          { this.nama = nama; }
    public void setKategori(String kategori)  { this.kategori = kategori; }
    public void setStok(int stok)             { this.stok = stok; }
    public void setHarga(int harga)           { this.harga = harga; }
    public void setSupplier(String supplier)  { this.supplier = supplier; }
    
    @Override
    public String toString() { return nama + " (Stok: " + stok + ")"; }
    
}
