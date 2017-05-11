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
import java.awt.Checkbox;

public class FinesGUI extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String currentSelectCardNumber = "";
	SearchModelGUI sm_gui[] = null;	
	JTable jtable_fines = null;	
	Checkbox check_whetherIn = null;	
	int maxtableRow = 100000;
	
	Vector<Vector<String>> searchResults = null;
	JLabel searchResult_label = null; 
	
	private static String ClassesSearch[] = {"card_no"};
	private static String bookLibrary[] = {"card_no","sum_fine_amt"};
	
	
	public FinesGUI(){		
		super(new BorderLayout());	
		
		JScrollPane scroll_p = creatTable();
        JPanel j_panel = generateSearchModel();        
        add(scroll_p, BorderLayout.CENTER);
        add(j_panel, BorderLayout.WEST);    
	}
		
	private class FinesSearch_Listener implements ActionListener{
		public void actionPerformed(ActionEvent action_event) {
			SearchModel models[] = new SearchModel[ClassesSearch.length];
			int a = 0;
			while(a<ClassesSearch.length){
				models[a] = sm_gui[a].createModels();
				a++;
			}			
			DefaultTableModel def_ml = (DefaultTableModel) jtable_fines.getModel();		
			int show_rows;
			if(searchResults.size() < maxtableRow) 
				show_rows = searchResults.size();
			else show_rows = maxtableRow;
			
			for(int i=0; i< show_rows;i++){
				Vector<String> row_num = searchResults.get(i);
				Object myobject[] = new Object[row_num.size()];
				for(int j = 0; j < row_num.size(); j++){
					myobject[j] = row_num.get(j);
				}			
				def_ml.addRow(myobject);
			}			
			if(searchResults.size() > maxtableRow){
				String warnwords = "No enough rows to display all results.\n";
				searchResult_label.setText(warnwords);			
			}	
		}
	}
		
	private class Updatefine_Listener implements ActionListener{
		public void actionPerformed(ActionEvent action_event) {
		  	DataBaseController database_controller = new DataBaseController();
	    	database_controller.execut("insert into fines(loan_id, fine_amt, paid) select  bl.loan_id, DATEDIFF(bl.date_in, bl.due_date) *0.25, 0 from  book_loans as bl left join Fines as f on bl.loan_id = f.loan_id where  f.loan_id is null and bl.date_in is not null and DATEDIFF(bl.date_in, bl.due_date) > 0;");
	    	database_controller.execut("update book_loans as bl, Fines as f set f.fine_amt = DATEDIFF(bl.date_in, bl.due_date) *0.25 where f.loan_id = bl.loan_id  and bl.date_in is not null and f.paid = 0 and f.fine_amt <> DATEDIFF(bl.date_in, bl.due_date) *0.25 and DATEDIFF(bl.date_in, bl.due_date) > 0 ;");
	    	database_controller.execut("insert into fines  select  bl.loan_id, DATEDIFF(current_date, bl.due_date) *0.25, 0 from  book_loans as bl left join Fines as f on bl.loan_id = f.loan_id where f.loan_id is null and bl.date_in is null and DATEDIFF(current_date, bl.due_date) > 0 ; ");
	    	database_controller.execut("update book_loans as bl, Fines as f set f.fine_amt = DATEDIFF(current_date, bl.due_date) *0.25 where f.loan_id = bl.loan_id and bl.date_in is null and f.paid = 0 and f.fine_amt <> DATEDIFF(current_date, bl.due_date) and DATEDIFF(current_date, bl.due_date) > 0 ;");  	
		}
	}
	
	
	private class Payfine_Listener implements ActionListener{
		public void actionPerformed(ActionEvent action_event) {				
		int row =  jtable_fines.getSelectedRow();
		DefaultTableModel def_model = (DefaultTableModel) jtable_fines.getModel();
		currentSelectCardNumber = (String) def_model.getValueAt(row, 0);		
		boolean whether_result = LibraryControl_LP.getexample().updatePayFine(currentSelectCardNumber);
		
		Vector<String> project = new Vector<String>();
		project.add("__count");	
		DataBaseController database_controller = new DataBaseController();								
		database_controller.execut("update book_loans as bl natural join  Fines as f set f.paid = 1 where f.loan_id = bl.loan_id "
				  + " and bl.date_in is not null and f.paid = 0 and bl.card_no = "
				  + "'" + currentSelectCardNumber + "'; ");
		whether_result=true;	
		if(whether_result ==false) return;
		
		SearchModel models[] = new SearchModel[ClassesSearch.length];
		int a = 0;
		while(a<ClassesSearch.length){
			models[a] = sm_gui[a].createModels();
			a++;
		}	
		searchResults = LibraryControl_LP.getexample().getResutls();	
		DefaultTableModel def_ml = (DefaultTableModel) jtable_fines.getModel();		
		int show_rows;
		if(searchResults.size() < maxtableRow) 
			show_rows = searchResults.size();
		else show_rows = maxtableRow;	
		for(int i=0; i< show_rows;i++){
			Vector<String> row_num = searchResults.get(i);
			Object myobject[] = new Object[row_num.size()];
			for(int j = 0; j < row_num.size(); j++){
				myobject[j] = row_num.get(j);
			}			
			def_ml.addRow(myobject);
		}
		
		if(searchResults.size() > maxtableRow){
			String warnwords = "No enough rows to display all results.\n";
			searchResult_label.setText(warnwords);			
		}			
		return;		
		}
	}
	
	private JScrollPane creatTable(){
		
		sm_gui = new SearchModelGUI[ClassesSearch.length];      
		jtable_fines = new JTable(new DefaultTableModel(bookLibrary, maxtableRow));        
        JScrollPane scroll_p = new JScrollPane(jtable_fines); 
        add(scroll_p); 
        return scroll_p;
	}
	
	private JPanel generateSearchModel(){
        JPanel j_panel = new JPanel(new GridLayout(3, 1, 15, 10));
        
        int a =0;
        while(a< ClassesSearch.length){
        	sm_gui[a] = new SearchModelGUI(a,ClassesSearch);
        	j_panel.add(sm_gui[a]);
        	a++;
        }
        sm_gui[ClassesSearch.length-1].HideChoiceModel();
        JPanel j_panel_1 = new JPanel(new GridLayout(1,3));
             
        searchResult_label= new JLabel("", JLabel.CENTER);   
        SetGUIControl.SetGUILabel(searchResult_label);
  
        JButton button_for_search = new JButton("Search");
        SetGUIControl.SetGUIButton(button_for_search);  
        button_for_search.addActionListener(new FinesSearch_Listener());

        JButton button_for_payfine = new JButton("Pay Now");
        SetGUIControl.SetGUIButton(button_for_payfine);        
        button_for_payfine.addActionListener(new Payfine_Listener());
        
        JButton button_for_updatefine = new JButton("Update Fine");
        SetGUIControl.SetGUIButton(button_for_updatefine);
        button_for_updatefine.addActionListener(new Updatefine_Listener());
        
        j_panel.add(searchResult_label);      
        j_panel_1.add(button_for_search);                
        j_panel_1.add(button_for_payfine);       
        j_panel_1.add(button_for_updatefine); 

        j_panel.add(j_panel_1);
        add(j_panel); 
        return j_panel;
	}
}
