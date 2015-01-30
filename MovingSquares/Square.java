package assin7;
/*
 * A Square object represents a square moving on a screen.  This class just records the status of the
 * square; it doesn't actually draw the square on the screen.
 * 
 * Starting code for Assignment 7.  This is my solution for Assignment 4 with an additional method:
 * the paint method to "paint" the square onto a Swing component.  This code was in the MovingShapes class
 * that I supplied with Assignment 4 because we hadn't studied Swing yet and I couldn't ask you to write it.
 * Now that we have studied Swing, I think it makes more sense in here.
 * 
 * CISC 124, Winter 2014
 * M. Lamb
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Square {

    // the four directions in which the square can move
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int UP = 2;
    public static final int DOWN = 3;


    
    // INSTANCE VARIABLES
    
    // true if the square is in motion, false if has stopped
    private boolean moving;
    
    // the current color of the square
    private Color color;

    // The size of each side of the square in pixels.  Must stay between MIN_SIZE and MAX_SIZE
    private int size;
    
    // The position of the upper left-hand corner of the square as (x,y) coordinates inside the game's window.
    private int pos_x;
    private int pos_y;
    
    // The direction in which the shape is moving
    private int direction = LEFT;
    
    // CLASS VARIABLES: dimensions of the panel in which the square is moving, defaulting to 600x400 pixels.
    // The Square class has to know this to make sure the square will "bounce" off the edges of its panel.
    private static int panelWidth = 600;
    private static int panelHeight = 400;
    
    // CONSTRUCTOR: Creates a new moving square with a specified size and color.  Puts the square in
    // a randomly-chosen location that is completely inside the panel and picks a random direction
    // in which the square will move.
    public Square(int initialSize, Color initialColor) {
        size = initialSize;
        color = initialColor;
        moving = true;
        direction = (int) (4*Math.random()); // random integer between 0 and 4
        pos_x = (int) ((panelWidth-size) * Math.random()); // random horizontal position inside panel
        pos_y = (int) ((panelHeight-size) * Math.random()); // random vertical position inside panel
        
    } // end Square
    
    
    // INSTANCE METHODS
   
    // Moves the square one pixel in its direction of motion (if it's moving).
    // If it hits the edge of the panel, it "bounces" back the other way.
    // Makes sure that it remains inside the boundaries of the panel, even if the panel
    // has decreased in size since the last call.
    public void move() {
        if (moving) {
            if (direction == LEFT)
                pos_x--;
            else if (direction == RIGHT) 
                pos_x++;
            else if (direction == UP) 
                pos_y--;
            else // direction == DOWN
                pos_y++;
        } // end if 
            
        // If the square is now partly or completely outside the panel, move it back in and
        // reverse its direction if necessary to make sure it's not heading back out again.
        if (pos_x < 0) {
            pos_x = 0;
            if (direction == LEFT)
                direction = RIGHT;
        }
        else if (pos_x > panelWidth-size) {
            pos_x = panelWidth-size;
            if (direction == RIGHT)
                direction = LEFT;
        } // end if
            
        if (pos_y < 0) {
            pos_y = 0;
            if (direction == UP)
                direction = DOWN;
        }
        else if (pos_y > panelHeight-size) {
            pos_y = panelHeight-size;
            if (direction == DOWN)
                direction = UP;
        }
    } // end move
    
   
    // makes the shape stop moving
    public void stop() {
        moving = false;
    } // end stop
    
    // Increases the size of the square by the parameter amount.
   public void grow(int increase) {
        size += increase;
    } // end changeSize
    
    // Returns true if the square is currently moving
    public boolean isMoving()   {
        return moving;
    } // end get isMovi
    
    // Returns the current horizontal position of the left side of the square
    public int getXposition() {
        return pos_x;
    } // end getXposition
    
    // Returns the current vertical position of the top of the square
    public int getYposition() {
        return pos_y;
    } // end getYposition
    
    // Returns the current size of the square
    public int getSize() {
        return size;
    } // end getSize
    
    // Returns the current color of the square
    public Color getColor() {
        return color;
    } // end getColor
    
    // Changes the current color of the square
    public void setColor(Color newColor) {
        color = newColor;
    } // end setColor    
    
    // "Paints" the square using a graphics context
    public void paint(Graphics gc) {
        gc.setColor(color);
        gc.fillRect(pos_x, pos_y, size, size);
    } // end paint
    
    // CLASS METHODS
    
    // Changes the dimensions of the panel in which this square is moving
    public static void setPanelDimensions(int newWidth, int newHeight) {
        panelWidth = newWidth;
        panelHeight = newHeight;
    } // end setPanelDimensions
    
    public boolean inside(int x,int y){
    	int left = this.getXposition();
    	int right = left +this.getSize() -1;
    	int top = this.getYposition();
    	int bottom = top +this.getSize() -1;
    	if((left<=x && x<=right) && (top<=y && y<=bottom)){
    		return true;
    	}
    		return false;
    }

	
    // Returns true if the two parameter squares touch -- in other words, if
    // there are any pixels that are in both squares.  This includes pixels that
    // are on the boundary of both squares.  The square must overlap in both the
    // horizontal and vertical dimensions
    public static boolean overlap(Square square1, Square square2) {
        int left1 = square1.pos_x;
        int right1 = left1 + square1.size -1;
        int left2 = square2.pos_x;
        int right2 = left2 + square2.size -1;
        boolean horizOverlap = (left1 <= left2 && left2 <= right1) || (left2 <= left1 && left1 <= right2);
        if (!horizOverlap)
            return false; // no need to check for vertical oferlap
            
        int top1 = square1.pos_y;
        int bot1 = top1 + square1.size -1;
        int top2 = square2.pos_y;
        int bot2 = top2 + square2.size -1;
        boolean vertOverlap = (top1 <= top2 && top2 <= bot1) || (top2 <= top1 && top1 <= bot2);
        return vertOverlap;        
    } // end overlap
    
    
} // end class Square
