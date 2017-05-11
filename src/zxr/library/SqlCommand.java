package zxr.library;

import java.util.Vector;

public class SqlCommand {
// these sql command not directly used in other classes
// need references
	public static String InsertToBorrowerCommand(String card_no, String ssn, String fname, String lname, String address, String phone){		
		String sqlcommand = "INSERT INTO BORROWER VALUES("+"'"+ card_no +"'," +"'"+ ssn +"'," +"'"+ fname +"'," +"'"+ lname +"'," +"'"+ address +"'," +phone +");";				
		return sqlcommand;		
	}
	

	public static String InsertToBookCommand(String isbn, String title){ 
		String sqlcommand = "INSERT INTO BOOK VALUES("+"'"+ isbn +"'," +"'"+ title +"');";	
		return sqlcommand;
	}
	

	public static String InsertToBookAuthorsCommand(String isbn, String author_name){
		String sqlcommand = "INSERT INTO BOOK_AUTHORS VALUES("+"'"+ isbn +"'," +"'"+ author_name +"');";	
		return sqlcommand;
	}
	

	public static String InsertToLibraryBranchCommand(String branch_Id, String branch_Name, String address){
		String sqlcommand = "INSERT INTO LIBRARY_BRANCH VALUES("+"'"+ branch_Id +"'," +"'"+ branch_Name +"'," +"'"+ address +"');";	
		return sqlcommand;
	}
	

	public static String InsertToBookCopiesCommand(String book_Id, String branch_Id, String numOfCopies){
		String sqlcommand = "INSERT INTO BOOK_COPIES VALUES("+"'"+ book_Id +"'," +"'"+ branch_Id +"'," +"'"+ numOfCopies +"');";	
		return sqlcommand;
	}	
	
		
	
	public static String InsertToBookLoansCommand(String loan_Id, String isbn, String branch_Id, String cardNumber){
		String sqlcommand = "INSERT INTO BOOK_LOANS VALUES("+"'"+ loan_Id +"'," +"'"+ isbn +"'," +"'"+ branch_Id +"'," +"'"+ cardNumber +"'," +     
	   //Due days is two weeks after the day-out
					"current_date(),date_add(current_date(), interval 14 day), NULL);";
		return sqlcommand;
	}
	
	
	 public static boolean WhetherMatrixNotEmpty(Vector<Vector<String>> martrix){
		 if((martrix == null)||(martrix.isEmpty())||(martrix.get(0) == null)||(martrix.get(0).isEmpty())||(martrix.get(0).get(0) == ""))
			 return false;
		 else
		 return true;
	 }

	}