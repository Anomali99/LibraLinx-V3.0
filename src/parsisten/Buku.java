/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parsisten;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author fatiq
 */
@Entity
@Table(name = "buku")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buku.findAll", query = "SELECT b FROM Buku b"),
    @NamedQuery(name = "Buku.findByIdBuku", query = "SELECT b FROM Buku b WHERE b.idBuku = :idBuku"),
    @NamedQuery(name = "Buku.findByJudul", query = "SELECT b FROM Buku b WHERE b.judul = :judul"),
    @NamedQuery(name = "Buku.findBySubjudul", query = "SELECT b FROM Buku b WHERE b.subjudul = :subjudul"),
    @NamedQuery(name = "Buku.findByIsbn", query = "SELECT b FROM Buku b WHERE b.isbn = :isbn"),
    @NamedQuery(name = "Buku.findByBahasa", query = "SELECT b FROM Buku b WHERE b.bahasa = :bahasa"),
    @NamedQuery(name = "Buku.findByPenerbit", query = "SELECT b FROM Buku b WHERE b.penerbit = :penerbit"),
    @NamedQuery(name = "Buku.findByTahunTerbit", query = "SELECT b FROM Buku b WHERE b.tahunTerbit = :tahunTerbit"),
    @NamedQuery(name = "Buku.findByJumlahHalaman", query = "SELECT b FROM Buku b WHERE b.jumlahHalaman = :jumlahHalaman"),
    @NamedQuery(name = "Buku.findByJumlahSalinan", query = "SELECT b FROM Buku b WHERE b.jumlahSalinan = :jumlahSalinan")})
public class Buku implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_buku")
    private String idBuku;
    @Column(name = "judul")
    private String judul;
    @Column(name = "subjudul")
    private String subjudul;
    @Column(name = "isbn")
    private String isbn;
    @Column(name = "bahasa")
    private String bahasa;
    @Column(name = "penerbit")
    private String penerbit;
    @Column(name = "tahun_terbit")
    private String tahunTerbit;
    @Column(name = "jumlah_halaman")
    private Integer jumlahHalaman;
    @Column(name = "jumlah_salinan")
    private Integer jumlahSalinan;
    @Lob
    @Column(name = "foto_sampul")
    private byte[] fotoSampul;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "buku")
    private Collection<DetailBuku> detailBukuCollection;
    
    @Transient
    private List<String> kategori = new ArrayList();
    @Transient
    private List<String> pengarang = new ArrayList();


    public Buku() {
    }

    public Buku(String idBuku) {
        this.idBuku = idBuku;
    }

    public String getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getSubjudul() {
        return subjudul;
    }

    public void setSubjudul(String subjudul) {
        this.subjudul = subjudul;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getBahasa() {
        return bahasa;
    }

    public void setBahasa(String bahasa) {
        this.bahasa = bahasa;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public String getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(String tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public Integer getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(Integer jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public Integer getJumlahSalinan() {
        return jumlahSalinan;
    }

    public void setJumlahSalinan(Integer jumlahSalinan) {
        this.jumlahSalinan = jumlahSalinan;
    }

    public byte[] getFotoSampul() {
        return fotoSampul;
    }

    public void setFotoSampul(byte[] fotoSampul) {
        this.fotoSampul = fotoSampul;
    }

    @XmlTransient
    public Collection<DetailBuku> getDetailBukuCollection() {
        return detailBukuCollection;
    }

    public void setDetailBukuCollection(Collection<DetailBuku> detailBukuCollection) {
        this.detailBukuCollection = detailBukuCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBuku != null ? idBuku.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Buku)) {
            return false;
        }
        Buku other = (Buku) object;
        if ((this.idBuku == null && other.idBuku != null) || (this.idBuku != null && !this.idBuku.equals(other.idBuku))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "parsisten.Buku[ idBuku=" + idBuku + " ]";
    }

    /**
     * @return the kategori
     */
    public List<String> getKategori() {
        return kategori;
    }

    /**
     * @param kategori the kategori to set
     */
    public void setKategori(List<String> kategori) {
        this.kategori = kategori;
    }

    /**
     * @return the pengarang
     */
    public List<String> getPengarang() {
        return pengarang;
    }

    /**
     * @param pengarang the pengarang to set
     */
    public void setPengarang(List<String> pengarang) {
        this.pengarang = pengarang;
    }
    
    public String getAllPengarang(){
        String pengarang = "";
        for(String peng : this.pengarang){
                if(pengarang.isEmpty())
                    pengarang = peng;
                else 
                       pengarang += ", " + peng;
            }
        return pengarang;
    }
    
    public String getAllKategori(){
        String kategori = "";
        for(String kat : this.kategori){
                if(kategori.isEmpty())
                    kategori = kat;
                else 
                    kategori += ", " + kat;
            }
        return kategori;
    }
    
    
}
