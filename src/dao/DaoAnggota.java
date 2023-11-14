/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import parsisten.Peminjam;
import servis.ServiceAnggota;

/**
 *
 * @author fatiq
 */
public class DaoAnggota implements ServiceAnggota {

    @Override
    public void tambahData(Peminjam mod) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        em.persist(mod);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void ubahData(Peminjam mod) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Peminjam p = em.find(Peminjam.class, mod.getIdPeminjam());
        if (mod.getFoto() == null) {
            mod.setFoto(p.getFoto());
        }
        em.merge(mod);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void hapusData(String id) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Peminjam p = em.find(Peminjam.class, id);
        em.remove(p);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Peminjam> ambilData() {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Peminjam p ORDER BY p.idPeminjam");
        List<Peminjam> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Peminjam> getByID(String id) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Peminjam p WHERE LOWER(p.idPeminjam) LIKE :idPeminjam ORDER BY p.idPeminjam");
        query.setParameter("idPeminjam", "%" + id.toLowerCase() + "%");
        List<Peminjam> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Peminjam> getByNama(String nama) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Peminjam p WHERE LOWER(p.nama) LIKE :nama ORDER BY p.idPeminjam");
        query.setParameter("nama", "%" + nama.toLowerCase() + "%");
        List<Peminjam> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Peminjam> getByNim(String nim) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Peminjam p WHERE LOWER(p.nim) LIKE :nim ORDER BY p.idPeminjam");
        query.setParameter("nim", "%" + nim.toLowerCase() + "%");
        List<Peminjam> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Peminjam> getByFakultas(String fakultas) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Peminjam p WHERE LOWER(p.fakultas) LIKE :fakultas ORDER BY p.idPeminjam");
        query.setParameter("fakultas", "%" + fakultas.toLowerCase() + "%");
        List<Peminjam> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Peminjam> getByProdi(String prodi) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Peminjam p WHERE LOWER(p.programStudi) LIKE :prodi ORDER BY p.idPeminjam");
        query.setParameter("prodi", "%" + prodi.toLowerCase() + "%");
        List<Peminjam> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Peminjam> getByAlamat(String alamat) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Peminjam p WHERE LOWER(p.alamat) LIKE :alamat ORDER BY p.idPeminjam");
        query.setParameter("alamat", "%" + alamat.toLowerCase() + "%");
        List<Peminjam> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public Peminjam getById(String id) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Peminjam p = em.find(Peminjam.class, id);
        em.getTransaction().commit();
        em.close();
        return p;
    }

    @Override
    public String nomer() {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        String jpql = "SELECT SUBSTRING(p.idPeminjam, LENGTH(p.idPeminjam) - 2) AS nomor "
                + "FROM Peminjam p WHERE p.idPeminjam LIKE 'PM%' ORDER BY p.idPeminjam DESC";
        TypedQuery<String> query = em.createQuery(jpql, String.class);
        query.setMaxResults(1);
        String urutan = "";
        try{
            urutan = "PM" + String.format("%03d", Integer.parseInt(query.getSingleResult()) + 1);
        } catch (NoResultException e){
            urutan = "PM001";
        }
        em.close();
        return urutan;
    }

    @Override
    public int jumlahAnggota() {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Peminjam p ORDER BY p.idPeminjam");
        List<Peminjam> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list.size();
    }

}
