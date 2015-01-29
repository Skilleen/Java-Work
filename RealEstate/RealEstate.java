package assin6;

/*
 * GUI main class for using the classes required in Assignment 6.
 * CISC 124, winter 2014.
 * Use this class when you have implemented error checking in at
 * least one of your classes (a BuildingException class plus at
 * least one method in one class that throws a BuildingException).
 * 
 * If you haven't started implementing error checking yet, use the
 * alternate version of this class: RealEstateNoExceptions.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
public class RealEstate extends JFrame implements ActionListener {

    private JFrame mainFrame = this;

    // buttons for changing the sorting criteria
    private JRadioButton sortByRentButton = new JRadioButton("rent");
    private JRadioButton sortByTaxButton = new JRadioButton("tax");
    private JRadioButton sortByProfitButton = new JRadioButton("profit");
    
    // components for adding a new house
    private JTextField newBedroomsField = new JTextField(4);
    private JTextField newBathroomsField = new JTextField(4);
    private JButton newHouseButton = new JButton("add");
    
    // components for adding a new apartment building
    private JTextField newApptUnitsField = new JTextField(4);
    private JTextField newApptLocationField = new JTextField(4);
    private JRadioButton newApptElevatorButton = new JRadioButton("elevator");
    private JButton newApptButton = new JButton("add");
    
    // components for adding a new office building
    private JTextField newOfficeUnitsField = new JTextField(4);
    private JRadioButton newOfficeElevatorButton = new JRadioButton("elevator");
    private JButton newOfficeButton = new JButton("add");
    
    // components for extending a house
    private JTextField extendHouseIndexField = new JTextField(4);
    private JTextField extendHouseBedroomsField = new JTextField(4);
    private JTextField extendHouseBathroomsField = new JTextField(4);
    private JButton extendHouseButton = new JButton("extend");
    
    // components for extending an apartment or office building
    private JTextField extendMultiIndexField = new JTextField(4);
    private JTextField extendMultiUnitsField = new JTextField(4);
    private JButton extendMultiButton = new JButton("extend");
    
    // components for adding an elevator
    private JTextField addElevatorIndexField = new JTextField(4);
    private JButton addElevatorButton = new JButton("add elevator");
    
    // components for deleting a building
    private JTextField delBuildingIndexField = new JTextField(4);
    private JButton delBuildingButton = new JButton("delete");
    
    // area for displaying the list of current buildings
    private JTextArea displayArea = new JTextArea(20,100);
    
    // list of buildings currently owned
    private ArrayList<Building> buildings = new ArrayList<Building>();
    
    // Refreshes the display of buildings, sorting it according to the
    // user's choice.  Formatting assumes no more than 99 buildings,
    // that rent/tax/profit numbers all <= 7 digits (including totals),
    // and that a building's "toString" will not take more than 53 characters
    private void updateDisplay() {
        if (buildings.size() == 0) {
            displayArea.setText("You don't own any buildings");
        }
        else {
            int totalRent = 0;
            int totalTax = 0;
            int totalProfit = 0;
            if (sortByRentButton.isSelected())
                Building.sortByRent(buildings);
            else if (sortByTaxButton.isSelected())
                Building.sortByTax(buildings);
            else
                Building.sortByProfit(buildings);
                
            displayArea.setText("    Description                                           Rent      Tax        Profit\n");
            for (int i = 0; i < buildings.size(); i++) {
                Building bldg = buildings.get(i);
                displayArea.append(String.format("%2d: %-54s", (i+1), bldg));
                int rent = bldg.rent();
                int tax = bldg.tax();
                int profit = rent - tax;
                totalRent += rent;
                totalTax += tax;
                totalProfit += profit;
                displayArea.append(String.format("$%-7d ", rent));
                displayArea.append(String.format("$%-7d ", tax));
                displayArea.append(String.format("$%-7d ", profit));
                displayArea.append("\n");
            } // end for
            // display totals at end
            displayArea.append("\n");
            displayArea.append(String.format("%-58s", "totals:"));
            displayArea.append(String.format("$%-7d ", totalRent));
            displayArea.append(String.format("$%-7d ", totalTax));
            displayArea.append(String.format("$%-7d ", totalProfit));
        } // end if
    } // end updateDisplay
    
    
    // Constructor: sets up the GUI
    public RealEstate() {
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane displayPane = new JScrollPane(displayArea);
        displayPane.setBorder(BorderFactory.createLineBorder(Color.BLUE,2));
        add(displayPane, BorderLayout.CENTER);
        
        JLabel titleLabel = new JLabel("Your Real Estate Holdings", JLabel.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);
        
        JPanel buttonPanel = new JPanel(new GridLayout(0,1));   // lay out in rows     
        add(buttonPanel, BorderLayout.SOUTH);
        
        // row 1: choosing sort criterion & quit button
        JPanel row1 = new JPanel();
        row1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        buttonPanel.add(row1);
        row1.add(new JLabel("sort by: "));
        row1.add(sortByRentButton);
        row1.add(sortByTaxButton);
        row1.add(sortByProfitButton);
        ButtonGroup sortGroup = new ButtonGroup();
        sortGroup.add(sortByRentButton);
        sortGroup.add(sortByTaxButton);
        sortGroup.add(sortByProfitButton);
        sortByRentButton.setSelected(true);
        sortByRentButton.addActionListener(this);
        sortByTaxButton.addActionListener(this);
        sortByProfitButton.addActionListener(this);
        row1.add(new JLabel("               ")); // space before quit button
        
        // row 2: adding a house
        JPanel row2 = new JPanel();
        buttonPanel.add(row2);
        row2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel addHouseLabel = new JLabel("Add house: ");
        addHouseLabel.setForeground(Color.BLUE);
        row2.add(addHouseLabel);
        row2.add(new JLabel("bedrooms"));
        row2.add(newBedroomsField);
        row2.add(new JLabel("bathrooms"));
        row2.add(newBathroomsField);
        row2.add(newHouseButton);      
        newHouseButton.addActionListener(this);
        
        // row 3: adding an apartment building
        JPanel row3 = new JPanel();
        buttonPanel.add(row3);
        row3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel addApptLabel = new JLabel("Add apartment building: ");
        addApptLabel.setForeground(Color.BLUE);
        row3.add(addApptLabel);
        row3.add(new JLabel("units"));
        row3.add(newApptUnitsField);
        row3.add(new JLabel("location rating (1-5)"));
        row3.add(newApptLocationField);
        row3.add(newApptElevatorButton);
        row3.add(newApptButton);
        newApptButton.addActionListener(this);
        
        // row 4: adding an office building
        JPanel row4 = new JPanel();
        buttonPanel.add(row4);
        row4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel addOfficeLabel = new JLabel("Add office building: ");
        addOfficeLabel.setForeground(Color.BLUE);
        row4.add(addOfficeLabel);
        row4.add(new JLabel("units"));
        row4.add(newOfficeUnitsField);
        row4.add(newOfficeElevatorButton);
        row4.add(newOfficeButton);
        newOfficeButton.addActionListener(this);
        
        // row5: extending a house
        JPanel row5 = new JPanel();
        buttonPanel.add(row5);
        row5.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel extendHouseLabel = new JLabel("Extend house: ");
        extendHouseLabel.setForeground(Color.BLUE);
        row5.add(extendHouseLabel);
        row5.add(new JLabel("index of house"));
        row5.add(extendHouseIndexField);
        row5.add(new JLabel("new bedrooms"));
        row5.add(extendHouseBedroomsField);
        row5.add(new JLabel("new bathrooms"));
        row5.add(extendHouseBathroomsField);
        row5.add(extendHouseButton);
        extendHouseButton.addActionListener(this); 
        
        // row 6: extending a multi-unit building
        JPanel row6 = new JPanel();
        buttonPanel.add(row6);
        row6.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel extendMultiLabel = new JLabel("Extend apartment or office building: ");
        extendMultiLabel.setForeground(Color.BLUE);
        row6.add(extendMultiLabel);
        row6.add(new JLabel("index of building"));
        row6.add(extendMultiIndexField);
        row6.add(new JLabel("new units"));
        row6.add(extendMultiUnitsField);
        row6.add(extendMultiButton);
        extendMultiButton.addActionListener(this); 
        
        // row 7: adding an elevator
        JPanel row7 = new JPanel();
        buttonPanel.add(row7);
        row7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel addElevatorLabel = new JLabel("Add an elevator: ");
        addElevatorLabel.setForeground(Color.BLUE);
        row7.add(addElevatorLabel);
        row7.add(new JLabel("index of building"));
        row7.add(addElevatorIndexField);
        row7.add(addElevatorButton);
        addElevatorButton.addActionListener(this);
        
        // row 8: delete a building
        JPanel row8 = new JPanel();
        buttonPanel.add(row8);
        row8.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel delBuildingLabel = new JLabel("Delete a building: ");
        delBuildingLabel.setForeground(Color.BLUE);
        row8.add(delBuildingLabel);
        row8.add(new JLabel("index of building"));
        row8.add(delBuildingIndexField);
        row8.add(delBuildingButton);
        delBuildingButton.addActionListener(this);
        
        updateDisplay();
        setDefaultCloseOperation(EXIT_ON_CLOSE);        
        pack();
        setVisible(true);
    } // end constructor
    
    
    // The actionPerformed method specifies what happens when buttons are clicked
    public void actionPerformed(ActionEvent e) {
        // things inside this method could throw NumberFormatException for fields expecting
        // integer input, and BuildingExceptions for illegal parameters to constructors or
        // extension methods
        try { 
            // the button that was clicked
            Object button = e.getSource();
            
            if (button == sortByRentButton || button == sortByTaxButton || button == sortByProfitButton) {
                updateDisplay(); // will sort buildings by new criteria
            }
            else if (button == newHouseButton) {
                int bedrooms = Integer.parseInt(newBedroomsField.getText());
                int bathrooms = Integer.parseInt(newBathroomsField.getText());
                House newHouse = new House(bedrooms, bathrooms);
                buildings.add(newHouse);
                updateDisplay();
            }
            else if (button == newApptButton) {
                int units = Integer.parseInt(newApptUnitsField.getText());
                int location = Integer.parseInt(newApptLocationField.getText());
                boolean elevator = newApptElevatorButton.isSelected();
                ApartmentBuilding newAppt = new ApartmentBuilding(units, location, elevator);
                buildings.add(newAppt);
                updateDisplay();
            } 
            else if (button == newOfficeButton) {
                int units = Integer.parseInt(newOfficeUnitsField.getText());
                boolean elevator = newOfficeElevatorButton.isSelected();
                OfficeBuilding newOffice = new OfficeBuilding(units, elevator);
                buildings.add(newOffice);
                updateDisplay();
            } 
            else if (button == extendHouseButton) {
                int houseIndex = Integer.parseInt(extendHouseIndexField.getText());
                int bedrooms = Integer.parseInt(extendHouseBedroomsField.getText());
                int bathrooms = Integer.parseInt(extendHouseBathroomsField.getText());
                if (houseIndex < 1 || houseIndex > buildings.size()) {
                    JOptionPane.showMessageDialog(this, "building index " + houseIndex + " out of range");
                }
                else {
                    Building toExtend = buildings.get(houseIndex-1);
                    if (toExtend instanceof House) {
                        House theHouse = (House) toExtend;
                        theHouse.extension(bedrooms, bathrooms);  
                        updateDisplay();
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "building " + houseIndex + " is not a house");  
                    } // end if
                } // end if
            }    
            else if (button == extendMultiButton) {
                int buildingIndex = Integer.parseInt(extendMultiIndexField.getText());
                int units = Integer.parseInt(extendMultiUnitsField.getText());
                if (buildingIndex < 1 || buildingIndex > buildings.size()) {
                    JOptionPane.showMessageDialog(this, "building index " + buildingIndex + " out of range");
                }
                else {
                    Building bldg = buildings.get(buildingIndex-1);
                    if (bldg instanceof MultiUnitBuilding) {
                        MultiUnitBuilding toExtend = (MultiUnitBuilding) bldg;
                        toExtend.extension(units);  
                        updateDisplay();
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "building " + buildingIndex + " is not an apartment or office building");  
                    } // end if
                } // end if
            } 
            else if (button == addElevatorButton) {
                int buildingIndex = Integer.parseInt(addElevatorIndexField.getText());
                if (buildingIndex < 1 || buildingIndex > buildings.size()) {
                    JOptionPane.showMessageDialog(this, "building index " + buildingIndex + " out of range");
                }
                else {
                    Building bldg = buildings.get(buildingIndex-1);
                    if (bldg instanceof MultiUnitBuilding) {
                        MultiUnitBuilding toExtend = (MultiUnitBuilding) bldg;
                        toExtend.addElevator();
                        updateDisplay();
                    }
                    else {
                        JOptionPane.showMessageDialog(this, "building " + buildingIndex + " is not an apartment or office building");  
                    } // end if
                } // end if
            } 
            else if (button == delBuildingButton) {
                int buildingIndex = Integer.parseInt(delBuildingIndexField.getText());
                if (buildingIndex < 1 || buildingIndex > buildings.size()) {
                    JOptionPane.showMessageDialog(this, "building index " + buildingIndex + " out of range");
                }
                else {
                    buildings.remove(buildingIndex-1);
                    updateDisplay();
                } // end if
            } 
            else { // sanity check
                // pop-up error message
                JOptionPane.showMessageDialog(this, "Internal error: unknown action source");
            } // end if
            
            // The following is a hack so that this testing program will work even if the student's
            // classes don't throw any exceptions yet.  Java will object to the "catch" for BuildingException
            // if nothing in this try block can throw one.
            if (false) 
                throw new BuildingException();
        } // end try
        catch (NumberFormatException NumEx) {
            // pop up with message from the exception
            JOptionPane.showMessageDialog(this, "non-integer input in integer field (or field left blank)");
        } 
        catch (BuildingException BuildEx) {
            // pop up with message from the exception
            JOptionPane.showMessageDialog(this, BuildEx.getMessage());
        }
    } // end actionPerformed
    
    
    // Main method just starts up the GUI
    public static void main(String args[]) {
        new RealEstate();
    } // end main
} // end RealEstate