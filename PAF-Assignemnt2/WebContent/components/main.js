$(document).ready(function() 
{  
	if ($("#alertSuccess").text().trim() == "")  
	{   
		$("#alertSuccess").hide();  
	} 
	$("#alertError").hide(); 
}); 

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) 
{  
	// Clear alerts---------------------  
	$("#alertSuccess").text("");  
	$("#alertSuccess").hide();  
	$("#alertError").text("");  
	$("#alertError").hide(); 

	// Form validation-------------------  
	var status = validateHospitalForm();  
	if (status != true)  
	{   
		$("#alertError").text(status);   
		$("#alertError").show();   
		return;  
	} 

	// If valid------------------------  
	var t = ($("#hidApp_IDSave").val() == "") ? "POST" : "PUT";
	
	$.ajax(
	{
		url : "ApprovementAPI",
		type : t,
		data : $("#formApprovement").serialize(),
		dataType : "text",
		complete : function(response, status)
		{
			onHospitalSaveComplete(response.responseText, status);
		}
	});
}); 

function onHospitalSaveComplete(response, status){
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Saved.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Saving.");
		$("#slertError").show();
	}else{
		$("#alertError").text("Unknown Error while Saving.");
		$("#alertError").show();
	}
	$("#hidApp_IDSave").val("");
	$("#formApprovement")[0].reset();
}

//UPDATE========================================== 
$(document).on("click", ".btnUpdate", function(event) 
		{     
	$("#hidApp_IDSave").val($(this).closest("tr").find('#hidApp_IDUpdate').val());     
	$("#app_status").val($(this).closest("tr").find('td:eq(0)').text());    
	$("#app_Details").val($(this).closest("tr").find('td:eq(1)').text());     
	$("#app_date").val($(this).closest("tr").find('td:eq(2)').text());     
	$("#endorser_type").val($(this).closest("tr").find('td:eq(3)').text()); 
	

});


//Remove Operation
$(document).on("click", ".btnRemove", function(event){
	$.ajax(
	{
		url : "ApprovementAPI",
		type : "DELETE",
		data : "app_ID=" + $(this).data("app_id"),
		dataType : "text",
		complete : function(response, status)
		{
			onHospitalDeletedComplete(response.responseText, status);
		}
	});
});

function onHospitalDeletedComplete(response, status)
{
	if(status == "success")
	{
		var resultSet = JSON.parse(response);
			
		if(resultSet.status.trim() == "success")
		{
			$("#alertSuccess").text("Successfully Deleted.");
			$("#alertSuccess").show();
					
			$("#divItemsGrid").html(resultSet.data);
	
		}else if(resultSet.status.trim() == "error"){
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	}else if(status == "error"){
		$("#alertError").text("Error While Deleting.");
		$("#alertError").show();
	}else{
		$("#alertError").text("Unknown Error While Deleting.");
		$("#alertError").show();
	}
}

//CLIENTMODEL
function validateHospitalForm() {  
	// app_status  
	if ($("#app_status").val().trim() == "")  {   
		return "Insert app_status.";  
		
	} 
	
	 // app_Details  
	if ($("#app_Details").val().trim() == "")  {   
		return "Insert app_Details.";  
		
	} 
	 
	
	 
	 // Email 
	if ($("#app_date").val().trim() == "")  {   
		return "Insert app_date.";  
		
	} 
	
	// endorser_type  
	if ($("#endorser_type").val().trim() == "")  {   
		return "Insert endorser_type.";  
		
	} 
	 
	 
	
 
	 
	 return true; 
	 
}
