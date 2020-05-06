package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LabReport {

			//A common method to connect to the DB
			private Connection connect()
			{
				Connection con = null;
				try
				{
					Class.forName("com.mysql.jdbc.Driver");

					//Provide the correct details: DBServer/DBName, username, password
					con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/lab_report?useTimezone=true&serverTimezone=UTC", "root", "");
				}
				catch (Exception e)
				{e.printStackTrace();}
				return con;
			} 

			
			//Read data
			public String readlabReport()
			{
				String output = "";
				try
				{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for reading."; }
					// Prepare the html table to be displayed
					output = "<table border='1'><th>Doctor ID</th><th>Patient ID</th><th>Report Type</th><th>Report</th><th>Date</th><th>Update</th><th>Remove</th></tr>";
					String query = "select * from labreport";
					Statement stmt = con.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					// iterate through the rows in the result set
					while (rs.next())
					{
						String reportId = Integer.toString(rs.getInt("reportId"));
						String doctorId = Integer.toString(rs.getInt("doctorId"));
						String patientId = Integer.toString(rs.getInt("patientId"));
						String reportType =rs.getString("reportType");
						String report = rs.getString("report");
						String date =rs.getString("date");
						
						// Add into the html table
						output += "<tr><td><input id='hidLabIDUpdate'name='hidLabIDUpdate' type='hidden'value='" + reportId + "'>" + doctorId + "</td>";
						output += "<td>" + patientId + "</td>";
						output += "<td>" + reportType + "</td>";
						output += "<td>" + report + "</td>";
						output += "<td>" + date + "</td>";
						
						// buttons
						output += "<td><input name='btnUpdate' type='button'"+ "value='Update'"+"class='btnUpdate btn btn-secondary'></td>"
						+"<td><input name='btnRemove' type='button'"+" value='Remove'"+"class='btnRemove btn btn-danger' data-reportid='"+ reportId + "'>" + "</td></tr>";
					}
					con.close();
					// Complete the html table
					output += "</table>";
				}
				catch (Exception e)
				{
					output = "Error while reading the items.";
					System.err.println(e.getMessage());
				}
				return output;
			}	

			//insert data
			public String insertlabReport(String docID, String pID, String rType, String report, String date)
			{
				String output = "";
				try
				{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for inserting."; }
					// create a prepared statement
					String query = " insert into labreport(`reportId`, `doctorId`, `patientId`, `reportType`, `report`, `date`)"
							+ " values (?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, docID);
					preparedStmt.setString(3, pID);
					preparedStmt.setString(4, rType);
					preparedStmt.setString(5, report);
					preparedStmt.setString(6, date);

					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Inserted successfully";
					
					con.close();
					String newlab = readlabReport();
					output = "{\"status\":\"success\", \"data\": \"" + newlab + "\"}";
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\":\"Error while inserting the item.\"}";
					System.err.println(e.getMessage());
				}
				return output;
			}

			//update
			public String updatelabReport(String rid, String docID, String pID, String rType, String report, String date)
			{
				String output = "";
				try
				{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for updating."; }
					// create a prepared statement
					String query = "UPDATE labreport SET doctorId=?,patientId=?,reportType=?,report=?,date=?WHERE reportId=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1,Integer.parseInt(docID));
					preparedStmt.setInt(2,Integer.parseInt(pID));
					preparedStmt.setString(3,rType);
					preparedStmt.setString(4, report);
					preparedStmt.setString(5, date);
					preparedStmt.setInt(6, Integer.parseInt(rid));
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Updated successfully";
					
					String newlab = readlabReport();
					output = "{\"status\":\"success\", \"data\": \"" + newlab + "\"}";
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
					System.err.println(e.getMessage());
				}
				return output;
			}

			//delete
			public String deletelabReport(String ID)
			{
				String output = "";
				try
				{
					Connection con = connect();
					if (con == null)
					{return "Error while connecting to the database for deleting."; }
					// create a prepared statement
					String query = "delete from labreport where reportId=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(ID));
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully";
					
					String newlab = readlabReport();
					output = "{\"status\":\"success\", \"data\": \"" + newlab + "\"}";
				}
				catch (Exception e)
				{
					output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
					System.err.println(e.getMessage());
				}
				return output;
			} 
}
 