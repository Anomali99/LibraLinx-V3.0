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
@Table(name = "detail_buku")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailBuku.findAll", query = "SELECT d FROM DetailBuku d"),
    @NamedQuery(name = "DetailBuku.findByIdBuku", query = "SELECT d FROM DetailBuku d WHERE d.detailBukuPK.idBuku = :idBuku"),
    @NamedQuery(name = "DetailBuku.findByIdPetugas", query = "SELECT d FROM DetailBuku d WHERE d.detailBukuPK.idPetugas = :idPetugas"),
    @NamedQuery(name = "DetailBuku.findByIdPeminjam", query = "SELECT d FROM DetailBuku d WHERE d.detailBukuPK.idPeminjam = :idPeminjam"),
    @NamedQuery(name = "DetailBuku.findByNoPeminjaman", query = "SELECT d FROM DetailBuku d WHERE d.detailBukuPK.noPeminjaman = :noPeminjaman"),
    @NamedQuery(name = "DetailBuku.findByJumlah", query = "SELECT d FROM DetailBuku d WHERE d.jumlah = :jumlah")})
public class DetailBuku implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected DetailBukuPK detailBukuPK;
    @Column(name = "jumlah")
    private Integer jumlah;
    @JoinColumn(name = "id_buku", referencedColumnName = "id_buku", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Buku buku;
    @JoinColumns({
        @JoinColumn(name = "id_petugas", referencedColumnName = "id_petugas", insertable = false, updatable = false),
        @JoinColumn(name = "id_peminjam", referencedColumnName = "id_peminjam", insertable = false, updatable = false),
        @JoinColumn(name = "no_peminjaman", referencedColumnName = "no_peminjaman", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Peminjaman peminjaman;

    public DetailBuku() {
    }

    public DetailBuku(DetailBukuPK detailBukuPK) {
        this.detailBukuPK = detailBukuPK;
    }

    public DetailBuku(String idBuku, String idPetugas, String idPeminjam, String noPeminjaman) {
        this.detailBukuPK = new DetailBukuPK(idBuku, idPetugas, idPeminjam, noPeminjaman);
    }

    public DetailBukuPK getDetailBukuPK() {
        return detailBukuPK;
    }

    public void setDetailBukuPK(DetailBukuPK detailBukuPK) {
        this.detailBukuPK = detailBukuPK;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public Peminjaman getPeminjaman() {
        return peminjaman;
    }

    public void setPeminjaman(Peminjaman peminjaman) {
        this.peminjaman = peminjaman;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (detailBukuPK != null ? detailBukuPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DetailBuku)) {
            return false;
        }
        DetailBuku other = (DetailBuku) object;
        if ((this.detailBukuPK == null && other.detailBukuPK != null) || (this.detailBukuPK != null && !this.detailBukuPK.equals(other.detailBukuPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "parsisten.DetailBuku[ detailBukuPK=" + detailBukuPK + " ]";
    }
    
}
