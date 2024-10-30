package gameInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class BuildGui {
	JFrame window;
	private JPanel panel,qPanel,miniPanel; // To reference a panel
	private JLabel messageLabel; // To reference a label
	private JTextField commandField; // To reference a text field
	private JButton commitButton,quitButton,helpButton; // To reference a button
	private final int WINDOW_WIDTH = 400; // Window width
	private final int WINDOW_HEIGHT = 400;// Window height
	JLabel mapSection= new JLabel();
	ImageIcon map;
	//***************************************************************
	//Creating all the rooms
	//***************************************************************
	Room plate= new Room ("plate","Round white disk with brown... crumbs? Scattered about.");
	Room napkins=new Room("napkins","A sqaure white pillowy stack. There are some colorful smudges around.");
	Room juice=new Room("juice","An orange sticky spoltch. A fly appears to have drowned.");
	Room bagguette=new Room("bagguette","A brown fluffy area. It would go well with butter and cheese.");
	Room currentRoom=new Room();
	//***************************************************************
	public BuildGui()//This is a constructor that creates a window
	{
		//***************************************************************
		//Connect rooms 
		//***************************************************************
		currentRoom=napkins;//Set user start
		
		//Directions and Rooms in that direction to rooms
		plate.addNeighbor("south",napkins);
		plate.addNeighbor("east", bagguette);
		napkins.addNeighbor("north",plate);
		napkins.addNeighbor("east",juice);
		juice.addNeighbor("north",bagguette);
		juice.addNeighbor("west", napkins);
		bagguette.addNeighbor("west", plate);
		bagguette.addNeighbor("south",juice);
		//*****************************************************************
		//Add map Paths
		//*****************************************************************
		plate.setMapLocation("C:\\Users\\lewil\\Pictures\\DTIYS\\GameMap\\plateLocal.png");
		napkins.setMapLocation("C:\\Users\\lewil\\Pictures\\DTIYS\\GameMap\\napkinLocal.png");
		juice.setMapLocation("C:\\Users\\lewil\\Pictures\\DTIYS\\GameMap\\juiceLocal.png");
		bagguette.setMapLocation("C:\\Users\\lewil\\Pictures\\DTIYS\\GameMap\\breadLocal.png");
		//*****************************************************************
		
		window=new JFrame();
		// Set the window title.
		window.setTitle("Generic Game");

		// Set the size of the window.
		window.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		

		// Specify what happens when the close button is clicked.
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Build the panel and add it to the frame.
		//*Without this there is no panel to add in next line
		buildPanel();

		// Add the panel to the frame's content pane.
		//*Without this the window is empty
		window.add(panel);//Where panel is a global variable used across multiple functions

		// Display the window.
		window.setVisible(true);
	}
	private void buildPanel()
	{
		//Create main GUI
		panel=new JPanel();
		panel.setLayout(new GridLayout(2,2));
		
		//********************************************************
		//Quadrant 2 Panel
		//********************************************************
		// Create a section that shows commands and allows user to input and show commands
		qPanel=new JPanel();
		qPanel.setLayout(new GridLayout(2,1));
		//Creating what needs to be added to miniPanel
		messageLabel = new JLabel("Enter a command:");//Add what user needs to do
		commandField = new JTextField(10);
		commitButton = new JButton("Commit");
		commitButton.addActionListener(new submitButtonListener());//Listen for a button being clicked
		//miniPanel to make sure what user needs to do is right next to where they can enter
		miniPanel=new JPanel();
		miniPanel.add(messageLabel);
		miniPanel.add(commandField);
		miniPanel.add(commitButton);
		//Change message label and add
		messageLabel=new JLabel("Show Command");
		//Add all to quadrant 2
		qPanel.add(messageLabel);
		qPanel.add(miniPanel);
		//Add completed panel to GUI
		panel.add(qPanel);
		//***********************************************************

		//**********************************************************
		//Quadrant 1 Panel
		//**********************************************************
		qPanel=new JPanel();
		qPanel.setLayout(new BorderLayout());
		//Creating an image
		map=new ImageIcon(currentRoom.getMapPath());
		Image resizedImage=map.getImage();
		resizedImage=resizedImage.getScaledInstance(200,200,Image.SCALE_SMOOTH);
		map=new ImageIcon(resizedImage);
		mapSection= new JLabel(map);
		
		qPanel.add(mapSection,BorderLayout.CENTER);
		panel.add(qPanel);
		//**********************************************************
		
		//**********************************************************
		//Quadrant 3 Panel
		//**********************************************************
		qPanel=new JPanel();
		qPanel.setLayout(new BorderLayout());
		messageLabel=new JLabel("Inventory");
		qPanel.add(messageLabel,BorderLayout.CENTER);
		panel.add(qPanel);
		//********************************************************


		//**********************************************************
		//Quadrant 4 Panel
		//**********************************************************
		qPanel=new JPanel();
		qPanel.setLayout(new BorderLayout());
		miniPanel=new JPanel();
		quitButton=new JButton("Quit");
		quitButton.addActionListener(new QuitButtonListener());
		helpButton=new JButton("Help");
		miniPanel.add(helpButton);
		miniPanel.add(quitButton);
		//Add to layout panels
		qPanel.add(miniPanel,BorderLayout.CENTER);
		panel.add(qPanel);
		//**********************************************************
	
	}
	private class submitButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e)//What the button does when clicked
		{
	
			// Display the result.
			if(currentRoom.checkIfToken(commandField.getText())) {
				currentRoom=currentRoom.getRoom(commandField.getText());
				updateMap();
				JOptionPane.showMessageDialog(null, "Wow! You went " + commandField.getText()
				+ " to go into room " +currentRoom.getName()+"." +currentRoom.getDescription());
				
			}
			else {
			JOptionPane.showMessageDialog(null, "You entered " + commandField.getText()
					+ "! You are still in " +currentRoom.getName());
			}
			
		}
		
	}
	private class QuitButtonListener implements ActionListener
	{

		public void actionPerformed(ActionEvent e)//What the button does when clicked
		{
			System.exit(0);
		}
		
	}
	private void updateMap() {
		map=new ImageIcon(currentRoom.getMapPath());
		Image resizedImage=map.getImage();
		resizedImage=resizedImage.getScaledInstance(200,200,Image.SCALE_SMOOTH);
		map=new ImageIcon(resizedImage);
		mapSection.setIcon(map);
		
		
	}
	
	
}
