package activity;

import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import attr.*;

public class updateCompany extends JFrame implements ActionListener {
	private JPanel panel;
	CompanyDetails prev;
	private company Company;
	private JButton buttonEdit, buttonDelete;
	private JLabel title, header, userIdLabel, employeeNameLabel, phoneNumberLabel, streetLabel;
        private JLabel postalCode,city,state;
	private JTextField regNoTF, companyNameTF, phoneNumberTF, phoneCodeTF,cityTF,streetTF,postalCodeTF,stateTF;
	private JComboBox roleCB;
	
	public updateCompany(String eid, CompanyDetails prev) {
		super("Manage Company");
		
		this.setSize(500,400);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.prev = prev;
		
		Company = new company(eid);
		Company.fetch();
		
		panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Theme.BACKGROUND_PANEL);
		
		userIdLabel = new JLabel("RegNO: ");
		userIdLabel.setBounds(60, 20, 140, 30);
		userIdLabel.setFont(Theme.FONT_INPUT);
		panel.add(userIdLabel);
		
		employeeNameLabel = new JLabel("Name: ");
		employeeNameLabel.setBounds(60, 60, 140, 30);
		employeeNameLabel.setFont(Theme.FONT_INPUT);
		panel.add(employeeNameLabel);
		
		phoneNumberLabel = new JLabel("Contact: ");
		phoneNumberLabel.setBounds(60, 100, 140, 30);
		phoneNumberLabel.setFont(Theme.FONT_INPUT);
		panel.add(phoneNumberLabel);
		
		streetLabel = new JLabel("Street: ");
		streetLabel.setBounds(60, 140, 140, 30);
		streetLabel.setFont(Theme.FONT_INPUT);
		panel.add(streetLabel);

		city = new JLabel("City: ");
		city.setBounds(60, 180, 140, 30);
		city.setFont(Theme.FONT_INPUT);
		panel.add(city);
		
		postalCode = new JLabel("Postal Code: ");
		postalCode.setBounds(60, 260, 140, 30);
		postalCode.setFont(Theme.FONT_INPUT);
		panel.add(postalCode);

		

		state = new JLabel("State: ");
		state.setBounds(60, 220, 140, 30);
		state.setFont(Theme.FONT_INPUT);
		panel.add(state);

		regNoTF = new JTextField(eid);
		regNoTF.setBounds(160, 20, 220, 30);
		regNoTF.setFont(Theme.FONT_INPUT);
		panel.add(regNoTF);
		
		companyNameTF = new JTextField(Company.getCompanyName());
		companyNameTF.setBounds(160, 60, 220, 30);
		companyNameTF.setFont(Theme.FONT_INPUT);
		panel.add(companyNameTF);
		
		phoneCodeTF = new JTextField("+92");
		phoneCodeTF.setEnabled(false);
		phoneCodeTF.setBounds(160, 100, 40, 30);
		phoneCodeTF.setFont(Theme.FONT_INPUT);
		panel.add(phoneCodeTF);
		

		phoneNumberTF = new JTextField(Company.getPhoneNumber());
		phoneNumberTF.setBounds(200, 100, 180, 30);
		phoneNumberTF.setFont(Theme.FONT_INPUT);
		panel.add(phoneNumberTF);

		streetTF = new JTextField(Company.getStreet());
		streetTF.setBounds(160, 140, 220, 30);
		streetTF.setFont(Theme.FONT_INPUT);
		panel.add(streetTF);

		cityTF = new JTextField(Company.getCity());
		cityTF.setBounds(160, 180, 220, 30);
		cityTF.setFont(Theme.FONT_INPUT);
		panel.add(cityTF);

		postalCodeTF = new JTextField(Company.getPost());
		postalCodeTF.setBounds(160, 260, 220, 30);
		postalCodeTF.setFont(Theme.FONT_INPUT);
		panel.add(postalCodeTF);

		stateTF = new JTextField(Company.getState());
		stateTF.setBounds(160, 220, 220, 30);
		stateTF.setFont(Theme.FONT_INPUT);
		panel.add(stateTF);
		
		buttonEdit = new JButton("Edit");
		buttonEdit.setBounds(60, 320, Theme.BUTTON_PRIMARY_WIDTH,30);
		buttonEdit.setFont(Theme.FONT_BUTTON);
		buttonEdit.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		buttonEdit.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		buttonEdit.addActionListener(this);
		panel.add(buttonEdit);
		
		buttonDelete = new JButton("Delete");
		buttonDelete.setBounds(180, 320, Theme.BUTTON_PRIMARY_WIDTH,30);
		buttonDelete.setFont(Theme.FONT_BUTTON);
		buttonDelete.setBackground(Theme.BACKGROUND_BUTTON_PRIMARY);
		buttonDelete.setForeground(Theme.COLOR_BUTTON_PRIMARY);
		buttonDelete.addActionListener(this);
		panel.add(buttonDelete);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae) {
	if (ae.getSource().equals(buttonEdit)) {
			
	Company.updateEmployee(regNoTF.getText(),companyNameTF.getText(),phoneNumberTF.getText(),streetTF.getText(),cityTF.getText(),stateTF.getText(),postalCodeTF.getText());
		}
		else if (ae.getSource().equals(buttonDelete)) {
			Company.deleteCompany();
		
		}
		else {}
	}
}