/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servis;

import java.util.List;
import parsisten.Buku;

/**
 *
 * @author fatiq
 */
public interface ServiceBuku {
    void tambahData(Buku mod);
    void ubahData(Buku mod);
    void hapusData(String id);
    
    Buku getById(String id);
    Buku getByIsbn(String isbn);
    List<Buku> getByID(String id);
    List<Buku> getByISBN(String isbn);
    List<Buku> getByJudul(String judul);
    List<Buku> getByKategori(String kategori);
    List<Buku> getByPengarang(String pengarang);
    List<Buku> ambilData();
    int jumlahBuku();
    int jmlDipinjam(String id);
    String getAllPengarang(String id);
    String getAllKategori(String id);
    String nomer();
}
