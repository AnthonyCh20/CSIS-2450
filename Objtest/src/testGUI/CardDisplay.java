package testGUI;

import java.awt.EventQueue;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;

public class CardDisplay extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public CardDisplay(String img) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1001, 654);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		displayCard(img);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(496, 11, 451, 129);
		contentPane.add(panel_1);

		JLabel lblNewLabel = new JLabel("New label");
		panel_1.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(496, 151, 451, 453);
		contentPane.add(panel_2);

		JLabel lblNewLabel_1 = new JLabel("New label");
		panel_2.add(lblNewLabel_1);
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
