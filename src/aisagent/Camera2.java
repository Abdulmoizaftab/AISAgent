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
//import java.util.logging.Logger;
import javax.imageio.ImageIO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author aaftab
 */
public class Camera2 {
     public static Logger logger = LogManager.getLogger(Camera2.class.getName());
     public void Open(int w, int h){
        AISAgent.Camera2 = Webcam.getWebcams().get(1);                
        AISAgent.Camera2.setViewSize(new Dimension(w,h));
        AISAgent.Camera2.open();
    }
     
      public String GetImage(String filename) throws IOException{
        
        ImageIO.write(AISAgent.Camera2.getImage(), "PNG", new File(filename));
       
        return filename;
    }
    
}
