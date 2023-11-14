/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsisten;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fatiq
 */
@Entity
@Table(name = "peminjam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peminjam.findAll", query = "SELECT p FROM Peminjam p"),
    @NamedQuery(name = "Peminjam.findByIdPeminjam", query = "SELECT p FROM Peminjam p WHERE p.idPeminjam = :idPeminjam"),
    @NamedQuery(name = "Peminjam.findByNama", query = "SELECT p FROM Peminjam p WHERE p.nama = :nama"),
    @NamedQuery(name = "Peminjam.findByNim", query = "SELECT p FROM Peminjam p WHERE p.nim = :nim"),
    @NamedQuery(name = "Peminjam.findByFakultas", query = "SELECT p FROM Peminjam p WHERE p.fakultas = :fakultas"),
    @NamedQuery(name = "Peminjam.findByProgramStudi", query = "SELECT p FROM Peminjam p WHERE p.programStudi = :programStudi"),
    @NamedQuery(name = "Peminjam.findBySemester", query = "SELECT p FROM Peminjam p WHERE p.semester = :semester"),
    @NamedQuery(name = "Peminjam.findByAlamat", query = "SELECT p FROM Peminjam p WHERE p.alamat = :alamat"),
    @NamedQuery(name = "Peminjam.findByTelephone", query = "SELECT p FROM Peminjam p WHERE p.telephone = :telephone"),
    @NamedQuery(name = "Peminjam.findByEmail", query = "SELECT p FROM Peminjam p WHERE p.email = :email")})
public class Peminjam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_peminjam")
    private String idPeminjam;
    @Column(name = "nama")
    private String nama;
    @Column(name = "nim")
    private String nim;
    @Column(name = "fakultas")
    private String fakultas;
    @Column(name = "program_studi")
    private String programStudi;
    @Column(name = "semester")
    private Integer semester;
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "email")
    private String email;
    @Lob
    @Column(name = "foto")
    private byte[] foto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "peminjam")
    private Collection<Peminjaman> peminjamanCollection;

    public Peminjam() {
    }

    public Peminjam(String idPeminjam) {
        this.idPeminjam = idPeminjam;
    }

    public String getIdPeminjam() {
        return idPeminjam;
    }

    public void setIdPeminjam(String idPeminjam) {
        this.idPeminjam = idPeminjam;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getFakultas() {
        return fakultas;
    }

    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    public String getProgramStudi() {
        return programStudi;
    }

    public void setProgramStudi(String programStudi) {
        this.programStudi = programStudi;
    }

    public Integer getSemester() {
        return semester;
    }

    public void setSemester(Integer semester) {
        this.semester = semester;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    @XmlTransient
    public Collection<Peminjaman> getPeminjamanCollection() {
        return peminjamanCollection;
    }

    public void setPeminjamanCollection(Collection<Peminjaman> peminjamanCollection) {
        this.peminjamanCollection = peminjamanCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeminjam != null ? idPeminjam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Peminjam)) {
            return false;
        }
        Peminjam other = (Peminjam) object;
        if ((this.idPeminjam == null && other.idPeminjam != null) || (this.idPeminjam != null && !this.idPeminjam.equals(other.idPeminjam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "parsisten.Peminjam[ idPeminjam=" + idPeminjam + " ]";
    }
    
}
