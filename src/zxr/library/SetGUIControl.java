package zxr.library;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class SetGUIControl {
	public static void SetGUIButton(JButton gui_button){
		gui_button.setBounds(20, 30, 100, 25);
 
        Border t_boarder = new LineBorder(Color.BLACK, 3);

        
        gui_button.setBorder(t_boarder);

        gui_button.setBackground(Color.WHITE);
        
        gui_button.setOpaque(true);
	}
	
	public static void SetGUILabel(JLabel gui_label){		  

		gui_label.setBackground(Color.WHITE);
		
		gui_label.setOpaque(true);
	}
}

