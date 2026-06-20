/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package model;

/**
 *
 * @author Novaka Saputra
 */
   public class BarangKeluar extends Transaksi {
    private String tujuan;

    public BarangKeluar() {}

    public BarangKeluar(String idTransaksi, String idBarang, String namaBarang,
                       int jumlah, String tanggal, String keterangan, String tujuan) {
        super(idTransaksi, idBarang, namaBarang, jumlah, tanggal, keterangan);
        this.tujuan = tujuan;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    @Override
    public String getTipeTransaksi() {
        return "KELUAR";
    }
}

