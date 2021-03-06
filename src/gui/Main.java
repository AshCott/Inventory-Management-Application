package gui;

import program.Item;
import program.Store;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.HashMap;
import java.util.TreeMap;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 * Main Method for running the program
 * 
 * @author Ashley Cottrell
 *
 */
public class Main {

	// Public variables
	Store superMart = Store.getInstance();

	JFrame mainFrame;
	JLabel capitalLbl;
	JPanel controlPanel;
	JPanel topPanel;
	JButton inventoryBtn;
	JButton salesLogBtn;
	JButton inpManifestBtn;
	JButton expManifestBtn;

	DefaultTableModel model;

	boolean invImported = false;

	/**
	 * GUI setup method
	 * 
	 * @param Sets
	 *            the title of the program
	 */
	public Main(String title) {
		mainFrame = new JFrame(title);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(750, 500);

		// Set layout
		mainFrame.setLayout(new GridLayout(2, 1));

		// Create Panels
		capitalLbl = new JLabel("Capital: $100,000.00", JLabel.CENTER);
		controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(2, 1));

		// Create Buttons
		inventoryBtn = new JButton("Import Inventory");
		salesLogBtn = new JButton("Import Sales Log");
		inpManifestBtn = new JButton("Import Manifest");
		expManifestBtn = new JButton("Export Manifest");

		// Disable buttons on startup
		salesLogBtn.setEnabled(false);
		inpManifestBtn.setEnabled(false);
		expManifestBtn.setEnabled(false);

		// Set action command on button press
		inventoryBtn.setActionCommand("Inventory");
		salesLogBtn.setActionCommand("Sales_Log");
		inpManifestBtn.setActionCommand("ImportManifest");
		expManifestBtn.setActionCommand("ExportManifest");

		// Create button listener for buttons
		inventoryBtn.addActionListener(new ButtonClickListener());
		salesLogBtn.addActionListener(new ButtonClickListener());
		inpManifestBtn.addActionListener(new ButtonClickListener());
		expManifestBtn.addActionListener(new ButtonClickListener());

		// Create JTable
		model = new DefaultTableModel();
		JTable table = new JTable(model);
		JScrollPane sp = new JScrollPane(table);
		model.addColumn("Name");
		model.addColumn("Cost");
		model.addColumn("Price");
		model.addColumn("Re-Order Point");
		model.addColumn("Re-Order Amount");
		model.addColumn("Tempeture");
		model.addColumn("Inventory");

		// Add objects to frame
		mainFrame.add(topPanel);
		topPanel.add(capitalLbl);
		topPanel.add(controlPanel);

		controlPanel.add(inventoryBtn);
		controlPanel.add(expManifestBtn);
		controlPanel.add(inpManifestBtn);
		controlPanel.add(salesLogBtn);

		mainFrame.add(sp);

		mainFrame.setVisible(true);
	}

	/**
	 * Opens a window where the user can select a file
	 * 
	 * @return the address of the selected files location as a String
	 */
	private String fileChooserWindow() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV File", "csv");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(filter);
		int result = fileChooser.showOpenDialog(mainFrame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile.getAbsolutePath();

		} else {
			return "error";
		}
	}

	/**
	 * Displays and updates the data in the table on the main form.
	 */
	private void updateTable() {

		// Clears the table if values exist
		if (invImported == true) {
			int rowCount = model.getRowCount();
			for (int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
		}

		// Convert HashMap to TreeMap so inventory Items are sorted
		HashMap<String, Item> tempinventory = superMart.getInventory();
		TreeMap<String, Item> inventory = new TreeMap<String, Item>();
		inventory.putAll(tempinventory);

		// Add items as new row in table
		for (String key : inventory.keySet()) {
			if (inventory.get(key).hasTempreture()) {
				model.addRow(new Object[] { inventory.get(key).getName(), inventory.get(key).getManufactureCost(),
						inventory.get(key).getSellPrice(), inventory.get(key).getReorderPoint(),
						inventory.get(key).getReorderAmount(), inventory.get(key).getTemperature(),
						inventory.get(key).getCurrentInventory() });
			} else {
				model.addRow(new Object[] { inventory.get(key).getName(), inventory.get(key).getManufactureCost(),
						inventory.get(key).getSellPrice(), inventory.get(key).getReorderPoint(),
						inventory.get(key).getReorderAmount(), "N/A", inventory.get(key).getCurrentInventory() });
				capitalLbl.setText("Capital: " + superMart.getStoreCapital());
			}
		}
	}

	/**
	 * Waits until button has been pressed and runs the appropiate function
	 * 
	 * @author Ashley Cottrell
	 *
	 */
	private class ButtonClickListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// Get Variables
			String command = e.getActionCommand();

			// What button was pressed
			switch (command) {

			case "Inventory":
				try {
					String file = fileChooserWindow();
					if (file == "error") {
						break;
					}
					superMart.creatInventory(file);
					updateTable();
					inventoryBtn.setEnabled(false);
					expManifestBtn.setEnabled(true);
					inpManifestBtn.setEnabled(true);
					invImported = true;
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(mainFrame, e1.getMessage(), e1.getClass().getName(),
							JOptionPane.ERROR_MESSAGE);
				}

				break;

			case "Sales_Log":
				try {
					String file = fileChooserWindow();
					if (file == "error") {
						break;
					}
					superMart.importSalesLog(file);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(mainFrame, e1.getMessage(), e1.getClass().getName(),
							JOptionPane.ERROR_MESSAGE);
				}
				updateTable();
				break;

			case "ImportManifest":
				try {
					String file = fileChooserWindow();
					if (file == "error") {
						break;
					}
					superMart.importManifest(file);
					salesLogBtn.setEnabled(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(mainFrame, e1.getMessage(), e1.getClass().getName(),
							JOptionPane.ERROR_MESSAGE);

				}
				updateTable();
				break;

			case "ExportManifest":
				try {
					superMart.exportingManifest();
					JOptionPane.showMessageDialog(mainFrame, "Exported Manifest Successfully to exportManifest.csv");
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(mainFrame, e1.getMessage(), e1.getClass().getName(),
							JOptionPane.ERROR_MESSAGE);
				}
				break;

			}
		}
	}

	/**
	 * Main method for running the program
	 * 
	 * @param none
	 */
	public static void main(String[] args) {
		System.out.println(System.getProperty("java.version"));
		new Main("Inventory Management Application");
	}

}
