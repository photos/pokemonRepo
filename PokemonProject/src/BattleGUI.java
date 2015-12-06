//********************************************************************
// EGR327 Project		CBU
// BattleGUI.java		java class file for PokemonProject
// Created 12-1-15		David Larsen
// In this class, a user battles 2 Pokemon, in which
// a winner is displayed in a pop up
// REVISION HISTORY:
// Date			By			Details
// 12-1-15		David		Created BattleGUI.java, created GUI, calculate
//							value method logic
// 12-1-15		Forrest		Added selected Pokemon logic, battle button
//							logic, SQL logic
// 12-1-15		Manuel		Fixed SQL bug in the battle button logic
//********************************************************************

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import net.proteanit.sql.DbUtils;

import javax.swing.SwingConstants;

public class BattleGUI extends JPanel {

	private JTable table;
	private JScrollPane scrollPane;
	private JButton selectFirstPokemon;
	private JButton selectSecondPokemon;
	private JButton battleButton;
	private JButton myPokemonButton;
	private JLabel statusLabel;
	
	public static boolean pokemonOneIsGood;
	public static boolean pokemonTwoIsGood;
	
	public static String pokemonOneToBattle;
	public static double pokemonOneBattleValue;
	public static int pokemonOneHealth;
	public static int pokemonOneAttack;
	public static int pokemonOneDefense;
	public static int pokemonOneSpeed;
	public static String pokemonOneKind;
	
	public static String pokemonTwoToBattle;
	public static double pokemonTwoBattleValue;
	public static int pokemonTwoHealth;
	public static int pokemonTwoAttack;
	public static int pokemonTwoDefense;
	public static int pokemonTwoSpeed;
	public static String pokemonTwoKind;
	
	public String winner;
	
	public BattleGUI() {
		setBounds(100, 100, 800, 495);
		setLayout(null);
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setBackground(Color.WHITE);
		// Background frame hex color
		setBackground(Color.decode("#039BE5"));
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 66, 752, 300);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setFont(new Font("Avenir", Font.PLAIN, 12));
		table.setBackground(Color.white);
		table.setForeground(Color.blue);
		table.setRowHeight(30);
		
		// Label to display the first pokemon selection
		JLabel selectionLabel1 = new JLabel("Select a Pokemon above");
		selectionLabel1.setForeground(new Color(255, 204, 51));
		selectionLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		selectionLabel1.setBounds(24, 368, 239, 33);
		selectionLabel1.setFont(new Font("Avenir Next", Font.BOLD, 15));
		add(selectionLabel1);
				
		// Label to display the second pokemon selection
		JLabel selectionLabel2 = new JLabel("Select a Pokemon above");
		selectionLabel2.setForeground(new Color(255, 204, 51));
		selectionLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		selectionLabel2.setBounds(537, 368, 239, 33);
		selectionLabel2.setFont(new Font("Avenir Next", Font.BOLD, 15));
		add(selectionLabel2);
		
		// Label to display status messages when Battle button is tapped
		JLabel battleStatusLabel = new JLabel("");
		battleStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		battleStatusLabel.setFont(new Font("Avenir Next", Font.BOLD, 15));
		battleStatusLabel.setBounds(275, 373, 250, 28);
		add(battleStatusLabel);
				
		// When this screen appears, update the My Pokemon table
		addAncestorListener ( new AncestorListener ()
	    {
	        public void ancestorAdded (AncestorEvent event) {
	        	// Update the table
	        	reloadTable();
	        }

			@Override
			public void ancestorRemoved(AncestorEvent event) {
				// empty
			}

			@Override
			public void ancestorMoved(AncestorEvent event) {
				// Update the table
	        	// Call the reload table method
				reloadTable();
			}
	    });
		
		// Button to return to the main list of pokemon
		myPokemonButton = new JButton("My Pokemon");
		myPokemonButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// Go to the Create Pokemon Screen
				PokemonGUI pokemonGui = (PokemonGUI) getParent().getParent().getParent().getParent();
				pokemonGui.changeCards("2");
			}
		});
		myPokemonButton.setFont(new Font("Avenir Next", Font.PLAIN, 23));
		myPokemonButton.setBounds(24, 17, 219, 40);
		add(myPokemonButton);
		
		// Button to select the first pokemon to battle
		selectFirstPokemon = new JButton("Fight this Pokemon");
		selectFirstPokemon.setForeground(new Color(102, 51, 204));
		selectFirstPokemon.setFont(new Font("Avenir Next", Font.PLAIN, 23));
		selectFirstPokemon.setBounds(24, 406, 239, 40);
		
		//--------------------------------------------
		// MARK: - Select First Pokemon Button Tapped
		//--------------------------------------------
		selectFirstPokemon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Connection myConnection = null;
				Statement myStatement = null;
				ResultSet myResultSet = null;
				
				int row = table.getSelectedRow();
				
				System.out.println((String) table.getValueAt(row, 0));
				
				// change the first Pokemon selection label to the Pokemon you selected
				// check so a Pokemon can't battle itself
				selectionLabel1.setText((String) table.getValueAt(row, 0));
				if (selectionLabel1.getText() != "Select a Pokemon above") {
					pokemonOneIsGood = true;
				} else {
					pokemonOneIsGood = false;
				}
				
				if (pokemonOneIsGood == true && pokemonTwoIsGood == true){
					battleButton.setEnabled(true);
				} else {
					battleButton.setEnabled(false);
				}
				
				try {
					try {
						// 1. Get a connection to database
						myConnection = DriverManager.getConnection("jdbc:mysql://localhost", "student","student");
						// 2. Create a statement
						myStatement = myConnection.createStatement();
					} catch(Exception exc){
						exc.printStackTrace();
					} finally {
						
						if (myResultSet != null) {
							try {
								myResultSet.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						if (myStatement != null) {
							try {
								myStatement.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						if (myConnection != null) {
							try {
								myConnection.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
					
				} catch (ArrayIndexOutOfBoundsException ex){
					statusLabel.setText("Please choose a Pokemon.");
				}
			}
		});
		add(selectFirstPokemon);
		
		// Button to select the second pokemon to battle
		selectSecondPokemon = new JButton("Fight this Pokemon");
	
		//--------------------------------------------
		// MARK: - Select Second Pokemon Button Tapped
		//--------------------------------------------
		selectSecondPokemon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Connection myConnection = null;
				Statement myStatement = null;
				ResultSet myResultSet = null;
				
				int row = table.getSelectedRow();
				
				System.out.println((String) table.getValueAt(row, 0));
				
				// change the second Pokemon selection label to the Pokemon you selected
				// check so a Pokemon can't battle itself
				selectionLabel2.setText((String) table.getValueAt(row, 0));
				
				if (selectionLabel2.getText() != "Select a Pokemon above") {
					pokemonTwoIsGood = true;
				} else {
					pokemonTwoIsGood = false;
				}
				if (pokemonOneIsGood == true && pokemonTwoIsGood == true){
					battleButton.setEnabled(true);
				} else {
					battleButton.setEnabled(false);
				}
				
				try {
					try {
						//1. Get a connection to database
						myConnection = DriverManager.getConnection("jdbc:mysql://localhost", "student","student");
						// 2. Create a statement
						myStatement = myConnection.createStatement();
					} catch(Exception exc){
						exc.printStackTrace();
					} finally {
						
						if (myResultSet != null) {
							try {
								myResultSet.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						if (myStatement != null) {
							try {
								myStatement.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
						if (myConnection != null) {
							try {
								myConnection.close();
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					}
					
				} catch (ArrayIndexOutOfBoundsException ex){
					statusLabel.setText("Please choose a Pokemon.");
				}
			}
		});
		selectSecondPokemon.setForeground(new Color(102, 51, 204));
		selectSecondPokemon.setFont(new Font("Avenir Next", Font.PLAIN, 23));
		selectSecondPokemon.setBounds(537, 406, 239, 40);
		add(selectSecondPokemon);
		
		
		
		// Button to initiate the battle. Disabled until pokemon are selected.
		battleButton = new JButton("BATTLE!");
		
		//-----------------------------
		// MARK: - Battle Button Tapped
		//-----------------------------
		battleButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// if both Pokemon selection labels are the same, display error
				if (selectionLabel1.getText() == selectionLabel2.getText()){	
					// Battle is Disabled!
					battleStatusLabel.setText("Can't battle the same Pokemon!");
				} else {
					// Battle is Enabled!
					battleStatusLabel.setText("Let's get ready to rumble!");
					
					// two PreparedStatements are two ResultSets are needed
					// because two Pokemon objects will be queried
					Connection myConnection = null;
					PreparedStatement myStatement1 = null;
					ResultSet myResultSet1 = null;
					PreparedStatement myStatement2 = null;
					ResultSet myResultSet2 = null;
					
					try {
						// Get a Pokemon's nickname at a particular row.
						// This is a check to see if user selected a row.
						pokemonOneToBattle = selectionLabel1.getText();
						pokemonTwoToBattle = selectionLabel2.getText();
						
							// We have two Statements and two resultSets because we are querying for two Pokemon
							try{
							
								myConnection = DriverManager.getConnection("jdbc:mysql://localhost", "student","student");
								
								// First Query for first Pokemon
								myStatement1 = myConnection.prepareStatement("SELECT nickname, health, attack, defense, speed, kindOfPokemon FROM PokemonSchema.Pokemon " +
														  "WHERE nickname = '" + pokemonOneToBattle + "'");
								
								// 4. Process a result set
								myResultSet1 = myStatement1.executeQuery();
								
								while (myResultSet1.next()) {
									pokemonOneHealth =  myResultSet1.getInt("health");
									pokemonOneAttack =  myResultSet1.getInt("attack");
									pokemonOneDefense =  myResultSet1.getInt("defense");
									pokemonOneSpeed =  myResultSet1.getInt("speed");
									pokemonOneKind =  myResultSet1.getString("kindOfPokemon");
									//System.out.println(pokemonOneKind =  myResultSet1.getString("kindOfPokemon"));
									
								}
								
								// Second Query for second Pokemon
								myStatement2 = myConnection.prepareStatement("SELECT nickname, health, attack, defense, speed, kindOfPokemon FROM PokemonSchema.Pokemon " +
										  "WHERE nickname = '" + pokemonTwoToBattle + "'");
				
								// 4. Process a result set
								myResultSet2 = myStatement2.executeQuery();
				
								while (myResultSet2.next()) {
									pokemonTwoHealth =  myResultSet2.getInt("health");
									pokemonTwoAttack =  myResultSet2.getInt("attack");
									pokemonTwoDefense =  myResultSet2.getInt("defense");
									pokemonTwoSpeed =  myResultSet2.getInt("speed");
									pokemonTwoKind =  myResultSet2.getString("kindOfPokemon");
								}
								
								// Pop up Battle Winner
								String pokemonWinner = "";
								String pokemonWinnerKind = "";
								pokemonOneBattleValue = calculateValue(pokemonOneHealth, pokemonOneAttack, pokemonOneDefense, pokemonOneSpeed);
								pokemonTwoBattleValue = calculateValue(pokemonTwoHealth, pokemonTwoAttack, pokemonTwoDefense, pokemonTwoSpeed);
								
								if (pokemonOneBattleValue > pokemonTwoBattleValue) {
									pokemonWinner = pokemonOneToBattle + " wins!";
									pokemonWinnerKind = pokemonOneKind;
								} else if (pokemonOneBattleValue < pokemonTwoBattleValue) {
									pokemonWinner = pokemonTwoToBattle + " wins!";
									pokemonWinnerKind = pokemonTwoKind;
								} else {
									pokemonWinner = "Draw";
								}
								
								// Create a pop-up frame size to display the winner
								battleWinner bw = new battleWinner(pokemonWinner, pokemonWinnerKind);
								JFrame frame = new JFrame ("Winner!");
								frame.getContentPane().add(bw);
								frame.setVisible(true);
								frame.setBounds(52, 63, 400, 420);
								
							} catch(Exception exc) {
								exc.printStackTrace();
							
							// close the MySQL connection, statement, and result set
							} finally {
								
								if (myResultSet1 != null && myResultSet2 != null) {
									try {
										myResultSet1.close();
										myResultSet2.close();
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
								}
								if (myStatement1 != null && myStatement2 != null) {
									try {
										myStatement1.close();
										myStatement2.close();
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
								}
								if (myConnection != null) {
									try {
										myConnection.close();
									} catch (SQLException e1) {
										e1.printStackTrace();
									}
								}
							}
	 
					// if the user did not select a Pokemon, display a message
					} catch (ArrayIndexOutOfBoundsException ex) {
						statusLabel.setText("Error with database.");
					}
				}
			}
		});
		
		battleButton.setForeground(new Color(255, 0, 0));
		battleButton.setFont(new Font("Avenir Next", Font.BOLD, 23));
		battleButton.setBounds(299, 406, 200, 40);
		battleButton.setEnabled(false);
		add(battleButton);
	
	}
	
	//------------------------------
	// MARK: - Reload Table Function
	//------------------------------
	public void reloadTable(){
		
		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultSet = null;
		
		try {
			// 1. Get a connection to the database
			myConnection = DriverManager.getConnection("jdbc:mysql://localhost", "student","student");
			//System.out.println("Database connection successful!\n");
			
			// 2. Create a statement
			myStatement = myConnection.createStatement();
			
			// 3. Execute a SQL Query
			myResultSet = myStatement.executeQuery("SELECT * FROM PokemonSchema.Pokemon");
			
			// 4. Update/refresh the table with new data from the SQL Query
			// FYI: Add the rs2xml.jar file to your Build Path. If you don't have this .jar, 
			// download this jar from drive.google.com/file/d/0BzIr4IDDKJEcdDE1YTlzbmtkMzg/view
			table.setModel(DbUtils.resultSetToTableModel(myResultSet));
			
		} catch (Exception exc) {
			
			statusLabel.setText("Bummer! The Pokemon table has no data.");
			
		// close the MySQL connection, statement, and result set
		} finally {
			
			if (myResultSet != null) {
				try {
					myResultSet.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (myStatement != null) {
				try {
					myStatement.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (myConnection != null) {
				try {
					myConnection.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	//--------------------------------------------------
	// MARK: - Calculate Value to Display Pokemon Winner
	//--------------------------------------------------
	public double calculateValue(int health, int attack, int defense, int speed) {
		double value = attack;
		value += (speed * speed);
		value += defense;
		value *= health;
		Random rand = new Random();
		value = value / (rand.nextDouble());
		return value;
	}
}

