/*
 * A simple image-processing program that will read images in jpg, png or gif formats,
 * flip or rotate them, and save them to files.
 * 
 * A class provided for use in Assignment 2 for CISC 124, Winter 2014.  Students must supply an
 * ArrayTransformations class to go along with it as described on the assignment's web page.
 * 
 * M. Lamb
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.filechooser.*;

public class ImageProcessing extends JFrame {
    
    // size of panel for displaying image
    private static final int IMAGE_PANEL_WIDTH = 500;
    private static final int IMAGE_PANEL_HEIGHT = 500;
    
    // GUI components
    private ImageDisplayPanel imagePanel = new ImageDisplayPanel(IMAGE_PANEL_WIDTH,IMAGE_PANEL_HEIGHT);
    private JFrame thisFrame = this; // for error messages from inner classes
    private JButton openFileButton = new JButton("open image");
    private JButton horizFlipButton = new JButton("horizontal flip");
    private JButton vertFlipButton = new JButton("vertical flip");
    private JButton rotateRightButton = new JButton("rotate right");
    private JButton rotateLeftButton = new JButton("rotate left");
    private JButton saveFileButton = new JButton("save image");
    
    // File choose for selecting files to open or save
    private JFileChooser fileChooser = new JFileChooser(new File(".")); // current directory
   
    public static void main(String args[]) {
        new ImageProcessing();
    } // end  main
    
    // constructor for ImageProcessing: sets up the GUI components
    public ImageProcessing() {
        setTitle("Image Processing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        imagePanel = new ImageDisplayPanel(IMAGE_PANEL_WIDTH,IMAGE_PANEL_HEIGHT);
        add(imagePanel); // center position
        JPanel buttonPanel = new JPanel(new GridLayout(0,1));
  
        // Action listeners linking buttons to methods to execute when
        // buttons are clicked
        openFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });
        buttonPanel.add(openFileButton);
        
        horizFlipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                horizFlip();
            } // end actionPerformed
        });
        buttonPanel.add(horizFlipButton);
        
        vertFlipButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                vertFlip();
            } // end actionPerformed
        });  
        buttonPanel.add(vertFlipButton);
        
        rotateRightButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rotateRight();
            } // end actionPerformed
        });  
        buttonPanel.add(rotateRightButton);
        
        rotateLeftButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rotateLeft();
            } // end actionPerformed
        });  
        buttonPanel.add(rotateLeftButton);
        
        saveFileButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                saveFile();
            } // end actionPerformed
        });
        buttonPanel.add(saveFileButton);
        saveFileButton.setEnabled(false); // can't save until a file is opened
        
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(buttonPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.EAST);        
        pack();
        
        setVisible(true);
    } // end constructor
    
    /*
     * Prompts the user for the name of an image file and displays its contents in the frame.  If the file
     * can't be opened, display does not change.
     */
    public void openFile() {
        // Create filter to accept only safe image types
        FileNameExtensionFilter filter = new FileNameExtensionFilter("image files", "jpg", "gif", "png");
        // Open dialog for user to locate image file
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(thisFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) { // user clicked "OK", not "cancel"
            File newFile = fileChooser.getSelectedFile();
            if (newFile == null) {
                JOptionPane.showMessageDialog(thisFrame, "internal error: null file name");
                return;
            }
            String newFileName = newFile.getPath();
            BufferedImage img = null;
            try {
                img = ImageIO.read(newFile);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(thisFrame, "Error: image \"" + newFileName + "\" could not be opened");
                return;
            }
            imagePanel.showImage(img);
            saveFileButton.setEnabled(true); // now it is OK to save the current fi
        } // end if
    } // end openFile
    
    /*
     * Prompts the user for a file name and saves the currently displayed image to that file name.  This will
     * not be called unless there is an image being displayed.
     */
    public void saveFile() {
        BufferedImage currentImage = imagePanel.getCurrentImage();
        if (currentImage == null) { // shouldn't happen, just in case
            JOptionPane.showMessageDialog(thisFrame, "Internal error: saveFile called with no current image");
            return;
        } // end if
        
        // Create filter to accept only safe image types
        FileNameExtensionFilter filter = new FileNameExtensionFilter("image files", "jpg", "gif", "png");
        // Open dialog for user to locate image file
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showSaveDialog(thisFrame);
        if (returnVal == JFileChooser.APPROVE_OPTION) { // user clicked "OK", not "cancel"
            File newFile = fileChooser.getSelectedFile();
            if (newFile == null) {
                JOptionPane.showMessageDialog(thisFrame, "internal error: null file name");
                return;
            }
            String newFileName = newFile.getPath();
            // Find the extension, assuming it's three characters long.  This program only
            // handles jpg, png and gif.
            String lowerCaseName = newFileName.toLowerCase();
            String fileType = null;
            if (lowerCaseName.endsWith(".jpg"))
                fileType = "jpg";
            else if (lowerCaseName.endsWith(".gif"))
                fileType = "gif";
            else if (lowerCaseName.endsWith(".png"))
                fileType = "png";
            else {
                JOptionPane.showMessageDialog(thisFrame, "error: unknown file type");
                return;
            } // end if
            try {
                if (!ImageIO.write(currentImage, fileType, newFile)) {
                    JOptionPane.showMessageDialog(thisFrame, "Error: error 1 writing to  \"" + newFileName);
                } // end if
            } catch (IOException e) {
                JOptionPane.showMessageDialog(thisFrame, "Error: error 2 writing to  \"" + newFileName);
            }
        } // end if
    } // end saveFile
    
    /*
     * Flips the currently showing image horizontally.
     */
    public void horizFlip() {
        BufferedImage img = imagePanel.getImage();
        if (img == null) {
            JOptionPane.showMessageDialog(thisFrame, "Error: no image currently showing");
        }
        else {
            int pixels[][] = getPixels(img);
            ArrayTransformations.horizontalFlip(pixels);
            imagePanel.showImage(imageFromPixels(pixels));
        } // end if
    } // end horizFlip
    
    /*
     * Flips the currently showing image vertically.
     */
    public void vertFlip() {
        BufferedImage img = imagePanel.getImage();
        if (img == null) {
            JOptionPane.showMessageDialog(thisFrame, "Error: no image currently showing");
        }
        else {
            int pixels[][] = getPixels(img);
            ArrayTransformations.verticalFlip(pixels);
            imagePanel.showImage(imageFromPixels(pixels));
        } // end if
    } // end vertFlip
    
    /* 
     * Rotates the currently showing image to the right (clockwise)
     */
    public void rotateRight() {
        BufferedImage img = imagePanel.getImage();
        if (img == null) {
            JOptionPane.showMessageDialog(thisFrame, "Error: no image currently showing");
        }
        else {
            int pixels[][] = getPixels(img);
            pixels = ArrayTransformations.rotateRight(pixels);
            imagePanel.showImage(imageFromPixels(pixels));
        } // end if
    } // end rotateRight
    
    /* 
     * Rotates the currently showing image to the left (counter-clockwise)
     */
    public void rotateLeft() {
        BufferedImage img = imagePanel.getImage();
        if (img == null) {
            JOptionPane.showMessageDialog(thisFrame, "Error: no image currently showing");
        }
        else {
            int pixels[][] = getPixels(img);
            pixels = ArrayTransformations.rotateLeft(pixels);
            imagePanel.showImage(imageFromPixels(pixels));
        } // end if
    } // end rotateRight
    
    // Returns a 2D array containing the pixel values of an image
    public int[][] getPixels(BufferedImage image) {
        int height = image.getHeight();
        int width = image.getWidth();
        int pixels[][] = new int[height][width];
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                try {
                    pixels[row][col] = image.getRGB(col,row);
                }
                catch (IllegalArgumentException e) {
                    // This happens if the file is in a format whose pixels can't be
                    // expressed as integers.
                    JOptionPane.showMessageDialog(thisFrame, "Error: program can't handle image of this type");
                    System.exit(1);
                } // end try/catch
            } // end for col
        } // end for row
        
        return pixels;
    } // end getPixels
    
    // Creates an image from a 2D array of pixel values
    public BufferedImage imageFromPixels(int[][] pixels) {
        int height = pixels.length;
        int width = pixels[0].length;
        BufferedImage newImage = new BufferedImage(
            width, 
            height,
            BufferedImage.TYPE_INT_RGB);
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                newImage.setRGB(col, row, pixels[row][col]);
            } // end for
        } // end for row
        return newImage;
    } // end imageFromPixels
    
        
    // inner class: panel for displaying an image
    private class ImageDisplayPanel extends JPanel {
        // The image to display
        private BufferedImage theImage;
        
        public ImageDisplayPanel(int width, int height) {
            setPreferredSize(new Dimension(width,height));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        } // end ImageDisplayPanel constructor
        
        // Ask panel to display an image
        public void showImage(BufferedImage image) {
            theImage = image;
            repaint();
        } // end image
        
        // Returns the image currently being displayed
        public BufferedImage getCurrentImage() {
            return theImage;
        } // end getCurrentImage
        
        // Returns a pointer to the image being displayed
        public BufferedImage getImage() {
            return theImage;
        } // end getImage
        
        // "Paint" the contents of the panel by showing the image
        public void paintComponent(Graphics gc) {
            super.paintComponent(gc);
            if (theImage == null) {
                return; // nothing to display
            }
                
            // if image is larger than panel, scale it down
            double imageWidth = theImage.getWidth();
            double imageHeight = theImage.getHeight();
            double horizSqueeze = 1;
            if (imageWidth > IMAGE_PANEL_WIDTH)
                horizSqueeze = IMAGE_PANEL_WIDTH / imageWidth;
            double vertSqueeze = 1;
            if (imageHeight > IMAGE_PANEL_HEIGHT)
                vertSqueeze = IMAGE_PANEL_HEIGHT / imageHeight;
                
            double totalSqueeze = Math.min(horizSqueeze, vertSqueeze);
            int displayWidth = (int) (imageWidth * totalSqueeze);
            int displayHeight = (int) (imageHeight * totalSqueeze);
                
            gc.drawImage(theImage, 0, 0, displayWidth, displayHeight, null);
        } // end gc
        
        
    } // end inner class ImageDisplayPanel
    
    public BufferedImage readImage(String fileName) {
        BufferedImage img = null;
         try {
            img = ImageIO.read(new File(fileName));
         } 
         catch (IOException e) {
            JOptionPane.showMessageDialog(thisFrame, "Error: image \"" + fileName + "\" could not be opened");
            return null;
         }
        return img;
    } // end readImage
    
   
    
    
} // end class