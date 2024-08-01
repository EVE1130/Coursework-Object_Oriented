import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ArtistPage extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearch;
	public static JTable tableArtist;
	private JTextField textFieldName;

	int row;
	static String tableHead []= new String[] {"Name", "Style", "Status", "Price Range"};
	public static DefaultTableModel dtm = new DefaultTableModel (tableHead,0);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//MainMenu.artistList.setVisible(true);
					//tableArtist.setModel(dtm);
					//displayArtistDetails();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void displayArtistDetails() {
		dtm.setRowCount(0);
		
		for (int i=0; i<MainMenu.artistList.size(); i++) {
			String range = String.valueOf(MainMenu.artistList.get(i).getMinP())+ "-" + String.valueOf(MainMenu.artistList.get(i).getMaxP());
			Object [] obj = {MainMenu.artistList.get(i).getName(),
							MainMenu.artistList.get(i).getStyle(),
							MainMenu.artistList.get(i).getStatus(),
							range};
			dtm.addRow(obj);
			
		}
	}

	/**
	 * Create the frame.
	 */
	public ArtistPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblArtist = new JLabel("Artist List");
		lblArtist.setFont(new Font("Times New Roman", Font.BOLD, 37));
		lblArtist.setBounds(44, 11, 180, 73);
		contentPane.add(lblArtist);
		
		String style1="Abstract",style2="Visual",style3="Photography",style4="Cubism";
		
		JComboBox dropStyle = new JComboBox();
		dropStyle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		dropStyle.setBounds(762, 164, 239, 34);
		dropStyle.addItem(style1);
	    dropStyle.addItem(style2);
	    dropStyle.addItem(style3);
	    dropStyle.addItem(style4);
	    dropStyle.setSelectedIndex(0);
		contentPane.add(dropStyle);
		
		JComboBox dropStatus = new JComboBox();
		dropStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		dropStatus.setBounds(762, 219, 239, 34);
		dropStatus.addItem("Alive");
		dropStatus.addItem("Dead");
		dropStatus.setSelectedIndex(0);
		contentPane.add(dropStatus);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnSearch.setBounds(897, 36, 104, 34);
		contentPane.add(btnSearch);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				DefaultTableModel obj = (DefaultTableModel) tableArtist.getModel();
				TableRowSorter<DefaultTableModel> obj1= new TableRowSorter<>(obj);
				tableArtist.setRowSorter(obj1);
				obj1.setRowFilter(RowFilter.regexFilter(textFieldSearch.getText()));
			}
		});
		textFieldSearch.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldSearch.setBounds(593, 38, 275, 34);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
			
		JButton btnBack = new JButton("Back");
		btnBack.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainMenu.MainMenu.setVisible(true);
				MainMenu.ArtistMenu.setVisible(false);
			}
		});
		btnBack.setBounds(44, 530, 104, 34);
		contentPane.add(btnBack);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MainMenu.artistList.get(row).setName(textFieldName.getText());
					MainMenu.artistList.get(row).setStyle(String.valueOf(dropStyle.getSelectedItem()));
					MainMenu.artistList.get(row).setStatus(String.valueOf(dropStatus.getSelectedItem()));
				} catch (UserDefinedException e1) {
					JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Validation Error", JOptionPane.INFORMATION_MESSAGE);		
				}
				displayArtistDetails();
				try {
					MainMenu.writeFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				textFieldName.setText("");
				dropStatus.setSelectedIndex(0);
				dropStyle.setSelectedIndex(0);
				
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnUpdate.setBounds(778, 530, 104, 34);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int select = JOptionPane.showConfirmDialog(null, "Confirm to delete this data?", "Delete", JOptionPane.YES_NO_OPTION);
				if(select==0) {
					dtm.removeRow(row);
					MainMenu.artistList.remove(row);
					displayArtistDetails();
					
					textFieldName.setText("");
					dropStatus.setSelectedIndex(0);
					dropStyle.setSelectedIndex(0);
					try {
						MainMenu.writeFile();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					JOptionPane.showMessageDialog(null, "Deleted Successfully", "Delete Existing Artist", JOptionPane.INFORMATION_MESSAGE);	
				}
			}
		});
		btnDelete.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnDelete.setBounds(897, 530, 104, 34);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(44, 95, 593, 414);
		contentPane.add(scrollPane);
		
		tableArtist = new JTable() {
			public boolean isCellEditable(int row,int column){
			    return false;//the 4th column is not editable
			 }
		};
		tableArtist.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				row=tableArtist.getSelectedRow();
				textFieldName.setText(dtm.getValueAt(row, 0).toString());
				
				int styleIn=0;
				if(dtm.getValueAt(row, 1).toString() == style2)
					styleIn = 1;
				else if (dtm.getValueAt(row, 1).toString() == style3)
					styleIn = 2;
				else if (dtm.getValueAt(row, 1).toString() == style4)
					styleIn = 3;
				dropStyle.setSelectedIndex(styleIn);
				
				int statusIn=0;
				if(dtm.getValueAt(row, 2).toString() == "Dead")
					statusIn = 1;
				dropStatus.setSelectedIndex(styleIn);
			}
		});
		scrollPane.setViewportView(tableArtist);
		tableArtist.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		
		JLabel lblArtistName = new JLabel("Artist Name");
		lblArtistName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblArtistName.setBounds(657, 109, 104, 34);
		contentPane.add(lblArtistName);
		
		JLabel lblStyle = new JLabel("Style");
		lblStyle.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStyle.setBounds(657, 164, 104, 34);
		contentPane.add(lblStyle);
		
		JLabel lblStatus = new JLabel("Live Status");
		lblStatus.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblStatus.setBounds(657, 219, 104, 34);
		contentPane.add(lblStatus);
		
		textFieldName = new JTextField();
		textFieldName.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		textFieldName.setBounds(762, 109, 239, 34);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnAdd.setBounds(657, 530, 104, 34);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sty = String.valueOf(dropStyle.getSelectedItem());
				String sts = String.valueOf(dropStatus.getSelectedItem());
				try {
					Artist artist = new Artist();
					artist.setName(textFieldName.getText());
					artist.setStyle(sty);
					artist.setStatus(sts);
					MainMenu.artistList.add(artist);
				}catch(UserDefinedException ex) {
					JOptionPane.showMessageDialog(null, "Error: "+ ex.getMessage(), "Validation Error", JOptionPane.INFORMATION_MESSAGE);
				}
				displayArtistDetails();
				try {
					MainMenu.writeFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				textFieldName.setText("");
				dropStatus.setSelectedIndex(0);
				dropStyle.setSelectedIndex(0);
			}
		});
		contentPane.add(btnAdd);
	}
}
