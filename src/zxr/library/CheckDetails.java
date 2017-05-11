package zxr.library;

import java.util.Vector;

public class CheckDetails{
		 
	public static boolean WhetherStringNull(String str){
		if((str==null)||(str.isEmpty())||(str == ""))
			return false;
		else return true;
	}
	
	public static boolean WhetherIntegerNull(String str){
		if((str==null)||(str.isEmpty())||(Integer.parseInt(str)< 1))
			return false;
		else return true;
	}
	
	    	
	public static boolean WhetherPayFine(String cardNumber){
		
		Vector<String> project = new Vector<String>();
		DataBaseController database_controller = new DataBaseController();
		Vector<Vector<String>> lines = database_controller.executQuery(" select count(*) as " 
				+ project 
				+ " from fines natural join book_loans where paid = 0 and date_in is null and card_no = " 
				+ "'" +cardNumber +"'"
				+ " group by card_no;", project);		
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
	
	 public static boolean WhetherMatrixIsEmpty(Vector<Vector<String>> martrix){
			 if((martrix == null)||(martrix.isEmpty())||(martrix.get(0) == null)||(martrix.get(0).isEmpty())||(martrix.get(0).get(0) == ""))
				 return false;
			 else
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
		
		public static boolean WhetherAlreadyExistIsbn(String inputisbn){		
			if((inputisbn==null)||(inputisbn.isEmpty())||(inputisbn == ""))
				return false;
			boolean bool_result;
			Vector<String> project = new Vector<String>();
			project.add("__count");
			DataBaseController database_controller = new DataBaseController();
			Vector<Vector<String>> lines = database_controller.executQuery("select count(*) as __count from book where isbn =" + "'" +inputisbn+"';", project);
			
			if((lines == null)||(lines.isEmpty())||(lines.get(0) == null)||(lines.get(0).isEmpty())||(lines.get(0).get(0) == ""))
				bool_result = false;
				else bool_result = true;	
			if(bool_result == false) 
				return false;
			
			String Str = lines.get(0).get(0);		
			if((Str==null)||(Str.isEmpty())||(Integer.parseInt(Str)< 1))
				return false;
			else return true;		
		}
		
		public static boolean WhetherAlreadyBranchId(String branch_Id){
			if((branch_Id==null)||(branch_Id.isEmpty())||(branch_Id == ""))
				return false;
			boolean bool_result;
			Vector<String> project = new Vector<String>();
			project.add("__count");
			DataBaseController database_controller = new DataBaseController();
			Vector<Vector<String>> lines = database_controller.executQuery("select count(*) as __count from book where isbn =" + "'" +branch_Id+"';", project);
			
			if((lines == null)||(lines.isEmpty())||(lines.get(0) == null)||(lines.get(0).isEmpty())||(lines.get(0).get(0) == ""))
				bool_result = false;
				else bool_result = true;	
			if(bool_result == false) 
				return false;
			
			String Str = lines.get(0).get(0);		
			if((Str==null)||(Str.isEmpty())||(Integer.parseInt(Str)< 1))
				return false;
			else return true;			
			}			
	
		public static boolean WhetherAlreadyCardNo(String cardNumber){		
			if((cardNumber==null)||(cardNumber.isEmpty())||(cardNumber == ""))
				return false;
			boolean bool_result;
			Vector<String> project = new Vector<String>();
			project.add("__count");
			DataBaseController database_controller = new DataBaseController();
			Vector<Vector<String>> lines = database_controller.executQuery("select count(*) as __count from book where isbn =" + "'" +cardNumber+"';", project);
			
			if((lines == null)||(lines.isEmpty())||(lines.get(0) == null)||(lines.get(0).isEmpty())||(lines.get(0).get(0) == ""))
				bool_result = false;
				else bool_result = true;	
			if(bool_result == false) 
				return false;
			
			String Str = lines.get(0).get(0);		
			if((Str==null)||(Str.isEmpty())||(Integer.parseInt(Str)< 1))
				return false;
			else return true;		
		}
		
		
	
	
}