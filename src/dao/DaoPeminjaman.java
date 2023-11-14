/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import parsisten.Peminjaman;
import servis.ServicePeminjaman;

/**
 *
 * @author fatiq
 */
public class DaoPeminjaman implements ServicePeminjaman {

    @Override
    public void tambahData(Peminjaman mod) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        em.persist(mod);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void ubahData(Peminjaman mod) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        em.merge(mod);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void hapusData(String id) {
        String sql = "DELETE FROM DETAIL_SKRIPSI WHERE NO_PEMINJAMAN = ?;\n"
                + "DELETE FROM DETAIL_BUKU WHERE NO_PEMINJAMAN = ?;\n"
                + "DELETE FROM PEMINJAMAN WHERE NO_PEMINJAMAN = ?;";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, id);
        query.setParameter(2, id);
        query.setParameter(3, id);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Peminjaman> ambilData() {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Peminjaman p ORDER BY p.peminjamanPK.idPeminjam");
        List<Peminjaman> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Peminjaman> getByTglPinjam(Date tgl) {
        String sql = "SELECT p FROM Peminjaman p WHERE p.tanggalPinjam = :tanggalPinjam ORDER BY p.peminjamanPK.idPeminjam";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(sql);
        query.setParameter("tanggalPinjam", tgl);
        List<Peminjaman> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Peminjaman> getByTglKembali(Date tgl) {
        String sql = "SELECT p FROM Peminjaman p WHERE p.tanggalKembali = :tanggalKembali ORDER BY p.peminjamanPK.idPeminjam";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(sql);
        query.setParameter("tanggalKembali", tgl);
        List<Peminjaman> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Peminjaman> getByPeminjam(String nama) {
        String sql = "SELECT p FROM Peminjaman p WHERE p.peminjamanPK.idPeminjam IN ("
                + "SELECT pj.idPeminjam FROM Peminjam pj WHERE LOWER(pj.nama) LIKE :nama) "
                + "ORDER BY p.peminjamanPK.idPeminjam";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(sql);
        query.setParameter("nama", "%"+nama.toLowerCase() +"%");
        List<Peminjaman> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Peminjaman> getBystatus(String status) {
        String sql = "SELECT p FROM Peminjaman p WHERE LOWER(p.status) = :status ORDER BY p.peminjamanPK.idPeminjam";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(sql);
        query.setParameter("status", status.toLowerCase() );
        List<Peminjaman> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Peminjaman> getByNO(String no) {
        String sql = "SELECT p FROM Peminjaman p WHERE LOWER(p.peminjamanPK.noPeminjaman) = :noPeminjaman";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery(sql);
        query.setParameter("noPeminjaman", "%"+no.toLowerCase() +"%");
        List<Peminjaman> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public Peminjaman getByNo(String no) {
        String jpql = "SELECT p FROM Peminjaman p WHERE p.peminjamanPK.noPeminjaman = :noPeminjaman";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        TypedQuery<Peminjaman> query = em.createQuery(jpql, Peminjaman.class);
        query.setParameter("noPeminjaman", no);
        query.setMaxResults(1);
        Peminjaman mod = query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        return mod;
    }

    @Override
    public String nomer() {
        String sql = "SELECT RIGHT(NO_PEMINJAMAN, 3) AS nomor "
                + "FROM PEMINJAMAN WHERE NO_PEMINJAMAN LIKE 'T%' "
                + "ORDER BY NO_PEMINJAMAN DESC LIMIT 1;";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNativeQuery(sql);
        Date now = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat nonformat = new SimpleDateFormat("yyMMdd");
        tanggal.format(now);
        String no = nonformat.format(now);
        String urutan = "";
        try{
            urutan = "T" + no + String.format("%03d", Integer.parseInt(query.getSingleResult().toString()) + 1);
        } catch (NoResultException e){
            urutan = "T" + no + "001";
        }
        em.getTransaction().commit();
        em.close();
        return urutan;
    }

    @Override
    public int jumlahPeminjaman() {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Peminjaman p ORDER BY p.peminjamanPK.idPeminjam");
        List<Peminjaman> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list.size();
    }

}
