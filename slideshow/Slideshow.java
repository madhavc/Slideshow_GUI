//
// Name: Chhura, Madhav
// Homework: #1
// Due: 02/05/15
// Course: cs-245-01-w15

/*
 * This is a Slideshow program, which displays images in 800x600
 */
package slideshow;


import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author madhavchhura
 */
public class Slideshow {
    
    JFrame frame;
    JLabel label;
    ArrayList <String> arrayList = new ArrayList<>();
    String []images;
    String filename = "/Users/madhavchhura/NetBeansProjects/Slideshow/src/slideshow/SlideShow.txt";
    ImageIcon []imageIcons;
    URL url;
    Timer timer;
    boolean emptyList = false;
 

    Slideshow() {
        
        frame = new JFrame("MChhura's Slideshow");
        frame.setLayout(new BorderLayout());
        
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        
        label = new JLabel();
        getImages();
        loadImages();
        
        label.setText("Loading Image...");
        timer = new Timer(3000, new ActionListener() {
        int i = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!emptyList){
                    label.setVerticalAlignment(JLabel.CENTER);
                    label.setHorizontalAlignment(JLabel.CENTER);
                    label.setIcon(imageIcons[i]);                    
                    label.setText("");
                    i++;
                    if(i == images.length)
                       i = 0;     
                }
                else{
                    label.setText("Image List Empty");
                }
            }
            
        });
        
        frame.add(label);
        frame.setVisible(true);
        timer.start();
           
    }
    private void loadImages(){
        imageIcons = new ImageIcon[images.length];
        for(int i = 0; i < images.length; i++){
            if(!emptyList){
                if(images[i].startsWith("http")){
                    try {
                        imageIcons[i] = new ImageIcon(new URL(images[i]));
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(Slideshow.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                   imageIcons[i] = new ImageIcon(images[i]);
                }
                
            }
            
        } 
    }
    
    private void getImages(){
       try (BufferedReader br = new BufferedReader(new FileReader(filename)))
       {
            String sCurrentLine;
            if(!br.ready())
                emptyList = true;
            
            else{
                while ((sCurrentLine = br.readLine()) != null) 
                       arrayList.add(sCurrentLine);
                
            }
        } catch (IOException e) {
                e.printStackTrace();
        } 
 
        images = new String[arrayList.size()];
        images = arrayList.toArray(images);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new Slideshow();
            }
        });
    }

   
    
}
