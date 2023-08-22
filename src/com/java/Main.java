package com.java;

import java.sql.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int choice=-1,empid;
		String newempname,newdept;
		try {
			
			
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
		    
		while(true) {
				System.out.println("\n=========================================");
				System.out.println("        Employee Management System");
				System.out.println("=========================================");
				System.out.println("1. Add a Employee");
				System.out.println("2. List all Employee");
				System.out.println("3. Delete a Employee");
				System.out.println("4. Update a Employee");
				System.out.println("5. Exit");
				System.out.println("=========================================");
				System.out.println("Enter your choice:(Integer Input) ");
				if(sc.hasNextInt()) {
					choice=sc.nextInt();
				}else 
					System.out.println("Invalid Input..");
					break;
				}
				
				switch(choice) {
				case 1:
										System.out.println("Enter Id(int) ");
					if(sc.hasNextInt()) {
						empid=sc.nextInt();
					}else {
						System.out.println("Invalid Input..");
						continue;
					}
					sc.nextLine();
					System.out.println("Enter Employee Name");
					newempname=sc.nextLine();
					System.out.println("Enter Department:");
					newdept=sc.nextLine();
					String sql="insert into employee (empid,empname,dept) values (?,?,?)";
					try(PreparedStatement stat=con.prepareStatement(sql)){
						stat.setInt(1, empid);
						stat.setString(2, newempname);
						stat.setString(3, newdept);
						stat.executeUpdate();
					}
					System.out.println("Employee Added Successfully!!!");
					break;
				case 2:
					
					System.out.println("List of all Employee :");
					sql="select * from employee";
					try(PreparedStatement stat=con.prepareStatement(sql)){
						ResultSet result=stat.executeQuery(sql);
						while(result.next()) {
							int id1=result.getInt("empid");
							String name=result.getString("empname");
							String dept=result.getString("dept");
							System.out.println("Employee id: \t "+id1+"Employee name:\t "+name+"Department: "+dept);
						}
					}
					break;
				case 3:
					
					System.out.println("Enter Employee id to delete");
									if(sc.hasNextInt()) {
						empid=sc.nextInt();
					}else {
						System.out.println("Invalid Input..");
						continue;
					}
					sc.nextLine();
					sql="delete from employee where empid=?";
					try(PreparedStatement stat=con.prepareStatement(sql)){
						stat.setInt(1, empid);
						int affectedrow=stat.executeUpdate();
						if(affectedrow>0) {
							System.out.println("Employee Deleted!!");
						}else {
							System.out.println("Employee id not found");
						}
					}
					break;
				case 4:
					
					System.out.println("Enter ID to update");
					if(sc.hasNextInt()) {
					   empid=sc.nextInt();
					}else {
						System.out.println("Invalid Input..");
						continue;
					}
					sc.nextLine();
					System.out.println("Enter new Employee Name");
					newempname=sc.nextLine();
					System.out.println("Enter new Department ");
					newdept=sc.nextLine();
					sql="update employee set empname=?,dept=? where empid=?";
					try(PreparedStatement stat=con.prepareStatement(sql)){
						stat.setString(1, newempname);
						stat.setString(2, newdept);
						stat.setInt(3, empid);
						int affectedrow=stat.executeUpdate();
						if(affectedrow>0) {
							System.out.println("Employee Updated!!");
						}else {
							System.out.println("Employee id not found");
						}
					}
					break;
				case 5:
					
					
					System.out.println("Exiting...");
					con.close();
					System.exit(0);
					default:
						System.exit(0);
				}
			}
		}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}