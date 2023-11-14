/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package custom;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author fatiq
 */
public class JTableCustom extends JTable{

    private int selectedRow = -1;
    private int paddingLeft = 10;
    private int paddingRight = 0;
    private int paddingTop = 0;
    private int paddingBottom = 0;
    
    public JTableCustom(){
        setShowHorizontalLines(true);
        setGridColor(new Color(230,230,230));
        setRowHeight(40);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable jtable,Object o,boolean bln,boolean bln1,int i,int i1){
                TablezHeader header = new TablezHeader(o+"");
                header.setHorizontalAlignment(JLabel.CENTER);
                return header;
            }
        });
        
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                Component com = super.getTableCellRendererComponent(table,0,isSelected,hasFocus,row,column);
                com.setBackground(Color.white);
                setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
                if(isSelected){
                    com.setForeground(new Color(15,89,140));
                    com.setBackground(new Color(204,204,204));
                }else{
                    com.setForeground(new Color(102,102,102));
                }
                
                setHorizontalAlignment(JLabel.LEFT);
                return com;
            }
        });
        
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

            ((JLabel) component).setBorder(BorderFactory.createEmptyBorder(getPaddingTop(), getPaddingLeft(), getPaddingBottom(), getPaddingRight()));

            return component;
        }
    };
        
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                int row = getSelectedRow();
                if (row == selectedRow){
                    clearSelection();
                    selectedRow = -1;
                }else{
                    selectedRow = row;
                }
            }
        });
    }
        
     public void addRow(Object[] row){
        DefaultTableModel model = (DefaultTableModel)getModel();
        model.addRow(row);
    }
    
    private class TablezHeader extends JLabel{
        public TablezHeader(String text){
            super(text);
            setOpaque(true);
            setBackground(Color.WHITE);
            setFont(new Font("sansserif",1,10));
            setBorder(new EmptyBorder(10,5,10,5));
        setRowHeight(20);
        }
        
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            g.setColor(new Color(230,230,230));
            g.drawLine(0, getHeight()-1, getWidth(),  getHeight()-1);
        }
    }
    
    public void setColumnWidth (int index, int Width){
        TableColumnModel tblModel = getColumnModel();
        tblModel.getColumn(index).setPreferredWidth(Width);
        tblModel.getColumn(index).setMaxWidth(Width);
        tblModel.getColumn(index).setMinWidth(Width);
    }

    /**
     * @return the paddingLeft
     */
    public int getPaddingLeft() {
        return paddingLeft;
    }

    /**
     * @param paddingLeft the paddingLeft to set
     */
    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
    }

    /**
     * @return the paddingRight
     */
    public int getPaddingRight() {
        return paddingRight;
    }

    /**
     * @param paddingRight the paddingRight to set
     */
    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
    }

    /**
     * @return the paddingTop
     */
    public int getPaddingTop() {
        return paddingTop;
    }

    /**
     * @param paddingTop the paddingTop to set
     */
    public void setPaddingTop(int paddingTop) {
        this.paddingTop = paddingTop;
    }

    /**
     * @return the paddingBottom
     */
    public int getPaddingBottom() {
        return paddingBottom;
    }

    /**
     * @param paddingBottom the paddingBottom to set
     */
    public void setPaddingBottom(int paddingBottom) {
        this.paddingBottom = paddingBottom;
    }
}
