package activity;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import attr.*;
public class Admin extends JFrame implements ActionListener
{
	private JPanel panel;
	private JButton buttonLogout;
    private JButton pwd;
    private JButton buttoncustomer;
    private JButton buttonadministrator, purchase;
    private JButton Histroy;
    private JButton Add, update, delete, search;
	private JLabel title;
	public Admin()
	{
		super("Welcome");
		this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Theme.BACKGROUND_PANEL);
		
		title = new JLabel("Welcome Admin");
		title.setBounds(30, 40, 650, 75);
		title.setOpaque(true);
		title.setBorder(new EmptyBorder(0,20,0,0));
		title.setFont(Theme.FONT_TITLE);
		title.setForeground(Theme.COLOR_TITLE);
		panel.add(title);
		
		JButton buttonLogout = new JButton("Logout");
		buttonLogout.setBounds(Theme.GUI_WIDTH-250, 40, 250,30);
		buttonLogout.setFont(Theme.FONT_BUTTON);
		buttonLogout.setBackground(Color.WHITE);
		buttonLogout.setForeground(Theme.COLOR_TITLE);
		buttonLogout.addActionListener(this);
		panel.add(buttonLogout);

                pwd = new JButton("Change Password");
		pwd.setBounds(Theme.GUI_WIDTH-250, 80, 250,30);
		pwd.setFont(Theme.FONT_BUTTON);
		pwd.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		pwd.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		pwd.addActionListener(this);
		panel.add(pwd);
		
		
		
		buttoncustomer = new JButton("Customer Details");
		buttoncustomer.setBounds(50, 150, 200,30);
		buttoncustomer.setFont(Theme.FONT_BUTTON);
		buttoncustomer.setBackground(Color.WHITE);
		buttoncustomer.setForeground(Theme.COLOR_TITLE);
		buttoncustomer.addActionListener(this);
		panel.add(buttoncustomer);
		
		
		buttonadministrator = new JButton("Companies");
		buttonadministrator.setBounds(50, 200, 200,30);
		buttonadministrator.setFont(Theme.FONT_BUTTON);
		buttonadministrator.setBackground(Color.WHITE);
		buttonadministrator.setForeground(Theme.COLOR_TITLE);
		buttonadministrator.addActionListener(this);
		panel.add(buttonadministrator);
                
          Histroy = new JButton("Sales Histroy");
          Histroy.setBounds(350, 200, 200,30);
          Histroy.setFont(Theme.FONT_BUTTON);
          Histroy.setBackground(Color.WHITE);
          Histroy.setForeground(Theme.COLOR_TITLE);
          Histroy.addActionListener(this);
         panel.add(Histroy);		 
		
                 Add = new JButton("Product");
                 Add.setBounds(350, 150, 200,30);
                 Add.setFont(Theme.FONT_BUTTON);		
	         Add.setBackground(Color.WHITE);
		 Add.setForeground(Theme.COLOR_TITLE);
		 Add.addActionListener(this);
		 panel.add(Add);		 
		
		/*update = new JButton("Purchase Product");
		update.setBounds(350, 200, 200,30);
		update.setFont(Theme.FONT_BUTTON);		
		update.setBackground(Color.WHITE);
		update.setForeground(Theme.COLOR_TITLE);
		update.addActionListener(this);
		panel.add(update);*/
		
		purchase = new JButton("Shop");
		purchase.setBounds(200, 250, 200,30);
		purchase.setFont(Theme.FONT_BUTTON);		
		purchase.setBackground(Color.WHITE);
		purchase.setForeground(Theme.COLOR_TITLE);
		purchase.addActionListener(this);
		panel.add(purchase);
		/*delete = new JButton("Delete Product");
		delete.setBounds(350, 250, 200,30);
		delete.setFont(Theme.FONT_BUTTON);		
		delete.setBackground(Color.WHITE);
		delete.setForeground(Theme.COLOR_TITLE);
		delete.addActionListener(this);
		panel.add(delete);*/
		
		/*search = new JButton("Search Product");
		search.setBounds(350, 250, 200,30);
		search.setFont(Theme.FONT_BUTTON);		
		search.setBackground(Color.WHITE);
		search.setForeground(Theme.COLOR_TITLE);
		search.addActionListener(this);
		panel.add(search);*/
		this.add(panel);
		
	}
public void actionPerformed(ActionEvent ae){

 if (ae.getSource().equals(buttonLogout)) {
			this.setVisible(false);
			new MyProfileActivity(this).setVisible(true);
		}
               else if (ae.getSource().equals(pwd)) {
			ChangePasswordActivity obj = new ChangePasswordActivity();
			obj.setVisible(true);
			//this.setVisible(false);
			//new MyProfileActivity(this).setVisible(true);
		}

            else if (ae.getSource().equals(Add)) {
			Product AP = new Product();
			AP.setVisible(true);
            this.setVisible(false);
		}
		
		 else if (ae.getSource().equals(buttoncustomer))
      	 {
			CustomerDetails BC = new CustomerDetails();
			BC.setVisible(true);
            this.setVisible(false);
		}
		
		else if (ae.getSource().equals(buttonadministrator))
      	 {
			CompanyDetails BC = new CompanyDetails();
			BC.setVisible(true);
            this.setVisible(false);
		}
		
		else if (ae.getSource().equals(search))
      	 {
			Search S = new Search();
			S.setVisible(true);
            this.setVisible(false);
		}
		
		/*else if (ae.getSource().equals(update))
      	 {
			Prolist up = new Prolist();
			up.setVisible(true);
            this.setVisible(false);
		}*/
		
		else if (ae.getSource().equals(Histroy))
      	 {
			SalesHistroy sh = new SalesHistroy();
			sh.setVisible(true);
            this.setVisible(false);
		}
		
		else if (ae.getSource().equals(purchase))
      	 {
			purchasepro p = new purchasepro();
			p.setVisible(true);
            this.setVisible(false);
		}
}
	
}

