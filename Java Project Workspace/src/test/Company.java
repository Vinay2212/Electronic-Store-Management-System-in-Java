package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.Color;

public class Company extends JFrame {

	private JPanel contentPane;
	public void refreshtable()
	{
		try
		{
        String query="select * from company";
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
					Company frame = new Company();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	private JTable table;
	private JTextField name;
	private JTextField number;
	private JTextField id;
	/**
	 * Create the frame.
	 */
	public Company() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rohit Nayar\\Desktop\\img\\company-building-icon.png"));
		setTitle("Company");
		connection=Sqconnection.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 613, 415);
		contentPane = new JPanel();
		contentPane.setBackground(Color.RED);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(228, 30, 359, 306);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 2, 2);
		contentPane.add(scrollPane_1);
		
		name = new JTextField();
		name.setBounds(132, 89, 86, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		number = new JTextField();
		number.setBounds(132, 135, 86, 20);
		contentPane.add(number);
		number.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Company ID:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 48, 130, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Company Name:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 90, 130, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Mobile Number:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 136, 119, 14);
		contentPane.add(lblNewLabel_2);
		
		JButton in = new JButton("Insert");
		in.setFont(new Font("Tahoma", Font.PLAIN, 13));
		in.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	                 String query="insert into company (company_id,company_name,phone_number)values(?,?,?)";
	                 PreparedStatement pst=connection.prepareStatement(query);
	                 pst.setString(1,id.getText());
	                 pst.setString(2,name.getText());
	                 pst.setString(3,number.getText());
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
		in.setBounds(68, 174, 89, 23);
		contentPane.add(in);
		
		JButton de = new JButton("Delete");
		de.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	                 String query="delete from company where company_id='"+id.getText()+"'";
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
		de.setBounds(68, 230, 89, 23);
		contentPane.add(de);
		
		JButton up = new JButton("Update");
		up.setFont(new Font("Tahoma", Font.PLAIN, 13));
		up.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
	                 String query="update company set company_id='"+id.getText()+"' ,company_name='"+name.getText()+"' ,phone_number='"+number.getText()+"'";
	                 PreparedStatement pst=connection.prepareStatement(query);
	                 
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
		up.setBounds(68, 287, 89, 23);
		contentPane.add(up);
		
		JLabel lblCompany = new JLabel("Company");
		lblCompany.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCompany.setBounds(54, 11, 75, 23);
		contentPane.add(lblCompany);
		
		id = new JTextField();
		id.setBounds(132, 47, 86, 20);
		contentPane.add(id);
		id.setColumns(10);
		refreshtable();
	}
}
