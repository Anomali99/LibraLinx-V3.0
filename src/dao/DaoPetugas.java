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
import parsisten.Petugas;
import servis.ServicePetugas;

/**
 *
 * @author fatiq
 */
public class DaoPetugas implements ServicePetugas {

    @Override
    public void tambahData(Petugas mod) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        em.persist(mod);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void ubahData(Petugas mod) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        em.merge(mod);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void hapusData(String id) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Petugas p = em.find(Petugas.class, id);
        em.remove(p);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Petugas> ambilData() {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Petugas p ORDER BY p.idPetugas");
        List<Petugas> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Petugas> getByID(String id) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Petugas p WHERE LOWER(p.idPetugas) LIKE ? ORDER BY p.idPetugas");
        query.setParameter(1, "%" + id.toLowerCase() + "%");
        List<Petugas> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Petugas> getByNama(String nama) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Petugas p WHERE LOWER(p.nama) LIKE ? ORDER BY p.idPetugas");
        query.setParameter(1, "%" + nama.toLowerCase() + "%");
        List<Petugas> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public List<Petugas> getByAlamat(String alamat) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Petugas p WHERE LOWER(p.alamat) LIKE ? ORDER BY p.idPetugas");
        query.setParameter(1, "%" + alamat.toLowerCase() + "%");
        List<Petugas> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list;
    }

    @Override
    public Petugas getById(String id) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Petugas p = em.find(Petugas.class, id);
        em.getTransaction().commit();
        em.close();
        return p;
    }

    @Override
    public String nomer() {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        String jpql = "SELECT SUBSTRING(p.idPetugas, LENGTH(p.idPetugas) - 2) AS nomor "
                + "FROM Petugas p WHERE p.idPetugas LIKE 'PT%' ORDER BY p.idPetugas DESC";
        TypedQuery<String> query = em.createQuery(jpql, String.class);
        query.setMaxResults(1);
        String urutan = "";
        try{
            urutan = "PT" + String.format("%03d", Integer.parseInt(query.getSingleResult()) + 1);
        } catch (NoResultException e){
            urutan = "PT001";
        }
        em.close();
        return urutan;
    }
    
}
