/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import java.text.SimpleDateFormat;

import java.util.Date;

import controller.BarangController;
import controller.BarangMasukController;
import model.Barang;
import model.BarangMasuk;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;
import java.awt.Color;

/**
 *
 * @author Novaka Saputra
 */
public class BarangMasukFrame extends javax.swing.JFrame {
    // ── 2. VARIABEL (taruh di dalam class, setelah deklarasi komponen GUI) ───────

    private BarangController barangCtrl = new BarangController();
    private BarangMasukController masukCtrl = new BarangMasukController();
    private DefaultTableModel tableModel;

    /**
     * Creates new form BarangMasukFrame
     */
    public BarangMasukFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        initTable();
        loadComboBarang();
//        setTanggalHariIni();
        txtTanggal.setDate(new java.util.Date());
        
        // ── KUNCI FIELD BIAR GA BISA DIEDIT USER ───────
        txtStokSaatIni.setEditable(false);
        txtKategori.setEditable(false);
        txtSupplier.setEditable(false); // Kunci supplier biar otomatis aja

    }

    // ── 4. METHOD-METHOD ─────────────────────────────────────────────────────────

    // Setup kolom JTable
    private void initTable() {
        tableModel = new DefaultTableModel(
            // "Kategori" resmi disisipkan di kolom ke-3 (indeks 2)
            new String[]{"ID Transaksi", "Nama Barang", "Kategori", "Jumlah", "Supplier", "Tanggal", "Keterangan"},
            0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Mengunci tabel agar tidak bisa diedit manual
            }
        };

        tblRiwayat.setModel(tableModel);

        tblRiwayat.setAutoResizeMode(
            javax.swing.JTable.AUTO_RESIZE_OFF
        );

        // Atur ulang proporsi lebar 7 kolom lo
        tblRiwayat.getColumnModel().getColumn(0).setPreferredWidth(100); // ID Transaksi
        tblRiwayat.getColumnModel().getColumn(1).setPreferredWidth(150); // Nama Barang
        tblRiwayat.getColumnModel().getColumn(2).setPreferredWidth(120); // Kategori (BARU)
        tblRiwayat.getColumnModel().getColumn(3).setPreferredWidth(80);  // Jumlah
        tblRiwayat.getColumnModel().getColumn(4).setPreferredWidth(150); // Supplier
        tblRiwayat.getColumnModel().getColumn(5).setPreferredWidth(100); // Tanggal
        tblRiwayat.getColumnModel().getColumn(6).setPreferredWidth(250); // Keterangan

        tblRiwayat.getTableHeader().setReorderingAllowed(false);

        refreshTable();
    }

// Isi ComboBox dengan data barang dari controller
private void loadComboBarang() {
    cmbPilihBarang.removeAllItems();
    List<Barang> listBarang = barangCtrl.getAllBarang();

    if (listBarang.isEmpty()) {
        cmbPilihBarang.addItem("-- Belum ada barang --");
        return;
    }

    for (Barang b : listBarang) {
        // Format: "B001 - Spidol"
        cmbPilihBarang.addItem(b.getIdBarang() + " - " + b.getNama());
    }
}

// Set tanggal otomatis ke hari ini (kalau ga pake JDateChooser)
    private void setTanggalHariIni() {
        txtTanggal.setDate(new java.util.Date());
    }

    // Ambil ID barang dari pilihan ComboBox
    // ComboBox isinya "B001 - Spidol", kita ambil bagian "B001" aja
    private String getIdBarangDipilih() {
        String selected = (String) cmbPilihBarang.getSelectedItem();
        if (selected == null || selected.startsWith("--")) return null;
        return selected.split(" - ")[0]; // ambil bagian sebelum " - "
    }

    // Refresh data tabel dari controller
    private void refreshTable() {
    tableModel.setRowCount(0); // Kosongkan tabel riwayat lama
    List<BarangMasuk> list = masukCtrl.getAllBarangMasuk();

    for (BarangMasuk bm : list) {
        // Cari objek barang asli berdasarkan idBarang transaksi
        Barang b = barangCtrl.findById(bm.getIdBarang());

        // Jika barang ketemu di master, ambil kategorinya. Jika tidak ada, kasih tanda strip
        String kategoriOtomatis = (b != null) ? b.getKategori() : "-";

        tableModel.addRow(new Object[]{
            bm.getIdTransaksi(),
            bm.getNamaBarang(),
            kategoriOtomatis, // Ditulis otomatis ke kolom tabel riwayat
            bm.getJumlah(),
            bm.getSupplier(),
            bm.getTanggal(),
            bm.getKeterangan()
        });
    }
}

    // Reset semua input form ke kondisi awal
    private void resetForm() {
        cmbPilihBarang.setSelectedIndex(0);
        txtStokSaatIni.setText("");
        txtKategori.setText(""); // Bersihkan teks komponen kategori baru
        txtJumlahMasuk.setText("");
        txtSupplier.setText("");
        setTanggalHariIni();
        txtKeterangan.setText("");
        txtJumlahMasuk.requestFocus();
    }


// ── 5. ISI TOMBOL (double click tombol di Design, lalu isi di sini) ──────────


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        cmbPilihBarang = new javax.swing.JComboBox<>();
        txtStokSaatIni = new javax.swing.JTextField();
        txtJumlahMasuk = new javax.swing.JTextField();
        txtSupplier = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtKeterangan = new javax.swing.JTextArea();
        btnKembali = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtKategori = new javax.swing.JTextField();
        btnReset1 = new javax.swing.JButton();
        txtTanggal = new com.toedter.calendar.JDateChooser();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblRiwayat = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(48, 48, 47));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(45, 122, 80));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Barang Masuk");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, -1, -1));

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(221, 221, 221));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Catat penambahan stok barang   ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 120, 220, 22));

        jLabel4.setForeground(Color.WHITE);
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/store.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 560, 160));

        jPanel3.setBackground(new java.awt.Color(80, 80, 80));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "FORM INPUT", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Poppins", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Keterangan    ");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, -1, -1));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Jumlah Masuk");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, -1, -1));

        jLabel6.setBackground(new java.awt.Color(255, 255, 255));
        jLabel6.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Pilih Barang");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Stok Saat Ini");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, -1, -1));

        jLabel8.setBackground(new java.awt.Color(255, 255, 255));
        jLabel8.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Supplier      ");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, -1, -1));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Tanggal       ");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        cmbPilihBarang.setBackground(new java.awt.Color(225, 225, 225));
        cmbPilihBarang.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbPilihBarangItemStateChanged(evt);
            }
        });
        cmbPilihBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbPilihBarangActionPerformed(evt);
            }
        });
        jPanel3.add(cmbPilihBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 250, -1));

        txtStokSaatIni.setBackground(new java.awt.Color(225, 225, 225));
        jPanel3.add(txtStokSaatIni, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 250, -1));

        txtJumlahMasuk.setBackground(new java.awt.Color(225, 225, 225));
        jPanel3.add(txtJumlahMasuk, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 250, -1));

        txtSupplier.setBackground(new java.awt.Color(225, 225, 225));
        jPanel3.add(txtSupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 250, -1));

        txtKeterangan.setBackground(new java.awt.Color(225, 225, 225));
        txtKeterangan.setColumns(20);
        txtKeterangan.setRows(5);
        jScrollPane1.setViewportView(txtKeterangan);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 250, -1));

        btnKembali.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        btnKembali.setText("Kembali");
        btnKembali.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        jPanel3.add(btnKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 430, 40));

        btnSimpan.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        btnSimpan.setText("Simpan");
        btnSimpan.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        jPanel3.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 210, 40));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Poppins", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Kategori");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        txtKategori.setBackground(new java.awt.Color(225, 225, 225));
        jPanel3.add(txtKategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 250, -1));

        btnReset1.setFont(new java.awt.Font("Poppins", 0, 12)); // NOI18N
        btnReset1.setText("Reset");
        btnReset1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnReset1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReset1ActionPerformed(evt);
            }
        });
        jPanel3.add(btnReset1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 310, 210, 40));
        jPanel3.add(txtTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 180, 250, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 500, 410));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 583, 530, 0));

        jPanel4.setBackground(new java.awt.Color(80, 80, 80));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "RIWAYAT BARANG MASUK", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Poppins", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setLayout(new java.awt.BorderLayout());

        tblRiwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblRiwayat);

        jPanel4.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 620, 500, 270));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 950));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        // TODO add your handling code here:
        String idBarang   = getIdBarangDipilih();
        String jumlahStr  = txtJumlahMasuk.getText().trim();
        String supplier   = txtSupplier.getText().trim();
        java.text.SimpleDateFormat sdf =
        new java.text.SimpleDateFormat("yyyy-MM-dd");

        String tanggal =
        sdf.format(txtTanggal.getDate());
        String keterangan = txtKeterangan.getText().trim();

        // Validasi lewat controller
        String error = masukCtrl.validasiInput(idBarang, jumlahStr);
        if (error != null) {
            javax.swing.JOptionPane.showMessageDialog(this, error, "Validasi Gagal",
                javax.swing.JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Ambil nama barang buat disimpen di transaksi
        Barang barang = barangCtrl.findById(idBarang);
        String namaBarang = barang.getNama();

        // Simpan lewat controller
        boolean berhasil = masukCtrl.catatBarangMasuk(
            idBarang, namaBarang,
            Integer.parseInt(jumlahStr),
            tanggal, keterangan, supplier
        );

        if (berhasil) {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Barang masuk berhasil dicatat!\n" +
                "Stok " + namaBarang + " bertambah " + jumlahStr + " unit.",
                "Berhasil", javax.swing.JOptionPane.INFORMATION_MESSAGE);

            refreshTable();   // update tabel riwayat
            resetForm();      // kosongkan form
            loadComboBarang(); // refresh stok di combobox
        } else {
            javax.swing.JOptionPane.showMessageDialog(this,
                "Gagal menyimpan data!", "Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        // TODO add your handling code here:
        // 1. Buat objek instansi dari halaman Main Menu lo
        // Sesuaikan "MainMenuFrame" dengan nama class JFrame menu utama lo yang asli
        MainMenuFrame mainMenu = new MainMenuFrame();

        // 2. Munculkan halaman Main Menu ke layar
        mainMenu.setVisible(true);

        // 3. Atur posisi Main Menu otomatis di tengah layar komputer
        mainMenu.setLocationRelativeTo(null);

        // 4. Tutup dan hancurkan halaman Barang Masuk saat ini dari memori
        this.dispose();
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void cmbPilihBarangItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbPilihBarangItemStateChanged
        // TODO add your handling code here:
        if (evt.getStateChange() == java.awt.event.ItemEvent.SELECTED) {
            String idBarang = getIdBarangDipilih();
            if (idBarang != null) {
                Barang barang = barangCtrl.findById(idBarang);
                if (barang != null) {
                    // 1. Otomatis set stok saat ini
                    txtStokSaatIni.setText(String.valueOf(barang.getStok()) + " unit");

                    // 2. Otomatis set kategori sesuai data master barang (BARU)
                    txtKategori.setText(barang.getKategori());
                    
                    // 3. Otomatis set supplier sesuai data master barang (BARU 🌟)
                    txtSupplier.setText(barang.getSupplier());
                }
            } else {
                txtStokSaatIni.setText("");
                txtKategori.setText("");
                txtSupplier.setText(""); // Kosongkan jika tidak ada pilihan
            }
        }
    }//GEN-LAST:event_cmbPilihBarangItemStateChanged

    private void btnReset1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReset1ActionPerformed
        // TODO add your handling code here:
        resetForm();
    }//GEN-LAST:event_btnReset1ActionPerformed

    private void cmbPilihBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbPilihBarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbPilihBarangActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BarangMasukFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BarangMasukFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BarangMasukFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BarangMasukFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BarangMasukFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnReset1;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbPilihBarang;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tblRiwayat;
    private javax.swing.JTextField txtJumlahMasuk;
    private javax.swing.JTextField txtKategori;
    private javax.swing.JTextArea txtKeterangan;
    private javax.swing.JTextField txtStokSaatIni;
    private javax.swing.JTextField txtSupplier;
    private com.toedter.calendar.JDateChooser txtTanggal;
    // End of variables declaration//GEN-END:variables
}
