/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import servis.ServiceBuku;
import parsisten.Buku;
import parsisten.DetailBuku;
import parsisten.Peminjaman;
import servis.ServicePeminjaman;

/**
 *
 * @author fatiq
 */
public class DaoBuku implements ServiceBuku {
    ServicePeminjaman servis = new DaoPeminjaman();

    @Override
    public void tambahData(Buku mod) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        em.persist(mod);
        em.getTransaction().commit();
        em.close();

        if (!mod.getKategori().isEmpty()) {
            tambahKategori(mod);
        }
        if (!mod.getPengarang().isEmpty()) {
            tambahPengarang(mod);
        }
    }

    private void tambahKategori(Buku mod) {
        String sql = "INSERT INTO KATEGORI_BUKU (ID_BUKU, KATEGORI) VALUES (?,?)";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        for (String kategori : mod.getKategori()) {
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, mod.getIdBuku());
            query.setParameter(2, kategori);
            query.executeUpdate();
        }
        em.getTransaction().commit();
        em.close();
    }

    private void tambahPengarang(Buku mod) {
        String sql = "INSERT INTO PENGARANG (ID_BUKU, NAMA_PENGARANG) VALUES (?,?)";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        for (String pengarang : mod.getPengarang()) {
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, mod.getIdBuku());
            query.setParameter(2, pengarang);
            query.executeUpdate();
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void ubahData(Buku mod) {
        String sql = "DELETE FROM PENGARANG WHERE ID_BUKU = ?; DELETE FROM KATEGORI_BUKU WHERE ID_BUKU = ?;";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Buku p = em.find(Buku.class, mod.getIdBuku());
        if (mod.getFotoSampul() == null) {
            mod.setFotoSampul(p.getFotoSampul());
        }
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, mod.getIdBuku());
        query.setParameter(2, mod.getIdBuku());
        query.executeUpdate();
        em.merge(mod);
        em.getTransaction().commit();
        em.close();

        if (!mod.getKategori().isEmpty()) {
            tambahKategori(mod);
        }
        if (!mod.getPengarang().isEmpty()) {
            tambahPengarang(mod);
        }
    }

    @Override
    public void hapusData(String id) {
        String sql = "DELETE FROM PENGARANG WHERE ID_BUKU = ?; DELETE FROM KATEGORI_BUKU WHERE ID_BUKU = ?;";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Buku p = em.find(Buku.class, id);
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, id);
        query.setParameter(2, id);
        query.executeUpdate();
        em.remove(p);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Buku getById(String id) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Buku p = em.find(Buku.class, id);
        if (p != null) {
            p.setKategori(ambilKategori(id));
            p.setPengarang(ambilPengarang(id));
        }
        em.getTransaction().commit();
        em.close();
        return p;
    }

    @Override
    public Buku getByIsbn(String isbn) {
        String jpql = "SELECT b FROM Buku b WHERE b.isbn = :isbn";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Buku> query = em.createQuery(jpql, Buku.class);
        query.setParameter("isbn", isbn);
        query.setMaxResults(1);
        Buku p = null;
        try {
            p = query.getSingleResult();
            p.setKategori(ambilKategori(p.getIdBuku()));
            p.setPengarang(ambilPengarang(p.getIdBuku()));
        } catch (NoResultException e) {
            p = null;
        }
        em.getTransaction().commit();
        em.close();
        return p;
    }

    @Override
    public List<Buku> getByID(String id) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT b FROM Buku b WHERE LOWER(b.idBuku) LIKE :idBuku ORDER BY b.idBuku");
        query.setParameter("idBuku", "%" + id.toLowerCase() + "%");
        List<Buku> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Buku b = list.get(i);
            b.setKategori(ambilKategori(b.getIdBuku()));
            b.setPengarang(ambilPengarang(b.getIdBuku()));
            list.set(i, b);
        }
        return list;
    }

    @Override
    public List<Buku> getByISBN(String isbn) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT b FROM Buku b WHERE LOWER(b.isbn) LIKE :isbn ORDER BY b.idBuku");
        query.setParameter("isbn", "%" + isbn.toLowerCase() + "%");
        List<Buku> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Buku b = list.get(i);
            b.setKategori(ambilKategori(b.getIdBuku()));
            b.setPengarang(ambilPengarang(b.getIdBuku()));
            list.set(i, b);
        }
        return list;
    }

    @Override
    public List<Buku> getByJudul(String judul) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT b FROM Buku b WHERE LOWER(b.judul) LIKE :judul ORDER BY b.idBuku");
        query.setParameter("judul", "%" + judul.toLowerCase() + "%");
        List<Buku> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Buku b = list.get(i);
            b.setKategori(ambilKategori(b.getIdBuku()));
            b.setPengarang(ambilPengarang(b.getIdBuku()));
            list.set(i, b);
        }
        return list;
    }

    @Override
    public List<Buku> getByKategori(String kategori) {
        String sql = "SELECT b.* FROM Buku b WHERE b.id_buku IN "
                + "(SELECT kb.ID_BUKU FROM KATEGORI_BUKU kb WHERE LOWER(kb.KATEGORI) LIKE ?) "
                + "ORDER BY b.id_buku";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNativeQuery(sql, Buku.class);
        query.setParameter(1, "%" + kategori.toLowerCase() + "%");
        List<Buku> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Buku b = list.get(i);
            b.setKategori(ambilKategori(b.getIdBuku()));
            b.setPengarang(ambilPengarang(b.getIdBuku()));
            list.set(i, b);
        }
        return list;
    }

    @Override
    public List<Buku> getByPengarang(String pengarang) {
        String sql = "SELECT b.* FROM Buku b WHERE b.id_buku IN "
                + "(SELECT p.ID_BUKU FROM PENGARANG p WHERE LOWER(p.NAMA_PENGARANG) LIKE ?) "
                + "ORDER BY b.id_buku";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNativeQuery(sql, Buku.class);
        query.setParameter(1, "%" + pengarang.toLowerCase() + "%");
        List<Buku> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Buku b = list.get(i);
            b.setKategori(ambilKategori(b.getIdBuku()));
            b.setPengarang(ambilPengarang(b.getIdBuku()));
            list.set(i, b);
        }
        return list;
    }

    @Override
    public List<Buku> ambilData() {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT b FROM Buku b ORDER BY b.idBuku");
        List<Buku> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Buku b = list.get(i);
            b.setKategori(ambilKategori(b.getIdBuku()));
            b.setPengarang(ambilPengarang(b.getIdBuku()));
            list.set(i, b);
        }
        return list;
    }

    private List<String> ambilKategori(String id) {
        String sql = "SELECT KATEGORI FROM KATEGORI_BUKU WHERE ID_BUKU = ?";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, id);
        List<String> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    private List<String> ambilPengarang(String id) {
        String sql = "SELECT NAMA_PENGARANG FROM PENGARANG WHERE ID_BUKU = ?";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, id);
        List<String> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public int jumlahBuku() {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT b FROM Buku b ORDER BY b.idBuku");
        List<Buku> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list.size();
    }

    @Override
    public String nomer() {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        String jpql = "SELECT SUBSTRING(b.idBuku, LENGTH(b.idBuku) - 2) AS nomor "
                + "FROM Buku b WHERE b.idBuku LIKE 'BK%' ORDER BY b.idBuku DESC";
        TypedQuery<String> query = em.createQuery(jpql, String.class);
        query.setMaxResults(1);
        Date now = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat nonformat = new SimpleDateFormat("yyMMdd");
        tanggal.format(now);
        String no = nonformat.format(now);
        String urutan = "";
        try {
            urutan = "BK" + no + String.format("%03d", Integer.parseInt(query.getSingleResult()) + 1);
        } catch (NoResultException e) {
            urutan = "BK" + no + "001";
        }
        em.close();
        return urutan;
    }

    @Override
    public int jmlDipinjam(String id) {
        List<Peminjaman> p = servis.getBystatus("Dipinjam");
        int i = 0;
        for(Peminjaman pem : p){
            List<DetailBuku> db = new ArrayList(pem.getDetailBukuCollection());
            for(DetailBuku b : db){
                if(b.getBuku().getIdBuku().equalsIgnoreCase(id)){
                    i += b.getJumlah();
                }
            }
        }
        return i;
    }

    @Override
    public String getAllPengarang(String id) {
        List<String> list = ambilPengarang(id);
        String pengarang = "";
        for(String peng : list){
                if(pengarang.isEmpty())
                    pengarang = peng;
                else 
                    pengarang += ", " + peng;
            }
        return pengarang;}

    @Override
    public String getAllKategori(String id) {
        List<String> list = ambilKategori(id);
        String kategori = "";
        for(String kat : list){
                if(kategori.isEmpty())
                    kategori = kat;
                else 
                    kategori += ", " + kat;
            }
        return kategori;}

}
