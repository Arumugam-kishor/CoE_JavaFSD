package feeReportSoftware;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		int ch=0;
		Scanner sc = new Scanner(System.in);
		FeeReportService frs = new FeeReportService();
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/fee_report";
		Connection con = DriverManager.getConnection(url,"root","root");
		
		while(ch!=3) {
			System.out.println("Log in as \n1.Admin\n2.Accountant\n3.Exit");
			ch=sc.nextInt();
			if(ch==3)break;
			frs.login(ch,con,sc);
		}
	}
}
