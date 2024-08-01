import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.*;
import java.io.*;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	public static JFrame MainMenu = new MainMenu();
	public static ArtistPage ArtistMenu = new ArtistPage();
	public static ArrayList<Artist> artistList = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//MainMenu frame = new MainMenu();
					readFile();
					MainMenu.setVisible(true);
					ArtistPage.tableArtist.setModel(ArtistPage.dtm);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMainMenu = new JLabel("MAIN MENU");
		lblMainMenu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainMenu.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblMainMenu.setBounds(10, 27, 1066, 105);
		contentPane.add(lblMainMenu);
		
		JButton btnArtwork = new JButton("Artwork List");
		btnArtwork.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnArtwork.setBounds(426, 173, 235, 41);
		contentPane.add(btnArtwork);
		
		JButton btnArtist = new JButton("Artist List");
		btnArtist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArtistMenu.setVisible(true);
				MainMenu.setVisible(false);
				ArtistMenu.displayArtistDetails();
			}
		});
		btnArtist.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnArtist.setBounds(426, 245, 235, 41);
		contentPane.add(btnArtist);
		
		JButton btnNewButton_2 = new JButton("Customer List");
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_2.setBounds(426, 322, 235, 41);
		contentPane.add(btnNewButton_2);
		
		JButton btnAddOrder = new JButton("Add Complete Order");
		btnAddOrder.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnAddOrder.setBounds(426, 397, 235, 41);
		contentPane.add(btnAddOrder);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogout.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		btnLogout.setBounds(488, 488, 111, 35);
		contentPane.add(btnLogout);
	}
	
	public static void readFile() throws FileNotFoundException {
		File file = new File("Artist.txt");
		Scanner inputFile = new Scanner(file);
			
		while(inputFile.hasNext()) {
			String data= inputFile.nextLine();
			String[] tok = data.split(",");
			Artist art = new Artist(tok[0],tok[1],tok[2],Integer.parseInt(tok[3]),Integer.parseInt(tok[4]));
			artistList.add(art);	
		}
		inputFile.close();
		}
	
	public static void writeFile() throws IOException {
		FileWriter write = new FileWriter("Artist.txt",false);
		PrintWriter outFile = new PrintWriter(write);
		for (int i=0; i<artistList.size();i++) {
				String aArtist = artistList.get(i).getName() + "," + artistList.get(i).getStyle() + ","
						+ artistList.get(i).getStatus() + "," + artistList.get(i).getMinP() + "," + artistList.get(i).getMaxP();
				
				outFile.println(aArtist);
		}
		outFile.close();
	}
}
