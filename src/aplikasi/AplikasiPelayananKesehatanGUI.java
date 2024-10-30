/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplikasi;

/**
 *
 * @author TIA
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

class Pasien {
    private String nama;
    private int umur;
    private String nomorRekamMedis;
    private String keluhan;

    public Pasien(String nama, int umur, String nomorRekamMedis, String keluhan) {
        this.nama = nama;
        this.umur = umur;
        this.nomorRekamMedis = nomorRekamMedis;
        this.keluhan = keluhan;
    }

    public String getNama() {
        return nama;
    }

    public int getUmur() {
        return umur;
    }

    public String getNomorRekamMedis() {
        return nomorRekamMedis;
    }

    public String getKeluhan() {
        return keluhan;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", Umur: " + umur + ", No. Rekam Medis: " + nomorRekamMedis + ", Keluhan: " + keluhan;
    }
}

public class AplikasiPelayananKesehatanGUI extends JFrame {
    private ArrayList<Pasien> daftarPasien;
    private JTextArea textArea;
    private JTextField namaField;
    private JTextField umurField;
    private JTextField nomorRekamMedisField;
    private JTextField keluhanField;
    private JTextField cariField;

    public AplikasiPelayananKesehatanGUI() {
        daftarPasien = new ArrayList<>();
        setTitle("Aplikasi Pelayanan Kesehatan");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel untuk input pasien
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2));

        inputPanel.add(new JLabel("Nama Pasien:"));
        namaField = new JTextField();
        inputPanel.add(namaField);

        inputPanel.add(new JLabel("Umur Pasien:"));
        umurField = new JTextField();
        inputPanel.add(umurField);

        inputPanel.add(new JLabel("No. Rekam Medis:"));
        nomorRekamMedisField = new JTextField();
        inputPanel.add(nomorRekamMedisField);

        inputPanel.add(new JLabel("Keluhan:"));
        keluhanField = new JTextField();
        inputPanel.add(keluhanField);

        JButton tambahButton = new JButton("Tambah Pasien");
        tambahButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tambahPasien();
            }
        });
        inputPanel.add(tambahButton);

        JButton tampilButton = new JButton("Tampilkan Pasien");
        tampilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tampilkanDaftarPasien();
            }
        });
        inputPanel.add(tampilButton);

        JButton hapusButton = new JButton("Hapus Pasien");
        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hapusPasien();
            }
        });
        inputPanel.add(hapusButton);

        add(inputPanel, BorderLayout.NORTH);

        // Area untuk menampilkan daftar pasien
        textArea = new JTextArea();
        textArea.setEditable(false);
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Panel untuk mencari pasien
        JPanel cariPanel = new JPanel();
        cariPanel.setLayout(new FlowLayout());

        cariPanel.add(new JLabel("Cari Pasien:"));
        cariField = new JTextField(10);
        cariPanel.add(cariField);

        JButton cariButton = new JButton("Cari");
        cariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cariPasien();
            }
        });
        cariPanel.add(cariButton);

        add(cariPanel, BorderLayout.SOUTH);
    }

    private void tambahPasien() {
        String nama = namaField.getText();
        String umurText = umurField.getText();
        String nomorRekamMedis = nomorRekamMedisField.getText();
        String keluhan = keluhanField.getText();

        if (nama.isEmpty() || umurText.isEmpty() || nomorRekamMedis.isEmpty() || keluhan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            int umur = Integer.parseInt(umurText);
            daftarPasien.add(new Pasien(nama, umur, nomorRekamMedis, keluhan));
            JOptionPane.showMessageDialog(this, "Pasien berhasil ditambahkan.");
            namaField.setText("");
            umurField.setText("");
            nomorRekamMedisField.setText("");
            keluhanField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Umur harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void tampilkanDaftarPasien() {
        textArea.setText("");
        if (daftarPasien.isEmpty()) {
            textArea.setText("Tidak ada pasien yang terdaftar.");
        } else {
            for (Pasien pasien : daftarPasien) {
                textArea.append(pasien.toString() + "\n");
            }
        }
    }

    private void cariPasien() {
        String nama = cariField.getText();
        boolean ditemukan = false;

        for (Pasien pasien : daftarPasien) {
            if (pasien.getNama().equalsIgnoreCase(nama)) {
                textArea.setText("Pasien ditemukan: " + pasien);
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            textArea.setText("Pasien dengan nama " + nama + " tidak ditemukan.");
        }
    }

    private void hapusPasien() {
        String nama = cariField.getText();
        boolean ditemukan = false;

        for (int i = 0; i < daftarPasien.size(); i++) {
            if (daftarPasien.get(i).getNama().equalsIgnoreCase(nama)) {
                daftarPasien.remove(i);
                JOptionPane.showMessageDialog(this, "Pasien berhasil dihapus.");
                ditemukan = true;
                break;
            }
        }

        if (!ditemukan) {
            JOptionPane.showMessageDialog(this, "Pasien dengan nama " + nama + " tidak ditemukan.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AplikasiPelayananKesehatanGUI aplikasi = new AplikasiPelayananKesehatanGUI();
            aplikasi.setVisible(true);
        });
    }
}