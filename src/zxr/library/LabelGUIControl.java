package zxr.library;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LabelGUIControl extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel j_label = null;
	TextField text_f = null;
	
	public LabelGUIControl(String str){
		super(new GridLayout(1, 2, 10, 2));
		
		j_label = new JLabel(str,JLabel.CENTER);
		text_f = new TextField(10);
		
		j_label.setBackground(Color.WHITE);		
		j_label.setOpaque(true);
		add(j_label);
		add(text_f);
	}
	public String getContent(){
		return text_f.getText();
	}	
}
