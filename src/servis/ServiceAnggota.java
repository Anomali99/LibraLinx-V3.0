/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servis;

import java.util.List;
import parsisten.Peminjam;

/**
 *
 * @author fatiq
 */
public interface ServiceAnggota {
    void tambahData(Peminjam mod);
    void ubahData(Peminjam mod);
    void hapusData(String id);
    
    List<Peminjam> ambilData();
    List<Peminjam> getByID(String id);
    List<Peminjam> getByNama(String nama);
    List<Peminjam> getByNim(String nim);
    List<Peminjam> getByFakultas(String fakultas);
    List<Peminjam> getByProdi(String prodi);
    List<Peminjam> getByAlamat(String alamat);
    Peminjam getById(String id);
    String nomer();
    int jumlahAnggota();
}
