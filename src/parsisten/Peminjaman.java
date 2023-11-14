/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsisten;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fatiq
 */
@Entity
@Table(name = "peminjaman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peminjaman.findAll", query = "SELECT p FROM Peminjaman p"),
    @NamedQuery(name = "Peminjaman.findByIdPetugas", query = "SELECT p FROM Peminjaman p WHERE p.peminjamanPK.idPetugas = :idPetugas"),
    @NamedQuery(name = "Peminjaman.findByIdPeminjam", query = "SELECT p FROM Peminjaman p WHERE p.peminjamanPK.idPeminjam = :idPeminjam"),
    @NamedQuery(name = "Peminjaman.findByNoPeminjaman", query = "SELECT p FROM Peminjaman p WHERE p.peminjamanPK.noPeminjaman = :noPeminjaman"),
    @NamedQuery(name = "Peminjaman.findByTanggalPinjam", query = "SELECT p FROM Peminjaman p WHERE p.tanggalPinjam = :tanggalPinjam"),
    @NamedQuery(name = "Peminjaman.findByTanggalKembali", query = "SELECT p FROM Peminjaman p WHERE p.tanggalKembali = :tanggalKembali"),
    @NamedQuery(name = "Peminjaman.findByKeterangan", query = "SELECT p FROM Peminjaman p WHERE p.keterangan = :keterangan"),
    @NamedQuery(name = "Peminjaman.findByStatus", query = "SELECT p FROM Peminjaman p WHERE p.status = :status")})
public class Peminjaman implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PeminjamanPK peminjamanPK;
    @Column(name = "tanggal_pinjam")
    @Temporal(TemporalType.DATE)
    private Date tanggalPinjam;
    @Column(name = "tanggal_kembali")
    @Temporal(TemporalType.DATE)
    private Date tanggalKembali;
    @Column(name = "keterangan")
    private String keterangan;
    @Column(name = "status")
    private String status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peminjaman")
    private Collection<DetailSkripsi> detailSkripsiCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peminjaman")
    private Collection<DetailBuku> detailBukuCollection;
    @JoinColumn(name = "id_peminjam", referencedColumnName = "id_peminjam", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Peminjam peminjam;
    @JoinColumn(name = "id_petugas", referencedColumnName = "id_petugas", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Petugas petugas;

    public Peminjaman() {
    }

    public Peminjaman(PeminjamanPK peminjamanPK) {
        this.peminjamanPK = peminjamanPK;
    }

    public Peminjaman(String idPetugas, String idPeminjam, String noPeminjaman) {
        this.peminjamanPK = new PeminjamanPK(idPetugas, idPeminjam, noPeminjaman);
    }

    public PeminjamanPK getPeminjamanPK() {
        return peminjamanPK;
    }

    public void setPeminjamanPK(PeminjamanPK peminjamanPK) {
        this.peminjamanPK = peminjamanPK;
    }

    public Date getTanggalPinjam() {
        return tanggalPinjam;
    }

    public void setTanggalPinjam(Date tanggalPinjam) {
        this.tanggalPinjam = tanggalPinjam;
    }

    public Date getTanggalKembali() {
        return tanggalKembali;
    }

    public void setTanggalKembali(Date tanggalKembali) {
        this.tanggalKembali = tanggalKembali;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<DetailSkripsi> getDetailSkripsiCollection() {
        return detailSkripsiCollection;
    }

    public void setDetailSkripsiCollection(Collection<DetailSkripsi> detailSkripsiCollection) {
        this.detailSkripsiCollection = detailSkripsiCollection;
    }

    @XmlTransient
    public Collection<DetailBuku> getDetailBukuCollection() {
        return detailBukuCollection;
    }

    public void setDetailBukuCollection(Collection<DetailBuku> detailBukuCollection) {
        this.detailBukuCollection = detailBukuCollection;
    }

    public Peminjam getPeminjam() {
        return peminjam;
    }

    public void setPeminjam(Peminjam peminjam) {
        this.peminjam = peminjam;
    }

    public Petugas getPetugas() {
        return petugas;
    }

    public void setPetugas(Petugas petugas) {
        this.petugas = petugas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (peminjamanPK != null ? peminjamanPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peminjaman)) {
            return false;
        }
        Peminjaman other = (Peminjaman) object;
        if ((this.peminjamanPK == null && other.peminjamanPK != null) || (this.peminjamanPK != null && !this.peminjamanPK.equals(other.peminjamanPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "parsisten.Peminjaman[ peminjamanPK=" + peminjamanPK + " ]";
    }
    
    public String getTglPinjam(){
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String tgl = "";
        try {
            Date date = inputDateFormat.parse(tanggalPinjam.toString());
            tgl = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tgl;
    }
    
    public String getTglKembali(){
        SimpleDateFormat inputDateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        String tgl = "";
        try {
            Date date = inputDateFormat.parse(tanggalKembali.toString());
            tgl = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tgl;
    }
    
}
