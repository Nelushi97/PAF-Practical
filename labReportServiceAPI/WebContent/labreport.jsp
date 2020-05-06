<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.LabReport"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<link rel="stylesheet" href="Views/bootstrap.min.css">
	<script src="Components/jquery-3.2.1.min.js"></script>
	<script src="Components/labreport.js"></script>
	<meta charset="ISO-8859-1">

	<meta charset="ISO-8859-1">
	<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">

				<h1>Lab Report </h1>
				<form id="formlab" name="formlab" method="post" action="labreport.jsp">
					Doctor ID : 
					<input id="doctorId" name="doctorId" type="text"class="form-control form-control-sm">
					 <br> Patient ID :
					<input id="patientId" name="patientId" type="text"class="form-control form-control-sm"> 
					<br> Report Type : 
					<input id="reportType" name="reportType" type="text"class="form-control form-control-sm"> 
					<br> report : 
					<input id="report" name="report" type="text"class="form-control form-control-sm">
					 <br>Date :
					 <input id="date" name="date" type="text"class="form-control form-control-sm">
					 <br> 
					<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
		<input type="hidden" id="hidLabIDSave" name="hidLabIDSave" value="">
				</form>


				<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
  
   <br>
   <div id="divItemsGrid">
   
   <%
   
   LabReport labobj = new LabReport();
      out.print(labobj.readlabReport());
   %>
   </div>

			</div>
		</div>
	</div>

</body>
</html>