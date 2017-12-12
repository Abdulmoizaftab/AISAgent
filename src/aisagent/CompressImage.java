/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aisagent;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author aaftab
 */
public class CompressImage {
    public static Logger logger = LogManager.getLogger(CompressImage.class.getName());
    
    public void CompressImage(BufferedImage image,String Filename,float p){
        OutputStream os = null;
        try {
            File compressedImageFile = new File(Filename);
            os = new FileOutputStream(compressedImageFile);
            Iterator<ImageWriter>writers =  ImageIO.getImageWritersByFormatName("JPG");
            ImageWriter writer = (ImageWriter) writers.next();
            ImageOutputStream ios = ImageIO.createImageOutputStream(os);
            writer.setOutput(ios);
            ImageWriteParam param = writer.getDefaultWriteParam();
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality(p);
            writer.write(null, new IIOImage(image, null, null), param);
            os.close();
            ios.close();
            writer.dispose();
        } catch (FileNotFoundException ex) {
           // Logger.getLogger(CompressImage.class.getName()).log(Level.SEVERE, null, ex);
            logger.error( ex);
        } catch (IOException ex) {
           // Logger.getLogger(CompressImage.class.getName()).log(Level.SEVERE, null, ex);
            logger.error( ex);
        } finally {
            try {
                os.close();
            } catch (IOException ex) {
               // Logger.getLogger(CompressImage.class.getName()).log(Level.SEVERE, null, ex);
                logger.error( ex);
            }
        }
    
    
    
    
    }
    
}
