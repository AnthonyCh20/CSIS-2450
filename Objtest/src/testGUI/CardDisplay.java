package testGUI;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import card.Attack;

import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
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
			List<Attack> attack, 
			String hp) {
		setTitle("Display: " + name);
		
		Image img = new ImageIcon("C:\\Users\\ajp48\\OneDrive\\Documents\\GitHub\\CSIS-2450\\Objtest\\img\\pokeball-png-45343.png").getImage();
		setIconImage(img);

		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1001, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		displayCard(cardImg);

		JPanel cardNamePanel = new JPanel();
		setPanelColorToTypeColor(cardNamePanel,type);
		cardNamePanel.setBounds(471, 11, 504, 85);
		contentPane.add(cardNamePanel);

		JLabel nameLbl = new JLabel(name);
		nameLbl.setFont(new Font("Segoe Print", Font.BOLD, 42));
		cardNamePanel.add(nameLbl);

		JPanel panel = new JPanel();
		panel.setBounds(471, 114, 504, 372);
		contentPane.add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		displayDetails(panel,"ID: ", id);
		displayDetails(panel,"Sub Type: ", subtype);
		displayDetails(panel,"SuperTypes: ", supertype);
		displayDetails(panel,"Type: ", type);
		displayDetails(panel,"HP: ", hp);
		displayDetails(panel,"Evolution: ", evolution);
		
		if(!attack.isEmpty() && attack != null) {
			for (Attack atk : attack)
			{
				displayDetails(panel, "Attack: ", atk.getName());
				displayDetails(panel, "Cost: ", atk.getCost());
				displayDetails(panel, "Damage: ", atk.getDamage());
				displayDetails(panel, "Text: ", atk.getText());
				
			}
		}
		
		if(!flavorText.isBlank()) 
		{ 
			displayDetails(panel,"Flavor Text: ", flavorText);
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
		
		setLocationRelativeTo(null);
	}

	private void setPanelColorToTypeColor(JPanel namePlatePanel,String type)
	{
		Color color;
		
		switch (type.toLowerCase())
		{
		case "normal":
			color = Color.decode("#A8A77A");
			break;
		case "fire":
			color = Color.decode("#EE8130");
			break;
		case "water":
			color = Color.decode("#A8A77A");
			break;
		case "electric", "lightning":
			color = Color.decode("#F7D02C");
			break;
		case "grass":
			color= Color.decode("#7AC74C");
			break;
		case "ice":
			color = Color.decode("#96D9D6");
			break;
		case "fighting":
			color= Color.decode("#C22E28");
			break;
		case "poison":
			color = Color.decode("#A33EA1");
			break;
		case "ground":
			color= Color.decode("#E2BF65");
			break;
		case "flying":
			color = Color.decode("#A98FF3");
			break;
		case "psychic":
			color= Color.decode("#F95587");
			break;
		case "bug":
			color = Color.decode("#A6B91A");
			break;
		case "rock":
			color= Color.decode("#B6A136");
			break;
		case "ghost":
			color = Color.decode("#735797");
			break;
		case "dragon":
			color= Color.decode("#6F35FC");
			break;
		case "dark":
			color = Color.decode("#705746");
			break;
		case "steel":
			color = Color.decode("#B7B7CE");
			break;
		case "fairy":
			color = Color.decode("#D685AD");
			break;
		default:
			color = Color.LIGHT_GRAY;
			break;
		}
		
		namePlatePanel.setBackground(color);
	}

	/**
	 * @param id
	 * @return
	 */
	private void displayDetails(JPanel panel, String label,String details) {
		if(details != "") {
			JTextArea textArea = new JTextArea(label + details);
			textArea.setFont(new Font("SansSerif", Font.PLAIN, 16));
			textArea.setEditable(false);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			textArea.setFocusable(false);
			panel.add(textArea);
		}else {
			
		}

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
