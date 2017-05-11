package zxr.library;

import java.util.Vector;

public class BookSearch_LP {
	private Vector<String> booklibrary = null;

	public BookSearch_LP(){		
		booklibrary = new Vector<String>();	
		booklibrary.add("show.isbn");   
		booklibrary.add("show.title");
		booklibrary.add("show.author_name");
		booklibrary.add("show.branch_id");
		booklibrary.add("show.branch_name");
		booklibrary.add("show.no_of_copies");
		booklibrary.add("checkout");
		booklibrary.add("available");
	}

	public Vector<Vector<String>> searchBook(SearchModel Models[]){		
		String location = SearchModel.createLocation(Models);
		if(!SearchModel.verfyDetails(location))
			return null;		
		DataBaseController database_controller = new DataBaseController();		
		return database_controller.executQuery("select distinct show.isbn, show.title, show.author_name, show.branch_id, show.branch_name,"
				+ " show.no_of_copies, checkout, no_of_copies - checkout as available from (((select isbn, title, author_name,branch_id,branch_name,no_of_copies "
				+ " from ((book natural join book_authors)  natural join ( select book_id as isbn, branch_id, no_of_copies from book_copies) as bc)natural join library_branch) as show )left join "
				+ "((select isbn, branch_id, count(*) as checkout from book_loans "
				+ " where date_in is null group by isbn, branch_id) as be) on show.isbn = be.isbn and show.branch_id = be.branch_id) where "+ location +";", booklibrary);		 
	}

}

