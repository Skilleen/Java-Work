package assin7;

/*
 * This is a slightly modified version of the GUI program I supplied for Assignment 4.  The only change
 * is that it calls the "paint" method from the Square class.  
 * 
 * For Assignment 7, you must make changes and additions to this class and the Square class.  Please read
 * the web page for this assignment carefully for instructions about the changes you must make and the
 * restrictions about things you can't change.
 * 
 * CISC 124, Winter 2014
 * M. Lamb
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
	 /**
 	 * 
 	 * Scott Killeen
	 * March 27, 2014
	 * 10093303
	 * This creates a square game that gets the user to try to destroy squares before they stop
	 * moving and causes the user to lose points.
	 */
public class MovingShapes extends JFrame implements ActionListener {

	// A timer to "tick" every 20 milliseconds (or as close to that as the
	// system can manage).
	// If you want the program to run slower while you're debugging, just
	// increase the 20 to
	// a larger number.
	private Timer timer = new Timer(20, this);
	
	
	// Variables I added.
	private JButton stopButton = new JButton("Stop"); // Button to Stop the game
	private JButton pauseButton = new JButton("Pause"); // Button to pause the game
	private boolean isPaused = false; // Boolean to check if the game is paused
	private int score = 0;// Score count
	private String getName = "User";// For getting the users name.
	private JLabel scoreLabel = new JLabel(getName + "'s Score: " + score);// Label to keep track of Score
	private Font myFont = new Font("Verdana", Font.BOLD, 16); // Font for Label
	private JTextField myText = new JTextField(15); // Text field for name
	private JLabel name = new JLabel("Enter your name!");
	private JButton okButton = new JButton("Enter"); // For entering your name

	// Number of ticks between creation of new squares. If you want fewer or
	// more shapes,
	// you can change this number.
	private static int CREATION_INTERVAL = 100; // 3 seconds

	// A list of the squares showing in the window. We'll be talking about the
	// ArrayList class soon;
	// it's like a Python list.
	private ArrayList<Square> squareList = new ArrayList<Square>();

	// The inner area of the window -- the part that can contain squares
	// (doesn't include
	// title, menu bar, and borders)
	private MovingSquarePanel innerPanel = new MovingSquarePanel();
	private MovingSquarePanel outerPanel = new MovingSquarePanel();

	// Initial dimensions of the inner panel. The user can change the size of
	// the frame while
	// the program is running.
	private static final int INITIAL_PANEL_WIDTH = 600;
	private static final int INITIAL_PANEL_HEIGHT = 400;

	// Count of number of ticks until it's time to create a new square
	private int creationCountdown = 0;

	// initial size for squares
	private int INITIAL_SQUARE_SIZE = 20;
	// Number of pixels a square grows after each collision
	final int SIZE_INCREMENT = 10;

	// Sequence of colors for squares -- they start at the first color
	// and move to the next after each collision until they reach the last
	// private static final Color squareColors[] = {Color.GREEN, Color.BLUE,
	// Color.RED, Color.GRAY, Color.BLACK};
	private static final Color squareColors[] = { Color.GREEN, Color.BLUE,
			Color.RED, Color.GRAY, Color.BLACK };
	private static final Color INITIAL_COLOR = squareColors[0];
	private static final Color LAST_COLOR = squareColors[squareColors.length - 1];

	// Pointer to the main frame of the program (for referencing from inside
	// inner classes)
	private JFrame thisFrame = this;

	// Constructor to set up the window for the program
	public MovingShapes() {
		// set position of window in screen (better for demo videos)
		setLocation(new Point(600, 100));

		setTitle("Moving Squares");
		// Make sure program cleans itself up when the user closes the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Adding my layouts and all my Components to them.
		JOptionPane.showMessageDialog(thisFrame,
				"Welcome! Click on squares to gain points. Avoid" +
				" the black squares! To win you must reach 30 points! If you"
						+ " fall below zero, you lose!");
		JPanel bottomPanel = new JPanel(new FlowLayout());// Panel for the South
		JPanel topPanel = new JPanel(new FlowLayout()); // Panel for the North
		outerPanel.setLayout(new BorderLayout());// Border Layout
		// editing stop button
		stopButton.addActionListener(this);
		stopButton.setFocusPainted(false);
		stopButton.setBackground(Color.RED);
		stopButton.setForeground(Color.WHITE);
		outerPanel.add(stopButton, BorderLayout.WEST);
		// editing pause button
		pauseButton.addActionListener(this);
		pauseButton.setBackground(Color.YELLOW);
		pauseButton.setFocusPainted(false);
		outerPanel.add(pauseButton, BorderLayout.EAST);

		bottomPanel.add(name);
		bottomPanel.add(myText);
		okButton.addActionListener(this);
		bottomPanel.add(okButton);
		outerPanel.add(bottomPanel, BorderLayout.SOUTH);
		scoreLabel.setFont(myFont);
		topPanel.add(scoreLabel);
		outerPanel.add(topPanel, BorderLayout.NORTH);
		innerPanel.setLayout(new FlowLayout());
		outerPanel.add(innerPanel, BorderLayout.CENTER);
		add(outerPanel);
		innerPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));

		// Add a "listener" to react every time the size of the window is
		// changed.
		innerPanel.addComponentListener(new Resizer());
		innerPanel.addMouseListener(new SquareClick());
		innerPanel.setPreferredSize(new Dimension(INITIAL_PANEL_WIDTH,
				INITIAL_PANEL_HEIGHT));

		// Specify that the actionPerformed method will be called each time the
		// timer ticks
		timer.addActionListener(this);

		// Now that everything's set up, show the window on the screen and start
		// the timer
		Object[] options = { "Easy", "Normal", "Hard (gg)" };// Creating Options for difficulties.
		int choice = JOptionPane.showOptionDialog(null,
				"Easy, Normal, or Hard?", "Pick one",
				JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.DEFAULT_OPTION,
				null, options, options[1]);
		//Depending on the choice, the speed of which the squares of speed will change.
		if (choice == 0)
			CREATION_INTERVAL = 70;
		else if (choice == 1)
			CREATION_INTERVAL = 30;
		else
			CREATION_INTERVAL = 5;

		pack();
		setVisible(true);
		timer.start();
	} // end MovingShapes

	// This method is called each time the timer "ticks". It updates the
	// position of every
	// square, creates a new square if it's time, and handles collisions.
	public void actionPerformed(ActionEvent e) {
		/***** Make each square move. *****/
		// The square itself will know what direction it needs to move and what
		// to do if it
		// hits the boundary of the panel. All we need to do here is tell it to
		// move.
		for (Square square : squareList)
			square.move();

		//For the Stop Button
		if (e.getSource() == stopButton) {
			timer.stop();
			JOptionPane.showMessageDialog(thisFrame,
					"Game Stopped! Exiting Program!");
			System.exit(0);// Stop the game.
		}
		// Pausing the Game
		if (e.getSource() == pauseButton
				&& pauseButton.getText().equals("Pause")) {
			pauseButton.setText("Go!");
			isPaused = true;
			timer.stop();
		}
		// Resuming the Game
		else if (e.getSource() == pauseButton
				&& pauseButton.getText().equals("Go!")) {
			pauseButton.setText("Pause");
			isPaused = false;
			timer.start();
		}
		// Entering Name
		if (e.getSource() == okButton) {
			getName = myText.getText();
			scoreLabel.setText(getName + "'s Score: " + score);
		}
		// Search for collisions. When a pair of collide, the newer one
		// disappears and the
		// older one grows and changes color (unless it's already black)
		for (int i = 0; i < squareList.size(); i++) {
			Square squareA = squareList.get(i);
			for (int j = i + 1; j < squareList.size(); j++) {
				Square squareB = squareList.get(j);
				/* see if the two squares are have collided */
				if (Square.overlap(squareA, squareB)) {
					// Get rid of squareB
					squareList.remove(j);
					j--; // so that we won't skip checking the next square
					// squareA grows & changes to next color unless it's already
					// at the last color (black)
					squareA.grow(SIZE_INCREMENT);
					Color colorA = squareA.getColor();
					boolean found = false;
					for (int colorIndex = 0; !found
							&& colorIndex < squareColors.length - 1; colorIndex++) {
						if (colorA == squareColors[colorIndex]) {
							colorA = squareColors[colorIndex + 1];
							squareA.setColor(colorA);
							found = true;
						} // end if
					} // end for
					if (colorA == LAST_COLOR)
						squareA.stop();
				} // end if
			} // end for

		} // end for

		// If it's time to create a new square, do that, but make sure it
		// doesn't appear
		// on top of an existing square. If the screen is so full that this
		// can't be done
		// after the maximum number of tries, the program ends.
		final int MAX_TRIES = 100; // number of times we try to create a new
									// square before giving up
		if (creationCountdown == 0) {
			int tries = 0; // number of times we try to create a new square in a
							// place
			boolean success = false; // becomes true when we've successfully
										// created a new square that doesn't
										// overlap with an older one
			Square newSquare = null;
			while (!success && tries < MAX_TRIES) {
				newSquare = new Square(INITIAL_SQUARE_SIZE, INITIAL_COLOR); // square constructor picks
																			// a random direction
																			// and position
				// See if the new square overlaps any of the others
				boolean hasOverlap = false; // true if the new square overlaps
											// with an existing one
				for (int i = 0; i < squareList.size() && !hasOverlap; i++) {
					if (Square.overlap(squareList.get(i), newSquare)) {
						hasOverlap = true;
					} // end if
				} // end for
				if (!hasOverlap) {
					success = true;
					break;
				} else {
					tries++;
				} // end if
			} // end while
			if (success) {
				squareList.add(newSquare);
				creationCountdown = CREATION_INTERVAL; // re-start count until time to add another shape
			} else {
				// Could not create a new square without overlapping with
				// another: end program.
				JOptionPane.showMessageDialog(thisFrame,
						"SCREEN IS TOO FULL, YOU LOSE; Final Score: " + score);
				System.exit(0);
			} // end if
		} else {
			creationCountdown--;
		} // end if

		// Tell the inner panel to re-display its contents according to the
		// updated list of squares
		innerPanel.repaint();

	} // end actionPerformed

	// This is an inner class that specifies what should happen if the window is
	// resized.
	// Its componentResized method will be called at the start of the program
	// and then
	// each time the user re-sizes the window.
	// It's responsible for informing the square class of its new limits.
	private class Resizer extends ComponentAdapter {
		public void componentResized(ComponentEvent e) {
			// Get the new dimensions of the inner panel
			int panelWidth = innerPanel.getWidth();
			int panelHeight = innerPanel.getHeight();

			// Tell the Square class that the size of its enclosing panel has
			// changed
			Square.setPanelDimensions(panelWidth, panelHeight);

		} // end componentResized
	} // end Resizer

	// This is an inner class for the inner panel. It adds knowledge about how
	// to "paint" the
	// contents of the panel to the standard JPanel class
	private class MovingSquarePanel extends JPanel {
		// This method describes how to "paint" the squares inside the panel
		public void paintComponent(Graphics gc) {
			super.paintComponent(gc); // default panel drawing
			// draw each square in the panel
			for (Square square : squareList) {
				square.paint(gc);

			} // end for
		} // end paintComponent
	} // end class MovingSquarePanel
	
	//Checking for when a square is clicked.
	private class SquareClick extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			int x =e.getX();
			int y = e.getY();
			if (isPaused == false) {// If the game is not paused
				for (int i = 0; i < squareList.size(); i++) { // Check to see which Square was clicked.
					if (squareList.get(i).inside(x, y)) {
						// Adding points based on Color.
						if (squareList.get(i).getColor() == Color.GREEN)
							score++;
						if (squareList.get(i).getColor() == Color.BLUE)
							score += 2;
						if (squareList.get(i).getColor() == Color.RED)
							score += 3;
						if (squareList.get(i).getColor() == Color.GRAY)
							score += 4;
						if (squareList.get(i).getColor() == Color.BLACK) {
							score -= 5;
						}
						//For adding the users name.
						if (!myText.getText().equals(""))
							scoreLabel.setText(myText.getText() + "'s Score: "
									+ score);
						else
							scoreLabel.setText(getName + "'s Score: " + score);
						squareList.remove(i); //Removing the square that was clicked.
					}
				}
				//For Winning
				if (score >= 30) {
					timer.stop();
					JOptionPane.showMessageDialog(thisFrame,
							"You Win! Good Job!");
					System.exit(0);
				}
				//For Losing
				if (score < 0) {
					timer.stop();
					JOptionPane
							.showMessageDialog(thisFrame,
									"You Lose! Take time to reflect upon your failure!");
					System.exit(0);
				}
			}

		}
	}

	public static void main(String args[]) {
		// create an instance of this class and let it run
		new MovingShapes();

	} // end main

} // end class MovingShapes
