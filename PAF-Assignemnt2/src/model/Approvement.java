package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Approvement {

	//A common method to connect to the DB 
		private Connection connect() {
			Connection con = null;
			
			try {
				 Class.forName("com.mysql.jdbc.Driver");
				 //Provide the correct details: DBServer/DBName, username, password 
				 con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/healthcared?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");

				//For testing          
				 System.out.print("Successfully connected");
				 
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			return con; 
		}
		
		public String readApprovement() {  
			String output = "";  
			
			try {  
				Connection con = connect();  
				if (con == null)  {   
					return "Error while connecting to the database for reading.";  
				} 

				// Prepare the html table to be displayed   
				output = "<table border='1'><tr><th>app_status</th>"
						+ "<th>app_Details</th>app_date<th></th>"
						+ "<th>endorser_type</th>"
						+ "<th>Update</th><th>Remove</th></tr>";


				  String query = "select * from Approvement";   
				  Statement stmt = con.createStatement();   
				  ResultSet rs = stmt.executeQuery(query); 

				  // iterate through the rows in the result set   
				  while (rs.next())   {  

					  	String app_ID = Integer.toString(rs.getInt("app_ID"));
						String app_status = rs.getString("app_status");
						String app_Details = rs.getString("app_Details");
						String app_date = rs.getString("app_date");
						String endorser_type = rs.getString("endorser_type");
						
					  // Add into the html table    

					  output += "<tr><td><input id='hidApp_IDUpdate' name='hidApp_IDUpdate' type='hidden' value='" + app_ID + "'>" + app_status + "</td>"; 

					  output += "<td>" + app_Details + "</td>";
						output += "<td>" + app_date + "</td>";
						output += "<td>" + endorser_type + "</td>";
						
					  
					// buttons     
					  output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
					  		+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-app_ID='"+ app_ID +"'>"+"</td></tr>";

					} 
				  
				  con.close(); 

				  // Complete the HTML tables
				  output += "</table>"; 
				}
				catch (Exception e) {  
					output = "Error while reading the approvement.";  
					System.err.println(e.getMessage()); 
				}

				return output;
			}
		
		//Insert appointment
		public String insertApprovement(String app_status, String app_Details, String app_date, String endorser_type) {
			String output = "";

			try {
				Connection con = connect();  

				if (con == null) {
					return "Error while connecting to the database";
				}

				// create a prepared statement   
				String query = " insert into approvement (`app_ID`,`app_status`,`app_Details`,`app_date`,`endorser_type`)"+" values (?, ?, ?, ?, ?)";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values 
				preparedStmt.setInt(1, 0);
				preparedStmt.setString(2, app_status);
				preparedStmt.setString(3, app_Details);
				preparedStmt.setString(4, app_date);
				preparedStmt.setString(5,endorser_type);
				
				
				//execute the statement   
				preparedStmt.execute();   
				con.close(); 

				//Create JSON Object to show successful msg.
				String newApprovement = readApprovement();
				output = "{\"status\":\"success\", \"data\": \"" + newApprovement + "\"}";
			}
			catch (Exception e) {  
				//Create JSON Object to show Error msg.
				output = "{\"status\":\"error\", \"data\": \"Error while Inserting approvement.\"}";   
				System.err.println(e.getMessage());  
			} 

			 return output; 
		}
		
		//Update appointment
		public String updateApprovement(String app_ID, String app_status, String app_Details, String app_date, String endorser_type )  {   
			String output = ""; 
		 
		  try   {   
			  Connection con = connect();
		 
			  if (con == null)    {
				  return "Error while connecting to the database for updating."; 
			  } 
		 
		   // create a prepared statement    
			   String query = "UPDATE approvement SET app_status=?,app_Details=?,app_date=?,endorser_type=?WHERE app_ID=?";
				 
		   PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		   // binding values    
		   preparedStmt.setString(1, app_status);
			preparedStmt.setString(2,app_Details);
			preparedStmt.setString(3, app_date);
			preparedStmt.setString(4,endorser_type);
			preparedStmt.setInt(5, Integer.parseInt(app_ID));
		   
		 
		   // execute the statement    
		   preparedStmt.execute();    
		   con.close(); 
		 
		   //create JSON object to show successful msg
		   String newApprovement = readApprovement();
		   output = "{\"status\":\"success\", \"data\": \"" + newApprovement + "\"}";
		   }   catch (Exception e)   {    
			   output = "{\"status\":\"error\", \"data\": \"Error while Updating approvement Details.\"}";      
			   System.err.println(e.getMessage());   
		   } 
		 
		  return output;  
		  }
		
		public String deleteApprovement(String app_ID) {  
			String output = ""; 
		 
		 try  {   
			 Connection con = connect();
		 
		  if (con == null)   {    
			  return "Error while connecting to the database for deleting.";   
		  } 
		 
		  // create a prepared statement   
		  String query = "DELETE FROM approvement WHERE app_ID=?"; 
		 
		  PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		  // binding values   
		  preparedStmt.setInt(1, Integer.parseInt(app_ID));       
		  // execute the statement   
		  preparedStmt.execute();   
		  con.close(); 
		 
		  //create JSON Object
		  String newApprovement = readApprovement();
		  output = "{\"status\":\"success\", \"data\": \"" + newApprovement + "\"}";
		  }  catch (Exception e)  {  
			  //Create JSON object 
			  output = "{\"status\":\"error\", \"data\": \"Error while Deleting approvement.\"}";
			  System.err.println(e.getMessage());  
			  
		 } 
		 
		 return output; 
		 }
}
