package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import java.awt.Color;

public class Customer extends JFrame {

	private JPanel contentPane;
	private JTextField phone;
	private JTextField name;
	private JTextField id;
	private JTable table;
	private JTextArea address;
	public void refreshtable()
	{
		try
		{
        String query="select * from customer";
        PreparedStatement pst=connection.prepareStatement(query);
        ResultSet rs=pst.executeQuery();
        table.setModel(DbUtils.resultSetToTableModel(rs));
        rs.close();
        pst.close();
	}
	catch(Exception e1)
	{
		e1.printStackTrace();
		
	}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer();
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
	public Customer() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rohit Nayar\\Desktop\\img\\Users-icon.png"));
		setTitle("Customer");
		connection=Sqconnection.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 857, 428);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(221, 160, 221));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Customer");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(59, 11, 112, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Customer ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 57, 122, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Customer Name:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 82, 148, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Customer Address:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(10, 107, 132, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel p = new JLabel("Customer Phone:");
		p.setFont(new Font("Tahoma", Font.PLAIN, 15));
		p.setBounds(10, 178, 132, 14);
		contentPane.add(p);
		
		phone = new JTextField();
		phone.setBounds(158, 177, 95, 20);
		contentPane.add(phone);
		phone.setColumns(10);
		
		name = new JTextField();
		name.setBounds(158, 79, 95, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		id = new JTextField();
		id.setBounds(158, 54, 94, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		address = new JTextArea();
		address.setBounds(158, 104, 85, 70);
		contentPane.add(address);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(262, 11, 579, 367);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton insert = new JButton("Insert");
		insert.setFont(new Font("Tahoma", Font.PLAIN, 13));
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
	                 String query="insert into customer (customer_id,customer_name,customer_address,customer_phone)values(?,?,?,?)";
	                 PreparedStatement pst=connection.prepareStatement(query);
	                 pst.setString(1,id.getText());
	                 pst.setString(2,name.getText());
	                 pst.setString(3,address.getText());
	                 pst.setString(4,phone.getText());
	                 pst.execute();
	                 JOptionPane.showMessageDialog(null,"Data Saved");
	                
	                 pst.close();
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
						
				}	
				refreshtable();
			}
		});
		insert.setBounds(26, 223, 89, 23);
		contentPane.add(insert);
		
		JButton delete = new JButton("Delete");
		delete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
	                 String query="delete from customer where customer_id='"+id.getText()+"'";
	                 PreparedStatement pst=connection.prepareStatement(query);
	                 pst.execute();
	                 JOptionPane.showMessageDialog(null,"Data Deleted");
	                
	                 pst.close();
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
						
				}
				refreshtable();
			}
		});
		delete.setBounds(26, 273, 89, 23);
		contentPane.add(delete);
		
		JButton update = new JButton("Update");
		update.setFont(new Font("Tahoma", Font.PLAIN, 13));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				try {
                String query="update customer set customer_id='"+id.getText()+"' ,customer_name='"+name.getText()+"' ,customer_address='"+address.getText()+"',customer_phone='"+phone.getText()+"'";
                PreparedStatement pst=connection.prepareStatement(query);
                pst.execute();
                JOptionPane.showMessageDialog(null,"Data Updated");
               
                pst.close();
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
					
			}
				refreshtable();
			}
		});
		update.setBounds(26, 326, 89, 23);
		contentPane.add(update);
		refreshtable();

	}

}
