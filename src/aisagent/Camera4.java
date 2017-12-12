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
public class Camera4 {
  public static Logger logger = LogManager.getLogger(Camera4.class.getName());
     public void Open(int w, int h){
        AISAgent.Camera4 = Webcam.getWebcams().get(3);                
        AISAgent.Camera4.setViewSize(new Dimension(w,h));
        AISAgent.Camera4.open();
    }
      public String GetImage(String filename) throws IOException{
        
        ImageIO.write(AISAgent.Camera4.getImage(), "PNG", new File(filename));
       
        return filename;
    }
}
