//********************************************************************
// Forrest Collins, Manuel Puentes, David Larsen

// Oct 20, 2015					EGR 327
// Time spent: 4 hrs
// Purpose: In CreatePokemonGui.java, a user creates 
//          a "pokemon" object from a GUI, which is 
//          then stored in a local MySQL database.
//********************************************************************

import java.sql.*;
import java.text.NumberFormat;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JComboBox;

import java.awt.Color;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFormattedTextField;
import javax.swing.border.MatteBorder;
import javax.swing.text.NumberFormatter;
import javax.swing.SwingConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CreatePokemon extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	//private JFrame frame;
	private JTextField nicknameTextField;
	private JTextField attackTextField;
	private JTextField defenseTextField;
	private JTextField speedTextField;
	
	//---------------------------------------
	// MARK: - List of Pokemon to choose from
	//---------------------------------------
	private static String[] kindOfPokemonList = {"Bulbasaur", "Charmander", "Squirtle",
												 "Caterpie", "Rattata", "Ekans", "Pikachu", 
												 "Sandshrew","Clefairy", "Vulpix", "Jigglypuff",
												 "Diglett", "Meowth", "Psyduck", "Mankey",
												 "Growlithe", "Poliwag", "Abra", "Machop",
												 "Ponyta", "Seel", "Grimer", "Shellder",
												 "Drowzee", "Krabby", "Creedmon", "Koltadactyl", 
												 "Magnemite", "Cubone", "Tyrogue", "Lickitung", "Koffing", 
												 "Chansey", "Tangela", "Kangaskhan", "Horsea", "Goldeen",
												 "Staryu", "Electabuzz", "Magmar", "Ledyba",
												 "Tauros", "Magikarp", "Ditto", "Eevee",
												 "Vaporeon", "Jolteon", "Flareon", "Porygon",
												 "Snorlax", "Dratini", "Mew"};

	public CreatePokemon() {
		
		setBounds(100, 100, 800, 495);
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		
		setLayout(null);
		// Background frame hex color
		setBackground(Color.decode("#039BE5"));
		
		//---------------
		// MARK: - Labels
		//---------------
		// set the imageLabel default image to Bulbasaur
		ImageIcon imageIcon = new ImageIcon("Images/Bulbasaur.gif");
		JLabel imageLabel = new JLabel();
		imageLabel.setBounds(413, 73, 250, 250);
		imageLabel.setIcon(imageIcon);
		
		imageLabel.setDoubleBuffered(true);
	
		add(imageLabel, java.awt.BorderLayout.NORTH);
		
		
		JLabel lblPokemonCreator = new JLabel("Pokemon Creator");
		lblPokemonCreator.setFont(new Font("Avenir Next", Font.PLAIN, 23));
		lblPokemonCreator.setBounds(115, 24, 184, 32);
		lblPokemonCreator.setForeground(Color.decode("#ffffff"));
		add(lblPokemonCreator);
		
		JLabel lblNickname = new JLabel("Nickname:");
		lblNickname.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblNickname.setBounds(30, 71, 75, 21);
		lblNickname.setForeground(Color.decode("#FFF3E0"));
		add(lblNickname);
		
		JLabel lblPokemon = new JLabel("Pokemon:");
		lblPokemon.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblPokemon.setBounds(34, 108, 71, 21);
		lblPokemon.setForeground(Color.decode("#FFF3E0"));
		add(lblPokemon);
		
		JLabel lblDistribute = new JLabel("Distribute 200 stat points to Health,");
		lblDistribute.setForeground(Color.GRAY);
		lblDistribute.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblDistribute.setBounds(103, 141, 208, 18);
		lblDistribute.setForeground(Color.decode("#FFFFFF"));
		add(lblDistribute);
		
		JLabel lblAttackDefense = new JLabel("Attack, Defense, & Speed");
		lblAttackDefense.setForeground(Color.GRAY);
		lblAttackDefense.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblAttackDefense.setBounds(133, 156, 148, 18);
		lblAttackDefense.setForeground(Color.decode("#FFFFFF"));
		add(lblAttackDefense);
		
		JLabel lblExHealth = new JLabel("Ex: Health: 100, Attack: 20, Defense: 30, Speed: 50");
		lblExHealth.setForeground(Color.GRAY);
		lblExHealth.setFont(new Font("Avenir Next", Font.PLAIN, 13));
		lblExHealth.setBounds(57, 171, 301, 18);
		lblExHealth.setForeground(Color.decode("#FFFFFF"));
		add(lblExHealth);
		
		JLabel lblHealth = new JLabel("Health:");
		lblHealth.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblHealth.setBounds(54, 204, 51, 21);
		lblHealth.setForeground(Color.decode("#FFF3E0"));
		add(lblHealth);
		
		JLabel lblAttack = new JLabel("Attack:");
		lblAttack.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblAttack.setBounds(55, 242, 50, 21);
		lblAttack.setForeground(Color.decode("#FFF3E0"));
		add(lblAttack);
		
		JLabel lblDefense = new JLabel("Defense:");
		lblDefense.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblDefense.setBounds(42, 280, 63, 21);
		lblDefense.setForeground(Color.decode("#FFF3E0"));
		add(lblDefense);
		
		JLabel lblSpeed = new JLabel("Speed:");
		lblSpeed.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		lblSpeed.setBounds(54, 318, 51, 21);
		lblSpeed.setForeground(Color.decode("#FFF3E0"));
		add(lblSpeed);
		
		// Error Label
		JLabel lblErrorOnly = new JLabel("Error: Only 200 stat points allowed!");
		lblErrorOnly.setHorizontalAlignment(SwingConstants.CENTER);
		lblErrorOnly.setFont(new Font("Avenir Next", Font.BOLD, 13));
		lblErrorOnly.setBounds(45, 353, 324, 18);
		lblErrorOnly.setVisible(false);
		lblErrorOnly.setForeground(Color.decode("#FFCB05"));
		add(lblErrorOnly);
		
		// Nickname TextField
		nicknameTextField = new JTextField();
		nicknameTextField.setFont(new Font("Avenir Next", Font.BOLD, 15));
		nicknameTextField.setBounds(117, 68, 181, 26);
		add(nicknameTextField);
		nicknameTextField.setColumns(10);

		// Kind of Pokemon ComboBox
		JComboBox kindOfPokemonComboBox = new JComboBox(kindOfPokemonList);
		kindOfPokemonComboBox.setFont(new Font("Avenir Next", Font.PLAIN, 15));
		kindOfPokemonComboBox.setBounds(115, 94, 184, 50);
		add(kindOfPokemonComboBox);
		
		//-------------------------------------------------
		// MARK: - kindOfPokemonComboBox Image-change Logic
		//-------------------------------------------------
		kindOfPokemonComboBox.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if (kindOfPokemonComboBox.getSelectedItem() != null) {
		            String imagePokemon = kindOfPokemonComboBox.getSelectedItem().toString();
		            ImageIcon imageIcon = new ImageIcon("Images/" + imagePokemon + ".gif");
		            imageLabel.setIcon(imageIcon);
		        }
		    }
		});
		
		// Health TextField
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setCommitsOnValidEdit(true);
		JFormattedTextField formattedHealthTextField = new JFormattedTextField(formatter);
		formattedHealthTextField.setFont(new Font("Avenir Next", Font.BOLD, 15));
		formattedHealthTextField.setBounds(115, 201, 184, 26);
		add(formattedHealthTextField);
		
		// Attack TextField
		attackTextField = new JTextField();
		attackTextField.setFont(new Font("Avenir Next", Font.BOLD, 15));
		attackTextField.setBounds(115, 239, 184, 26);
		add(attackTextField);
		attackTextField.setColumns(10);
		
		// Defense TextField
		defenseTextField = new JTextField();
		defenseTextField.setFont(new Font("Avenir Next", Font.BOLD, 15));
		defenseTextField.setBounds(115, 277, 184, 26);
		add(defenseTextField);
		defenseTextField.setColumns(10);
		
		// Speed TextField
		speedTextField = new JTextField();
		speedTextField.setFont(new Font("Avenir Next", Font.BOLD, 15));
		speedTextField.setBounds(115, 315, 184, 26);
		add(speedTextField);
		speedTextField.setColumns(10);
		
		//----------------------------------------------------------------------
		// MARK: - Create Pokemon Button, store Pokemon object in MySQL Database
		//----------------------------------------------------------------------
		JButton btnCreate = new JButton("CREATE");
		btnCreate.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				// Initialize the MySQL connection and preparedStatement
				Connection myConnection = null;
				PreparedStatement myStatement = null;
				
				// Create variables to get the text of the textField inputs
				String nickname = nicknameTextField.getText();
				String kindOfPokemon = (String) kindOfPokemonComboBox.getSelectedItem();
				int health = Integer.parseInt(formattedHealthTextField.getText());
				int attack = Integer.parseInt(attackTextField.getText());
				int defense = Integer.parseInt(defenseTextField.getText());
				int speed = Integer.parseInt(speedTextField.getText());
				
				try {
					
					// If fields are empty, display error message
					if (nickname == null || kindOfPokemon == null || formattedHealthTextField == null ||
						attackTextField == null || defenseTextField == null || speedTextField == null){
							
						lblErrorOnly.setVisible(true);
						lblErrorOnly.setText("Please fill in all fields.");
						lblErrorOnly.setHorizontalAlignment(JLabel.CENTER);
								
					// If stats are negative, display error message
					} else if (health < 0 || attack < 0 || defense < 0 || speed < 0){
							
						lblErrorOnly.setVisible(true);
						lblErrorOnly.setText("Negative stats are not allowed.");
						lblErrorOnly.setHorizontalAlignment(JLabel.CENTER);
						
					// If stats sum is greater than 200, display error message
					} else if ((health + attack + defense + speed) > 200){
							
						lblErrorOnly.setVisible(true);
						int statPointsOver = (health + attack + defense + speed) - 200;
						lblErrorOnly.setText("Only 200 stat points allowed. Subtract " + statPointsOver); 
						lblErrorOnly.setHorizontalAlignment(JLabel.CENTER);
							
					// If stats sum is less than 200, display error message			
					} else if ((health + attack + defense + speed) < 200){
								
						int statPointsLeft = 200 - (health + attack + defense + speed);
						lblErrorOnly.setVisible(true);
						lblErrorOnly.setText("You have " + statPointsLeft + " stat points left.");
						lblErrorOnly.setHorizontalAlignment(JLabel.CENTER);
									
					} else {
						
						// Get a connection to the MySQL database
						myConnection = DriverManager.getConnection("jdbc:mysql://localhost/PokemonSchema", "student", "student");
						System.out.println("Database connection successful!");
						
						// Create a statement to "insert" a new Pokemon object in the database
						// "Pokemon" in this insert statement is the name of the table in my MySQL database
						myStatement = myConnection.prepareStatement("INSERT INTO Pokemon VALUES(?,?,?,?,?,?,?,?)");
						
						myStatement.setString(1, nickname);
						myStatement.setString(2, kindOfPokemon);
						myStatement.setInt(3, health);
						myStatement.setInt(4, attack);
						myStatement.setInt(5, defense);
						myStatement.setInt(6, speed);
						myStatement.setString(7, "no"); // "no" refers to isEvolved column default
						myStatement.setString(8, "none"); // "none" refers to ability column default
							
						// If there is new data in the database, let the user know their data was saved
						int data = myStatement.executeUpdate();
								
						if (data > 0){
							JOptionPane.showMessageDialog(null, "Data is saved.");
							
							// if data was saved (your Pokemon was created successfully),
							// go to the "My Pokemon" Screen
							PokemonGUI pokemonGui = (PokemonGUI) getParent().getParent().getParent().getParent();
							pokemonGui.changeCards("2");
							
						} else {
							JOptionPane.showMessageDialog(null, "Data is not saved.");
						}
					}
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		
		btnCreate.setFont(new Font("Avenir Next", Font.PLAIN, 23));
		btnCreate.setBounds(115, 381, 184, 50);
		add(btnCreate);
		
		//---------------------------------
		// MARK: - My Pokemon Button Tapped
		//---------------------------------
		JButton myPokemonButton = new JButton("My Pokemon");
		myPokemonButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				PokemonGUI pokemonGui = (PokemonGUI) getParent().getParent().getParent().getParent();
				pokemonGui.changeCards("2");
			}
		});
		
		myPokemonButton.setFont(new Font("Avenir Next", Font.PLAIN, 23));
		myPokemonButton.setBounds(444, 381, 200, 50);
		add(myPokemonButton);
	}
}


