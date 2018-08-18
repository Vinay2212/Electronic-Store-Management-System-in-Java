package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Toolkit;

public class Bill extends JFrame {
	

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bill frame = new Bill();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	private JTextField date;
	private JTextField quan;
	private JTextField total;
	private JTable table;
	private JComboBox<String>item;
	private JComboBox<String>customer;
	private JComboBox<String>employee;
	private JTable table_1;
	/**
	 * Create the frame.
	 */
    public int getSum(){
        int rowsCount = table_1.getRowCount();
        int sum = 0;
        for(int i = 0; i < rowsCount; i++){
            sum = sum+Integer.parseInt(table_1.getValueAt(i,5).toString());
        }
        return sum;
    }
    
	public void customercombo()
	{
		try {
            String query="select * from customer";
            PreparedStatement pst=connection.prepareStatement(query);
      
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
            	String query1=rs.getString("customer_id").toString();
                customer.addItem(query1);
            }
            pst.close();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
				
		}
	}
	public void stockcombo()
	{
		try {
            String query="select * from stock";
            PreparedStatement pst=connection.prepareStatement(query);
      
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
            	String query1=rs.getString("stock_id").toString();
                item.addItem(query1);
            }
            pst.close();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
				
		}
	}
	public void employeecombo()
	{
		try {
            String query="select * from employee";
            PreparedStatement pst=connection.prepareStatement(query);
      
            ResultSet rs=pst.executeQuery();
            while(rs.next())
            {
            	String query1=rs.getString("employee_id").toString();
                employee.addItem(query1);
            }
            pst.close();
		}
		catch(Exception e1)
		{
			e1.printStackTrace();
				
		}
	}
	public void stored_procedure()
	{
		int n = Integer.parseInt(quan.getText());
        String sql1="select quantity,price from stock where stock_id=?";
        try {
           PreparedStatement pst=connection.prepareStatement(sql1);
            pst.setString(1,item.getSelectedItem().toString());
             ResultSet rs=pst.executeQuery();
            
            int q = rs.getInt("quantity");
	    int price =rs.getInt("price");
            if(q>=n){
                int totalprice=(price*n);
            DefaultTableModel model=(DefaultTableModel)table_1.getModel();
            model.addRow(new Object[]{date.getText(),customer.getSelectedItem().toString(),employee.getSelectedItem().toString(),item.getSelectedItem().toString(),quan.getText(),totalprice});
           total.setText(Integer.toString(getSum()));//to display in the bill table
            rs.close();
                pst.close();
               
            try{
        String value1=date.getText();//sales code
        String value2=customer.getSelectedItem().toString();
        String value3=employee.getSelectedItem().toString();
        String value4=item.getSelectedItem().toString();
        String value5=quan.getText();
        String value6=Integer.toString(totalprice);
     
        new Sales(value1,value2,value3,value4,value5,value6).setVisible(false);
        
      }catch(Exception e){} 
        
      }
         else{
                JOptionPane.showMessageDialog(null,"no stock");
             }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
            
        }       
    }
	
	
	  private void View_table(){
	        try{
	            String query="select * from stock where stock_id=?";
	            
	            PreparedStatement pst=connection.prepareStatement(query);
	            String value=item.getSelectedItem().toString();
	            pst.setString(1,value);
	            ResultSet rs=pst.executeQuery();
	            table.setModel(DbUtils.resultSetToTableModel(rs));
	        }
	        catch(Exception e){
	            JOptionPane.showMessageDialog(null,e);
	    }
	    }
	public Bill() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rohit Nayar\\Desktop\\img\\Finance-Bill-icon.png"));
		setTitle("Bill");
		connection=Sqconnection.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 849, 458);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Date:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 66, 46, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Customer ID:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(121, 66, 111, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Item ID:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(272, 66, 76, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Quantity:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(395, 66, 61, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Employee ID:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(530, 66, 93, 14);
		contentPane.add(lblNewLabel_4);
		
		date = new JTextField();
		date.setBounds(51, 65, 66, 20);
		contentPane.add(date);
		date.setColumns(10);
		
		quan = new JTextField();
		quan.setBounds(459, 65, 61, 20);
		contentPane.add(quan);
		quan.setColumns(10);
		
		customer = new JComboBox<String>();
		customer.setFont(new Font("Tahoma", Font.PLAIN, 15));
		customer.setBounds(214, 62, 53, 22);
		contentPane.add(customer);
		
		item = new JComboBox<String>();
		item.setFont(new Font("Tahoma", Font.PLAIN, 15));
		item.setBounds(332, 62, 53, 22);
		contentPane.add(item);
		
		employee = new JComboBox<String>();
		employee.setFont(new Font("Tahoma", Font.PLAIN, 15));
		employee.setBounds(622, 62, 53, 22);
		contentPane.add(employee);
		
		JButton show = new JButton("Show");
		show.setFont(new Font("Tahoma", Font.PLAIN, 13));
		show.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				View_table();
				
			}
		});
		show.setBounds(10, 112, 89, 23);
		contentPane.add(show);
		
		JButton add = new JButton("Add to cart");
		add.setIcon(new ImageIcon("C:\\Users\\Rohit Nayar\\Desktop\\img\\add-item-icon.png"));
		add.setFont(new Font("Tahoma", Font.PLAIN, 13));
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				stored_procedure();
			}
	
		});
		add.setBounds(111, 112, 121, 23);
		contentPane.add(add);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 146, 813, 134);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton submit = new JButton("Submit");
		submit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(null,"Sucess");
		        table_1.setModel(new DefaultTableModel(null,new String[]{"date","employee_id","stock_id","quantity","price"}));
		        total.setText("");
			}
		});
		submit.setBounds(442, 385, 89, 23);
		contentPane.add(submit);
		
		JLabel lblNewLabel_5 = new JLabel("Total bill amount:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(214, 386, 119, 19);
		contentPane.add(lblNewLabel_5);
		
		total = new JTextField();
		total.setBounds(343, 386, 86, 20);
		contentPane.add(total);
		total.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(20, 291, 522, 83);
		contentPane.add(scrollPane_1);
		
		table_1 = new JTable();
        table_1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Date", "Customer_ID", "Employee_ID", "Stock_ID","Quantity","Price"
            }
        ));
        table_1.setOpaque(false);
		scrollPane_1.setViewportView(table_1);
		
		JLabel lblBill = new JLabel("Bill");
		lblBill.setHorizontalAlignment(SwingConstants.CENTER);
		lblBill.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblBill.setBounds(302, 11, 76, 44);
		contentPane.add(lblBill);
		stockcombo();
		customercombo();
		employeecombo();

		

		
	}
}
