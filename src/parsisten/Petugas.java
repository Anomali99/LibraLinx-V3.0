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
@Table(name = "petugas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Petugas.findAll", query = "SELECT p FROM Petugas p"),
    @NamedQuery(name = "Petugas.findByIdPetugas", query = "SELECT p FROM Petugas p WHERE p.idPetugas = :idPetugas"),
    @NamedQuery(name = "Petugas.findByNama", query = "SELECT p FROM Petugas p WHERE p.nama = :nama"),
    @NamedQuery(name = "Petugas.findByEmail", query = "SELECT p FROM Petugas p WHERE p.email = :email"),
    @NamedQuery(name = "Petugas.findByAlamat", query = "SELECT p FROM Petugas p WHERE p.alamat = :alamat"),
    @NamedQuery(name = "Petugas.findByJenisKelamin", query = "SELECT p FROM Petugas p WHERE p.jenisKelamin = :jenisKelamin"),
    @NamedQuery(name = "Petugas.findByTelephone", query = "SELECT p FROM Petugas p WHERE p.telephone = :telephone"),
    @NamedQuery(name = "Petugas.findByUsername", query = "SELECT p FROM Petugas p WHERE p.username = :username"),
    @NamedQuery(name = "Petugas.findByPassword", query = "SELECT p FROM Petugas p WHERE p.password = :password"),
    @NamedQuery(name = "Petugas.findByLevel", query = "SELECT p FROM Petugas p WHERE p.level = :level")})
public class Petugas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_petugas")
    private String idPetugas;
    @Column(name = "nama")
    private String nama;
    @Column(name = "email")
    private String email;
    @Column(name = "alamat")
    private String alamat;
    @Column(name = "jenis_kelamin")
    private Character jenisKelamin;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "level")
    private String level;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "petugas")
    private Collection<Peminjaman> peminjamanCollection;

    public Petugas() {
    }

    public Petugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public Character getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(Character jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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
        hash += (idPetugas != null ? idPetugas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Petugas)) {
            return false;
        }
        Petugas other = (Petugas) object;
        if ((this.idPetugas == null && other.idPetugas != null) || (this.idPetugas != null && !this.idPetugas.equals(other.idPetugas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "parsisten.Petugas[ idPetugas=" + idPetugas + " ]";
    }
    
}
