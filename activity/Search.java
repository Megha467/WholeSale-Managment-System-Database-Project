package activity;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import attr.*;
public class Search extends JFrame implements ActionListener
{
	private JLabel proname;
	private JButton back;
	private JLabel title, header;
	private JPanel panel;
	private JTextField Pro_name;
	
    public Search()
    {
		this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Theme.BACKGROUND_PANEL);
		
		title = new JLabel("Search Product");
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
		
		JLabel Proname = new JLabel("Enter Product");
		Proname.setBounds(100, 150, 180, 30);
		Proname.setFont(Theme.FONT_REGULAR);
		panel.add(Proname);
        
		Pro_name = new JTextField();
	    Pro_name.setBounds(280, 150, 220, 30);	
		Pro_name.setFont(Theme.FONT_INPUT);
		panel.add(Pro_name);
		
		back = new JButton("Back");
		back.setBounds(350, 400, 150, 70);
		back.setFont(Theme.FONT_BUTTON);
		back.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		back.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		back.addActionListener(this);
		panel.add(back);
		this.add(panel);  
		
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