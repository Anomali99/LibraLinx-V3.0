/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsisten;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author fatiq
 */
@Embeddable
public class PeminjamanPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "id_petugas")
    private String idPetugas;
    @Basic(optional = false)
    @Column(name = "id_peminjam")
    private String idPeminjam;
    @Basic(optional = false)
    @Column(name = "no_peminjaman")
    private String noPeminjaman;

    public PeminjamanPK() {
    }

    public PeminjamanPK(String idPetugas, String idPeminjam, String noPeminjaman) {
        this.idPetugas = idPetugas;
        this.idPeminjam = idPeminjam;
        this.noPeminjaman = noPeminjaman;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getIdPeminjam() {
        return idPeminjam;
    }

    public void setIdPeminjam(String idPeminjam) {
        this.idPeminjam = idPeminjam;
    }

    public String getNoPeminjaman() {
        return noPeminjaman;
    }

    public void setNoPeminjaman(String noPeminjaman) {
        this.noPeminjaman = noPeminjaman;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPetugas != null ? idPetugas.hashCode() : 0);
        hash += (idPeminjam != null ? idPeminjam.hashCode() : 0);
        hash += (noPeminjaman != null ? noPeminjaman.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeminjamanPK)) {
            return false;
        }
        PeminjamanPK other = (PeminjamanPK) object;
        if ((this.idPetugas == null && other.idPetugas != null) || (this.idPetugas != null && !this.idPetugas.equals(other.idPetugas))) {
            return false;
        }
        if ((this.idPeminjam == null && other.idPeminjam != null) || (this.idPeminjam != null && !this.idPeminjam.equals(other.idPeminjam))) {
            return false;
        }
        if ((this.noPeminjaman == null && other.noPeminjaman != null) || (this.noPeminjaman != null && !this.noPeminjaman.equals(other.noPeminjaman))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "parsisten.PeminjamanPK[ idPetugas=" + idPetugas + ", idPeminjam=" + idPeminjam + ", noPeminjaman=" + noPeminjaman + " ]";
    }
    
}
