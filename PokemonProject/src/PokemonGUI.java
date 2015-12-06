//********************************************************************
// Forrest Collins, Manuel Puentes, David Larsen

// Nov 2, 2015					EGR 327
// Time spent: 1 hour
// Purpose: This class contains the logic for the cardLayout GUI
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
		if(changeJFrame == "1"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "1");
		}
		if(changeJFrame == "2"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "2");
		}
		if(changeJFrame == "3"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "3");
		}
		
	}
}
