import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JTextField;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Library {

	private JFrame frame;
	private JTable table;
	private JTextField txtbname;
	private JTextField txtedition;
	private JTextField txtprice;
	private JTextField txtbid;

	/**
	 * Launch the application. 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Library window = new Library();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Library() {
		initialize();
		Connect();
		table_load();
	}
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table_1;
	private JTextField txtid;

	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","12345");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	        	   ex.printStackTrace();
	        }

	    }
	 
	 public void table_load()
	     {
	      try
	      {
	     pst = con.prepareStatement("select * from book");
	     rs = pst.executeQuery();
	     table_1.setModel(DbUtils.resultSetToTableModel(rs));
	 }
	      catch (SQLException e)
	      {
	      e.printStackTrace();
	   }
	     }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 847, 509);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Library Management System");
		lblNewLabel.setForeground(UIManager.getColor("ToolBar.dockingForeground"));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(228, 23, 386, 44);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel.setBounds(439, 97, 324, 219);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblBookId_1 = new JLabel("Book ID:");
		lblBookId_1.setBounds(27, 34, 78, 16);
		panel.add(lblBookId_1);
		
		JLabel lblBookName = new JLabel("Book Name:");
		lblBookName.setBounds(27, 69, 78, 16);
		panel.add(lblBookName);
		
		JLabel lblEdition = new JLabel("Edition:");
		lblEdition.setBounds(27, 101, 78, 16);
		panel.add(lblEdition);
		
		JLabel lblBookName_1_1 = new JLabel("Price:");
		lblBookName_1_1.setBounds(27, 133, 78, 16);
		panel.add(lblBookName_1_1);
		
		txtid = new JTextField();
		txtid.setColumns(10);
		txtid.setBounds(117, 32, 173, 22);
		panel.add(txtid);
		
		txtbname = new JTextField();
		txtbname.setBounds(117, 67, 173, 22);
		panel.add(txtbname);
		txtbname.setColumns(10);
		
		txtedition = new JTextField();
		txtedition.setColumns(10);
		txtedition.setBounds(117, 99, 173, 22);
		panel.add(txtedition);
		
		txtprice = new JTextField();
		txtprice.setColumns(10);
		txtprice.setBounds(117, 131, 173, 22);
		panel.add(txtprice);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBackground(UIManager.getColor("activeCaption"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id,bname,edition,price;
				id = txtid.getText();
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
							
				 try {
					pst = con.prepareStatement("insert into book(id,name,edition,price)values(?,?,?,?)");
					pst.setString(1, id);
					pst.setString(2, bname);
					pst.setString(3, edition);
					pst.setString(4, price);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
					
					txtid.setText("");
					txtbname.setText("");
					txtedition.setText("");
					txtprice.setText("");
					txtid.requestFocus();
				   }

				catch (SQLException e1) 
			        {
									
				e1.printStackTrace();
			        }
			}
		}
			);
		btnNewButton.setBounds(27, 170, 78, 25);
		panel.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit\r\n");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBackground(UIManager.getColor("activeCaption"));
		btnExit.setBounds(117, 170, 78, 25);
		panel.add(btnExit);
		
		JButton btnNewButton_1_1 = new JButton("Clear");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtid.setText("");
				txtbname.setText("");
	            txtedition.setText("");
	            txtprice.setText("");
			}
		});
		btnNewButton_1_1.setBackground(UIManager.getColor("activeCaption"));
		btnNewButton_1_1.setBounds(207, 170, 83, 25);
		panel.add(btnNewButton_1_1);
		
		table = new JTable();
		table.setBounds(52, 97, 1, 1);
		frame.getContentPane().add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Search", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_1.setBounds(439, 329, 324, 91);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblBookId = new JLabel("Book ID:");
		lblBookId.setBounds(31, 40, 57, 16);
		panel_1.add(lblBookId);
		
		txtbid = new JTextField();
		txtbid.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				try {
			          
		            String id = txtbid.getText();
		 
		                pst = con.prepareStatement("select id,name,edition,price from book where id = ?");
		                pst.setString(1, id);
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		                
		            	String Id = rs.getString(1);
		                String name = rs.getString(2);
		                String edition = rs.getString(3);
		                String price = rs.getString(4);
		                
		                txtid.setText(Id);
		                txtbname.setText(name);
		                txtedition.setText(edition);
		                txtprice.setText(price);
		                
		                
		            }  
		            else
		            {
		            	txtid.setText("");
		            	txtbname.setText("");
		            	txtedition.setText("");
		                txtprice.setText("");
		                
		            }
		            
		 
		 
		        }
		catch (SQLException ex) {
		          
		        }
			}
		});
		txtbid.setColumns(10);
		txtbid.setBounds(88, 37, 40, 22);
		panel_1.add(txtbid);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id,bname,edition,price,bid;
				
				id = txtid.getText();
				bname = txtbname.getText();
				edition = txtedition.getText();
				price = txtprice.getText();
				bid  = txtbid.getText();
				
				 try {
						pst = con.prepareStatement("update book set id=?,name=?,edition=?,price=? where id =?");
						pst.setString(1, id);
						pst.setString(2, bname);
			            pst.setString(3, edition);
			            pst.setString(4, price);
			            pst.setString(5, bid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Update!!!!!");
			            table_load();
			            
			            txtid.setText("");
			            txtbname.setText("");
			            txtedition.setText("");
			            txtprice.setText("");
			            txtid.requestFocus();
					}

		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
			}
		});
		btnUpdate.setBackground(UIManager.getColor("activeCaption"));
		btnUpdate.setBounds(140, 36, 79, 25);
		panel_1.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
	            String bid;
				bid  = txtbid.getText();
				
				 try {
						pst = con.prepareStatement("delete from book where id =?");
				
			            pst.setString(1, bid);
			            pst.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Record Delete!!!!!");
			            table_load();
			            
			            txtid.setText("");
			            txtbname.setText("");
			            txtedition.setText("");
			            txtprice.setText("");
			            txtid.requestFocus();
					}

		            catch (SQLException e1) {
						
						e1.printStackTrace();
					}
			}
		});
		btnDelete.setBackground(UIManager.getColor("activeCaption"));
		btnDelete.setBounds(231, 36, 73, 25);
		panel_1.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(65, 97, 324, 316);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
	}
}
