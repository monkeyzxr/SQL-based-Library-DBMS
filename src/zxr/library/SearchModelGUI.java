package zxr.library;

import java.awt.Choice;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JPanel;

public class SearchModelGUI  extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Choice choice_search_type = null;	
	Choice choice_model_compare = null;
	TextField textdetails = null;
	Choice choice_model = null;
	
	public void HideChoiceModel(){
		choice_model.setVisible(false);
	}
	
	public SearchModelGUI(int find_index, String find_classes[]){
		super(new GridLayout(1, 4, 10, 2));
		choice_search_type =new Choice();
		int a =0;
		while(a<find_classes.length){
			choice_search_type.add(find_classes[a]);
			a++;
		}
		choice_search_type.select(find_index);			
		add(choice_search_type);		
		choice_model_compare =new Choice();			
		choice_model_compare.add("CONTAINS");    
		choice_model_compare.add("=");
		choice_model_compare.select(0);			
		add(choice_model_compare);					
		textdetails = new TextField(10);			
		add(textdetails);						
		choice_model =new Choice();
		choice_model.add("AND");           
		choice_model.add("OR");         
		choice_model.select(0);			
		add(choice_model);
		setVisible(true);			
	}
	
	public SearchModel createModels(){
		SearchModel search_model_a = new SearchModel(
				choice_search_type.getSelectedItem(),
				choice_model_compare.getSelectedItem(),
				textdetails.getText(),
				choice_model.getSelectedItem());
		return search_model_a;
	}	
	
	
}
