package dao;

import model.BarangKeluar; // <-- Pastikan import model ini ada!
import java.util.ArrayList;
import java.util.List;

/**
 * DAO — BarangKeliar.java
 */
public class BarangKeliar {
    
    // 1. DI SINI HARUS PAKAI BarangKeluar (MODEL), BUKAN BarangKeliar (DAO)
    private static List<BarangKeluar> dataBarangKeluar = new ArrayList<>(); 

    // 2. DI SINI JUGA HARUS public List<BarangKeluar>
    public List<BarangKeluar> getAll() { 
        return dataBarangKeluar;
    }

    public boolean simpan(BarangKeluar bk) { 
        dataBarangKeluar.add(bk);
        return true;
    }

    public String generateId() { 
        int next = dataBarangKeluar.size() + 1;
        return String.format("BK%03d", next);
    }
}