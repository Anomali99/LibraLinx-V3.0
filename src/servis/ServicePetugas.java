/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servis;

import java.util.List;
import parsisten.Petugas;

/**
 *
 * @author fatiq
 */
public interface ServicePetugas {
    void tambahData(Petugas mod);
    void ubahData(Petugas mod);
    void hapusData(String id);
    
    List<Petugas> ambilData();
    List<Petugas> getByID(String id);
    List<Petugas> getByNama(String nama);
    List<Petugas> getByAlamat(String alamat);
    Petugas getById(String id);
    String nomer();
}
