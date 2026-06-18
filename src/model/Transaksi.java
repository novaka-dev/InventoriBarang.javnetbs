/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;

/**
 *
 * @author Novaka Saputra
 */
public abstract class Transaksi {
    protected String idTransaksi;
    protected String idBarang;
    protected String namaBarang;
    protected int    jumlah;
    protected String tanggal;
    protected String keterangan;
 
    public Transaksi() {}
 
    public Transaksi(String idTransaksi, String idBarang, String namaBarang,
                     int jumlah, String tanggal, String keterangan) {
        this.idTransaksi = idTransaksi;
        this.idBarang    = idBarang;
        this.namaBarang  = namaBarang;
        this.jumlah      = jumlah;
        this.tanggal     = tanggal;
        this.keterangan  = keterangan;
    }
 
    public String getIdTransaksi() { return idTransaksi; }
    public String getIdBarang()    { return idBarang; }
    public String getNamaBarang()  { return namaBarang; }
    public int    getJumlah()      { return jumlah; }
    public String getTanggal()     { return tanggal; }
    public String getKeterangan()  { return keterangan; }
 
    public void setIdTransaksi(String v) { this.idTransaksi = v; }
    public void setIdBarang(String v)    { this.idBarang = v; }
    public void setNamaBarang(String v)  { this.namaBarang = v; }
    public void setJumlah(int v)         { this.jumlah = v; }
    public void setTanggal(String v)     { this.tanggal = v; }
    public void setKeterangan(String v)  { this.keterangan = v; }
 
    public abstract String getTipeTransaksi();
}
