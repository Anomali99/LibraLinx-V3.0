/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JRViewer;
import parsisten.Buku;
import parsisten.DetailBuku;
import parsisten.DetailSkripsi;
import parsisten.Peminjam;
import parsisten.Peminjaman;
import parsisten.Skripsi;
import servis.ServiceAnggota;
import servis.ServiceBuku;
import servis.ServiceLaporan;
import servis.ServicePeminjaman;
import servis.ServiceSkripsi;

/**
 *
 * @author fatiq
 */
public class DaoLaporan implements ServiceLaporan {

    private ServicePeminjaman servis = new DaoPeminjaman();
    private ServiceBuku servisB = new DaoBuku();
    private ServiceSkripsi servisS = new DaoSkripsi();
    private ServiceAnggota servisA = new DaoAnggota();

    @Override
    public JasperPrint cetakKta(String id, JPanel jp) {
        String reportPath = "src/report/Kartu.jrxml";
        List<Peminjam> result = new ArrayList();
        result.add(servisA.getById(id));
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
        JasperPrint print = null;
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            print = JasperFillManager.fillReport(jasperReport, null, dataSource);
            jp.removeAll();
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        } catch (JRException ex) {
            Logger.getLogger(DaoLaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return print;
    }

    @Override
    public JasperPrint laporanPeminjaman(String no) {
        String reportPath = "src/report/LaporanPeminjaman.jrxml";
        Peminjaman p = servis.getByNo(no);
        List<DetailBuku> db = new ArrayList(p.getDetailBukuCollection());
        List<DetailSkripsi> ds = new ArrayList(p.getDetailSkripsiCollection());
        for (DetailSkripsi s : ds) {
            DetailBuku b = new DetailBuku();
            Buku bk = new Buku();
            bk.setIdBuku(s.getSkripsi().getIdSkripsi());
            bk.setJudul(s.getSkripsi().getJudul());
            bk.setBahasa(s.getSkripsi().getBahasa());
            bk.setFotoSampul(s.getSkripsi().getFotoSampul());
            b.setBuku(bk);
            b.setJumlah(s.getJumlah());
            db.add(b);
        }
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("LibraLinxPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(db);
        HashMap<String, Object> par = new HashMap<>();
        par.put("nama", p.getPeminjam().getNama());
        par.put("tgl1", p.getTglPinjam());
        par.put("tgl2", p.getTglKembali());
        JasperPrint print = null;
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            print = JasperFillManager.fillReport(jasperReport, par, dataSource);
        } catch (JRException ex) {
            Logger.getLogger(DaoLaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
        return print;
    }

    @Override
    public JasperPrint cetakBuku(JPanel jp) {
        String reportPath = "src/report/LaporanBuku.jrxml";
        List<Buku> result = servisB.ambilData();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
        JasperPrint print = null;
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            print = JasperFillManager.fillReport(jasperReport, null, dataSource);
            jp.removeAll();
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        } catch (JRException ex) {
            Logger.getLogger(DaoLaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return print;
    }

    @Override
    public JasperPrint cetakSkripsi(JPanel jp) {
        String reportPath = "src/report/LaporanSkripsi.jrxml";
        List<Skripsi> result = servisS.ambilData();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
        JasperPrint print = null;
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            print = JasperFillManager.fillReport(jasperReport, null, dataSource);
            jp.removeAll();
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        } catch (JRException ex) {
            Logger.getLogger(DaoLaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return print;
    }

    @Override
    public JasperPrint cetakAnggota(JPanel jp) {
        String reportPath = "src/report/LaporanAnggota.jrxml";
        List<Peminjam> result = servisA.ambilData();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
        JasperPrint print = null;
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            print = JasperFillManager.fillReport(jasperReport, null, dataSource);
            jp.removeAll();
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        } catch (JRException ex) {
            Logger.getLogger(DaoLaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return print;
    }

    @Override
    public JasperPrint cetakBukuCari(JPanel jp, String s, String k) {
        String reportPath = "src/report/LaporanBukuCr.jrxml";
        List<Buku> result = new ArrayList();
        switch (k) {
            case "ID":
                result = servisB.getByID(s);
                break;
            case "ISBN":
                result = servisB.getByISBN(s);
                break;
            case "JUDUL":
                result = servisB.getByJudul(s);
                break;
            case "KATEGORI":
                result = servisB.getByKategori(s);
                break;
            case "PENGARANG":
                result = servisB.getByPengarang(s);
                break;
        }
        HashMap<String, Object> par = new HashMap<>();
        par.put("cari", s);
        par.put("berdasarkan", k);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
        JasperPrint print = null;
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            print = JasperFillManager.fillReport(jasperReport, par, dataSource);
            jp.removeAll();
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        } catch (JRException ex) {
            Logger.getLogger(DaoLaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return print;
    }

    @Override
    public JasperPrint cetakSkripsiCari(JPanel jp, String s, String k) {
        String reportPath = "src/report/LaporanSkripsiCr.jrxml";
        List<Skripsi> result = new ArrayList();
        switch (k) {
            case "ID":
                result = servisS.getByID(s);
                break;
            case "JUDUL":
                result = servisS.getByJudul(s);
                break;
            case "KATEGORI":
                result = servisS.getByKategori(s);
                break;
            case "PEMBIMBING":
                result = servisS.getByPembimbing(s);
                break;
            case "PENULIS":
                result = servisS.getByPenulis(s);
                break;
            case "PROGRAM STUDI":
                result = servisS.getByProdi(s);
                break;
            case "FAKULTAS":
                result = servisS.getByFakultas(s);
                break;
        }
        HashMap<String, Object> par = new HashMap<>();
        par.put("cari", s);
        par.put("berdasarkan", k);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
        JasperPrint print = null;
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            print = JasperFillManager.fillReport(jasperReport, par, dataSource);
            jp.removeAll();
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        } catch (JRException ex) {
            Logger.getLogger(DaoLaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return print;
    }

    @Override
    public JasperPrint cetakAnggotaCari(JPanel jp, String s, String k) {
        String reportPath = "src/report/LaporanAnggotaCr.jrxml";
        List<Peminjam> result = new ArrayList();
        switch (k) {
            case "ID":
                result = servisA.getByID(s);
                break;
            case "NAMA":
                result = servisA.getByNama(s);
                break;
            case "NIM":
                result = servisA.getByNim(s);
                break;
            case "ALAMAT":
                result = servisA.getByAlamat(s);
                break;
            case "PROGRAM STUDI":
                result = servisA.getByProdi(s);
                break;
            case "FAKULTAS":
                result = servisA.getByFakultas(s);
                break;
        }
        HashMap<String, Object> par = new HashMap<>();
        par.put("cari", s);
        par.put("berdasarkan", k);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(result);
        JasperPrint print = null;
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
            print = JasperFillManager.fillReport(jasperReport, par, dataSource);
            jp.removeAll();
            jp.setLayout(new BorderLayout());
            jp.repaint();
            jp.add(new JRViewer(print));
            jp.revalidate();
        } catch (JRException ex) {
            Logger.getLogger(DaoLaporan.class.getName()).log(Level.SEVERE, null, ex);
        }
        return print;
    }

}
