/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aisagent;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author aaftab
 */
public class WaterMark {
    public static Logger logger = LogManager.getLogger(CompressImage.class.getName());
    public void write(BufferedImage image, String s1,String Camera){
    Graphics g = image.getGraphics();
    g.setFont(g.getFont().deriveFont(20f));
    g.setColor(Color.yellow);
     FontMetrics fm = g.getFontMetrics();
    g.drawString(s1, image.getWidth() - fm.stringWidth(s1) - 5, fm.getHeight());
     g.dispose();
     Graphics g1 = image.getGraphics();
    g1.setFont(g1.getFont().deriveFont(20f));
    g1.setColor(Color.green);
     FontMetrics fm1 = g1.getFontMetrics();
    g1.drawString(Camera, image.getWidth() - fm1.stringWidth(Camera) - 5, image.getHeight()-5);
    g1.dispose();
    
    
    }
    
}
