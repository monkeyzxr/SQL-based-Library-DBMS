package zxr.library;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class BookCheckInGUI_LP extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Vector<Vector<String>> searchResults = null;
	SearchModelGUI sm_gui[] = null;
	JTable bookLoans_table = null;	
	int maxtableRow = 100000;	
	JLabel searchResult_label = null; 

	private static String ClasseSearch[] = {"card_no","fname","lname","isbn"};
	private static String bookLibrary[] = {"loan_id","card_no","fname","lname","isbn","date_out","due_date","date_in"};	

	
	public BookCheckInGUI_LP(){	// a constructor
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
        JPanel j_panel = new JPanel(new GridLayout(6, 1, 15, 10));
        for(int i = 0; i < ClasseSearch.length; i++){
        	sm_gui[i] = new SearchModelGUI(i,ClasseSearch);
        	j_panel.add(sm_gui[i]);
        }         
        sm_gui[ClasseSearch.length-1].HideChoiceModel();
        JPanel j_panel_1 = new JPanel(new GridLayout(1,3));
        JLabel j_label[] = new JLabel[4];       
        int a = 0;
        while(a < 4){
        	j_label[a] = new JLabel("", JLabel.CENTER);
        	a++;
        } 
        searchResult_label= new JLabel("", JLabel.CENTER);   
        SetGUIControl.SetGUILabel(searchResult_label);
        
        JButton button_for_search = new JButton("search");
        SetGUIControl.SetGUIButton(button_for_search);  
        button_for_search.addActionListener(new Search_Listener());
        
        JButton button_for_checkin = new JButton("checkIn");
        SetGUIControl.SetGUIButton(button_for_checkin);  
        button_for_checkin.addActionListener(new CheckIn_Listener());
        
        j_panel.add(searchResult_label);       
        j_panel_1.add(button_for_search);                
        j_panel_1.add(button_for_checkin);        
        j_panel_1.add(j_label[0]); 
        j_panel_1.add(j_label[1]); 
        j_panel.add(j_panel_1);
        add(j_panel); 
        return j_panel;
	}
				
	private class Search_Listener implements ActionListener{// inherit the actionlistener from java.awt
		public void actionPerformed(ActionEvent action_event) {	
			SearchModel models[] = new SearchModel[ClasseSearch.length];
			int a = 0;
			while(a <ClasseSearch.length){
				models[a] = sm_gui[a].createModels();
				a++;
			}		
			if((searchResults == null)||(searchResults.isEmpty())){
				String warnword = "There is no search result to display.";
				searchResult_label.setText(warnword);
				return;
			}
			else{			
				DefaultTableModel def_ml = (DefaultTableModel) bookLoans_table.getModel();
				int show_rows;
				if(searchResults.size() < maxtableRow) show_rows = searchResults.size();
				else show_rows = maxtableRow;		
				for(int i=0; i< show_rows; i++){
					Vector<String> row_no = searchResults.get(i);
					Object myobject[] = new Object[row_no.size()];
					int b = 0;
					while(b <row_no.size()){
						myobject[b] = row_no.get(b);
						b++;
					}					
					def_ml.addRow(myobject);
				}		
				if(searchResults.size() > maxtableRow){
					String warnwords = "No enough rows to display all results.\n";
					searchResult_label.setText(warnwords);			
				}
			}
		}
	}
		
	private class CheckIn_Listener implements ActionListener{	// inherit the actionlistener from java.awt
		public void actionPerformed(ActionEvent action_event) {			
		int select_row =  bookLoans_table.getSelectedRow();
		DefaultTableModel def_model = (DefaultTableModel) bookLoans_table.getModel();
		String loan_Id = (String) def_model.getValueAt(select_row, 0);		
		LibraryControl_LP.getexample().updateBookLoans(loan_Id);				
		String showwhat = "already update successsfully";			
		searchResult_label.setText(showwhat);	
		SearchModel models[] = new SearchModel[ClasseSearch.length];
		int a = 0;
		while(a <ClasseSearch.length){
			models[a] = sm_gui[a].createModels();
			a++;
		}		
		if((searchResults == null)||(searchResults.isEmpty())){
			String warnword = "There is no search result to display.";
			searchResult_label.setText(warnword);
			return;
		}
		else{			
			DefaultTableModel def_ml = (DefaultTableModel) bookLoans_table.getModel();
			int show_rows;
			if(searchResults.size() < maxtableRow) show_rows = searchResults.size();
			else show_rows = maxtableRow;		
			for(int i=0; i< show_rows; i++){
				Vector<String> row_no = searchResults.get(i);
				Object myobject[] = new Object[row_no.size()];
				int b = 0;
				while(b <row_no.size()){
					myobject[b] = row_no.get(b);
					b++;
				}					
				def_ml.addRow(myobject);
			}		
			if(searchResults.size() > maxtableRow){
				String warnwords = "No enough rows to display all results.\n";
				searchResult_label.setText(warnwords);			
			}
		}
		return;		
		}
	}	
		
}
