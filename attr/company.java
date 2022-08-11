package attr;

import java.lang.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;
import attr.*;
import activity.*;

public class company {
	private String companyName;
	private String regNO;
	private String phoneNumber;
	private String street;
	private String postalCode;
	private String city;
	private String state;
	private int location;
	public static String[] columnNames = {"Registration NO", "Name", "Contact","Street Address","Postal Code","City","State"};

	public company(String regNO) {
		this.regNO=regNO;
	}
	
	public void setCompanyName(String name) {
		if (!name.isEmpty())
			this.companyName = name;
		else
			throw new IllegalArgumentException("Fill in the name");
	}
	public void setPhoneNumber(int num) {
		this.phoneNumber = "+880"+num;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setPost(String postalCode) {
		this.postalCode = postalCode;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public String getStreet() {
		return street;
	}
	public String getPost() {
		return postalCode;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	
	/*public void createEmployee() {
		String query1 = "INSERT INTO `login` VALUES ('"+userId+"','"+password+"',"+status+");";
		String query2 = "INSERT INTO `employee` VALUES ('"+userId+"','"+employeeName+"','"+phoneNumber+"','"+role+"', '"+salary+"');";
		Connection con = null;
        Statement st = null;
		System.out.println(query1);
		System.out.println(query2);
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection(Database.HOST_URI, Database.USER, Database.PASSWORD);
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			st.execute(query1);//insert
			st.execute(query2);
			System.out.println("data inserted");
			JOptionPane.showMessageDialog(null,"Account Created!");
		}
        catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Failed to create account!");
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
	}*/
	
	public void fetch() {
		String query = "select name,contact,street,postalCode,city,state,locationID from company c inner join location l on c.locationID=l.id where CompanyReg='"+this.regNO+"' ;";     
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
			System.out.println("regNO"+regNO);
			boolean flag = false;
			while(rs.next()) {
				this.companyName = rs.getString("name");
				System.out.println(this.companyName); 
				this.phoneNumber = rs.getString("contact");
				this.street = rs.getString("street");
				this.postalCode = rs.getString("postalCode");
				this.city = rs.getString("city");
				this.state = rs.getString("state");
				System.out.println("results received"); 
				this.location=rs.getInt("locationID");
                               System.out.println("results received"); 
			}
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
	
public void updateEmployee(String regNO,String name, String contact, String streetName, String cityName,String stateName, String post) {
String query1 = "UPDATE `location` SET`street` = '"+streetName+"', `postalCode` = '"+post+"', `city` = '"+cityName+"', `state` = '"+stateName+"' WHERE `location`.`id` = '"+this.location+"';";
String query = "UPDATE `company` SET `CompanyReg` = '"+regNO+"', `name` = '"+name+"', `contact` = '"+contact+"' WHERE `company`.`CompanyReg` ='"+this.regNO+"';";

		Connection con = null;
        Statement st = null;
		System.out.println(query);
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection(Database.HOST_URI, Database.USER, Database.PASSWORD);
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			st.executeUpdate(query1);//insert
			System.out.println("data inserted");
			st.executeUpdate(query);//insert
			System.out.println("data inserted");
			JOptionPane.showMessageDialog(null,"Information Updated!");
		/*	this.employeeName = name;
			this.phoneNumber = "+880"+phone;
			this.role = role;
			this.salary = salary;*/
		}
        catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Failed to update account!");
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
	
	public void deleteCompany() {
		String query1 = "DELETE FROM `company` WHERE `CompanyReg`='"+this.regNO+"';";
		String query2 = "DELETE FROM `location` WHERE `id`='"+this.location+"';";
		Connection con = null;
        Statement st = null;
		System.out.println(query1);
		System.out.println(query2);
        try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("driver loaded");
			con = DriverManager.getConnection(Database.HOST_URI, Database.USER, Database.PASSWORD);
			System.out.println("connection done");//connection with database established
			st = con.createStatement();//create statement
			System.out.println("statement created");
			st.execute(query1);
			st.execute(query2);//delete
			System.out.println("data deleted");
			JOptionPane.showMessageDialog(null,"Account Deleted!");
			this.regNO = "";
			this.companyName = "";
			this.phoneNumber = "";
			this.state = "";
			this.city ="";
		}
        catch(Exception ex) {
			JOptionPane.showMessageDialog(null,"Failed to delete account!");
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
	
	public static DefaultTableModel searchCompany(String keyword, String byWhat) {
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		System.out.println("print"+byWhat);
		String query;	
		if (byWhat.equals("By Name"))
		query = "select name,contact,street,postalCode,city,state,locationID from company c inner join location l on 'c'.'locationID'='l'.'id' WHERE `name` LIKE '%"+keyword+"%';";
		else {query = "select CompanyReg,name,contact,street,postalCode,city,state from company c inner join location l on c.locationID=l.id where CompanyReg='"+keyword+"' ;";}
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

model.addRow(new Object[] { rs.getString("CompanyReg"),rs.getString("name"), rs.getString("contact"), rs.getString("street"), rs.getString("postalCode"), rs.getString("city"),rs.getString("state") });

			}
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
		return model;
	}
}