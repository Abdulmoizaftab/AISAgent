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
public class Camera3 {
    public static Logger logger = LogManager.getLogger(Camera3.class.getName());
     public void Open(int w, int h){
        AISAgent.Camera3 = Webcam.getWebcams().get(2);                
        AISAgent.Camera3.setViewSize(new Dimension(w,h));
        AISAgent.Camera3.open();
    }
     
      public String GetImage(String filename) throws IOException{
        
        ImageIO.write(AISAgent.Camera3.getImage(), "PNG", new File(filename));
       
        return filename;
    }
    
}
