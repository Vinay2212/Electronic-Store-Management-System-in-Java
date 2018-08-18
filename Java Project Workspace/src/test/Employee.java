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
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class Employee extends JFrame {

	private JPanel contentPane;
	private JTextField id;
	private JTextField name;
	private JTable table;
	public void refreshtable()
	{
		try
		{
        String query="select * from employee";
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
					Employee frame = new Employee();
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
	public Employee() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rohit Nayar\\Desktop\\img\\Preppy-icon.png"));
		setTitle("Employee");
		connection=Sqconnection.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 419);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(148, 0, 211));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Employee");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(86, 1, 105, 31);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Employee ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 70, 99, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Employee Name:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(10, 109, 119, 14);
		contentPane.add(lblNewLabel_2);
		
		id = new JTextField();
		id.setBounds(129, 69, 89, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		name = new JTextField();
		name.setBounds(129, 108, 89, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(246, 11, 393, 358);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton insert = new JButton("Insert");
		insert.setFont(new Font("Tahoma", Font.PLAIN, 13));
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	                 String query="insert into employee (employee_id,employee_name)values(?,?)";
	                 PreparedStatement pst=connection.prepareStatement(query);
	                 pst.setString(1,id.getText());
	                 pst.setString(2,name.getText());
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
		insert.setBounds(67, 183, 89, 23);
		contentPane.add(insert);
		
		JButton delete = new JButton("Delete");
		delete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
	                 String query="delete from employee where employee_id='"+id.getText()+"'";
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
		delete.setBounds(67, 238, 89, 23);
		contentPane.add(delete);
		
		JButton update = new JButton("Update");
		update.setFont(new Font("Tahoma", Font.PLAIN, 13));
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	                 String query="update employee set employee_id='"+id.getText()+"' ,employee_name='"+name.getText()+"'";
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
		update.setBounds(67, 291, 89, 23);
		contentPane.add(update);
		refreshtable();
	}

}
