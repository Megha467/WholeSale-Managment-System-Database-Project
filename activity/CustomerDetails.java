package activity;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.event.*;
import attr.*;
import java.util.Scanner;
import java.io.*;

public class CustomerDetails extends JFrame implements ActionListener
{
	private JButton back,Add;
    private JPanel panel;	
	private JTable table;
	private JLabel title, header;
    private DefaultTableModel model;
	public CustomerDetails()
	{
		
		this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
		this.setResizable(false);
		
		this.setLocationRelativeTo(null);
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Theme.BACKGROUND_PANEL);

		title = new JLabel("Customer List ");
		title.setBounds(30, 40, Theme.GUI_WIDTH-120,75);
		title.setOpaque(true);
		title.setBorder(new EmptyBorder(0,20,0,0));
		title.setFont(Theme.FONT_TITLE);
		title.setForeground(Theme.COLOR_TITLE);
		panel.add(title);

		Add = new JButton("Add");
		Add.setBounds(Theme.GUI_WIDTH-140, 75, Theme.BUTTON_PRIMARY_WIDTH+20,30);
		Add.setFont(Theme.FONT_BUTTON);
		Add.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		Add.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		Add.addActionListener(this);
		panel.add(Add);		

		back = new JButton("Back");
		back.setBounds(Theme.GUI_WIDTH-140, 40, Theme.BUTTON_PRIMARY_WIDTH+20,30);
		back.setFont(Theme.FONT_BUTTON);
		back.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		back.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		back.addActionListener(this);
		add(back);
		
		model=new DefaultTableModel(0,7);
		table=new JTable(model);
		table.setRowHeight(20);
		String[]header={"ID","Name","Contact", "Street Address","Postal Code","City","State"};
		model.setColumnIdentifiers(header);	
		//model.insertRow(0, new Object[] { "Sadia", "0233", "mirpur", "2345" });;\
		JScrollPane pane=new JScrollPane(table);	
		pane.setBounds(40,135,720,300);
		add(pane);
		
		int result = -1;
		String query = "select c.ID,firstname,lastName,contact,street,postalCode,city,state from customer c inner join                 location l on c.locationID=l.id";     
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
		
		model.insertRow(i, new Object[] { rs.getString("ID"),rs.getString("firstname")+" "+rs.getString("lastName"), rs.getString("contact"), rs.getString("street"), rs.getString("postalCode"), rs.getString("city"),rs.getString("state")});
              
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
		
       /* table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
               jTable_ClickMouseClicked(evt);
            }
		});*/
	
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
		if (ae.getSource().equals(Add)) {
		adcustomer obj=new adcustomer();
		obj.setVisible(true);
                this.setVisible(false);	
		}

	}
}