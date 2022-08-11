package activity;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import attr.*;
import java.sql.*;
public class stock extends JFrame implements ActionListener
{
	private JPanel panel;
	private JLabel  pro_id, quty, date;
    private JLabel title;
		private JTextField id, dt, qty;
	private JButton	buttonSubmit, buttonback;
	stock()
	{
		super("Stock");
		this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Theme.BACKGROUND_PANEL);
		
		title = new JLabel("Stock");
		title.setBounds(30, 40, 650, 75);
		title.setOpaque(true);
		title.setBorder(new EmptyBorder(0,20,0,0));
		title.setFont(Theme.FONT_TITLE);
		title.setForeground(Theme.COLOR_TITLE);
		panel.add(title);
		
		pro_id = new JLabel("Product ID");
		pro_id.setBounds(50, 150, 200,30);
		pro_id.setFont(Theme.FONT_BUTTON);		
		panel.add(pro_id);
		
		quty = new JLabel("Quantity");
		quty.setBounds(50, 200, 200,30);
		quty.setFont(Theme.FONT_BUTTON);		
		panel.add(quty);
	
		date = new JLabel("Date");
		date.setBounds(50, 250, 200,30);
		date.setFont(Theme.FONT_BUTTON);		
		panel.add(date);
		
		id = new JTextField();
		id.setBounds(180, 150, 220, 30);
		id.setFont(Theme.FONT_INPUT);
		panel.add(id);
		
		qty = new JTextField();
		qty.setBounds(180, 200, 220, 30);
		qty.setFont(Theme.FONT_INPUT);
		panel.add(qty);
		
		dt = new JTextField();
		dt.setBounds(180, 250, 220, 30);
		dt.setFont(Theme.FONT_INPUT);
		panel.add(dt);
		
		buttonSubmit = new JButton("Submit");
		buttonSubmit.setBounds(300, 400, 120, 55);
		buttonSubmit.setFont(Theme.FONT_BUTTON);
		buttonSubmit.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		buttonSubmit.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		buttonSubmit.addActionListener(this);
		panel.add(buttonSubmit);
		
		buttonback = new JButton("Back");
		buttonback.setBounds(200, 400, 120, 55);
		buttonback.setFont(Theme.FONT_BUTTON);
		buttonback.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		buttonback.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		buttonback.addActionListener(this);
		panel.add(buttonback);
		
		this.add(panel);

	}
	public void actionPerformed(ActionEvent ae){
	
	if (ae.getSource().equals(buttonSubmit)) {
		String query1="Select availableQuantity from product WHERE productID = "+id.getText()+" ";

	int qtyToAdd=Integer.parseInt(qty.getText());
	
	Connection con = null;	
	ResultSet rs=null;	
        Statement st = null;
		System.out.println(query1);
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection(Database.HOST_URI, Database.USER, Database.PASSWORD);
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			rs = st.executeQuery(query1);
			System.out.println("Available quantity"+qtyToAdd);
			int qnty=0;
			while(rs.next()){qnty=rs.getInt("availableQuantity");}
			
			int sum=qnty+qtyToAdd;

			String query = ("UPDATE product SET availableQuantity =  "+sum+"   WHERE productID =  "+id.getText()+" ");
		

			int res = st.executeUpdate(query);//insert
			System.out.println("data inserted");
			System.out.println(res);
			if (res > 0) {
				JOptionPane.showMessageDialog(null,"stock Updated!");
			//a.setVisible(false);
			}
			else {
				JOptionPane.showMessageDialog(null,"stock didn't update!");
			}
		}
        catch(Exception ex) {
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
        }
			
		}
		
		if (ae.getSource().equals(buttonback)) {
			Product ob = new Product();
			ob.setVisible(true);
            this.setVisible(false);
		}
	}
	
}