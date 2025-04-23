package testGUI;

import java.io.IOException;
import java.util.List;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JButton;
import java.awt.Component;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MyCollectionFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	   // List of card names and image paths (replace with your actual data)
    private List<String> cardNames = List.of("Pikachu","","","","","","","","","","","","","","","","");
    private List<String> cardImgPaths = List.of(
        "https://images.pokemontcg.io/xy1/1_hires.png");

	/**
	 * Create the frame.
	 */
	public MyCollectionFrame() {
		setTitle("My Collection");
		
		Image img = new ImageIcon("C:\\Users\\ajp48\\OneDrive\\Documents\\GitHub\\CSIS-2450\\Objtest\\img\\pokeball-png-45343.png").getImage();
		setIconImage(img);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1062, 500);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(135);
		menuBar.add(horizontalStrut_2);
		
		JButton homeBtn = new JButton("Home");
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainFrame mf = new MainFrame();
				mf.setVisible(true);
			}
		});
		menuBar.add(homeBtn);
		
		Component horizontalStrut = Box.createHorizontalStrut(100);
		menuBar.add(horizontalStrut);
		
		JButton filterBtn = new JButton("Filter");
		menuBar.add(filterBtn);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(100);
		menuBar.add(horizontalStrut_1);
		
		JButton btnNewButton_2 = new JButton("Sort");
		menuBar.add(btnNewButton_2);
		
		Component horizontalStrut_1_1 = Box.createHorizontalStrut(100);
		menuBar.add(horizontalStrut_1_1);
		
		JButton btnNewButton = new JButton("Search");
		menuBar.add(btnNewButton);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		displayCard();
	}

	/**
	 * 
	 */
	private void displayCard() {
		JPanel cardPanel = new JPanel();
		cardPanel.setBounds(10, 11, 949, 414);
		cardPanel.setLayout(new GridLayout(0, 4, 10, 10));
		for (int i = 0; i < cardNames.size(); i++) {
			try {
				
				URL input = new URL("https://images.pokemontcg.io/xy1/1_hires.png");
				
				Image img = ImageIO.read(input);
				
				if (img == null) {
					System.err.println("Image not found" + cardImgPaths.get(i));
					continue;
				}
				
				img = img.getScaledInstance(127, 153, Image.SCALE_SMOOTH);

				ImageIcon icon = new ImageIcon(img);

				JLabel imgLabel = new JLabel(icon);
				imgLabel.setHorizontalAlignment(JLabel.CENTER);

				JLabel nameLabel = new JLabel("card Name", JLabel.CENTER);

				JPanel cardContainer = new JPanel();
				cardContainer.setLayout(new BoxLayout(cardContainer, BoxLayout.Y_AXIS));
				cardContainer.add(imgLabel);
				cardContainer.add(nameLabel);

				cardPanel.add(cardContainer);
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		// Wrap the card panel in a scroll pane
        JScrollPane scrollPane = new JScrollPane(cardPanel);
        scrollPane.setBounds(10, 11, 962, 414); // Adjust bounds as needed
        contentPane.add(scrollPane);
	}
}
