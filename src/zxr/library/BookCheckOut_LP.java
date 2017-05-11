package zxr.library;

import java.util.Vector;

public class BookCheckOut_LP{

	private String isbn;
	private String branch_Id; 
	private String cardNumber;
	private String loan_Id;
	
	public static boolean checkExistIsbn(String inputisbn){		
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
	
	
	public static boolean checkExistBranchId(String branch_Id){
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
	
	
	
	
	public static boolean checkExistCardNo(String cardNumber){		
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

		
	public boolean checkOutBook(String isbn2, String branchId, String cardNo){
		this.isbn = isbn2;
		this.branch_Id = branchId;
		this.cardNumber = cardNo;
		if((checkExistIsbn(isbn)== true)||
				(checkExistBranchId(branch_Id)== true)||
				 (checkExistCardNo(cardNumber)== true))
			return false;				
		if((!checkMaximumBookLoans())||(!checkBookCopysAvailalbe()))
			return false;
		loan_Id = createNewLoadNumber();
		DataBaseController database_controller = new DataBaseController();
		database_controller.execut("INSERT INTO BOOK_LOANS VALUES("+
				"'"+ loan_Id +"'," +
				"'"+ isbn2 +"'," +
				"'"+ branchId +"'," +
				"'"+ cardNo +"'," +   
				"current_date(),date_add(current_date(), interval 14 day), NULL);");
		return true;
	}
	
	
	private String createNewLoadNumber(){
		String newLoadNumber = "";
		String curMaxCardNumber;				
		Vector<String> project = new Vector<String>();
		project.add("__max");	
		DataBaseController database_controller = new DataBaseController();
		Vector<Vector<String>> lines = database_controller.executQuery("select max(loan_id) as _max from BOOK_LOANS;", project);
		boolean bool_result;	
		if((lines == null)||(lines.isEmpty())||(lines.get(0) == null)||(lines.get(0).isEmpty())||(lines.get(0).get(0) == ""))
			bool_result = false;
			else bool_result = true;
		
		if(bool_result == false) 
			return null;
		curMaxCardNumber = lines.get(0).get(0);	
		
		boolean bool_Result_1 = true;
		if((curMaxCardNumber==null)||(curMaxCardNumber.isEmpty())||(curMaxCardNumber == ""))
			bool_Result_1 = false;
		if(bool_Result_1 == false) {
			newLoadNumber = "1";
			return newLoadNumber;
		}
		newLoadNumber = String.valueOf(Integer.parseInt(curMaxCardNumber)+1);
		return newLoadNumber;		
	}
	
	
	private boolean checkMaximumBookLoans(){		
		Vector<String> project = new Vector<String>();
		project.add("__num");	
		DataBaseController database_controller = new DataBaseController();
		Vector<Vector<String>> lines = database_controller.executQuery("select count(*) as __num from book_loans where card_no = '"+cardNumber+"' and date_in is null ;", project);
		boolean bool_result;	
		if((lines == null)||(lines.isEmpty())||(lines.get(0) == null)||(lines.get(0).isEmpty())||(lines.get(0).get(0) == ""))
			bool_result = false;
			else bool_result = true;
		
		if(bool_result == false) 
			return false;
		String maximumBookLoans = lines.get(0).get(0);		
				
		if((maximumBookLoans==null)||(maximumBookLoans.isEmpty())||(maximumBookLoans == ""))
			maximumBookLoans = "0";
		
		if(Integer.parseInt(maximumBookLoans) >= 3){ // one man could borrow at most 3 books
			System.out.print("each borrower is permitted a maximum of 3 book_loans\n");
			return false;
		}
		return true;
	}
	
	
	
	private boolean checkBookCopysAvailalbe(){
		Vector<String> booklibrary = new Vector<String>();
		booklibrary.add("no_of_copies");	
		booklibrary.add("checkout");
		booklibrary.add("available");
		DataBaseController database_controller = new DataBaseController();
		Vector<Vector<String>> rowdetail = database_controller.executQuery("select no_of_copies, checkout, no_of_copies - checkout as available from (book_copies as bc left join "
				+ "((select isbn as book_id, branch_id, count(*) as checkout from book_loans where date_in is null group by isbn, branch_id) as ibc) "
				+ "on ibc.book_id = bc.book_id and ibc.branch_id = bc.branch_id) "
				+ "where bc.book_id = "
				+ " '"+isbn+"' "
				+" and bc.branch_id ="
				+ "'" + branch_Id + "'", booklibrary);

		if((rowdetail.isEmpty())||(rowdetail.get(0).isEmpty())) 
			return false;
		
		String no_of_copies = rowdetail.get(0).get(0);
		String checkout = rowdetail.get(0).get(1);
		String available = rowdetail.get(0).get(2);
		
		System.out.println("no_of_copies is "+ no_of_copies
				+"  checkout is "+ checkout
				+"  available is "+ available);
		
		if((available!=null)&&(!available.equalsIgnoreCase("null"))){
				System.out.println("available is "+ available);
				if(Integer.parseInt(available) > 0) 
				return true;
		}
		else if ((no_of_copies!= null)&&(Integer.parseInt(no_of_copies) > 0)){			
				System.out.println("no_of_copies is "+ no_of_copies);
				return true;
		}		
		return false;
	}

}
