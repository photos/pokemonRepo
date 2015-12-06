
//********************************************************************
// EGR327 Project		CBU
// battleWinner.java	java class file for PokemonProject
// Created 12-1-15		David Larsen
// Pop up window displays the winner of a Pokemon battle
// REVISION HISTORY:
// Date			By			Details
// 12-1-15		David		Created battleWinner.java, created GUI, created
//							constructor logic
// 12-1-15		Forrest		Added Pokemon image label logic 
// 12-1-15		Manuel		Helped with UI color design for pop up
//********************************************************************
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.ImageIcon;
import java.awt.Color;


public class battleWinner extends JPanel {

	/**
	 * Create the panel.
	 */
	public battleWinner(String pokemonWinner, String pokemonWinnerKind) {
		setBackground(Color.decode("#039BE5")); // blue hex color
		setLayout(null);
		
		// Displays the nickname of the Pokemon who won the battle
		JLabel battleWinnerLabel = new JLabel(pokemonWinner);
		battleWinnerLabel.setForeground(new Color(135, 252, 112));
		battleWinnerLabel.setFont(new Font("Avenir Next", Font.BOLD, 24));
		battleWinnerLabel.setHorizontalAlignment(SwingConstants.CENTER);
		battleWinnerLabel.setBounds(6, 6, 388, 45);
		add(battleWinnerLabel);
		
		// ImageIcon displays the pokemon winner .gif image
		ImageIcon imageIcon = new ImageIcon("Images/" + pokemonWinnerKind + ".gif");
		JLabel imageWinnerLabel = new JLabel("");
		imageWinnerLabel.setIcon(imageIcon);
		imageWinnerLabel.setBounds(52, 63, 300, 300);
		add(imageWinnerLabel);
		
        imageWinnerLabel.setIcon(imageIcon);
	
		imageWinnerLabel.setDoubleBuffered(true);
			
		add(imageWinnerLabel, java.awt.BorderLayout.NORTH);

	}

}
