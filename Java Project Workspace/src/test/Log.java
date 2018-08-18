package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.Dialog.ModalExclusionType;
import java.awt.ComponentOrientation;

public class Log extends JFrame {
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Log frame = new Log();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
     Connection connection=null;
	/**
	 * Create the frame.
	 */
	
     
	public Log() {
		setBackground(new Color(0, 0, 0));
		setForeground(Color.LIGHT_GRAY);
		setTitle("Login");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rohit Nayar\\Desktop\\img\\Login-icon.png"));
		connection=Sqconnection.dbConnector();
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().setBackground(new Color(188, 143, 143));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Sign-in");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(47, 79, 79));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
                 String query="select * from login where uid=? and upassword=?";
                 PreparedStatement pst=connection.prepareStatement(query);
                 pst.setString(1,textField.getText());
                 pst.setString(2,passwordField.getText());
                 ResultSet rs=pst.executeQuery();
                 int count=0;
                 while(rs.next()) {
                	 count=count+1;
                	 
                 }
                 if(count==1)
                 {
                	 JOptionPane.showMessageDialog(null,"Correct user-id and password");
                	 ES es=new ES();
             		 es.setVisible(true);
                 }
                 else if(count>1)
                 {
                	 JOptionPane.showMessageDialog(null,"Duplicate user-id and password");
                 }
                 else
                 {
                	 JOptionPane.showMessageDialog(null,"Incorrect user-id and password\n Try Again");
                 }
                 rs.close();
                 pst.close();
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null,e1);
				
			}	
		}
		});
		btnNewButton.setBounds(160, 156, 89, 23);
		getContentPane().add(btnNewButton);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(150, 102, 112, 20);
		getContentPane().add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(150, 71, 112, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblUsername = new JLabel("User ID:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblUsername.setBounds(51, 74, 89, 14);
		getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPassword.setBounds(51, 105, 89, 14);
		getContentPane().add(lblPassword);
		
		JLabel lblLogin = new JLabel("Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLogin.setBounds(150, 11, 74, 34);
		getContentPane().add(lblLogin);
		
		JLabel lblNewLabel = new JLabel("");
		Image img=new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(269, 11, 155, 202);
		getContentPane().add(lblNewLabel);
	}
}
