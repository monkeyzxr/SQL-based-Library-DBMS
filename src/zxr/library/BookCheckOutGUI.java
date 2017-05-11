package zxr.library;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BookCheckOutGUI extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	LabelGUIControl label_isbn = null;
	LabelGUIControl label_branch_Id = null;
	LabelGUIControl label_CardNumber = null;
	
	JLabel label_show = null;
	
	
	public BookCheckOutGUI(){//constructor
		super(new BorderLayout());
        JPanel j_panel = new JPanel(new GridLayout(11,1));
        JButton button_push = new JButton("CHECK OUT");
		label_isbn = new LabelGUIControl("ISBN");		
		label_branch_Id = new LabelGUIControl("Branch ID");
		label_CardNumber = new LabelGUIControl("CARD NUMBER");			
		label_show = new JLabel("",JLabel.CENTER);			
		JPanel j_panel_a = new JPanel(new GridLayout(1,2));
		
		JLabel label_arr[] = new JLabel[9];
		int a = 0;
		while(a<9){
			label_arr[a] = new JLabel("");
			a++;
		}			
		button_push.addActionListener(new CheckOut_Listener());
		SetGUIControl.SetGUIButton(button_push);
		SetGUIControl.SetGUILabel(label_show);						
        j_panel.add(label_arr[0]);
        j_panel.add(label_arr[1]);
        j_panel.add(label_isbn);		
        j_panel.add(label_arr[2]);
        j_panel.add(label_branch_Id);
        j_panel.add(label_arr[3]);
        j_panel.add(label_CardNumber);
        j_panel.add(label_arr[4]);			
        j_panel.add(label_show);
        j_panel.add(label_arr[5]);
        j_panel_a.add(button_push);		
        j_panel_a.add(label_arr[6]);
        j_panel_a.add(label_arr[7]);
        j_panel_a.add(label_arr[8]);				
        j_panel.add(j_panel_a);
		add(j_panel,BorderLayout.CENTER);
	}
				
	private class CheckOut_Listener implements ActionListener{	
		public void actionPerformed(ActionEvent action_event) {
			String isbn = label_isbn.getContent();
			String branchId = label_branch_Id.getContent();
			String cardNo = label_CardNumber.getContent();					
			if(isbn.isEmpty()||branchId.isEmpty()||cardNo.isEmpty()){
				String warnword = "ISBN, Branch_id and Card_no can not be empty!";
				label_show.setText(" Cannot check out!");
				JOptionPane.showMessageDialog(null, warnword);
				return;
			}		
		}
	}
}
