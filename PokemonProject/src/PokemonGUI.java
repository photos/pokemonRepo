
//********************************************************************
// EGR327 Project		CBU
// PokemonGUI.java		java class file for PokemonProject
// Created 11-2-15		Forrest
// This class contains the logic for the cardLayout GUI
// REVISION HISTORY:
// Date			By			Details
// 11-2-15		Forrest		Created PokemonGUI.java and cardLayout logic
// 12-1-15		Manuel		Eliminated unnecessary cardLayout contentPanes
//********************************************************************
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class PokemonGUI extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					PokemonGUI frame = new PokemonGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public PokemonGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 495);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		contentPane.setLayout(new CardLayout(0,0));
		
		// This is where we create instances of all the atm screens!
		CreatePokemon createPokemonScreen = new CreatePokemon();
		MyPokemon myPokemonScreen = new MyPokemon();
		BattleGUI myBattleScreen = new BattleGUI();
		
		
		// Then, perform: contentPane.add(nameOfInstance, id)
		contentPane.add(createPokemonScreen, "1");
		contentPane.add(myPokemonScreen, "2");
		contentPane.add(myBattleScreen, "3");

		CardLayout cl = (CardLayout)(contentPane.getLayout());
		cl.show(contentPane, "1");
	
	}
	
	//--------------------------------
	// MARK: - Logic to Change Screens
	//--------------------------------
	public void changeCards(String changeJFrame) {
		if("1" == changeJFrame) {
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "1");
		}
		if("2" == changeJFrame) {
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "2");
		}
		if("3" == changeJFrame) {
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "3");
		}
	}
}
