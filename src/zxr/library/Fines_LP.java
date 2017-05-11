package zxr.library;

import java.util.Vector;

public class Fines_LP{

    private Vector<Vector<String>> result = null;
   
	public static String ShowAllFinesGroupbyCardNumber(boolean containPrevios,String cardNo, String project, String location){
		String paidWhereStatement = null;		
		if(containPrevios == true) 
			paidWhereStatement = " ";
		else 
			paidWhereStatement = "paid = 0 and ";	
		 String sqlcommand = " select card_no as "
		 		+ cardNo +
		 		", sum(fine_amt) as "
		 		+ project +
		 " from fines natural join book_loans where " + paidWhereStatement
		  + location + 
		 " group by card_no; ";
		 return sqlcommand;
	}
    
    
	public  boolean displayAllFinesGroupByCardNo(boolean whetherIn, SearchModel models[]){		
		String location = SearchModel.createLocation(models);
		if(!SearchModel.verfyDetails(location))
			return false;	
		
		String sum_fine = "sum_fine_amt";
		String cardNumber = "cardNo";
		Vector<String> booklibrary = new Vector<String>();
		booklibrary.add(cardNumber);
		booklibrary.add(sum_fine);
		
		String sqlcommand = ShowAllFinesGroupbyCardNumber(whetherIn,cardNumber,sum_fine,location);
		DataBaseController database_controller = new DataBaseController();		
		result = database_controller.executQuery(sqlcommand, booklibrary);	
		
		 if((result == null)||(result.isEmpty())||(result.get(0) == null)||(result.get(0).isEmpty())||(result.get(0).get(0) == ""))
			 return false;	
		return true;
	}
    		
	public  boolean updatePayFine(String cardNumber){

		Vector<String> project = new Vector<String>();
		project.add("__count");	
		DataBaseController database_controller = new DataBaseController();
		Vector<Vector<String>> lines = database_controller.executQuery("select count(*) as __count from fines natural join book_loans where paid = 0 and date_in is null "
				+ " and card_no = " 
				+ "'" +cardNumber +"'"
				+ " group by card_no;", project);	
		String count = lines.get(0).get(0);									
		if((count==null)||(count.isEmpty())||(Integer.parseInt(count)< 1))
			return false;
		database_controller.execut("update book_loans as bl natural join  Fines as f set f.paid = 1 where f.loan_id = bl.loan_id "
				  + " and bl.date_in is not null and f.paid = 0 and bl.card_no = "
				  + "'" + cardNumber + "'; ");
		return true;
	}

}
