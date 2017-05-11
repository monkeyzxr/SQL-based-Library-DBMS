package zxr.library;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;
import javax.swing.*;

import zxr.library.SearchModel;

public class BookSearchGUI extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	SearchModelGUI sm_gui[] = null;
	JTable bookLoans_table = null;	
	int maxtableRow = 100000;	
	Vector<Vector<String>> searchResults = null;
	JLabel searchResult_label = null; 
	
	private static String ClasseSearch[] = {"show.isbn","show.title","show.author_name"};
	
	private static String bookLibrary[] = {"show.isbn","show.title", "show.author_name","show.branch_id","show.branch_name","show.no_of_copies","checkout","available"};
	
	
	public BookSearchGUI(){//constructor
		super(new BorderLayout());
		sm_gui = new SearchModelGUI[ClasseSearch.length];      
		bookLoans_table = new JTable(new DefaultTableModel(bookLibrary, maxtableRow));  
		JScrollPane scroll_p = new JScrollPane(bookLoans_table);
		add(scroll_p);
        JPanel j_panel = generateSearchModel();
        add(scroll_p, BorderLayout.CENTER);
        add(j_panel, BorderLayout.WEST);     
	}
	

	private JPanel generateSearchModel(){
        JPanel j_panel = new JPanel(new GridLayout(5, 1, 15, 10));
        for(int i = 0; i < 3; i++){
        	sm_gui[i] = new SearchModelGUI(i,ClasseSearch);
        	j_panel.add(sm_gui[i]);
        }         
        sm_gui[2].HideChoiceModel();       
        JPanel j_panel_1 = new JPanel(new GridLayout(1,3));
        JLabel j_label[] = new JLabel[4];
        
        for(int i = 0; i < 4; i++){
        	j_label[i]= new JLabel("", JLabel.CENTER);
        }
        
        searchResult_label= new JLabel("", JLabel.CENTER);   
        SetGUIControl.SetGUILabel(searchResult_label);
        
        JButton button_for_search = new JButton("search");
        SetGUIControl.SetGUIButton(button_for_search);  
        button_for_search.addActionListener(new BookSearch_Listener());
        j_panel.add(searchResult_label); 
        
        j_panel_1.add(button_for_search);          
        j_panel_1.add(j_label[0]);        
        j_panel_1.add(j_label[1]);
        j_panel_1.add(j_label[2]);
        j_panel.add(j_panel_1);
        add(j_panel); 
        return j_panel;
	}
	
	private class BookSearch_Listener implements ActionListener{
		public void actionPerformed(ActionEvent action_event) {
			SearchModel Models[] = new SearchModel[3];
			for(int i =0; i < 3; i++)
				Models[i]= sm_gui[i].createModels();
			if(((searchResults=LibraryControl_LP.getexample().searchBook(Models))
					==null)||(searchResults.isEmpty())){
				String warnword = "No search result";
				searchResult_label.setText(warnword);
				return;
			}
			if((searchResults == null)||(searchResults.isEmpty())) return;		
			DefaultTableModel def_ml = (DefaultTableModel) bookLoans_table.getModel();		
			int show_rows;
			if(searchResults.size() < maxtableRow) 
				show_rows = searchResults.size();
			else show_rows = maxtableRow;
				
			for(int i=0; i< show_rows;i++){
				Vector<String> row_no = searchResults.get(i);
				String myobject[] = new String[row_no.size()];				
				for(int j = 0; j < row_no.size(); j++){
					myobject[j] = row_no.get(j);
				}	
				if(myobject[6]==null) myobject[6] = "0";
				else if(myobject[7] == null) myobject[7] = myobject[5];			
				def_ml.addRow(myobject);
			}			
		}
	}
		
}
