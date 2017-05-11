package zxr.library;

import java.util.Vector;

public class Borrower_LP{

	String new_card_no = null;

	public Borrower_LP(){//constructor	
	}
	
	
	public String CreateNewCardNumber(){
		Vector<String> project = new Vector<String>();
		project.add("__max");	
		DataBaseController database_controller = new DataBaseController();
		Vector<Vector<String>> lines = database_controller.executQuery("select max(card_no) as __max from borrower;", project);
		boolean bool_result;	
		if((lines == null)||(lines.isEmpty())||(lines.get(0) == null)||(lines.get(0).isEmpty())||(lines.get(0).get(0) == ""))
			bool_result = false;
			else bool_result = true;
		
		if(bool_result == false) 
			return null;
		String currentMaxCardNumber = lines.get(0).get(0);			
			
		currentMaxCardNumber = currentMaxCardNumber.substring(2, currentMaxCardNumber.length());
		int InewCardNumber = Integer.parseInt(currentMaxCardNumber)+1;
		String newCardNumber = String.format("ID%06d", InewCardNumber);
		return newCardNumber;		
	}
	
	
	public boolean createNewBorrower(String ssn,String fname,String lname,String address,String phone){
		
		if((ssn==null)||(ssn.isEmpty())||(ssn == ""))
			return false;
		if((fname==null)||(fname.isEmpty())||(fname == ""))
			return false;
		if((lname==null)||(lname.isEmpty())||(lname == ""))
			return false;
		if((address==null)||(address.isEmpty())||(address == ""))
			return false;

		if(!checkExistSsn(ssn)){
			System.out.print("already have the same SSN. Change Another One. \n");
			return false;
		}		
		new_card_no = CreateNewCardNumber();		
		DataBaseController database_controller = new DataBaseController();
		database_controller.execut("INSERT INTO BORROWER VALUES("+"'"+ new_card_no +"'," +
				"'"+ ssn +"'," +"'"+ fname +"'," +"'"+ lname +"'," +"'"+ address +"'," +phone +");");
		return true;
	}	
	
	public static boolean checkExistSsn(String ssn){

		Vector<String> project = new Vector<String>();
		project.add("__count");	
		DataBaseController database_controller = new DataBaseController();
		Vector<Vector<String>> lines = database_controller.executQuery("select count(*) as __count from borrower where ssn = "
				+ "'" +ssn+"';", project);
		boolean bool_result;	
		if((lines == null)||(lines.isEmpty())||(lines.get(0) == null)||(lines.get(0).isEmpty())||(lines.get(0).get(0) == ""))
			bool_result = false;
			else bool_result = true;
		
		if(bool_result == false) 
			return false;
		String count_no = lines.get(0).get(0);					
		if((count_no!=null)||(Integer.parseInt(count_no)>=1))
			return false;	
		return true;		
	}
	
}

