/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author fatiq
 */
public class JPanelCustom extends JPanel {

    private int kananAtas = 0;
    private int kiriAtas = 0;
    private int kananBawah = 0;
    private int kiriBawah = 0;

    public JPanelCustom() {
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        Area area = new Area(setTumpul1());
        
        if (getKananAtas() > 0)
            area.intersect(new Area(setTumpul2()));

        if (getKiriBawah() > 0)
            area.intersect(new Area(setTumpul3()));

        if (getKananBawah() > 0)
            area.intersect(new Area(setTumpul4()));

        g2d.fill(area);
        g2d.dispose();
        super.paintComponent(g);
    }

    private Shape setTumpul1() {
        int panjang = getWidth();
        int tinggi = getHeight();
        int TX = Math.min(panjang, getKananAtas());
        int TY = Math.min(tinggi, getKiriAtas());

        Area area = new Area(new RoundRectangle2D.Double(0, 0, panjang, tinggi, TX, TY));
        area.add(new Area(new Rectangle2D.Double(TX / 2, 0, panjang - TX / 2, tinggi)));
        area.add(new Area(new Rectangle2D.Double(0, TY / 2, panjang, tinggi - TY / 2)));
        return area;
    }

    private Shape setTumpul2() {
        int panjang = getWidth();
        int tinggi = getHeight();
        int TX = Math.min(panjang, getKananAtas());
        int TY = Math.min(tinggi, getKananAtas());

        Area area = new Area(new RoundRectangle2D.Double(0, 0, panjang, tinggi, TX, TY));
        area.add(new Area(new Rectangle2D.Double(0, 0, panjang - TX / 2, tinggi)));
        area.add(new Area(new Rectangle2D.Double(0, TY / 2, panjang, tinggi - TY / 2)));
        return area;
    }

    private Shape setTumpul3() {
        int panjang = getWidth();
        int tinggi = getHeight();
        int TX = Math.min(panjang, getKiriBawah());
        int TY = Math.min(tinggi, getKiriBawah());

        Area area = new Area(new RoundRectangle2D.Double(0, 0, panjang, tinggi, TX, TY));
        area.add(new Area(new Rectangle2D.Double(TX / 2, 0, panjang - TX / 2, tinggi)));
        area.add(new Area(new Rectangle2D.Double(0, 0, panjang, tinggi - TY / 2)));
        return area;
    }

    private Shape setTumpul4() {
        int panjang = getWidth();
        int tinggi = getHeight();
        int TX = Math.min(panjang, getKananBawah());
        int TY = Math.min(tinggi, getKananBawah());

        Area area = new Area(new RoundRectangle2D.Double(0, 0, panjang, tinggi, TX, TY));
        area.add(new Area(new Rectangle2D.Double(0, 0, panjang - TX / 2, tinggi)));
        area.add(new Area(new Rectangle2D.Double(0, 0, panjang, tinggi - TY / 2)));
        return area;
    }

    /**
     * @return the kananAtas
     */
    public int getKananAtas() {
        return kananAtas;
    }

    /**
     * @param kananAtas the kananAtas to set
     */
    public void setKananAtas(int kananAtas) {
        this.kananAtas = kananAtas;
    }

    /**
     * @return the kiriAtas
     */
    public int getKiriAtas() {
        return kiriAtas;
    }

    /**
     * @param kiriAtas the kiriAtas to set
     */
    public void setKiriAtas(int kiriAtas) {
        this.kiriAtas = kiriAtas;
    }

    /**
     * @return the kananBawah
     */
    public int getKananBawah() {
        return kananBawah;
    }

    /**
     * @param kananBawah the kananBawah to set
     */
    public void setKananBawah(int kananBawah) {
        this.kananBawah = kananBawah;
    }

    /**
     * @return the kiriBawah
     */
    public int getKiriBawah() {
        return kiriBawah;
    }

    /**
     * @param kiriBawah the kiriBawah to set
     */
    public void setKiriBawah(int kiriBawah) {
        this.kiriBawah = kiriBawah;
    }
}
