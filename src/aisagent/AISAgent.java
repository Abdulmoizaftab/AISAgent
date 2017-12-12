/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aisagent;

import com.github.sarxos.webcam.Webcam;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 *
 * @author aaftab
 */
public class AISAgent {


    
    public static Logger logger = LogManager.getLogger(AISAgent.class.getName());
    public static Webcam Camera1=null;
    public static Webcam Camera2=null;
    public static Webcam Camera3=null;
    public static Webcam Camera4=null;
   
    
    public static void main(String[] args) throws Exception{
        // TODO code application logic here
             

        
        try{
    Camera1 cam1=new Camera1();
    Camera2 cam2=new Camera2();
    Camera3 cam3=new Camera3();
    Camera4 cam4=new Camera4();
    CompressImage cmp=new CompressImage();
    WaterMark wm=new WaterMark();
        
         //Handler fh=null;

         File TempDir = new File("./Temp");
         double FileSizeold=0;
         double FileSizenew=0;
         int counter=0;
         String temp="";
         String Match="";
         String EventNo="";
         boolean runprime=true;
         String CardNo="XXXX";        
         int NoOfCameras=1;   
         float ImageQuality=0.4f;
          String ImageStart1=null;
          String ImageStart2=null;
          String ImageStart3=null;
          String ImageStart4=null;
          String s1=null;
          int imwidth=640;
          int imhieght=480;
          int trxncounter=0;
           
          
          try {
                         
                         if (!TempDir.exists()){
                        TempDir.mkdirs();
                         }
                        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { 
            public void uncaughtException(Thread t, Throwable e) { 
              
                logger.error("Error: UncaughtException: ",e);
            }
        });
                       
              
                    } catch (SecurityException e) {
                        e.printStackTrace();
                        logger.error("Error:",e);
                    } 
          logger.info("Application Started");
          
          
         while(runprime!=false){  
         
          ArrayList<String> Eventlist = new ArrayList<String>();
           ArrayList<String> Regexlist = new ArrayList<String>();
           ArrayList<String> Camlist = new ArrayList<String>();
                   
          int camdetected= Webcam.getWebcams().size();
          System.out.println("No. of Cameras detected="+camdetected);
          logger.info("No. of Cameras detected="+camdetected);
          System.out.println("Names="+Webcam.getWebcams());
          logger.info("Names="+Webcam.getWebcams());
          
                    

          
                    String ImagePath=null;
                    String JournalPath=null;
                    String DeviceID=null;
                    String ImagePath1=null;
                     
                    
                    
                  DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                    Calendar cal = Calendar.getInstance();
           
                    Date datetemp= cal.getTime();
                    
                    String Date=dateFormat.format(datetemp);
                   // Date="20161207";
                   System.out.println("Today's Date="+Date);
                   logger.info("Today's Date="+Date);
                 
          
          
             boolean run= true;        
                

ImagePath=WinRegistry.readString (WinRegistry.HKEY_LOCAL_MACHINE,"SOFTWARE\\AIS","ImagePath"); 
JournalPath=WinRegistry.readString (WinRegistry.HKEY_LOCAL_MACHINE,"SOFTWARE\\AIS","JournalPath");
DeviceID=WinRegistry.readString (WinRegistry.HKEY_LOCAL_MACHINE,"SOFTWARE\\AIS","ATMID");
try{
NoOfCameras=Integer.parseInt(WinRegistry.readString (WinRegistry.HKEY_LOCAL_MACHINE,"SOFTWARE\\AIS","NoOfCameras"));
trxncounter=Integer.parseInt(WinRegistry.readString (WinRegistry.HKEY_LOCAL_MACHINE,"SOFTWARE\\AIS","TrxnCounter"));
ImageQuality=Float.parseFloat(WinRegistry.readString (WinRegistry.HKEY_LOCAL_MACHINE,"SOFTWARE\\AIS","ImageQuality"));
imwidth=Integer.parseInt(WinRegistry.readString (WinRegistry.HKEY_LOCAL_MACHINE,"SOFTWARE\\AIS","ImageWidth"));
imhieght=Integer.parseInt(WinRegistry.readString (WinRegistry.HKEY_LOCAL_MACHINE,"SOFTWARE\\AIS","ImageHieght"));
}
catch (NumberFormatException ex) {
            
          logger.error("Error:", ex);
          NoOfCameras=1;   
          ImageQuality=0.4f;
          imwidth=640;
          imhieght=480;
                  trxncounter=0;
        }
System.out.println("No. of Cameras to work with="+NoOfCameras);
System.out.println("Image Quality set="+ImageQuality);
  File outDir = new File(ImagePath);
                       if (!outDir.exists()){
                        outDir.mkdirs();
                       }

                        if(NoOfCameras>0){                       
                           if(Camera1==null){
                            cam1.Open(imwidth,imhieght);
                            System.out.println("Camera 1 Opened ("+imwidth+"x"+imhieght+")");
                            logger.info("Camera 1 Opened ("+imwidth+"x"+imhieght+")");
                        }                       
                        }
                        if(NoOfCameras>1){                       
                            if(Camera2==null){
                            cam2.Open(imwidth,imhieght);
                            System.out.println("Camera 2 Opened ("+imwidth+"x"+imhieght+")");
                            logger.info("Camera 2 Opened ("+imwidth+"x"+imhieght+")");
                        }                       
                        }
                        if(NoOfCameras>2){                       
                            if(Camera3==null){
                            cam3.Open(imwidth,imhieght);
                            
                            System.out.println("Camera 3 Opened ("+imwidth+"x"+imhieght+")");
                            logger.info("Camera 3 Opened ("+imwidth+"x"+imhieght+")");
                        }                       
                        }
                        if(NoOfCameras>3){                       
                            if(Camera4==null){
                            cam4.Open(imwidth,imhieght);
                            System.out.println("Camera 4 Opened ("+imwidth+"x"+imhieght+")");
                            logger.info("Camera 4 Opened ("+imwidth+"x"+imhieght+")");
                        }                      
                        }
                        
                        
                        
                        FileReader fr1=null;
                       
                            File filter=new File("filter.ini");
                  
                            fr1 = new FileReader(filter);
                            BufferedReader br1 = new BufferedReader(fr1);
             try {
                 while(br1.ready()){
                     String l=br1.readLine();
                     if(l.startsWith("8")){
                       String part[] = l.split(":");
                       
                       
                       temp=part[2];
                       for (int i=3;i<part.length;i++){
                         
                           temp=temp+":"+part[i];
                       }
                       // String part1[]=temp.split("=");
                        Eventlist.add(temp.substring(0,4));
                        Regexlist.add(temp.substring(5));
                        Camlist.add(part[1]);
                        temp="";
                       
                     }
                      
                 }
             } catch (IOException ex) {
              //   Logger.getLogger(AISAgent.class.getName()).log(Level.SEVERE, null, ex);
                 logger.error( "Error:",ex);
             }
                           
                         System.out.println("Event List:"+ Eventlist);
                         logger.info("Event List:"+ Eventlist);
                        System.out.println("Regex List:"+Regexlist);
                        logger.info("Regex List:"+Regexlist);
                        System.out.println("Event Cam List:"+Camlist);
                        logger.info("Event Cam List:"+Camlist);
                        
                        FileReader fr=null;
                        
                            File file=new File(""+JournalPath+"\\"+Date+".jrn");
                            if(!file.exists()){
                                System.out.println(""+JournalPath+"\\"+Date+".jrn not found");
                                logger.info(""+JournalPath+"\\"+Date+".jrn not found");
                                 BufferedWriter writer = null;
            writer= new BufferedWriter(new FileWriter(""+JournalPath+"\\"+Date+".jrn",true));
            logger.info(""+JournalPath+"\\"+Date+".jrn made");
            
            
                              
                              
                             continue;   
                           // break;
                            }
                            // System.out.println("File No."+(j+1));
                            FileSizeold = file.length();
                            System.out.println(FileSizeold);
                            
                            fr = new FileReader(file);
                            BufferedReader br = new BufferedReader(fr);
                            String s;
                            
                            LineNumberReader lnr = new LineNumberReader(fr);
                            
                            int linenumber = 0;
                            
                            while (lnr.readLine() != null){
                                linenumber++;
                            }
                            
                            System.out.println("Total number of lines : " + linenumber);
                            
                            lnr.close();
                            //Secondary loop starts here
                            while(run!=false){   
                                   
                                    
                                    Calendar cal1 = Calendar.getInstance();
                                    Date datetemp1= cal1.getTime();
                                    String Date1=dateFormat.format(datetemp1);
                                  
                                    if(!Date1.equals(Date)){
                                    run=false;
                                    }
                            
                            FileSizenew=file.length();
                            System.out.println("EJ Size= "+FileSizenew+" B");
                            
                              if (FileSizenew>FileSizeold){
                               fr = new FileReader(file);
                            
                              System.out.println("EJ Edited");
                               br = new BufferedReader(fr);
                               ImagePath1=ImagePath+"\\"+DeviceID+"\\"+Date;
                               File outDir1 = new File(ImagePath1);
                       if (!outDir1.exists()){
                        outDir1.mkdirs();
                       }
                               
                              while (br.ready())
                              {
                                  s=br.readLine();
                                  counter=counter+1;
                            if (counter==linenumber+1){
                    
                            linenumber=linenumber+1;
                            System.out.println(s);
                           if(s.contains("TRANSACTION START")||s.contains("transaction start")){
                           trxncounter=trxncounter+1;
                           

                WinRegistry.writeStringValue(WinRegistry.HKEY_LOCAL_MACHINE,"SOFTWARE\\AIS","TrxnCounter",trxncounter+"");
            
         

        
		
                           EventNo="1000";
                           s1=s;
                           
                            if(NoOfCameras>0){                       
                           ImageStart1=cam1.GetImage(TempDir+"\\ImageStart1.png"); 
                        System.out.println(ImageStart1);     
                        }
                        if(NoOfCameras>1){                       
                           ImageStart2=cam2.GetImage(TempDir+"\\ImageStart2.png"); 
                        System.out.println(ImageStart2);                       
                        }
                        if(NoOfCameras>2){                       
                            ImageStart3=cam3.GetImage(TempDir+"\\ImageStart3.png"); 
                        System.out.println(ImageStart3);         
                        }
                        if(NoOfCameras>3){                       
                           ImageStart4=cam4.GetImage(TempDir+"\\ImageStart4.png"); 
                        System.out.println(ImageStart4);                   
                        }
                           
                                                 
                           }
                           
                           if(s.contains("TRACK 2 DATA")){
                           CardNo=MatchRegex(s, "....$");
                           DateFormat timeFormat1 = new SimpleDateFormat("HHmmssSSS");
                           String trxnco=String.format("%04d", trxncounter);
//                       CardNo=String.format("%04d", CardNo);
                    EventNo="1000";
                    if(NoOfCameras>0){                       
                                   Calendar cal2 = Calendar.getInstance();
                    Date timetemp1= cal2.getTime();
                    String Time=timeFormat1.format(timetemp1);
                        File input = new File(ImageStart1);
                    BufferedImage image = ImageIO.read(input);
                     wm.write(image, s1, "Camera 1");
                     String Filename=ImagePath1+"\\"+Time+"_"+CardNo+"_"+EventNo+"_"+trxnco+"_1.jpg";
                   cmp.CompressImage(image, Filename,ImageQuality);  
                        }
                    if(NoOfCameras>1){                       
                                   Calendar cal2 = Calendar.getInstance();
                    Date timetemp1= cal2.getTime();
                    String Time=timeFormat1.format(timetemp1);
                        File input = new File(ImageStart2);
                    BufferedImage image = ImageIO.read(input);
        wm.write(image, s1, "Camera 2");
      String Filename=ImagePath1+"\\"+Time+"_"+CardNo+"_"+EventNo+"_"+trxnco+"_2.jpg";
      cmp.CompressImage(image, Filename,ImageQuality);  
                        }
                    if(NoOfCameras>2){                       
                                   Calendar cal2 = Calendar.getInstance();
                    Date timetemp1= cal2.getTime();
                    String Time=timeFormat1.format(timetemp1);
                        File input = new File(ImageStart3);
                    BufferedImage image = ImageIO.read(input);
        wm.write(image, s1, "Camera 3");
      String Filename=ImagePath1+"\\"+Time+"_"+CardNo+"_"+EventNo+"_"+trxnco+"_3.jpg";
      cmp.CompressImage(image, Filename,ImageQuality);  
                        }
                     if(NoOfCameras>3){                       
                                   Calendar cal2 = Calendar.getInstance();
                    Date timetemp1= cal2.getTime();
                    String Time=timeFormat1.format(timetemp1);
                        File input = new File(ImageStart4);
                    BufferedImage image = ImageIO.read(input);
        wm.write(image, s1, "Camera 4");
      String Filename=ImagePath1+"\\"+Time+"_"+CardNo+"_"+EventNo+"_"+trxnco+"_4.jpg";
      cmp.CompressImage(image, Filename,ImageQuality);  
                        }
                           }
                            for (int i=0;i<Regexlist.size();i++){
                                //System.out.println(i);
                            Match=MatchRegex(s,Regexlist.get(i)); 
                            //System.out.println(Eventlist.get(i)+">"+Regexlist.get(i)+">"+Match);
                            if (!Match.equals("")){
                                
                            EventNo=Eventlist.get(i);
                            String Eventcam=Camlist.get(i);
                          
                        if (Eventcam.contains("1")){
                       DateFormat timeFormat1 = new SimpleDateFormat("HHmmssSSS");
                    Calendar cal2 = Calendar.getInstance();           
                    Date timetemp1= cal2.getTime();                    
                    String Time=timeFormat1.format(timetemp1);                   
                       
                     String filename=cam1.GetImage(TempDir+"\\Image1.png"); 
                    File input = new File(filename);
                    BufferedImage image = ImageIO.read(input);
                       String trxnco=String.format("%04d", trxncounter);
                    wm.write(image, s, "Camera 1");
                    String Filename=ImagePath1+"\\"+Time+"_"+CardNo+"_"+EventNo+"_"+trxnco+"_1.jpg";
                    cmp.CompressImage(image, Filename,ImageQuality);
                        }
                        if (Eventcam.contains("2")){
                       DateFormat timeFormat1 = new SimpleDateFormat("HHmmssSSS");
                    Calendar cal2 = Calendar.getInstance();           
                    Date timetemp1= cal2.getTime();                    
                    String Time=timeFormat1.format(timetemp1);                   
                       
                     String filename=cam2.GetImage(TempDir+"\\Image2.png"); 
                    File input = new File(filename);
                    BufferedImage image = ImageIO.read(input);
                       String trxnco=String.format("%04d", trxncounter);
                    wm.write(image, s, "Camera 2");
                    String Filename=ImagePath1+"\\"+Time+"_"+CardNo+"_"+EventNo+"_"+trxnco+"_2.jpg";
                    cmp.CompressImage(image, Filename,ImageQuality);
                        }
                        if (Eventcam.contains("3")){
                       DateFormat timeFormat1 = new SimpleDateFormat("HHmmssSSS");
                    Calendar cal2 = Calendar.getInstance();           
                    Date timetemp1= cal2.getTime();                    
                    String Time=timeFormat1.format(timetemp1);                   
                       
                     String filename=cam3.GetImage(TempDir+"\\Image3.png"); 
                    File input = new File(filename);
                    BufferedImage image = ImageIO.read(input);
                       String trxnco=String.format("%04d", trxncounter);
                    wm.write(image, s, "Camera 3");
                    String Filename=ImagePath1+"\\"+Time+"_"+CardNo+"_"+EventNo+"_"+trxnco+"_3.jpg";
                    cmp.CompressImage(image, Filename,ImageQuality);
                        }
                        if (Eventcam.contains("4")){
                       DateFormat timeFormat1 = new SimpleDateFormat("HHmmssSSS");
                    Calendar cal2 = Calendar.getInstance();           
                    Date timetemp1= cal2.getTime();                    
                    String Time=timeFormat1.format(timetemp1);                   
                       
                     String filename=cam4.GetImage(TempDir+"\\Image4.png"); 
                    File input = new File(filename);
                    BufferedImage image = ImageIO.read(input);
                       String trxnco=String.format("%04d", trxncounter);
                    wm.write(image, s, "Camera 4");
                    String Filename=ImagePath1+"\\"+Time+"_"+CardNo+"_"+EventNo+"_"+trxnco+"_4.jpg";
                    cmp.CompressImage(image, Filename,ImageQuality);
                        }
                           
                            }
                            }
                         //   }
                            
                            }
                                }
                              counter=0;
                             
                            FileSizeold=FileSizenew;
                            EventNo="";
                            temp="";
                            
                              }
                             Thread.sleep(400);
                            
                            }
                        
                            
                         
                             trxncounter=0;
                          
                             WinRegistry.writeStringValue(WinRegistry.HKEY_LOCAL_MACHINE,"SOFTWARE\\AIS","TrxnCounter", trxncounter+"");
		            
                            
                 
                 System.out.println("Date changed");           
                 Thread.sleep(100);
                 //fh.close();
            
             
    
             
                    }
         
    
        
                    } catch (FileNotFoundException ex) {
            //Logger.getLogger(AISAgent.class.getName()).log(Level.SEVERE, null, ex);
            logger.error("Error:", ex);
        } catch (InterruptedException ex) {
            //Logger.getLogger(AISAgent.class.getName()).log(Level.SEVERE, null, ex);
            logger.error( "Error:",ex);
        } catch (IOException ex) {
                //  Logger.getLogger(AISAgent.class.getName()).log(Level.SEVERE, null, ex);
                  logger.error( "Error:",ex);
        } catch (IllegalArgumentException ex) {
            logger.error("Error:", ex);
        } catch (IllegalAccessException ex) {
             logger.error("Error:", ex);
        } catch (InvocationTargetException ex) {
             logger.error("Error:", ex);
        }
                    
         
    }
    
    public static String MatchRegex(String line, String pattern){
        
         Pattern r = Pattern.compile(pattern);
         String Tr="";
        

      // Now create matcher object.
      Matcher m = r.matcher(line);
      
      if (m.find( )) {
          Tr=m.group();
          
        //System.out.print("Found value: " +Tr );
         //  count++;
     //  System.out.println("Count No="+count);
        
    
      } else {
        // System.out.println("NO MATCH");
      }          
   return Tr;
        
    
    
    }
}
