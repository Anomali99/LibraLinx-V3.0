/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsisten;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author fatiq
 */
@Entity
@Table(name = "detail_skripsi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailSkripsi.findAll", query = "SELECT d FROM DetailSkripsi d"),
    @NamedQuery(name = "DetailSkripsi.findByIdSkripsi", query = "SELECT d FROM DetailSkripsi d WHERE d.detailSkripsiPK.idSkripsi = :idSkripsi"),
    @NamedQuery(name = "DetailSkripsi.findByIdPetugas", query = "SELECT d FROM DetailSkripsi d WHERE d.detailSkripsiPK.idPetugas = :idPetugas"),
    @NamedQuery(name = "DetailSkripsi.findByIdPeminjam", query = "SELECT d FROM DetailSkripsi d WHERE d.detailSkripsiPK.idPeminjam = :idPeminjam"),
    @NamedQuery(name = "DetailSkripsi.findByNoPeminjaman", query = "SELECT d FROM DetailSkripsi d WHERE d.detailSkripsiPK.noPeminjaman = :noPeminjaman"),
    @NamedQuery(name = "DetailSkripsi.findByJumlah", query = "SELECT d FROM DetailSkripsi d WHERE d.jumlah = :jumlah")})
public class DetailSkripsi implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetailSkripsiPK detailSkripsiPK;
    @Column(name = "jumlah")
    private Integer jumlah;
    @JoinColumns({
        @JoinColumn(name = "id_petugas", referencedColumnName = "id_petugas", insertable = false, updatable = false),
        @JoinColumn(name = "id_peminjam", referencedColumnName = "id_peminjam", insertable = false, updatable = false),
        @JoinColumn(name = "no_peminjaman", referencedColumnName = "no_peminjaman", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Peminjaman peminjaman;
    @JoinColumn(name = "id_skripsi", referencedColumnName = "id_skripsi", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Skripsi skripsi;

    public DetailSkripsi() {
    }

    public DetailSkripsi(DetailSkripsiPK detailSkripsiPK) {
        this.detailSkripsiPK = detailSkripsiPK;
    }

    public DetailSkripsi(String idSkripsi, String idPetugas, String idPeminjam, String noPeminjaman) {
        this.detailSkripsiPK = new DetailSkripsiPK(idSkripsi, idPetugas, idPeminjam, noPeminjaman);
    }

    public DetailSkripsiPK getDetailSkripsiPK() {
        return detailSkripsiPK;
    }

    public void setDetailSkripsiPK(DetailSkripsiPK detailSkripsiPK) {
        this.detailSkripsiPK = detailSkripsiPK;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    public Skripsi getSkripsi() {
        return skripsi;
    }

    public void setSkripsi(Skripsi skripsi) {
        this.skripsi = skripsi;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailSkripsiPK != null ? detailSkripsiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailSkripsi)) {
            return false;
        }
        DetailSkripsi other = (DetailSkripsi) object;
        if ((this.detailSkripsiPK == null && other.detailSkripsiPK != null) || (this.detailSkripsiPK != null && !this.detailSkripsiPK.equals(other.detailSkripsiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "parsisten.DetailSkripsi[ detailSkripsiPK=" + detailSkripsiPK + " ]";
    }
    
}
