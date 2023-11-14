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
import parsisten.DetailSkripsi;
import parsisten.Peminjaman;
import parsisten.Skripsi;
import servis.ServicePeminjaman;
import servis.ServiceSkripsi;

/**
 *
 * @author fatiq
 */
public class DaoSkripsi implements ServiceSkripsi {
    private ServicePeminjaman servis = new DaoPeminjaman();

    @Override
    public void tambahData(Skripsi mod) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        em.persist(mod);
        em.getTransaction().commit();
        em.close();

        if (!mod.getKategori().isEmpty()) {
            tambahKategori(mod);
        }
    }

    private void tambahKategori(Skripsi mod) {
        String sql = "INSERT INTO KATEGORI_SKRIPSI (ID_SKRIPSI, KATEGORI) VALUES (?,?)";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        for (String kategori : mod.getKategori()) {
            Query query = em.createNativeQuery(sql);
            query.setParameter(1, mod.getIdSkripsi());
            query.setParameter(2, kategori);
            query.executeUpdate();
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void ubahData(Skripsi mod) {
        String sql = "DELETE FROM KATEGORI_SKRIPSI WHERE ID_SKRIPSI = ?;";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Skripsi p = em.find(Skripsi.class, mod.getIdSkripsi());
        if (mod.getFotoSampul() == null) {
            mod.setFotoSampul(p.getFotoSampul());
        }
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, mod.getIdSkripsi());
        query.executeUpdate();
        em.merge(mod);
        em.getTransaction().commit();
        em.close();

        if (!mod.getKategori().isEmpty()) {
            tambahKategori(mod);
        }
    }

    @Override
    public void hapusData(String id) {
        String sql = "DELETE FROM KATEGORI_SKRIPSI WHERE ID_SKRIPSI = ?;";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Skripsi p = em.find(Skripsi.class, id);
        Query query = em.createNativeQuery(sql);
        query.setParameter(1, id);
        query.executeUpdate();
        em.remove(p);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Skripsi getById(String id) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Skripsi p = em.find(Skripsi.class, id);
        if (p != null) {
            p.setKategori(ambilKategori(id));
        }
        em.getTransaction().commit();
        em.close();
        return p;
    }

    @Override
    public List<Skripsi> getByID(String id) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT s FROM Skripsi s WHERE LOWER(s.idSkripsi) LIKE :idSkripsi ORDER BY s.idSkripsi");
        query.setParameter("idSkripsi", "%" + id.toLowerCase() + "%");
        List<Skripsi> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Skripsi b = list.get(i);
            b.setKategori(ambilKategori(b.getIdSkripsi()));
            list.set(i, b);
        }
        return list;
    }

    @Override
    public List<Skripsi> getByJudul(String judul) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT s FROM Skripsi s WHERE LOWER(s.judul) LIKE :judul ORDER BY s.idSkripsi");
        query.setParameter("judul", "%" + judul.toLowerCase() + "%");
        List<Skripsi> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Skripsi b = list.get(i);
            b.setKategori(ambilKategori(b.getIdSkripsi()));
            list.set(i, b);
        }
        return list;
    }

    @Override
    public List<Skripsi> getByPenulis(String penulis) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT s FROM Skripsi s WHERE LOWER(s.penulis) LIKE :penulis ORDER BY s.idSkripsi");
        query.setParameter("penulis", "%" + penulis.toLowerCase() + "%");
        List<Skripsi> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Skripsi b = list.get(i);
            b.setKategori(ambilKategori(b.getIdSkripsi()));
            list.set(i, b);
        }
        return list;
    }

    @Override
    public List<Skripsi> getByPembimbing(String pembimbing) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT s FROM Skripsi s WHERE LOWER(s.pembimbing) LIKE :pembimbing ORDER BY s.idSkripsi");
        query.setParameter("pembimbing", "%" + pembimbing.toLowerCase() + "%");
        List<Skripsi> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Skripsi b = list.get(i);
            b.setKategori(ambilKategori(b.getIdSkripsi()));
            list.set(i, b);
        }
        return list;
    }

    @Override
    public List<Skripsi> getByFakultas(String fakultas) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT s FROM Skripsi s WHERE LOWER(s.fakultas) LIKE :fakultas ORDER BY s.idSkripsi");
        query.setParameter("fakultas", "%" + fakultas.toLowerCase() + "%");
        List<Skripsi> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Skripsi b = list.get(i);
            b.setKategori(ambilKategori(b.getIdSkripsi()));
            list.set(i, b);
        }
        return list;
    }

    @Override
    public List<Skripsi> getByProdi(String prodi) {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT s FROM Skripsi s WHERE LOWER(s.programStudi) LIKE :programStudi ORDER BY s.idSkripsi");
        query.setParameter("programStudi", "%" + prodi.toLowerCase() + "%");
        List<Skripsi> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Skripsi b = list.get(i);
            b.setKategori(ambilKategori(b.getIdSkripsi()));
            list.set(i, b);
        }
        return list;
    }

    @Override
    public List<Skripsi> getByKategori(String kategori) {
        String sql = "SELECT b.* FROM Skripsi b WHERE b.id_skripsi IN "
                + "(SELECT kb.ID_SKRIPSI FROM KATEGORI_SKRIPSI kb WHERE LOWER(kb.KATEGORI) LIKE ?) "
                + "ORDER BY b.id_skripsi";
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createNativeQuery(sql, Skripsi.class);
        query.setParameter(1, "%" + kategori.toLowerCase() + "%");
        List<Skripsi> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Skripsi b = list.get(i);
            b.setKategori(ambilKategori(b.getIdSkripsi()));
            list.set(i, b);
        }
        return list;
    }

    @Override
    public List<Skripsi> ambilData() {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT s FROM Skripsi s ORDER BY s.idSkripsi");
        List<Skripsi> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        for (int i = 0; i < list.size(); i++) {
            Skripsi b = list.get(i);
            b.setKategori(ambilKategori(b.getIdSkripsi()));
            list.set(i, b);
        }
        return list;
    }

    @Override
    public String nomer() {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        String jpql = "SELECT SUBSTRING(b.idSkripsi, LENGTH(b.idSkripsi) - 2) AS nomor "
                + "FROM Skripsi b WHERE b.idSkripsi LIKE 'SK%' ORDER BY b.idSkripsi DESC";
        TypedQuery<String> query = em.createQuery(jpql, String.class);
        query.setMaxResults(1);
        Date now = new Date();
        SimpleDateFormat tanggal = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat nonformat = new SimpleDateFormat("yyMMdd");
        tanggal.format(now);
        String no = nonformat.format(now);
        String urutan = "";
        try {
            urutan = "SK" + no + String.format("%03d", Integer.parseInt(query.getSingleResult()) + 1);
        } catch (NoResultException e) {
            urutan = "SK" + no + "001";
        }
        em.close();
        return urutan;
    }

    @Override
    public int jumlahSkripsi() {
        EntityManager em = Persistence.createEntityManagerFactory("LibraLinxPU").createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT s FROM Skripsi s ORDER BY s.idSkripsi");
        List<Skripsi> list = query.getResultList();
        em.getTransaction().commit();
        em.close();
        return list.size();
    }

    private List<String> ambilKategori(String id) {
        String sql = "SELECT KATEGORI FROM KATEGORI_SKRIPSI WHERE ID_SKRIPSI = ?";
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
    public int jmlPipinjam(String id) {
        List<Peminjaman> p = servis.getBystatus("Dipinjam");
        int i = 0;
        for(Peminjaman pem : p){
            List<DetailSkripsi> ds = new ArrayList(pem.getDetailSkripsiCollection());
            for(DetailSkripsi s : ds){
                if(s.getSkripsi().getIdSkripsi().equalsIgnoreCase(id)){
                    i += s.getJumlah();
                }
            }
        }
        return i;
    }

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
        return kategori;
    }

}
