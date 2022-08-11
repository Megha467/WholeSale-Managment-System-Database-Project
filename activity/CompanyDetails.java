package activity;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.table.*;
import attr.*;

public class CompanyDetails extends JFrame implements ActionListener
{       private static final long serialVersionUID =4l;
	private JTable table;
        private DefaultTableModel model;
	private JScrollPane frame;
     //  JComboBox<String> byWhatCB;
	private JPanel panel;
	private company Company;
	private JButton buttonLogout, addButton, logout,searchButton,buttonViewProduct,back;
	private JButton buttonViewCustomer, buttonViewEmployee;
	private JTextField regNO;
	private JLabel title, header;

	public CompanyDetails()
	{	
		super("Dashboard - Companies");
		
		this.setSize(Theme.GUI_WIDTH, Theme.GUI_HEIGHT);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		/*Company = new company(userId);
		company.fetch();*/
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Theme.BACKGROUND_PANEL);

		title = new JLabel("Welcome ");
		title.setBounds(30, 40, 300,75);
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
		
		addButton = new JButton("+ ADD");
		addButton.setBounds(Theme.GUI_WIDTH-400, 40, Theme.BUTTON_PRIMARY_WIDTH+20,30);
		addButton.setFont(Theme.FONT_BUTTON);
		addButton.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		addButton.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		addButton.addActionListener(this);
		panel.add(addButton);

		logout = new JButton("LOGOUT");
		logout.setBounds(Theme.GUI_WIDTH-300, 40, Theme.BUTTON_PRIMARY_WIDTH+20,30);
		logout.setFont(Theme.FONT_BUTTON);
		logout.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		logout.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		logout.addActionListener(this);
		panel.add(logout);

		/*searchButton = new JButton("SEARCH");
		searchButton.setBounds(Theme.GUI_WIDTH-400, 80, Theme.BUTTON_PRIMARY_WIDTH+20,30);
		searchButton.setFont(Theme.FONT_BUTTON);
		searchButton.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		searchButton.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		searchButton.addActionListener(this);
		panel.add(searchButton);*/

		/*regNO = new JTextField("");
		regNO.setBounds(Theme.GUI_WIDTH-300, 80, Theme.BUTTON_PRIMARY_WIDTH+30,30);
		regNO.setFont(Theme.FONT_INPUT);
		panel.add(regNO);*/

		/*String[] options={"By ID", "By Name"};		
		JComboBox<String> byWhatCB = new JComboBox<>(options);

		//byWhatCB = new JComboBox(new Object[]{"By ID", "By Name"});
		byWhatCB.setBounds(Theme.GUI_WIDTH-140, 80, Theme.BUTTON_PRIMARY_WIDTH+20,30);
		byWhatCB.setFont(Theme.FONT_INPUT);
		byWhatCB.addActionListener(this);
		panel.add(byWhatCB);*/
		

		model=new DefaultTableModel();
		table=new JTable(model);
		String[]header={"Registration NO", "Name", "Contact","Street Address","Postal Code","City","State"};
		model.setColumnIdentifiers(header);
		table.setModel(model);
		frame = new JScrollPane(table);
		frame.setBounds(40,185,720,300);
		panel.add(frame);
		
		
		

		int result = -1;
		String query = "select CompanyReg,name,contact,street,postalCode,city,state from company c inner join                 location l on c.locationID=l.id";     
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
		
		model.insertRow(i, new Object[] { rs.getString("CompanyReg"),rs.getString("name"), rs.getString                ("contact"), rs.getString("street"), rs.getString("postalCode"), rs.getString("city"),rs.getString("state") });
              
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
		
        table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {
               jTable_ClickMouseClicked(evt);
            }
		});
	
		
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

	if (ae.getSource().equals(addButton)) {
		adcompany ob = new adcompany();
			ob.setVisible(true);
            this.setVisible(false);
			
		}

	if (ae.getSource().equals(logout)) {
		this.setVisible(false);
			new LoginActivity().setVisible(true);	
		}
	if (ae.getSource().equals(searchButton)) {
			/*System.out.println("hello"+byWhatCB.getSelectedItem().toString());
			company.searchCompany(regNO.getText().trim(),byWhatCB.getSelectedItem().toString());
			//table.setModel();
System.out.println("hello");*/
		}	
	}
	private void jTable_ClickMouseClicked(MouseEvent evt) {                                          
       int index = table.getSelectedRow();

       TableModel model = table.getModel();

       String value1 = model.getValueAt(index, 0).toString();
       new updateCompany(value1, this).setVisible(true);
    }

}