package gui;
import program.Store;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Main {
	
	Store superMart = Store.getInstance();

	JFrame mainFrame;
	JLabel headerLabel;
	JPanel controlPanel;
	
	public Main(String title) {
		// TODO Auto-generated constructor stub
				mainFrame = new JFrame(title);
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setSize(1000,1000);
				
				//Set layout
				mainFrame.setLayout(new GridLayout(3, 1));
				
				//Create Panels
				headerLabel = new JLabel("Main Menu",JLabel.CENTER );
				controlPanel = new JPanel();
			    controlPanel.setLayout(new FlowLayout());
			    
			    //Create Buttons
				JButton inventoryBtn = new JButton("Import Inventory");
			    JButton salesLogBtn = new JButton("Import Sales Log");
			    JButton inpManifestBtn = new JButton("Import Manifest");
			    JButton expManifestBtn = new JButton("Export Manifest");
			    
			    inventoryBtn.setActionCommand("Inventory");
			    salesLogBtn.setActionCommand("Sales_Log");
			    inpManifestBtn.setActionCommand("ImportManifest");
			    expManifestBtn.setActionCommand("ExportManifest");
			    
			    inventoryBtn.addActionListener(new ButtonClickListener());		
			    salesLogBtn.addActionListener(new ButtonClickListener());
			    inpManifestBtn.addActionListener(new ButtonClickListener());
			    expManifestBtn.addActionListener(new ButtonClickListener());
			    
			    //Create JTable
			    DefaultTableModel model = new DefaultTableModel(); 
			    JTable table = new JTable(model);
			    JScrollPane sp=new JScrollPane(table); 
			    model.addColumn("Name");
			    model.addColumn("Cost");
			    model.addColumn("Price");
			    model.addColumn("Re-Order Point");
			    model.addColumn("Re-Order Amount");
			    model.addColumn("Tempeture");
				
				//Add objects to frame
				mainFrame.add(headerLabel);
				mainFrame.add(controlPanel);
				 
			    controlPanel.add(inventoryBtn);
			    controlPanel.add(salesLogBtn);
			    controlPanel.add(inpManifestBtn);   
			    controlPanel.add(expManifestBtn);
			    
			    mainFrame.add(sp);
				
				mainFrame.setVisible(true);
	}

	private String fileChooserWindow() {
		JFileChooser fileChooser = new JFileChooser();
		int result = fileChooser.showOpenDialog(mainFrame);
		//fileChooser.setCurrentDirectory(new File(System.getProperty("C:\\Users\\LoLsA\\Desktop\\CAB302 Assignment\\CAB302Ass2Group18")));
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile.getAbsolutePath();
   		    
   		}else {
   			return "error";
   		}
	}
	
	private void updateTable() {
		
	}
	
	private class ButtonClickListener implements ActionListener{
	      public void actionPerformed(ActionEvent e) {
	         String command = e.getActionCommand();  
	         String file = fileChooserWindow();
	         
	         switch(command) {
	         case "Inventory" :
	        	 try {
					superMart.creatInventory(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	 break;
	        	 
	         case "Sales_Log" :
	        	 try {
					superMart.importSalesLog(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	 break;
	        	 
	         case "ImportManifest" :
	        	 try {
					superMart.importManifest(file);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	 break;
	        	 
	         case "ExportManifest" :
	        	 System.out.println("EXM");
	        	 break;
	         
	         }
	      }		
	   }
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main("My Application");
	}

}
