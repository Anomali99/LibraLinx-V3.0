/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tableModel;

import dao.DaoBuku;
import dao.DaoSkripsi;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import parsisten.Buku;
import parsisten.DetailBuku;
import parsisten.DetailSkripsi;
import parsisten.Peminjaman;
import parsisten.Skripsi;
import servis.ServiceBuku;
import servis.ServiceSkripsi;

/**
 *
 * @author fatiq
 */
public class TabelDetail extends AbstractTableModel{

    private Peminjaman mod = new Peminjaman();
    private List<DetailBuku> listBuku = new ArrayList();
    private List<DetailSkripsi> listSkripsi = new ArrayList();
    private final String[] headerName = {"NO", "ID Buku", "Judul", "Subjudul", "Kategori", "Bahasa", "Pengarang","Jumlah"};
    private ServiceBuku servB = new DaoBuku();
    private ServiceSkripsi servS = new DaoSkripsi();
    
    
    
    public void clear (){
        listBuku.clear();
        listSkripsi.clear();
        fireTableDataChanged();
    }
    
    public void setData(Peminjaman mod){
        clear();
        this.mod = mod;
        Collection<DetailBuku> coBuku = mod.getDetailBukuCollection();
        Collection<DetailSkripsi> coSkripsi = mod.getDetailSkripsiCollection();
        this.listBuku.addAll(new ArrayList(coBuku));
        this.listSkripsi.addAll(new ArrayList(coSkripsi));
        fireTableDataChanged();
    }
    @Override
    public int getRowCount() {
        return listBuku.size()+listSkripsi.size();
    }

    @Override
    public int getColumnCount() {
        return headerName.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0){
            return "   "+ (rowIndex +1);
        } else {
            if(rowIndex < listBuku.size()){
                switch (columnIndex-1){
                    case 0 : return listBuku.get(rowIndex).getBuku().getIdBuku();
                    case 1 : return listBuku.get(rowIndex).getBuku().getJudul();
                    case 2 : return listBuku.get(rowIndex).getBuku().getSubjudul();
                    case 3 : return servB.getAllKategori(listBuku.get(rowIndex).getBuku().getIdBuku());
                    case 4 : return listBuku.get(rowIndex).getBuku().getBahasa();
                    case 5 : return servB.getAllPengarang(listBuku.get(rowIndex).getBuku().getIdBuku());
                    case 6 : return listBuku.get(rowIndex).getJumlah();
                    default : return null;
                }
            } else {
                int newRowIndex = rowIndex-listBuku.size();
                switch (columnIndex-1){
                    case 0 : return listSkripsi.get(newRowIndex).getSkripsi().getIdSkripsi();
                    case 1 : return listSkripsi.get(newRowIndex).getSkripsi().getJudul();
                    case 2 : return null;
                    case 3 : return servS.getAllKategori(listSkripsi.get(newRowIndex).getSkripsi().getIdSkripsi());
                    case 4 : return listSkripsi.get(newRowIndex).getSkripsi().getBahasa();
                    case 5 : return listSkripsi.get(newRowIndex).getSkripsi().getPenulis();
                    case 6 : return listSkripsi.get(newRowIndex).getJumlah();
                    default : return null;
                }
            }
        }
    }
    
    
    @Override
    public String getColumnName(int column){
        if (column == 0){
            return "   "+headerName[column];
        } else {
            return headerName[column];
        }
    }
    
}
