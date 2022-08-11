package activity;
import java.sql.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import attr.*;


public class AddProduct extends JFrame implements ActionListener
{
	private JButton submit, backButton, next, close;
	private JLabel Product_id, cost_price, sell_price, quantity, company_reg, Product_name, Producttype;
	private JPanel panel;
	private JTextField Pro_id, Cmp_reg, Pro_name, Pro_type, qty, sell, cost;
	private JLabel title, header;
	public AddProduct()
	{
		this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Theme.BACKGROUND_PANEL);
		
		title = new JLabel("Add Product");
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
		
		JLabel Product_id = new JLabel("ID");
		Product_id.setBounds(100, 150, 120, 30);
		Product_id.setFont(Theme.FONT_REGULAR);
		panel.add(Product_id);
        
		
		company_reg = new JLabel("Reg_no");
		company_reg.setBounds(100, 200, 120, 30);
		company_reg.setFont(Theme.FONT_REGULAR);
		panel.add(company_reg);
		
		Product_name = new JLabel("Name");
		Product_name.setBounds(100, 250, 120, 30);
		Product_name.setFont(Theme.FONT_REGULAR);
		panel.add(Product_name);
		 
		cost_price = new JLabel("Cost Price");
		cost_price.setBounds(100, 350, 120, 30);
		cost_price.setFont(Theme.FONT_REGULAR);
		panel.add(cost_price);
		 
		sell_price = new JLabel("Sell Price");
        sell_price.setBounds(100, 400, 120, 30);
        sell_price.setFont(Theme.FONT_REGULAR);
		panel.add(sell_price);
		
		quantity = new JLabel("Quantity");
		quantity.setBounds(100, 450, 120, 30);
		quantity.setFont(Theme.FONT_REGULAR);
		panel.add(quantity);
		
	    Producttype = new JLabel("Type");
	    Producttype.setBounds(100, 300, 120, 30);
	    Producttype.setFont(Theme.FONT_REGULAR);
	    panel.add( Producttype);
	   
	    Pro_id = new JTextField();
	    Pro_id.setBounds(200, 150, 220, 30);
	    Pro_id.setFont(Theme.FONT_INPUT);
	    panel.add(Pro_id);
		
		Cmp_reg = new JTextField();
		Cmp_reg.setBounds(200, 200, 220, 30);
		Cmp_reg.setFont(Theme.FONT_INPUT);
		panel.add(Cmp_reg);
       	
		Pro_name = new JTextField();
		Pro_name.setBounds(200, 250, 220, 30);
		Pro_name.setFont(Theme.FONT_INPUT);
		panel.add(Pro_name);
		
		Pro_type = new JTextField();
		Pro_type.setBounds(200, 300, 220, 30);
		Pro_type.setFont(Theme.FONT_INPUT);
		panel.add(Pro_type);
		
		//qty, sell, cost
		qty = new JTextField();
		qty.setBounds(200, 450, 220, 30);
		qty.setFont(Theme.FONT_INPUT);
		panel.add(qty);
		
		sell = new JTextField();
		sell.setBounds(200, 400, 220, 30);
		sell.setFont(Theme.FONT_INPUT);
		panel.add(sell);
		
		cost = new JTextField();
		cost.setBounds(200, 350, 220, 30);
		cost.setFont(Theme.FONT_INPUT);
		panel.add(cost);
		
		submit = new JButton("Submit");
		submit.setBounds(480, 450, 100, 30);
		submit.setFont(Theme.FONT_BUTTON);
		submit.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		submit.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		submit.addActionListener(this);
		panel.add(submit);

		backButton = new JButton("Back");
		backButton.setBounds(Theme.GUI_WIDTH-120, 80, 100,30);
		backButton.setFont(Theme.FONT_BUTTON);
		backButton.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		backButton.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		backButton.addActionListener(this);
		panel.add(backButton);
		//add(backButton); 

		next = new JButton("Next");
		next.setBounds(590, 450, 100, 30);
		next.setFont(Theme.FONT_BUTTON);
		next.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		next.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		next.addActionListener(this);
		panel.add(next);
		//next.add(backButton);
		//add(next);
		/*
		close = new JButton("Close");
		close.setBounds(Theme.GUI_WIDTH-120, 220, 100,30);
		close.setFont(Theme.FONT_BUTTON);
		close.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		close.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		close.addActionListener(this);
		panel.add(backButton);
		add(close);*/

		this.add(panel); 

		
		 
		   
	}
	
	

public void actionPerformed(ActionEvent ae)
{ 
  	 if (ae.getSource().equals(submit)) {

String query1 = "INSERT INTO product (productID, companyReg, name, costPrice, salesPrice, availableQuantity) VALUES ('"+Pro_id.getText()+"','"+Cmp_reg.getText()+"','"+Pro_name.getText()+"',"+cost.getText()+","+sell.getText()+","+qty.getText()+");";
		
		Connection con = null;
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
			st.execute(query1);//insert
			//st.execute(query2);
			System.out.println("data inserted");
			JOptionPane.showMessageDialog(null,"Product Added!");
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
        }
			
		}
	
	else if (ae.getSource().equals(next)) {
			Pro_id.setText("");
			//Cmp_reg.setText("");
			Cmp_reg.setText("");
			Pro_name.setText("");
			Pro_type.setText("");
			qty.setText("");
			sell.setText("");
			cost.setText("");
		}
	else if (ae.getSource().equals(backButton)) {
			Product ob = new Product();
			ob.setVisible(true);
                        this.setVisible(false);
		}
	
}}