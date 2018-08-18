package test;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Toolkit;

public class Sales extends JFrame {
	ResultSet rs=null;
    PreparedStatement pst=null;
	private JPanel contentPane;
	private JTextField date;
	private JTextField total1;
	private JTable table1;
	private void Quantity_trigger(){
	    String sql3="select * from Stock where stock_id='"+value4+"'";
	 try {
	     pst=connection.prepareStatement(sql3);
	     rs=pst.executeQuery();
	     int Quantity=rs.getInt("Quantity");
	      Quantity=Quantity-Integer.valueOf(value5);
	      rs.close();
	     String sql4="update stock set quantity=='"+Quantity+"' where stock_id='"+value4+"'";
	     pst=connection.prepareStatement(sql4);
	     pst.execute();
	     pst.close();
	 } catch (Exception e) {
	     JOptionPane.showMessageDialog(null,e);
	}
	 

	}
	 private void Totalsales_trigger(){
	      String sql1="select * from employee where employee_id='"+value3+"'";
	 try {
	     pst=connection.prepareStatement(sql1);
	     rs=pst.executeQuery();
	     int total=rs.getInt("total_sales");
	      rs.close();
	      total=total+Integer.valueOf(value6);
	     String sql2="update employee set total_sales=='"+total+"' where employee_id='"+value3+"'";
	     pst=connection.prepareStatement(sql2);
	     pst.execute();
	     pst.close();
	      
	 } catch (Exception e) {
	    JOptionPane.showMessageDialog(null,e);
	 }
	     
	 }
	 
	 private void Sales_table(){
	     try{
	         String query="select * from Sales";
	         pst=connection.prepareStatement(query);
	         rs=pst.executeQuery();
	         table1.setModel(DbUtils.resultSetToTableModel(rs));
	     }
	     catch(Exception e){
	         JOptionPane.showMessageDialog(null,e);
	 }finally{
	         try{
	             rs.close();
	             pst.close();
	         }
	         catch(Exception e){
	         }

	     }
	     total1.setText(Integer.toString(getSum()));
	 }
	 private void Date(){
	     try{
	         String query="select * from Sales where order_date=?";
	         pst=connection.prepareStatement(query);
	         pst.setString(1,date.getText());
	         rs=pst.executeQuery();
	         table1.setModel(DbUtils.resultSetToTableModel(rs));
	        
	       }
	     catch(Exception e){
	         JOptionPane.showMessageDialog(null,e);
	 }finally{
	         try{
	             rs.close();
	             pst.close();
	         }
	         catch(Exception e){
	         }
	     
	 total1.setText(Integer.toString(getSum()));
	 }}
	 public int getSum(){
	     int rowsCount = table1.getRowCount();
	     int sum = 0;
	     for(int i = 0; i < rowsCount; i++){
	         sum = sum+Integer.parseInt(table1.getValueAt(i, 5).toString());
	     }
	     return sum;
	 }

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sales frame = new Sales();
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
	
		public Sales() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Rohit Nayar\\Desktop\\img\\Sale-icon.png"));
		setTitle("Sales");
	    
		connection=Sqconnection.dbConnector();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 680, 341);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 139, 139));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Enter Date:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(10, 41, 96, 14);
		contentPane.add(lblNewLabel);
		
		date = new JTextField();
		date.setBounds(97, 38, 86, 20);
		contentPane.add(date);
		date.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Total Sales:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(293, 41, 76, 14);
		contentPane.add(lblNewLabel_1);
		
		total1 = new JTextField();
		total1.setBounds(383, 38, 86, 20);
		contentPane.add(total1);
		total1.setColumns(10);
		
		JButton viewall = new JButton("View All");
		viewall.setFont(new Font("Tahoma", Font.PLAIN, 13));
		viewall.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Sales_table();
			}
		});
		viewall.setBounds(479, 37, 89, 23);
		contentPane.add(viewall);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 636, 207);
		contentPane.add(scrollPane);
		
		table1 = new JTable();
        table1.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "Date", "Customer_ID", "Employee_ID","Item_ID", "Quantity", "Price"
                }
            ));
		scrollPane.setViewportView(table1);
		
		JButton view = new JButton("View");
		view.setFont(new Font("Tahoma", Font.PLAIN, 13));
		view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date();
				
			}
		});
		view.setBounds(194, 37, 89, 23);
		contentPane.add(view);
		
		JLabel lblSales = new JLabel("Sales");
		lblSales.setHorizontalAlignment(SwingConstants.CENTER);
		lblSales.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSales.setBounds(277, 7, 60, 23);
		contentPane.add(lblSales);

	}
		String value1,value2,value3,value4,value5,value6;
        public Sales(String val1, String val2, String val3, String val4, String val5, String val6) {
		    connection=Sqconnection.dbConnector();
			this.value1=val1;
			this.value2=val2;
			this.value3=val3;
			this.value4=val4;
			this.value5=val5;
			this.value6=val6;
			try {
		        String query="insert into sales(order_date,customer_id,employee_id,stock_id,quantity,price)values(?,?,?,?,?,?)";
		        PreparedStatement pst=connection.prepareStatement(query);
		        pst.setString(1,value1);
		        pst.setString(2,value2);
		        pst.setString(3,value3);
		        pst.setString(4,value4);
		        pst.setString(5,value5);
		        pst.setString(6,value6);
		        pst.execute();
		        JOptionPane.showMessageDialog(null,"Data Saved");
		        
		      
		        pst.close();
			}
			catch(Exception e1)
			{
				e1.printStackTrace();
					
			}
			  Quantity_trigger();
		      Totalsales_trigger();
			  Sales_table();
		}	
}


