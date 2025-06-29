package testGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import apiClient.TestCall;
import card.PokemonCard;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		Image img = new ImageIcon("C:\\Users\\ajp48\\OneDrive\\Documents\\GitHub\\CSIS-2450\\Objtest\\img\\pokeball-png-45343.png").getImage();

		setTitle("Pokemon Collection Orginizer");
		setIconImage(img);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 665, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel welcome = new JLabel("Welcome");
		welcome.setHorizontalAlignment(SwingConstants.CENTER);
		welcome.setSize(262, 29);
		welcome.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 24));
		welcome.setLocation(10, 11);
		contentPane.add(welcome);

		JButton myCollectionBtn = new JButton("My Collection");
		myCollectionBtnListener(myCollectionBtn);
		myCollectionBtn.setFont(new Font("Cambria", Font.PLAIN, 14));
		myCollectionBtn.setBounds(48, 51, 224, 44);
		contentPane.add(myCollectionBtn);

		JButton searchBtn = new JButton("Search");
		//displayCard(searchBtn);
		searchBtn.setFont(new Font("Cambria", Font.PLAIN, 14));
		searchBtn.setBounds(48, 150, 224, 44);
		contentPane.add(searchBtn);

		JButton wishlistBtn = new JButton("WishList");
		wishlistBtn.setFont(new Font("Cambria", Font.PLAIN, 14));
		wishlistBtn.setBounds(48, 261, 224, 44);
		contentPane.add(wishlistBtn);

		JLabel cardImageLbl = new JLabel("Image here");
		cardImageLbl.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
		cardImageLbl.setHorizontalAlignment(SwingConstants.CENTER);
		cardImageLbl.setBounds(319, 11, 300, 441);
		contentPane.add(cardImageLbl);

		JButton aboutBtn = new JButton("About");
		aboutBtn.setFont(new Font("Cambria", Font.PLAIN, 14));
		aboutBtn.setBounds(23, 413, 110, 39);
		contentPane.add(aboutBtn);
		
		setLocationRelativeTo(null);
	}

//	/**
//	 * @param searchBtn
//	 */
//	private void displayCard(JButton searchBtn) {
//		searchBtn.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				TestCall tc = new TestCall();
//				List<PokemonCard> card = tc.testCall("https://api.pokemontcg.io/v2/cards?q=name:charizard&page=1&pageSize=1");
//				
//				Card firstCard = card.get(0);
//				
//				System.out.println(firstCard.print());
//				
//				CardDisplay cd = new CardDisplay(this,firstCard.getName(), 
//						firstCard.getId(), 
//						firstCard.getSuperType(),
//						firstCard.getCardImg(),
//						((PokemonCard) firstCard).getEvolution(),
//						((PokemonCard) firstCard).getSubType(), 
//						((PokemonCard) firstCard).getType(),
//						((PokemonCard) firstCard).getWeakness(),
//						((PokemonCard) firstCard).getFlavorText(),
//						((PokemonCard) firstCard).getAttack(),
//						((PokemonCard) firstCard).getHp());
//				cd.setVisible(true);
//				
//				dispose();
//			}
//		});
//	}

	/**
	 * @param myCollectionBtn Closes main page and opens collection page
	 */
	private void myCollectionBtnListener(JButton myCollectionBtn) {
		myCollectionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TestCall tc = new TestCall();
				List<PokemonCard> card = tc.testCall("https://api.pokemontcg.io/v2/cards?q=name:charizard&page=1&pageSize=25");
				MyCollectionFrame collection = new MyCollectionFrame(card);
				collection.setVisible(true);
				dispose();
			}
		});
	}
}
