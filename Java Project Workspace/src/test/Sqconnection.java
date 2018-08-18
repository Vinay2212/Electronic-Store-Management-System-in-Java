package test;
import java.sql.*;
import javax.swing.*;


public class Sqconnection {
Connection conn=null;
public static Connection dbConnector()
{
	try {
		Class.forName("org.sqlite.JDBC");
		Connection conn=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Rohit Nayar\\Desktop\\ESM.sqlite");
		return conn;
	}
	catch(Exception e)
	{
		JOptionPane.showMessageDialog(null, e);
		return null;	
	}
}
}
