package activity;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import attr.*;
import java.util.Scanner;
import java.io.*;
import java.sql.*;

public class SalesHistroy extends JFrame implements ActionListener
{
	private JTable table;
    	private DefaultTableModel model;
	private JButton back;
	private JScrollPane pane;
	public SalesHistroy()
	{

				

		this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		JPanel panel= new JPanel();
		panel.setLayout(null);
		panel.setBackground(Theme.BACKGROUND_PANEL);

		JLabel title = new JLabel("Sales History ");
		title.setBounds(30, 40, Theme.GUI_WIDTH-200,75);
		title.setOpaque(true);
		title.setBorder(new EmptyBorder(0,20,0,0));
		title.setFont(Theme.FONT_TITLE);
		title.setForeground(Theme.COLOR_TITLE);
		panel.add(title);
				
		back = new JButton("Back");
		back.setBounds(Theme.GUI_WIDTH-140, 40, Theme.BUTTON_PRIMARY_WIDTH+20,30);
		back.setFont(Theme.FONT_BUTTON);
		back.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		back.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		back.addActionListener(this);
		panel.add(back);
		
		model=new DefaultTableModel(0,5);
		table=new JTable(model);
		table.setRowHeight(20);
		String[]header={"billID", "customerID", "productID", "quantity","date"};
		model.setColumnIdentifiers(header);	
		table.setModel(model);
		pane=new JScrollPane(table);
		pane.setBounds(40,135,720,300);	
		panel.add(pane);
		
		int result = -1;
		String query = "select * from billdetails;";     
                Connection con = null;
       	 	Statement st = null;
		ResultSet rs = null;
		System.out.println(query);
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection(Database.HOST_URI, Database.USER,"2222");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
			
			while(rs.next()) {
		int i=0;
		
		model.insertRow(i, new Object[] { rs.getString("billID"),rs.getString("customerID"), rs.getString                ("productID"), rs.getString("quantity"), rs.getString("date")});
              
		i++;
				
				
			}
		}
        catch(Exception ex) {
			System.out.println("Exception : " +ex.getMessage());
			ex.printStackTrace();
        }
        finally {
            try {
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex) {}
        }
	
		this.add(panel);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(back)) {
			Admin ob = new Admin();
			ob.setVisible(true);
            this.setVisible(false);
		}
	}
}