/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aisagent;


import com.github.sarxos.webcam.Webcam;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author aaftab
 */

public class Camera1 {
     public static Logger logger = LogManager.getLogger(Camera1.class.getName());
    public void Open(int w, int h){
        AISAgent.Camera1 = Webcam.getWebcams().get(0);      
        AISAgent.Camera1.setViewSize(new Dimension(w,h));
        AISAgent.Camera1.open();
    }
    public String GetImage(String filename) throws IOException{
        
        ImageIO.write(AISAgent.Camera1.getImage(), "PNG", new File(filename));
       
        return filename;
    }
    
    
}
