package zxr.library;
import java.util.Vector;

public class BookCheckIn_LP {
	private Vector<String> booklibrary = null; //create a vector to contain the library attributes
	public BookCheckIn_LP(){// a constructor
		booklibrary = new Vector<String>();
		booklibrary.add("loan_id");
		booklibrary.add("card_no");
		booklibrary.add("fname");
		booklibrary.add("lname");		
		booklibrary.add("isbn");
		booklibrary.add("date_out");
		booklibrary.add("due_date");
		booklibrary.add("date_in");		
	}

	public Vector<Vector<String>> searchBookLoans(SearchModel Models[]){
		String Location = SearchModel.createLocation(Models);
		if(!SearchModel.verfyDetails(Location)) return null;					
		DataBaseController database_controller = new DataBaseController();		
		return database_controller.executQuery("select loan_id, card_no, fname, lname, isbn, data_out, due_date, date_in from book_loans natural join borrower where" 
	              + Location 
	              + ";", booklibrary);				
	}
		
	
	public void updateBookLoans(String loan_Id){
		DataBaseController database_controller = new DataBaseController();		
		database_controller.execut("UPDATE book_loans set date_in = current_date() where loan_id = "
				+ "'" + loan_Id+ "'"
				+ " and date_in is null;");
	}
}
