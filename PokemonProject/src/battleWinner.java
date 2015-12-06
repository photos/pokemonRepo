//********************************************************************
// Forrest Collins, Manuel Puentes, David Larsen

// Dec 1, 2015					EGR 327
// Time spent: 1 hr
// Purpose: Pop up window to display the winner
//          of a Pokemon battle.
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
		setBackground(Color.decode("#039BE5"));
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
