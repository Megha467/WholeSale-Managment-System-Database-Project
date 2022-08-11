package activity;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import attr.*;
import java.sql.*;

public class ChangePasswordActivity extends JFrame implements ActionListener {
	private static final long serialVersionUID =4l;
	private JPanel panel;
	private JLabel oldLabel, newLabel;
	private JPasswordField oldPF, newPF;
	private JButton buttonSubmit, buttonCancel;
	//user User
	public ChangePasswordActivity() {
		super("Change Password");
		
		this.setSize(500,200);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		
		
		
		panel = new JPanel();
		panel.setLayout(null);
		
		oldLabel = new JLabel("Old Password: ");
		oldLabel.setBounds(40, 20, 150, 30);
		oldLabel.setFont(Theme.FONT_INPUT);
		panel.add(oldLabel);
		
		oldPF = new JPasswordField();
		oldPF.setBounds(160, 20, 280, 30);
		oldPF.setFont(Theme.FONT_INPUT);
		panel.add(oldPF);
		
		newLabel = new JLabel("New Password: ");
		newLabel.setBounds(40, 70, 150, 30);
		newLabel.setFont(Theme.FONT_INPUT);
		panel.add(newLabel);
		
		newPF = new JPasswordField();
		newPF.setBounds(160, 70, 280, 30);
		newPF.setFont(Theme.FONT_INPUT);
		panel.add(newPF);
		
		buttonSubmit = new JButton("Change");
		buttonSubmit.setBounds(90, 120, 110,30);
		buttonSubmit.setFont(Theme.FONT_BUTTON);
		buttonSubmit.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		buttonSubmit.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		buttonSubmit.addActionListener(this);
		panel.add(buttonSubmit);
		
		buttonCancel = new JButton("Cancel");
		buttonCancel.setBounds(300, 120,110,30);
		buttonCancel.setFont(Theme.FONT_BUTTON);
		buttonCancel.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		buttonCancel.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		buttonCancel.addActionListener(this);
		panel.add(buttonCancel);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource().equals(buttonSubmit)) {
		String newPass=String.valueOf(newPF.getPassword());
		String oldPass=String.valueOf(oldPF.getPassword());
		  
	 String query = "UPDATE `login` SET `password`='"+newPass+"' WHERE `password`='"+oldPass+"';";  
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
			
			System.out.println("results received");

			st.executeUpdate(query);//insert
			JOptionPane.showMessageDialog(null,"Password Updated!");
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
			
		}
		else if (ae.getSource().equals(buttonCancel)) {
			this.setVisible(false);
		}
		else {}
	}
}