
package zxr.library;

import java.util.Vector;

import zxr.library.SearchModel;

public class LibraryControl_LP{
	private BookSearch_LP bookSearch = null;
	private Borrower_LP borrowerManagement = null;
	private BookCheckOut_LP bookCheckOut = null;
	private BookCheckIn_LP bookCheckIn = null;
	private Fines_LP fines = null;
	
	static private LibraryControl_LP one_example = null;
	private Vector<Vector<String>> matrix = null;
	
	private LibraryControl_LP(){
		bookSearch = new BookSearch_LP();
		borrowerManagement = new Borrower_LP();
		bookCheckOut = new BookCheckOut_LP();
		bookCheckIn = new BookCheckIn_LP();
		fines = new Fines_LP();
	}	
	
	public static LibraryControl_LP getexample(){
		if(one_example==null){
			one_example = new LibraryControl_LP();
		}
		return one_example;
	}
	
	public Vector<Vector<String>> searchBook(SearchModel models[]){
		return bookSearch.searchBook(models);
	}
	
	public boolean createNewBorrower(
			String ssn,
			String fname,
			String lname,
			String address,
			String phone
			){
		boolean result = borrowerManagement.createNewBorrower(ssn,fname, lname, address, phone);
		return result;
	}
		
	public boolean checkOutBook(String isbn1, String branch_Id, String card_no){
		boolean result = bookCheckOut.checkOutBook(isbn1, branch_Id, card_no);
		return result;
	}
	
	public Vector<Vector<String>> searchBookLoans(SearchModel models[]){
		return bookCheckIn.searchBookLoans(models);
	}
	
	public void updateBookLoans(String loan_Id){
		bookCheckIn.updateBookLoans(loan_Id);
	}
	
	public boolean updatePayFine(String card_no){
		boolean bool_result = fines.updatePayFine(card_no);;
		return bool_result;
	}
	
	public  boolean displayAllFinesGroupByCardNo(boolean containPrevios, SearchModel models[]){
		boolean b_result = fines.displayAllFinesGroupByCardNo(containPrevios, models);
		return b_result;
	}
	
	public  Vector<Vector<String>> getResutls(){
	    	return matrix;
	}
}
