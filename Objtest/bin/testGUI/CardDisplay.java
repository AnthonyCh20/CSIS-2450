package testGUI;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JButton;

public class CardDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @param subtype 
	 * @param evolution 
	 * @param weakness 
	 * @param string 
	 */
	public CardDisplay(String img, String name, String id,String supertype, String evolution, String subtype, String weakness, String hp) {
		setTitle("Display: " + name);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kings\\eclipse-workspace\\testGUI\\icons\\pokeball-png-45343.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1001, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		displayCard(img);

		JPanel cardNamePanel = new JPanel();
		cardNamePanel.setBackground(new Color(64, 128, 128));
		cardNamePanel.setBounds(557, 31, 348, 85);
		contentPane.add(cardNamePanel);

		JLabel nameLbl = new JLabel(name);
		nameLbl.setFont(new Font("Segoe Print", Font.BOLD, 42));
		cardNamePanel.add(nameLbl);

		JPanel cardInfoPanel = new JPanel();
		cardInfoPanel.setBounds(496, 127, 451, 348);
		contentPane.add(cardInfoPanel);
		cardInfoPanel.setLayout(new GridLayout(0,1,2,2));
		
		displayDetails(cardInfoPanel,"ID: " + id);
		displayDetails(cardInfoPanel,"Sub Type: " + subtype);
		displayDetails(cardInfoPanel,"SuperTypes: " + supertype);
		displayDetails(cardInfoPanel,"HP: " + hp);
		displayDetails(cardInfoPanel,"Evolution: " + evolution);
		displayDetails(cardInfoPanel,"Weakness: " + weakness);
		
		JButton homeBtn = new JButton("Home");
		homeBtn.setBounds(858, 510, 89, 48);
		contentPane.add(homeBtn);
		
		JButton addCardBtn = new JButton(" Collection");
		addCardBtn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		addCardBtn.setBounds(496, 510, 89, 48);
		contentPane.add(addCardBtn);
		
		JButton btnWishlist = new JButton("Wishlist");
		btnWishlist.setBounds(677, 510, 89, 48);
		contentPane.add(btnWishlist);
	}

	/**
	 * @param id
	 * @return
	 */
	private void displayDetails(JPanel panel, String details) {
		JTextArea textArea = new JTextArea(details);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		
		textArea.setFont(new Font("Segoe Print", Font.BOLD, 20));
		textArea.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBorder(null);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		panel.add(scrollPane);
	}

	/**
	 * @param img
	 * @param panel
	 */
	private void displayCard(String imgUrl) {
		JPanel cardPanel = new JPanel();
		cardPanel.setBounds(10, 11, 451, 593);
		contentPane.add(cardPanel);
		cardPanel.setLayout(new CardLayout(0, 0));
		try {
			@SuppressWarnings("deprecation")
			URL input = new URL(imgUrl);

			Image img = ImageIO.read(input);

			if (img == null) {
				System.err.println("Image not found" + img);
			}

			img = img.getScaledInstance(cardPanel.getWidth(), cardPanel.getHeight(), Image.SCALE_SMOOTH);

			ImageIcon icon = new ImageIcon(img);

			JLabel imgLabel = new JLabel(icon);
			imgLabel.setHorizontalAlignment(JLabel.CENTER);


			JPanel cardContainer = new JPanel();
			cardContainer.setLayout(new BoxLayout(cardContainer, BoxLayout.Y_AXIS));
			cardContainer.add(imgLabel);
			cardPanel.add(cardContainer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
