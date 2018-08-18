package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.JToolBar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Desktop;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Cursor;
import java.awt.Dimension;

public class ES extends JFrame {
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ES frame = new ES();
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
	public ES() {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rohit Nayar\\Desktop\\img\\Apple-Store-Louvre-Front-icon.png"));
		setTitle("Electronic Store Home");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 707, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(null);
		menuBar.setForeground(new Color(107, 142, 35));
		menuBar.setBackground(new Color(107, 142, 35));
		menuBar.setBounds(0, 0, 691, 60);
		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("User");
		mnNewMenu.setBackground(new Color(128, 128, 0));
		mnNewMenu.setOpaque(true);
		mnNewMenu.setForeground(Color.BLACK);
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Create User");
		mntmNewMenuItem.setIcon(new ImageIcon("C:\\Users\\Rohit Nayar\\Desktop\\img\\Administrator-icon.png"));
		mntmNewMenuItem.setBackground(new Color(30, 144, 255));
		mntmNewMenuItem.setOpaque(true);
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                
				try {
					 Sign_up su=new Sign_up();
             		 su.setVisible(true);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Sign-Out");
		mntmNewMenuItem_1.setIcon(new ImageIcon("C:\\Users\\Rohit Nayar\\Desktop\\img\\sign-left-icon.png"));
		mntmNewMenuItem_1.setBackground(new Color(30, 144, 255));
		mntmNewMenuItem_1.setOpaque(true);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		JMenuItem mntmRemoveUser = new JMenuItem("Remove User");
		mntmRemoveUser.setIcon(new ImageIcon("C:\\Users\\Rohit Nayar\\Desktop\\img\\user-delete-icon.png"));
		mntmRemoveUser.setBackground(new Color(30, 144, 255));
		mntmRemoveUser.setOpaque(true);
		mntmRemoveUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 Remove c=new Remove();
         		 c.setVisible(true);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
				
			}
		});
		mntmRemoveUser.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnNewMenu.add(mntmRemoveUser);
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenu mnNewMenu_1 = new JMenu("Company");
		mnNewMenu_1.setBackground(new Color(128, 128, 0));
		mnNewMenu_1.setOpaque(true);
		mnNewMenu_1.setForeground(Color.BLACK);
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmInsertdeleteupdate = new JMenuItem("Insert/Delete/Update");
		mntmInsertdeleteupdate.setIcon(new ImageIcon("C:\\Users\\Rohit Nayar\\Desktop\\img\\company-building-icon.png"));
		mntmInsertdeleteupdate.setBackground(new Color(30, 144, 255));
		mntmInsertdeleteupdate.setOpaque(true);
		mntmInsertdeleteupdate.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mntmInsertdeleteupdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 Company c=new Company();
           		 c.setVisible(true);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		mnNewMenu_1.add(mntmInsertdeleteupdate);
		
		JMenu mnNewMenu_2 = new JMenu("Stock");
		mnNewMenu_2.setBackground(new Color(128, 128, 0));
		mnNewMenu_2.setOpaque(true);
		mnNewMenu_2.setForeground(Color.BLACK);
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmInsertdeleteupdate_1 = new JMenuItem("Insert/Delete/Update");
		mntmInsertdeleteupdate_1.setIcon(new ImageIcon("C:\\Users\\Rohit Nayar\\Desktop\\img\\item-configuration-icon.png"));
		mntmInsertdeleteupdate_1.setBackground(new Color(30, 144, 255));
		mntmInsertdeleteupdate_1.setOpaque(true);
		mntmInsertdeleteupdate_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 Stock c=new Stock();
          		 c.setVisible(true);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
			}

		});
		mntmInsertdeleteupdate_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnNewMenu_2.add(mntmInsertdeleteupdate_1);
		
		JMenu mnNewMenu_3 = new JMenu("Employee");
		mnNewMenu_3.setBackground(new Color(128, 128, 0));
		mnNewMenu_3.setOpaque(true);
		mnNewMenu_3.setForeground(Color.BLACK);
		mnNewMenu_3.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_3);
		
		JMenuItem mntmInsertdeleteupdate_2 = new JMenuItem("Insert/Delete/Update");
		mntmInsertdeleteupdate_2.setIcon(new ImageIcon("C:\\Users\\Rohit Nayar\\Desktop\\img\\Preppy-icon.png"));
		mntmInsertdeleteupdate_2.setBackground(new Color(30, 144, 255));
		mntmInsertdeleteupdate_2.setOpaque(true);
		mntmInsertdeleteupdate_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 Employee c=new Employee();
          		 c.setVisible(true);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		mntmInsertdeleteupdate_2.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnNewMenu_3.add(mntmInsertdeleteupdate_2);
		
		JMenu mnNewMenu_4 = new JMenu("Customer");
		mnNewMenu_4.setBackground(new Color(128, 128, 0));
		mnNewMenu_4.setOpaque(true);
		mnNewMenu_4.setForeground(Color.BLACK);
		mnNewMenu_4.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_4);
		
		JMenuItem mntmInsertupdatedelete = new JMenuItem("Insert/Update/Delete");
		mntmInsertupdatedelete.setIcon(new ImageIcon("C:\\Users\\Rohit Nayar\\Desktop\\img\\Users-icon.png"));
		mntmInsertupdatedelete.setBackground(new Color(30, 144, 255));
		mntmInsertupdatedelete.setOpaque(true);
		mntmInsertupdatedelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					 Customer c=new Customer();
          		 c.setVisible(true);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		mntmInsertupdatedelete.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnNewMenu_4.add(mntmInsertupdatedelete);
		
		JMenu mnNewMenu_5 = new JMenu("Sales");
		mnNewMenu_5.setBackground(new Color(128, 128, 0));
		mnNewMenu_5.setOpaque(true);
		mnNewMenu_5.setForeground(Color.BLACK);
		mnNewMenu_5.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmBill = new JMenuItem("Bill");
		mntmBill.setIcon(new ImageIcon("C:\\Users\\Rohit Nayar\\Desktop\\img\\Finance-Bill-icon.png"));
		mntmBill.setBackground(new Color(30, 144, 255));
		mntmBill.setOpaque(true);
		mntmBill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 Bill c=new Bill();
         		 c.setVisible(true);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		mntmBill.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnNewMenu_5.add(mntmBill);
		
		JMenuItem mntmSales = new JMenuItem("Sales");
		mntmSales.setIcon(new ImageIcon("C:\\Users\\Rohit Nayar\\Desktop\\img\\Sale-icon.png"));
		mntmSales.setBackground(new Color(30, 144, 255));
		mntmSales.setOpaque(true);
		mntmSales.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					 Sales c=new Sales();
         		 c.setVisible(true);
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1);
				}
			}
		});
		mntmSales.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		mnNewMenu_5.add(mntmSales);
		
		JMenuItem menuItem = new JMenuItem("");
		menuItem.setBackground(new Color(128, 128, 0));
		menuItem.setOpaque(true);
		menuBar.add(menuItem);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setOpaque(true);
		lblNewLabel.setBackground(Color.BLACK);
		Image img=new ImageIcon(this.getClass().getResource("/screen.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(0, 59, 691, 381);
		contentPane.add(lblNewLabel);
	}
}
