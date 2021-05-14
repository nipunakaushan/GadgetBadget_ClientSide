<%@page import="model.Approvement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Approvement Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/main.js"></script>
</head>
<body>
<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Approvement Management</h1>        
				
				<form id="formApprovement" name="formApprovement" method="post" action="Approvement.jsp">  
					app_status:  
					<input id="app_status" name="app_status" type="text" class="form-control form-control-sm">  
					
					<br> 
					app_Details:  
					<input id="app_Details" name="app_Details" type="text" class="form-control form-control-sm">  
					
					<br>
					 app_date:  
					 <input id="app_date" name="app_date" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 endorser_type:  
					 <input id="endorser_type" name="endorser_type" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					
					 
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidApp_IDSave" name="hidApp_IDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>  
				<div id="divItemsGrid">   
					<%
   					Approvement appObj = new Approvement();
   									out.print(appObj.readApprovement());
   					%>  
					
				</div> 
				  
 			</div>
 		 
 		</div>    
 		
 
	</div> 

</body>

</html>