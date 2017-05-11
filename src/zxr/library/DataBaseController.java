package zxr.library;
import java.sql.*;
import java.util.Vector;

import zxr.library.DataBaseController;

public class DataBaseController {// Follow professor's example: DBTest.java
	
	Connection conn = null;
	
	public DataBaseController(){
		connectDatabase();
		execut("use BookLibrary;");
	}	
    

     private void connectDatabase(){
     	try{
    	// Create a connection to the local MySQL server, with the "company" database selected.
		//        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "mypassword");
		// Create a connection to the local MySQL server, with the NO database selected.
		conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/booklibrary", "root", "");
    	}catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
    }
     
     public void execut(String command){
    	try{
		// Create a SQL statement object and execute the query.
		Statement stmt = conn.createStatement();
		// Set the current database, if not already set in the getConnection
		// Execute a SQL statement
		stmt.execute(command);
		}
		catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
    }
     
     public void close(){
     	try{
     		if(conn != null)
     			conn.close();
     	}
     	catch(SQLException ex) {
 			System.out.println("Error in connection: " + ex.getMessage());
 		} 
     }    
        
    public Vector<Vector<String>> executQuery(String command, Vector<String> booklibrary){
    	
    	Vector<Vector<String>> row_no = new Vector<Vector<String>>();    	
    	Statement stmt;
    	ResultSet result_set;
    	try{
			// Create a SQL statement object and execute the query.
    		stmt = conn.createStatement();
			// Set the current database, if not already set in the getConnection
			// Execute a SQL statement
    		result_set = stmt.executeQuery(command);
			while(result_set.next()){
				Vector<String> line = new Vector<String>();
				for(int i = 0; i < booklibrary.size(); i++){
					String details = result_set.getString(booklibrary.get(i));
					line.add(details);					
				}
				row_no.add(line);
			}	
			result_set.close();			
		}
		catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}finally{	
			  close();
		}    	
    	return row_no;
    }
 
		   
}