package activity;
import java.sql.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import attr.*;
public class adcustomer extends JFrame implements ActionListener
{
	private JButton submit, backButton;
	private JPanel panel;
	private JLabel id, firstname, lastname,contact, street, city,post,state;
        private JTextField ID, f_name, L_name, cnt, streetTF, cty,postTF,stateTF;
        private JLabel title, header;
        adcustomer()
        {
           this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Theme.BACKGROUND_PANEL);
		
		title = new JLabel("Add customer");
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

            /*    id = new JLabel("State");
		id.setBounds(100, 150, 120, 30);
		id.setFont(Theme.FONT_REGULAR);
 		panel.add(id);*/
		
 		firstname = new JLabel("Firstname");
		firstname.setBounds(100, 150, 120, 30);
		firstname.setFont(Theme.FONT_REGULAR);
		panel.add(firstname);

		lastname = new JLabel("Lastname");
		lastname.setBounds(100, 200, 120, 30);
		lastname.setFont(Theme.FONT_REGULAR);
		panel.add(lastname);

		contact =new JLabel("Contact");
		contact.setBounds(100, 250, 120, 30);
		contact.setFont(Theme.FONT_REGULAR);
		panel.add(contact);
		
		street = new JLabel("Street");
                street.setBounds(100, 300, 120, 30);
		street.setFont(Theme.FONT_REGULAR);
		panel.add(street);

		 city = new JLabel("City");
		 city.setBounds(100, 350, 120, 30);
		 city.setFont(Theme.FONT_REGULAR);
		 panel.add( city);

		 post = new JLabel("Post");
		 post.setBounds(100, 400, 120, 30);
		 post.setFont(Theme.FONT_REGULAR);
		 panel.add( post);

		 state = new JLabel("State");
		 state.setBounds(100, 450, 120, 30);
		 state.setFont(Theme.FONT_REGULAR);
		 panel.add( state);

		/*ID  = new JTextField();
                ID.setBounds(200, 150, 220, 30);
		ID.setFont(Theme.FONT_INPUT);
		panel.add(ID);*/
		
		f_name = new JTextField();
		f_name.setBounds(200, 150, 220, 30);
		f_name.setFont(Theme.FONT_INPUT);
		panel.add(f_name);

		L_name = new JTextField();
		L_name.setBounds(200, 200, 220, 30);
		L_name.setFont(Theme.FONT_INPUT);
		panel.add(L_name);
		
		cnt = new JTextField();
		cnt.setBounds(200, 250, 220, 30);
		cnt.setFont(Theme.FONT_INPUT);
		panel.add(cnt);

		streetTF  = new JTextField();
		streetTF.setBounds(200, 300, 220, 30);
		streetTF.setFont(Theme.FONT_INPUT);
		panel.add(streetTF);

		
		cty = new JTextField();
                cty.setBounds(200, 350, 220, 30);
		cty.setFont(Theme.FONT_INPUT);
		panel.add(cty);

		postTF  = new JTextField();
		postTF.setBounds(200, 400, 220, 30);
		postTF.setFont(Theme.FONT_INPUT);
		panel.add(postTF);

		stateTF  = new JTextField();
		stateTF.setBounds(200, 450, 220, 30);
		stateTF.setFont(Theme.FONT_INPUT);
		panel.add(stateTF);
		

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

		this.add(panel); 		
 		
		
        }

public void actionPerformed(ActionEvent ae)
{ 
int location_id=0;
int customer_id=0;
int newID=0;
int new_C=0;
  	 if (ae.getSource().equals(submit)) {
		String query="Select id from location order by id desc limit 1;";
		String query1="Select ID from customer order by ID desc limit 1;";
		
		Connection con = null;
        Statement st = null;
		ResultSet rs = null;
		System.out.println(query1);
		//System.out.println(query2);
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection(Database.HOST_URI, Database.USER,"2222");
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			
			rs = st.executeQuery(query);
			while(rs.next()){location_id=rs.getInt("id");}
			newID=location_id+1;
			System.out.println(query);
			System.out.println(newID);
			
			rs = st.executeQuery(query1);
			while(rs.next()){customer_id=rs.getInt("ID");}
			new_C=1+customer_id;
			System.out.println(new_C);
			System.out.println(query1);


			String query2="INSERT INTO location (id, street, postalCode, city, state) VALUES ('"+newID+"', '"+streetTF.getText()+"', '"+postTF.getText()+"', '"+cty.getText()+"', '"+stateTF.getText()+"');";

			System.out.println(query2);

			st.execute(query2);//insert

String query3 = "INSERT INTO customer (ID,  firstname, lastname, contact, locationID) VALUES ('"+new_C+"','"+f_name.getText() +"','"+L_name.getText()+"','"+cnt.getText()+"','"+newID+"');";

			System.out.println(query3);

			st.execute(query3);
			System.out.println("data inserted");
			JOptionPane.showMessageDialog(null,"customer Added!");
			f_name.setText("");
			L_name.setText("");
			streetTF.setText("");
			cnt.setText("");
			cty.setText("");
			stateTF.setText("");
			postTF.setText("");
		}
        catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Failed to add customer!");
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
	
else if (ae.getSource().equals(backButton)) {
			Admin ob = new Admin();
			ob.setVisible(true);
            this.setVisible(false);
		}
	
     
}}