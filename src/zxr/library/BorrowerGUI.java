package zxr.library;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BorrowerGUI extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LabelGUIControl label_ssn  = null;
	LabelGUIControl label_fname  = null;
	LabelGUIControl label_lname  = null;
	LabelGUIControl label_address  = null;
	LabelGUIControl label_phone = null;
	JLabel label_show = null;
	
	BorrowerGUI(){//constructor		
		super(new BorderLayout());
		JPanel j_panel = new JPanel(new GridLayout(15,1));	
		JButton button_push = new JButton("Submit");			
		label_ssn = new LabelGUIControl("SSN");
		label_fname = new LabelGUIControl("First Name");		
		label_lname = new LabelGUIControl("Last Name");
		label_address = new LabelGUIControl("Address");
		label_phone = new LabelGUIControl("Phone Number");

		label_show = new JLabel("",JLabel.CENTER);			
		JPanel j_panel_1 = new JPanel(new GridLayout(1,2));
		JLabel label_arr[] = new JLabel[11];
		int a = 0;
		while(a < 11){
			label_arr[a] = new JLabel("");
			a++;
		}
		button_push.addActionListener(new BorrowerSubmit_Listener());
		SetGUIControl.SetGUIButton(button_push);
		SetGUIControl.SetGUILabel(label_show);
				
        j_panel.add(label_arr[10]);
        j_panel.add(label_arr[0]);
        j_panel.add(label_ssn);
        j_panel.add(label_arr[1]);
        j_panel.add(label_fname);		
        j_panel.add(label_arr[2]);
        j_panel.add(label_lname);
        j_panel.add(label_arr[3]);
        j_panel.add(label_address);
        j_panel.add(label_arr[4]);
        j_panel.add(label_phone);
        j_panel.add(label_arr[5]);		
        j_panel.add(label_show);
        j_panel.add(label_arr[6]);
        j_panel_1.add(button_push);		
        j_panel_1.add(label_arr[7]);
        j_panel_1.add(label_arr[8]);
        j_panel_1.add(label_arr[9]);
        j_panel.add(j_panel_1);
		add(j_panel,BorderLayout.CENTER);
	}
	
	private class BorrowerSubmit_Listener implements ActionListener{
		public void actionPerformed(ActionEvent action_event) {
			String ssn = label_ssn.getContent();
			String fname = label_fname.getContent();
			String lname = label_lname.getContent();
			String address = label_address.getContent();
			String phone = label_phone.getContent();
			
			if(ssn.isEmpty()||fname.isEmpty()||lname.isEmpty()||address.isEmpty()){
				String warnword = "ssn, firstName, lastName, address can not be null.";
				label_show.setText(" Cannot  creat New Borrower!");
				JOptionPane.showMessageDialog(null, warnword);
				return;
			}	
							
			if(LibraryControl_LP.getexample().createNewBorrower(ssn, fname,lname,address,phone))
				label_show.setText("new borrower set up successfully.");					
		}
	}
	
	
}
