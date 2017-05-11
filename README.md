# SQL-based-Library-DBMS

Created by Xiangru Zhou

This SQL programming project is to create of a database host application that interfaces with a backend SQL database implementing a DBMS for library circulation desk application.

****************************************
About the database schema:

I only upload the graph of this schema. 

The sql file is NOT uploaded. It's easy to be created based on this schema.

******************************************
Project Requirements:

Your application must utilize a SQL database as its back end data store. You may use MySQL, PostgreSQL, Oracle Express, or MS SQL Server.

Your host application must use a GUI interface. You may choose to implement either a native GUI application or a web application interface. Permitted application languages are Java, C#, PHP, and Python, Ruby, and Javascript. You are free to use any third party libraries, frameworks, or templates to implement your GUI (e.g. Swing, JSP, ASP, Rails, AngularJS, Django, etc.).

********************************************
Functional Requirements:

1. Graphical User Interface (GUI), Overall Design, and Architecture

   All user interaction with the Library Circulation Desk DBMS (queries, updates, deletes, etc.) must be done from a graphical user interface of your original design.
   
2. Book Search and Availability
   
   Using your GUI, provide a single search field to locate a book given any combination of ISBN, title, and/or Author(s). Your query should support substring matching.

   Search should display the following information for each book in the result set:
   
       • ISBN
       
       • Book title
       
       • All book author(s)

       • Availability status at the currently selected branch
 
 3. Checking Out Books
 
    Using your GUI, be able to check out a book at the current location, which will create a new record in the BOOK_LOANS table. The Date_out should be today’s date. The Due_date should be 14 days after the Date_out. A checkout requires selecting a combination of BOOK and a BORROWER.
    
    Each BORROWER is permitted a maximum of 3 BOOK_LOANS. If a BORROWER already has 3 active BOOK_LOANS, then the checkout (i.e. create new BOOK_LOANS tuple) should fail and display an appropriate error message.
    
    BORROWERS are not permitted to check out a book if they have either an unpaid fine or a currently overdue book. The system should reject such attempts and display an appropriate error message.
    
 4. Checking In Books
 
    Using your GUI, be able to check in a book. Be able to locate BOOK_LOANS tuples by searching on any book information or BORROWER information.
    
    If an overdue book is checked in, a FINES record should be created.
    
 5. Borrower Management
 
    Using your GUI, be able to create new a BORROWER record in the system.
    
    BORROWER Name, Ssn, and Address attribute values are required to create a new account (i.e. values must be NOT NULL).
    
    Borrowers are allowed to possess exactly one library card. If a new borrower is attempted with a duplicate Ssn, then your system should reject the new BORROWER and return an appropriate error message.

6.  Fines
    
    Using your GUI, be able display FINES information for a specified BORROWER. You should provide a mechanism to display paid, unpaid, or both.
    
    Using your GUI, be able display all overdue books for a specified BORROWER.
    
    Fines may not be assessed (i.e. create a record in the FINES table) until a book is returned.
    
    Fines are assessed at a rate of $0.25/day (twenty-five cents per day).
    
    Using your GUI, provide a mechanism for librarians to enter payment of fines (i.e. update a FINES record to set Paid attribute to be true.
    
    
    
