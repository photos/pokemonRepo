//********************************************************************
// Forrest Collins, Manuel Puentes, David Larsen

// Nov 14, 2015					EGR 327
// Time spent: 17 hours
// Purpose: This class displays the Pokemon a user creates in a table,
//          which the user can interact with to evolve or delete a
//          Pokemon. Also, in future iterations, the user will be able
//          to battle two Pokemon.
//********************************************************************
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.*;
import java.util.Random;

import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import java.awt.Color;
import java.awt.event.MouseAdapter;

import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class MyPokemon extends JPanel {

	private JTable table;
	private JScrollPane scrollPane;
	private JButton evolveButton;
	private JButton createPokemonButton;
	private JButton deleteButton;
	private JLabel statusLabel;
	private JButton battlePokemonButton;

	public MyPokemon() {
	
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
		
		// When this screen appears, update the My Pokemon table
		addAncestorListener ( new AncestorListener ()
	    {
	        public void ancestorAdded ( AncestorEvent event ){
	        	
	        	// Update the table
	        	// Call the reload table method
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

		//-----------------------------
		// MARK: - Reload Button Tapped
		//-----------------------------
		JButton refreshButton = new JButton("Refresh");
		refreshButton.setForeground(new Color(0, 153, 0));
		refreshButton.setFont(new Font("Avenir Next", Font.PLAIN, 23));
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// call reload table method
				reloadTable();
				statusLabel.setText("Your Pokemon table has been refreshed!");
			}
		});
		
		refreshButton.setBounds(24, 406, 150, 40);
		add(refreshButton);
		
		//-----------------------------
		// MARK: - Evolve Button Tapped
		//-----------------------------
		evolveButton = new JButton("Evolve");
		evolveButton.setForeground(new Color(102, 51, 204));
		evolveButton.setFont(new Font("Avenir Next", Font.PLAIN, 23));
		evolveButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Connection myConnection = null;
				Statement myStatement = null;
				ResultSet myResultSet = null;
				
				int row = table.getSelectedRow();
				String pokemonToEvolve = "";
				String isPokemonEvolved = "";
				
				try {
					
					// get a Pokemon's nickname and its evolved status at a selected row
					pokemonToEvolve = (String) table.getValueAt(row, 0);
					isPokemonEvolved = (String) table.getValueAt(row, 6);
					//System.out.println(pokemonToEvolve);
					//System.out.println(isPokemonEvolved);
					
					// if the Pokemon has NOT been evolved, modify it's stats
					if (isPokemonEvolved.equals("no")) {
						
						// update the Pokemon's stats, give it a random ability, and update its
						// isEvolved status to "yes"
						try{
							// Create a basic instance of EvolvedPokemon Class that inherits from Pokemon Class
							Pokemon pokemon = new Pokemon("","",0,0,0,0,false);
							EvolvedPokemon evolvedPokemon = new EvolvedPokemon(pokemon);
							
							// test to update a selected Pokemon's attack value and ability
							// increaseAttributeVal allows us to use the increaseAttribute method
							// from the EvolvedPokemon Class with a Pokemon's database values.
							//double increaseAttributeVal = evolvedPokemon.increaseAttribute();
							// default ability for now when you evolve a Pokemon
							String ability[] = {"Intimidate", "Blaze", "Hustle", "Sturdy","Insomnia", "Levitate",
												"Oblivious", "Prankster", "Reckless", "Technician"};
							int randomIndex = new Random().nextInt(ability.length);
							String randomAbility = (ability[randomIndex]);
							
							//1. Get a connection to database
							myConnection = DriverManager.getConnection("jdbc:mysql://localhost", "student","student");
							// 2. Create a statement
							myStatement = myConnection.createStatement();
							
							// 3. Execute a statement query
							// Update all the Pokemon's stats
							myStatement.executeUpdate("UPDATE PokemonSchema.Pokemon " +
								  "SET attack =  attack * " + evolvedPokemon.increaseAttribute() + 
								  " ,health = health * " + evolvedPokemon.increaseAttribute() +
								  " ,defense = defense * " + evolvedPokemon.increaseAttribute() +
								  " ,speed = speed * " + evolvedPokemon.increaseAttribute() +
								  " ,isEvolved = 'yes' " + 
								  " ,ability = '" + randomAbility + "' " +
								  "WHERE nickname = '" + pokemonToEvolve + "'");
							
							// 4. Process a result set
							myResultSet = myStatement.executeQuery("SELECT * FROM PokemonSchema.Pokemon");
							
							// update/refresh the table with new data
							table.setModel(DbUtils.resultSetToTableModel(myResultSet));
							
							statusLabel.setText("Congratulations, you evolved " + pokemonToEvolve + "!");
							
						} catch(Exception exc){
						
							exc.printStackTrace();
						
						// close the MySQL connection, statement, and result set
						} finally {
							
							if (myResultSet != null) {
								try {
									myResultSet.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							if (myStatement != null) {
								try {
									myStatement.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							if (myConnection != null) {
								try {
									myConnection.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
						
					// if the selected Pokemon has already been evolved, display a message
					} else {
						
						statusLabel.setText("This pokemon is already evolved! Choose a different Pokemon.");
					}
				
				// if the user did not select a Pokemon, display a message
				} catch (ArrayIndexOutOfBoundsException ex){
					
					statusLabel.setText("Please choose a Pokemon to evolve.");
				}
			}
		});
		
		evolveButton.setBounds(327, 406, 150, 40);
		add(evolveButton);
		
		//-------------------------------------
		// MARK: - Create Pokemon Button Tapped
		//-------------------------------------
		createPokemonButton = new JButton("Create Pokemon");
		createPokemonButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				// Go to the Create Pokemon Screen
				PokemonGUI pokemonGui = (PokemonGUI) getParent().getParent().getParent().getParent();
				pokemonGui.changeCards("1");
			}
		});
		
		createPokemonButton.setFont(new Font("Avenir Next", Font.PLAIN, 23));
		createPokemonButton.setBounds(24, 17, 219, 40);
		add(createPokemonButton);
		
		//-----------------------------
		// MARK: - Delete Button Tapped
		//-----------------------------
		deleteButton = new JButton("Delete");
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Connection myConnection = null;
				Statement myStatement = null;
				ResultSet myResultSet = null;
				
				int row = table.getSelectedRow();
				String pokemonToDelete = "";
				
				try {
					
					// Get a Pokemon's nickname at a particular row.
					// This is a check to see if user selected a row.
					pokemonToDelete = (String) table.getValueAt(row, 0);
					//System.out.println(pokemonToDelete);
					
						try{
							
							//1. Get a connection to database
							myConnection = DriverManager.getConnection("jdbc:mysql://localhost", "student","student");
							// 2. Create a statement
							myStatement = myConnection.createStatement();
							
							// 3. Execute a statement query
							myStatement.executeUpdate("DELETE FROM PokemonSchema.Pokemon " +
													  "WHERE nickname = '" + pokemonToDelete + "'");
							
							// 4. Process a result set
							myResultSet = myStatement.executeQuery("SELECT * FROM PokemonSchema.Pokemon");
							
							// update/refresh the table with new data
							table.setModel(DbUtils.resultSetToTableModel(myResultSet));
							
							statusLabel.setText("Successfully deleted " + pokemonToDelete);
							
						} catch(Exception exc){
						
							exc.printStackTrace();
						
						// close the MySQL connection, statement, and result set
						} finally {
							
							if (myResultSet != null) {
								try {
									myResultSet.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							if (myStatement != null) {
								try {
									myStatement.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
							if (myConnection != null) {
								try {
									myConnection.close();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						}
 
				// if the user did not select a Pokemon, display a message
				} catch (ArrayIndexOutOfBoundsException ex){
					
					statusLabel.setText("Please choose a Pokemon to delete.");
				}
			}
		});
		
		deleteButton.setForeground(Color.RED);
		deleteButton.setFont(new Font("Avenir Next", Font.PLAIN, 23));
		deleteButton.setBounds(626, 406, 150, 40);
		add(deleteButton);
		
		statusLabel = new JLabel("Welcome!");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setForeground(new Color(255, 204, 51));
		statusLabel.setFont(new Font("Avenir Next", Font.BOLD, 15));
		statusLabel.setBounds(24, 378, 752, 16);
		add(statusLabel);
		
		//-------------------------------------
		// MARK: - Battle Pokemon Button Tapped
		//-------------------------------------
		battlePokemonButton = new JButton("Battle Pokemon");
		battlePokemonButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				PokemonGUI pokemonGui = (PokemonGUI) getParent().getParent().getParent().getParent();
				pokemonGui.changeCards("3");
			}
		});
		battlePokemonButton.setFont(new Font("Avenir Next", Font.PLAIN, 23));
		battlePokemonButton.setBounds(567, 17, 209, 40);
		add(battlePokemonButton);
	}
	
	//---------------------------------------
	// MARK: - Reload My Pokemon Table Method
	//---------------------------------------
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
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (myStatement != null) {
				try {
					myStatement.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (myConnection != null) {
				try {
					myConnection.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}

