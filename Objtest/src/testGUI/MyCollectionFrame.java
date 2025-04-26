package testGUI;

import java.io.IOException;
import java.util.List;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import card.PokemonCard;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class MyCollectionFrame extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private List<PokemonCard> cards;

	/**
	 * Create the frame.
	 */
	public MyCollectionFrame(List<PokemonCard> cards)
	{	
		this.cards = cards;
		initializeUI();
	}
	
	/**
	 * 
	 */
	private void initializeUI()
	{
		setTitle("My Collection");

		Image img = new ImageIcon("C:\\Users\\ajp48\\OneDrive\\Documents\\GitHub\\CSIS-2450\\Objtest\\img\\pokeball-png-45343.png")
												.getImage();
		setIconImage(img);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1062, 500);

		initializeMenuBar();
		
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);

		displayCard();
	}

	/**
	 * 
	 */
	private void initializeMenuBar()
	{
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		Component horizontalStrut_2 = Box.createHorizontalStrut(135);
		menuBar.add(horizontalStrut_2);

		JButton homeBtn = new JButton("Home");
		homeBtn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				dispose();
				new MainFrame().setVisible(true);
			}
		});
		menuBar.add(homeBtn);

		Component horizontalStrut = Box.createHorizontalStrut(100);
		menuBar.add(horizontalStrut);

		JButton filterBtn = new JButton("Filter");
		filterBtn.addActionListener(e -> showFilterDialog());
		menuBar.add(filterBtn);

		Component horizontalStrut_1 = Box.createHorizontalStrut(100);
		menuBar.add(horizontalStrut_1);

		JButton sortBtn = new JButton("Sort");
		menuBar.add(sortBtn);

		Component horizontalStrut_1_1 = Box.createHorizontalStrut(100);
		menuBar.add(horizontalStrut_1_1);

		JButton searchBtn = new JButton("Search");
		searchBtn.addActionListener(e -> showSearchDialog());
		menuBar.add(searchBtn);
	}

	private void showSearchDialog()
	{
		JOptionPane.showMessageDialog(this, "Search functionality will go here.");
	}

	private void showFilterDialog()
	{
		JOptionPane.showMessageDialog(this, "Filter functionality will go here.");
	}

	

	/**
	 * 
	 */
	private void displayCard()
	{
		JPanel cardPanel = new JPanel();
		cardPanel.setLayout(new GridLayout(0, 4, 0, 0));

		if (cards == null || cards.isEmpty())
		{
			cardPanel.add(new JLabel("no cards in collection.", JLabel.CENTER));
		}
		else
		{
			for (PokemonCard card : cards)
			{
				try
				{
					JPanel cardContainer = createCardThumbnail(card);
					cardPanel.add(cardContainer);
				} catch (Exception e)
				{
					e.printStackTrace();

					cardPanel.add(new JLabel("Error loading: " + card.getName()));
				}
			}
		}

		// Wrap the card panel in a scroll pane
		JScrollPane scrollPane = new JScrollPane(cardPanel);
		contentPane.add(scrollPane,BorderLayout.CENTER);
	}

	private JPanel createCardThumbnail(PokemonCard card) throws IOException
	{
		URL url = new URL(card.getCardImg());
		Image img = ImageIO.read(url);
		img = img.getScaledInstance(127, 153, Image.SCALE_SMOOTH);

		JLabel imgLabel = new JLabel(new ImageIcon(img));
		JLabel nameLabel = new JLabel("Name: " + card.getName() + "\n", JLabel.CENTER);
		JLabel IDLabel = new JLabel("ID: " + card.getId(), JLabel.CENTER);
		// Change Cusor to pointer so user can tell it clickable
		imgLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		nameLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		IDLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		// Create the click listener
		MouseAdapter cardClickListener = listenerForClickables(card);
		
		// Create the hover Listener
		MouseAdapter mouseHover = hoverEffect();
		
		// Attaching the click listener to the card
		imgLabel.addMouseListener(cardClickListener);
		nameLabel.addMouseListener(cardClickListener);
		IDLabel.addMouseListener(cardClickListener);
		imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		// Attaching the hover listeners to the card
		imgLabel.addMouseListener(mouseHover);
		nameLabel.addMouseListener(mouseHover);
		IDLabel.addMouseListener(mouseHover);
		// Gid bag layout for card display
		JPanel cardContainer = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0,0,0,0);
		gbc.weightx = 1;
		
		gbc.gridy = 0;
		cardContainer.add(imgLabel, gbc);
		
		gbc.gridy = 1;
		cardContainer.add(nameLabel, gbc);
		
		gbc.gridy = 2;
		cardContainer.add(IDLabel, gbc);
		
		return cardContainer;
	}
	// Changes the cursor to a pointer and sets text to blue so user can tell they are links.
	private MouseAdapter hoverEffect()
	{
		return new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				((JComponent)e.getSource()).setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				((JComponent)e.getSource()).setForeground(null);
			}
		};
	}

	/**
	 * @param card
	 * @return MouseAdapter
	 * 
	 * Listener for click on card if card image name or id is clicked the card details are displayed.
	 */
	private MouseAdapter listenerForClickables(PokemonCard card)
	{
		return new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e) {
				showCardDetails(card);
			}
		};
	}

	private void showCardDetails(PokemonCard card)
	{
		CardDisplay cd = new CardDisplay(this,card.getName(), card.getId(), card.getSuperType(), card.getCardImg(),
												card.getEvolution(), card.getSubType(), card.getType(),
												card.getWeakness(), card.getFlavorText(),card.getAbilities(),card.getAttack(),
												card.getHp());
		
		cd.setVisible(true);

	}
}
