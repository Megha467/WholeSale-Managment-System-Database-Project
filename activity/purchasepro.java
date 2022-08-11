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

public class purchasepro extends JFrame implements ActionListener
{
	private JButton add,checkout,close;
	private JLabel pro_id, id, qty, choice,bil,bill_id,dateFormat,format;
	private JPanel panel;
	private JLabel title, header;
	private JTextField ct_id, Pro_name, qunty,bill,date;
	private int amount=0;
	int bill_no,salesPrice;
	purchasepro()
	{
		super();

		int result = -1;		
		 String query = "select billID from billdetails order by billid desc limit 1;";     
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
			
			//boolean flag = false;
			while(rs.next()){ 
			this.bill_no=rs.getInt("billID");}
			System.out.println("bill"+bill_no);
			this.bill_no=bill_no+1;                           
			
		}
        catch(Exception ex) {
			System.out.println("Exception : " +ex.getMessage());
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
		 
		this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Theme.BACKGROUND_PANEL);
		
		title = new JLabel("Purchase Product");
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
		
		choice = new JLabel("Insert Data For Product");
		choice.setBounds(100, 150, 120, 30);
		choice.setFont(Theme.FONT_REGULAR);
		panel.add(choice);
		
		bill_id = new JLabel("bill_id: "+bill_no);
		bill_id.setBounds(Theme.GUI_WIDTH-130, 120, 100,30);
		bill_id.setFont(Theme.FONT_REGULAR);
		panel.add(bill_id);

		dateFormat = new JLabel("Date");
		dateFormat.setBounds(515, 250, 50, 30);
		dateFormat.setFont(Theme.FONT_REGULAR);
		panel.add(dateFormat);

		format = new JLabel("format: Y-M-D");
		format.setBounds(515, 300, 400, 30);
		format.setFont(Theme.FONT_REGULAR);
		panel.add(format);
		
		id = new JLabel("Cust_ID");
		id.setBounds(100, 200, 120, 30);
		id.setFont(Theme.FONT_REGULAR);
		panel.add(id);

		bil = new JLabel("Bill:");
		bil.setBounds(515, 200, 50, 30);
		bil.setFont(Theme.FONT_REGULAR);
		panel.add(bil);
        
		pro_id = new JLabel("Pro_Name");
		pro_id.setBounds(100, 250, 120, 30);
		pro_id.setFont(Theme.FONT_REGULAR);
		panel.add(pro_id);
		
		
		qty = new JLabel("Quantity");
		qty.setBounds(100, 300, 120, 30);
		qty.setFont(Theme.FONT_REGULAR);
		panel.add(qty);
	
		ct_id = new JTextField();
		ct_id.setBounds(210, 200, 220, 30);
		ct_id.setFont(Theme.FONT_INPUT);
		panel.add(ct_id);

		bill = new JTextField();
		bill.setBounds(565, 200, 150, 30);
		bill.setFont(Theme.FONT_INPUT);
		panel.add(bill);
		
		qunty = new JTextField();
		qunty.setBounds(210, 300, 220, 30);
		qunty.setFont(Theme.FONT_INPUT);
		panel.add(qunty);
		
		Pro_name = new JTextField();
		Pro_name.setBounds(210, 250, 220, 30);
		Pro_name.setFont(Theme.FONT_INPUT);
		panel.add(Pro_name);

		date = new JTextField();
		date.setBounds(565, 250, 150, 30);
		date.setFont(Theme.FONT_INPUT);
		panel.add(date);

		
		
		close = new JButton("Close");
		close.setBounds(Theme.GUI_WIDTH-120, 80, 100,30);
		close.setFont(Theme.FONT_BUTTON);
		close.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		close.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		close.addActionListener(this);
		panel.add(close);

		add = new JButton("Purchase");
		add.setBounds(210, 350, 150,30);
		add.setFont(Theme.FONT_BUTTON);
		add.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		add.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		add.addActionListener(this);
		panel.add(add);

		checkout = new JButton("Checkout");
		checkout.setBounds(210, 400, 150,30);
		checkout.setFont(Theme.FONT_BUTTON);
		checkout.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		checkout.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		checkout.addActionListener(this);
		panel.add(checkout);
		
		this.add(panel); 
		
		
	}

	
	public void actionPerformed(ActionEvent ae){
	
		int quantity=0;
		if (ae.getSource().equals(close)) {
			Admin ob = new Admin();
			ob.setVisible(true);
            this.setVisible(false);
		}
		if (ae.getSource().equals(add)) {
		String query="select availableQuantity from product where productID ='"+Pro_name.getText()+"';";

		String query1 = "INSERT INTO billdetails (billID,customerID,productID,quantity,date) VALUES ('"+bill_no				+"','"+ct_id.getText()+"','"+Pro_name.getText()+"','"+qunty.getText()+"','"+date.getText()+"');";

		String query2 ="Select salesPrice from Product where productID='"+Pro_name.getText()+"'";
		amount=amount+Integer.parseInt(qunty.getText())*salesPrice; 

		

		
		Connection con = null;
		ResultSet rs = null;
        	 Statement st = null;
		System.out.println(query1);
		//System.out.println(query2);
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection(Database.HOST_URI, Database.USER, Database.PASSWORD);
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");

			rs=st.executeQuery(query);
			while(rs.next()){ 
			quantity=rs.getInt("availableQuantity");}

			if(quantity<Integer.parseInt(qunty.getText())){
			JOptionPane.showMessageDialog(null,"Sorry! we have limited stock of "+quantity+" products");
			
			qunty.setText("");

			}
			else{
			int stock=quantity-Integer.parseInt(qunty.getText());
			System.out.println("Qunatity"+quantity);
			String query3="UPDATE `product` SET `availableQuantity` = '"+stock+"' WHERE 							`product`.`productID`='"+Pro_name.getText()+"';";
			st.executeUpdate(query3);
			
			
			st.execute(query1);//insert
			rs=st.executeQuery(query2);
			while(rs.next()){ 
			salesPrice=rs.getInt("salesPrice");}
			amount=amount+Integer.parseInt(qunty.getText())*salesPrice;
			System.out.println("data inserted");
			//JOptionPane.showMessageDialog(null,"Product Added!");
			bill_no=bill_no+1;
			bill_id.setText("bill_id: "+bill_no);
			}
			
		}
        catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Failed to add product!");
			System.out.println("Exception : " +ex.getMessage());
        }
        finally {
            try {
                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex) {}
	
		
        }Pro_name.setText("");
	qunty.setText("");
	bill.setText(""+amount);
			
		}
		if (ae.getSource().equals(checkout)) {
		amount=0;
		//bill_no=bill_no+1;
		//bill_id.setText("bill_id: "+bill_no);
		qunty.setText("");
		ct_id.setText("");
		bill.setText("");	
		Pro_name.setText("");
		}

	}
}
