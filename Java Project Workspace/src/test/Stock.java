package test;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Toolkit;

public class Stock extends JFrame {

	private JPanel contentPane;
	private JComboBox<String> cid;
	public void refreshtable()
	{
		try
		{
        String query="select * from stock";
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
					Stock frame = new Stock();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void fillcombo()
	{
		try {
            String query="select * from company";
            PreparedStatement pst=connection.prepareStatement(query);
      
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
            	String query1=rs.getString("company_id").toString();
                cid.addItem(query1);
            }
            pst.close();
            rs.close();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
				
		}
	}
	
	Connection connection=null;
	private JTextField id;
	private JTextField name;
	private JTextField price;
	private JTextField quan;
	private JTable table;
	/**
	 * Create the frame.
	 */
	public Stock() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rohit Nayar\\Desktop\\img\\item-configuration-icon.png"));
		setTitle("Stock");
		connection=Sqconnection.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1227, 429);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 196, 222));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Stock");
		lblNewLabel.setBounds(59, 11, 61, 23);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Item ID:");
		lblNewLabel_1.setBounds(10, 48, 89, 14);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Item Name:");
		lblNewLabel_2.setBounds(10, 82, 89, 14);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Item Description:");
		lblNewLabel_3.setBounds(10, 107, 128, 14);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Quantity:");
		lblNewLabel_4.setBounds(10, 171, 89, 14);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Price:");
		lblNewLabel_5.setBounds(10, 196, 89, 14);
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Company ID:");
		lblNewLabel_6.setBounds(10, 230, 89, 14);
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		contentPane.add(lblNewLabel_6);
		
		id = new JTextField();
		id.setBounds(127, 47, 101, 20);
		contentPane.add(id);
		id.setColumns(10);
		
		name = new JTextField();
		name.setBounds(127, 81, 101, 20);
		contentPane.add(name);
		name.setColumns(10);
		
		JTextArea desc = new JTextArea();
		desc.setBounds(127, 106, 101, 59);
		contentPane.add(desc);
		
		price = new JTextField();
		price.setBounds(127, 195, 101, 20);
		contentPane.add(price);
		price.setColumns(10);
		
		JButton insert = new JButton("Insert");
		insert.setBounds(49, 288, 89, 23);
		insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
                String query="insert into stock (stock_id,stock_name,stock_description,quantity,price,company_id)values(?,?,?,?,?,?)";
                PreparedStatement pst=connection.prepareStatement(query);
                String value=cid.getSelectedItem().toString();
               
                pst.setString(1,id.getText());
                pst.setString(2,name.getText());
                pst.setString(3,desc.getText());
                pst.setString(4,quan.getText());
                pst.setString(5,price.getText());
                pst.setString(6,value);
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
		insert.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(insert);
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	                 String query="delete from stock where stock_id='"+id.getText()+"'";
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
		delete.setBounds(49, 322, 89, 23);
		delete.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(delete);
		
		JButton update = new JButton("Update");
		update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String value=cid.getSelectedItem().toString();
	                 String query="update stock set stock_id='"+id.getText()+"' ,stock_name='"+name.getText()+"' ,stock_description='"+desc.getText()+"' ,quantity='"+quan.getText()+"',price='"+price.getText()+"',company_id='"+value+"'";
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
		update.setBounds(49, 356, 89, 23);
		update.setFont(new Font("Tahoma", Font.PLAIN, 13));
		contentPane.add(update);
		
		quan = new JTextField();
		quan.setBounds(127, 170, 101, 20);
		contentPane.add(quan);
		quan.setColumns(10);
		
		cid = new JComboBox<String>();
		cid.setFont(new Font("Tahoma", Font.PLAIN, 15));
		cid.setBounds(127, 228, 101, 22);
		contentPane.add(cid);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(235, 11, 966, 357);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		fillcombo();
		refreshtable();
	}
}
