package activity;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import attr.*;
import java.sql.*;
import java.util.Scanner;
import java.io.*;

public class Product extends JFrame implements ActionListener
{
	private JPanel panel;
	private JLabel title, header, choice;
	private JTable table;
	private DefaultTableModel model;
	private JButton back, addButton, Stock;
	public Product()
	{
		this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);
	
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Theme.BACKGROUND_PANEL);
		
		title = new JLabel("Product List");
		title.setBounds(30, 40, 650, 75);
		title.setOpaque(true);
		title.setBorder(new EmptyBorder(0,20,0,0));
		title.setFont(Theme.FONT_TITLE);
		title.setForeground(Theme.COLOR_TITLE);
		panel.add(title);
		
		
		header = new JLabel();
		header.setBackground(Theme.BACKGROUND_HEADER);
		header.setOpaque(true);
		header.setBounds(0, 0, Theme.GUI_WIDTH, 75);
		panel.add(header);
		
		back = new JButton("Back");
		back.setBounds(Theme.GUI_WIDTH-120, 80, Theme.BUTTON_PRIMARY_WIDTH+20,30);
		back.setFont(Theme.FONT_BUTTON);
		back.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		back.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		back.addActionListener(this);
		add(back);
		
		Stock = new JButton("Stock");
		Stock.setBounds(Theme.GUI_WIDTH-120,150, Theme.BUTTON_PRIMARY_WIDTH+20,30);
		Stock.setFont(Theme.FONT_BUTTON);
		Stock.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
	    Stock.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		Stock.addActionListener(this);
		add(Stock);
		
		addButton = new JButton("+ADD");
		addButton.setBounds(Theme.GUI_WIDTH-120, 115, Theme.BUTTON_PRIMARY_WIDTH+20,30);
		addButton.setFont(Theme.FONT_BUTTON);
		addButton.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		addButton.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		addButton.addActionListener(this);
		add(addButton);

		model=new DefaultTableModel(0,6);
		table=new JTable(model);
		table.setRowHeight(30);
		JScrollPane pane=new JScrollPane(table);	
		pane.setBounds(40,185,720,300);
		String[]header={"ID","Company Reg:","Name","CostPrice", "Sales Price","Quantity"};
		model.setColumnIdentifiers(header);	
		//model.insertRow(0, new Object[] { "Sadia", "0233", "mirpur", "2345" });;
		
		add(pane);
		
		int result = -1;
		String query = "select Productid,companyReg, name, costPrice, salesprice,availableQuantity from product";     
        Connection con = null;
       	Statement st = null;
		ResultSet rs = null;
		System.out.println(query);

		 try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection(Database.HOST_URI, Database.USER, Database.PASSWORD);
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query);//getting result
			System.out.println("results received");
			
			while(rs.next()) {
		int i=0;
		model.insertRow(i, new Object[] { rs.getString("productID"),rs.getString("companyReg"),rs.getString("name"), rs.getInt("costprice"), rs.getInt("salesprice"), rs.getInt("availableQuantity") });
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
		
     this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(panel);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource().equals(back)) {
			Admin ob = new Admin();
			ob.setVisible(true);
                        this.setVisible(false);
		}
		if (ae.getSource().equals(addButton)) {
			AddProduct ob=new AddProduct();
			ob.setVisible(true);
            this.setVisible(false);
		}
		
		if (ae.getSource().equals(Stock)) {
			stock ob=new stock();
			ob.setVisible(true);
            this.setVisible(false);
		}

	}
}