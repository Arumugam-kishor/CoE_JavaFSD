package feeReportSoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FeeReportDataAccess {

	//Accountant
	
	public void addAccountant(Connection con, Scanner sc) throws SQLException {
		String query = "insert into accountant (name, email, phone, password) values (?,?,?,?)";
		PreparedStatement ps = con.prepareStatement(query);
		System.out.print("Enter Name : ");
		ps.setString(1, sc.next());
		System.out.print("Enter Email : ");
		ps.setString(2, sc.next());
		System.out.print("Enter Phone : ");
		ps.setString(3, sc.next());
		System.out.print("Enter Password : ");
		ps.setString(4, sc.next());
		
		System.out.println(ps.executeUpdate()+" Accountant added Successfully");
	}

	public void viewAccountant(Connection con, Scanner sc) throws SQLException{
		String query = "Select * from accountant";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println("----------------------------------");
			System.out.println("\nID : "+rs.getInt("id"));
			System.out.println("\nName : "+rs.getString("name"));
			System.out.println("\nEmail : "+rs.getString("email"));
			System.out.println("\nPhone : "+rs.getString("phone"));
			System.out.println("----------------------------------");
		}
	}

	public void editAccountant(Connection con, Scanner sc) throws SQLException {
	    System.out.print("Enter Accountant ID: ");
	    String query = "select * from accountant where id = ?";
	    PreparedStatement ps = con.prepareStatement(query);
	    ps.setInt(1, sc.nextInt());
	    ResultSet rs = ps.executeQuery();

	    if (!rs.next()) {
	        System.out.println("Invalid ID");
	        return;
	    }

	    query = "update accountant set name=?, email=?, phone=?, password=? where id=?";
	    ps = con.prepareStatement(query);

	    System.out.print("Enter Name: ");
	    ps.setString(1, sc.next());
	    System.out.print("Enter Email: ");
	    ps.setString(2, sc.next());
	    System.out.print("Enter Phone: ");
	    ps.setString(3, sc.next());
	    System.out.print("Enter Password: ");
	    ps.setString(4, sc.next());
	    ps.setInt(5, rs.getInt("id"));

	    System.out.println(ps.executeUpdate() == 1 ? "Accountant Updated Successfully" : "Update Failed");
	}


	public void deleteAccountant(Connection con, Scanner sc) throws SQLException{
		System.out.print("Enter Accountant ID: ");
		String query = "delete from accountant where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, sc.nextInt());
		System.out.println(ps.executeUpdate()==1?"Accountant Deleted Successfully":"Invalid ID");
	}
	
	//Student

	public void addStudent(Connection con, Scanner sc) throws SQLException {
		String query = "insert into student (name, email, course, fee, paid, due, address, phone) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        System.out.print("Enter Name: ");
        ps.setString(1, sc.next());
        System.out.print("Enter Email: ");
        ps.setString(2, sc.next());
        System.out.print("Enter Course: ");
        ps.setString(3, sc.next());
        System.out.print("Enter Total Fee: ");
        double fee = sc.nextDouble();
        ps.setDouble(4, fee);
        System.out.print("Enter Paid Amount: ");
        double paid = sc.nextDouble();
        ps.setDouble(5, paid);
        ps.setDouble(6, fee - paid);
        System.out.print("Enter Address: ");
        ps.setString(7, sc.next());
        System.out.print("Enter Phone: ");
        ps.setString(8, sc.next());

        
        System.out.println(ps.executeUpdate() + " Student added successfully");		
	}

	public void viewStudent(Connection con, Scanner sc) throws SQLException {
		String query = "select * from student";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        viewDetails(rs);
		
	}

	public void viewStudentWithPendingFee(Connection con, Scanner sc) throws SQLException {
		String query = "select * from student where due>0";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        viewDetails(rs);
		
	}

	public void viewDetails(ResultSet rs) throws SQLException {
		while (rs.next()) {
            System.out.println("----------------------------------");
            System.out.println("\nID: " + rs.getInt("id"));
            System.out.println("\nName: " + rs.getString("name"));
            System.out.println("\nEmail: " + rs.getString("email"));
            System.out.println("\nCourse: " + rs.getString("course"));
            System.out.println("\nTotal Fee: " + rs.getDouble("fee"));
            System.out.println("\nPaid Amount: " + rs.getDouble("paid"));
            System.out.println("\nDue Amount: " + rs.getDouble("due"));
            System.out.println("\nAddress: " + rs.getString("address"));
            System.out.println("\nPhone: " + rs.getString("phone"));
            System.out.println("----------------------------------");
        }
	}
	
	public void editStudent(Connection con, Scanner sc) throws SQLException {
	    System.out.print("Enter Student ID: ");
	    String query = "select * from student where id = ?";
	    PreparedStatement ps = con.prepareStatement(query);
	    ps.setInt(1, sc.nextInt());
	    ResultSet rs = ps.executeQuery();

	    if (!rs.next()) {
	        System.out.println("Invalid ID");
	        return;
	    }

	    query = "update student set name=?, email=?, course=?, fee=?, paid=?, due=?, address=?, phone=? where id=?";
	    ps = con.prepareStatement(query);

	    System.out.print("Enter Name: ");
	    ps.setString(1, sc.next());
	    System.out.print("Enter Email: ");
	    ps.setString(2, sc.next());
	    System.out.print("Enter Course: ");
	    ps.setString(3, sc.next());
	    System.out.print("Enter Fee: ");
	    double fee = sc.nextDouble();
	    ps.setDouble(4, fee);
	    System.out.print("Enter Paid Amount: ");
	    double paid = sc.nextDouble();
	    ps.setDouble(5, paid);
	    ps.setDouble(6, fee-paid);
	    System.out.print("Enter Address: ");
	    ps.setString(7, sc.next());
	    System.out.print("Enter Phone: ");
	    ps.setString(8, sc.next());
	    ps.setInt(9, rs.getInt("id"));

	    System.out.println(ps.executeUpdate() == 1 ? "Student Updated Successfully" : "Update Failed");
	}


	public void deleteStudent(Connection con, Scanner sc) throws SQLException {
		System.out.print("Enter Student ID: ");
		String query = "delete from student where id=?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, sc.nextInt());
		System.out.println(ps.executeUpdate()==1?"Student Deleted Successfully":"Invalid ID");
	}
}
