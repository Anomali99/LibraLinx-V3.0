/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package servis;

import javax.swing.JPanel;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author fatiq
 */
public interface ServiceLaporan {
    JasperPrint cetakKta(String id, JPanel jp);
    JasperPrint cetakBuku(JPanel jp);
    JasperPrint cetakSkripsi(JPanel jp);
    JasperPrint cetakAnggota(JPanel jp);
    JasperPrint laporanPeminjaman(String no);
    JasperPrint cetakBukuCari(JPanel jp,String s, String k);
    JasperPrint cetakSkripsiCari(JPanel jp,String s, String k);
    JasperPrint cetakAnggotaCari(JPanel jp,String s, String k);
}
