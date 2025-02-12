package feeReportSoftware;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class FeeReportService {

	public void login(int ch, Connection con,Scanner sc){
		
		if(ch!=1||ch!=2) {
			System.out.println("Invalid Option");
			return;
		}
		
		FeeReportDataAccess fda = new FeeReportDataAccess();
		
		System.out.print("Enter UserName/Email : ");
		String username = sc.next();
		System.out.print("Enter Password : ");
		String pwd = sc.next();
		if(ch==1) {
			String query = "select * from admin where username=? and password=?";
			PreparedStatement p;
			try {
				p = con.prepareStatement(query);
				p.setString(1, username);
				p.setString(2, pwd);
				ResultSet rs = p.executeQuery();
				if(rs.next()) {
					System.out.println("Admin Found with ID: "+ rs.getInt("id"));
					int ch1=0;
					while(ch1!=5) {
						System.out.println("1.Add new accountant\n2.View existing accountants\n3.Edit accountant details.\r\n4.Delete accountants.\n5.Logout");
						ch1 = sc.nextInt();
						switch(ch1) {
						case 1:
							fda.addAccountant(con,sc);
							break;
						case 2:
							fda.viewAccountant(con,sc);
							break;
						case 3:
							fda.editAccountant(con,sc);
							break;
						case 4:
							fda.deleteAccountant(con,sc);
							break;
						}
					}
				}
				else System.out.println("Incorrect Username or Password");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		else if(ch==2) {
			String query = "select * from accountant where email=? and password=?";
			PreparedStatement p;
			try {
				p = con.prepareStatement(query);
				p.setString(1, username);
				p.setString(2, pwd);
				ResultSet rs = p.executeQuery();
				if(rs.next()) {
					System.out.println("Accountant Found: "+ rs.getString("name"));
					int ch1=0;
					while(ch1!=6) {
						System.out.println("1.Add new Student\n2.View Student Records\n3.View Student with Pending Fee\n4.Edit Student Details.\n5.Delete Student.\n6.Logout");
						ch1 = sc.nextInt();
						switch(ch1) {
						case 1:
							fda.addStudent(con,sc);
							break;
						case 2:
							fda.viewStudent(con,sc);
							break;
						case 3:
							fda.viewStudentWithPendingFee(con,sc);
							break;
						case 4:
							fda.editStudent(con,sc);
							break;
						case 5:
							fda.deleteStudent(con,sc);
							break;
						}
					}
				}
				else System.out.println("Incorrect Username or Password");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
