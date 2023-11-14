/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package custom;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

/**
 *
 * @author fatiq
 */
public class JPanelGradient extends JPanel {

    private Color CStart = Color.black;
    private Color CEnd = Color.white;
    
    public JPanelGradient() {
    }

    /**
     * @return the CStart
     */
    public Color getCStart() {
        return CStart;
    }

    /**
     * @param CStart the CStart to set
     */
    public void setCStart(Color CStart) {
        this.CStart = CStart;
    }

    /**
     * @return the CEnd
     */
    public Color getCEnd() {
        return CEnd;
    }

    /**
     * @param CEnd the CEnd to set
     */
    public void setCEnd(Color CEnd) {
        this.CEnd = CEnd;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gd = new GradientPaint(0,0, CStart, getWidth(),getHeight(), CEnd);
        g2d.setPaint(gd);
        g2d.fillRect(0, 0, getWidth(), getHeight());
    }
    
    
    
}
