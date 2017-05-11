package zxr.library;


import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;

public class LibraryControlAPP extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public LibraryControlAPP() {
        super(new GridLayout(1, 1));
        JTabbedPane librarytabpane = new JTabbedPane();
        JComponent panels[] = new JComponent[5];
        
        panels[0] = new BookSearchGUI();        
        librarytabpane.addTab("Book  Search", null, panels[0],"Book Search");
        librarytabpane.setMnemonicAt(0, KeyEvent.VK_1);
        
        panels[1] = new BookCheckOutGUI();
        panels[1].setPreferredSize(new Dimension(410, 50));
        librarytabpane.addTab("Check Out", null, panels[1],"Check Out");
        librarytabpane.setMnemonicAt(1, KeyEvent.VK_2);   
        
        panels[2] = new BookCheckInGUI_LP();
        librarytabpane.addTab("Check In", null, panels[2],"Check In");
        librarytabpane.setMnemonicAt(2, KeyEvent.VK_3);
        
        panels[3] = new BorrowerGUI();
        librarytabpane.addTab("Borrowers", null, panels[3],"Borrowers");
        librarytabpane.setMnemonicAt(3, KeyEvent.VK_4);
        
        panels[4] = new FinesGUI();
        librarytabpane.addTab("Fines", null, panels[4],"Fines");
        librarytabpane.setMnemonicAt(4, KeyEvent.VK_5);
        add(librarytabpane);
        
        
        librarytabpane.setBackgroundAt(0, Color.WHITE);
        librarytabpane.setBackgroundAt(1, Color.WHITE);
        librarytabpane.setBackgroundAt(2, Color.WHITE);
        librarytabpane.setBackgroundAt(3, Color.WHITE);
        librarytabpane.setBackgroundAt(4, Color.WHITE);
  
        librarytabpane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }  
	
	   
    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
		UIManager.put("", Boolean.FALSE);
		displayGUI();
            }
        });
    }
	
	
	  protected static ImageIcon createImageIcon(String location) {
	        java.net.URL imgURL = LibraryControlAPP.class.getResource(location);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL);
	        } else return null;
	    }
	

    private static void displayGUI() {
 
        JFrame frame = new JFrame("BookLibraryProjectAPP");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new LibraryControlAPP(), BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(700, 700);
    }
 
}