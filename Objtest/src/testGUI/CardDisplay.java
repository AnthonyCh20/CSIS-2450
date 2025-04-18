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
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
	public CardDisplay(String name,
			String id, 
			String supertype,
			String cardImg ,
			String evolution, 
			String subtype,
			String type, 
			String weakness,
			String flavorText, 
			String attack,
			String attackCost, 
			String attackText, 
			String hp,
			String attackDmg) {
		setTitle("Display: " + name);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\kings\\eclipse-workspace\\Objtest\\img\\pokeball-png-45343.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1001, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		displayCard(cardImg);

		JPanel cardNamePanel = new JPanel();
		cardNamePanel.setBackground(new Color(64, 128, 128));
		cardNamePanel.setBounds(471, 11, 504, 85);
		contentPane.add(cardNamePanel);

		JLabel nameLbl = new JLabel(name);
		nameLbl.setFont(new Font("Segoe Print", Font.BOLD, 42));
		cardNamePanel.add(nameLbl);

		JPanel panel = new JPanel();
		panel.setBounds(471, 114, 504, 372);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		displayDetails(panel,"ID: " + id);
		displayDetails(panel,"Sub Type: " + subtype);
		displayDetails(panel,"SuperTypes: " + supertype);
		displayDetails(panel,"Type: " + type);
		displayDetails(panel,"HP: " + hp);
		displayDetails(panel,"Evolution: " + evolution);
		displayDetails(panel,"Attack: " + attack);
		displayDetails(panel,"Attack Cost: " + attackCost);
		displayDetails(panel,"Attack Damage: " + attackDmg);
		displayDetails(panel,"Attack Text: " + attackText);
		
		if(!flavorText.isBlank()) 
		{ 
			displayDetails(panel,"Flavor Text: " + flavorText);
		};
		
		JButton homeBtn = new JButton("Home");
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
				dispose();
			}
		});
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
		textArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setFocusable(false);
		panel.add(textArea);

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
