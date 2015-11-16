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
//		Screen3 scrn3 = new Screen3();
//		Screen4 scrn4 = new Screen4();
//		Screen5 scrn5 = new Screen5();
//		Screen6 scrn6 = new Screen6();
//		Screen7 scrn7 = new Screen7();
//		Screen8 scrn8 = new Screen8();
//		Screen9 scrn9 = new Screen9();
//		Screen10 scrn10 = new Screen10();
//		Screen11 scrn11 = new Screen11();
//		Screen12 scrn12 = new Screen12();
//		Screen13 scrn13 = new Screen13();
//		Screen14 scrn14 = new Screen14();
//		Screen15 scrn15 = new Screen15();
		
		
		// Then, perform: contentPane.add(nameOfInstance, id)
		contentPane.add(createPokemonScreen, "1");
		contentPane.add(myPokemonScreen, "2");
//		contentPane.add(scrn3, "3");
//		contentPane.add(scrn4, "4");
//		contentPane.add(scrn5, "5");
//		contentPane.add(scrn6, "6");
//		contentPane.add(scrn7, "7");
//		contentPane.add(scrn8, "8");
//		contentPane.add(scrn9, "9");
//		contentPane.add(scrn10, "10");
//		contentPane.add(scrn11, "11");
//		contentPane.add(scrn12, "12");
//		contentPane.add(scrn13, "13");
//		contentPane.add(scrn14, "14");
//		contentPane.add(scrn15, "15");
		
		CardLayout cl = (CardLayout)(contentPane.getLayout());
		cl.show(contentPane, "1");
	
	}
	
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
		if(changeJFrame == "4"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "4");
		}
		if(changeJFrame == "5"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "5");
		}
		if(changeJFrame == "6"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "6");
		}
		if(changeJFrame == "7"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "7");
		}
		if(changeJFrame == "8"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "8");
		}
		if(changeJFrame == "9"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "9");
		}
		if(changeJFrame == "10"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "10");
		}
		if(changeJFrame == "11"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "11");
		}
		if(changeJFrame == "12"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "12");
		}
		if(changeJFrame == "13"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "13");
		}
		if(changeJFrame == "14"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "14");
		}
		if(changeJFrame == "15"){
			CardLayout cl = (CardLayout)(contentPane.getLayout());
			cl.show(contentPane, "15");
		}
	}
}
