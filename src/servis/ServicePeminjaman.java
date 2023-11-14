/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servis;

import java.util.Date;
import java.util.List;
import parsisten.Peminjaman;

/**
 *
 * @author fatiq
 */
public interface ServicePeminjaman {
    void tambahData(Peminjaman mod);
    void ubahData(Peminjaman mod);
    void hapusData(String id);
    
    List<Peminjaman> ambilData();
    List<Peminjaman> getByTglPinjam(Date tgl);
    List<Peminjaman> getByTglKembali(Date tgl);
    List<Peminjaman> getByPeminjam(String nama);
    List<Peminjaman> getBystatus(String status);
    List<Peminjaman> getByNO(String no);
    Peminjaman getByNo(String no);
    String nomer();
    int jumlahPeminjaman();
}
