/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servis;

import java.util.List;
import parsisten.Skripsi;

/**
 *
 * @author fatiq
 */
public interface ServiceSkripsi {
    void tambahData(Skripsi mod);
    void ubahData(Skripsi mod);
    void hapusData(String id);
    
    Skripsi getById(String id);
    List<Skripsi> getByID(String id);
    List<Skripsi> getByJudul(String judul);
    List<Skripsi> getByPenulis(String penulis);
    List<Skripsi> getByPembimbing(String pembimbing);
    List<Skripsi> getByFakultas(String fakultas);
    List<Skripsi> getByProdi(String prodi);
    List<Skripsi> getByKategori(String kategori);
    List<Skripsi> ambilData();
    String nomer();
    int jumlahSkripsi();
    int jmlPipinjam(String id);
    String getAllKategori(String id);
}
