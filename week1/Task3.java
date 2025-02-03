package week1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Task3 {

	public static void processInput(int num) throws Exception{
		num = 1/num;
		System.out.println("Reciprocal : "+num);
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		sc.close();
		
		try {
			processInput(num);
		}
		
		catch(InputMismatchException e) {
			System.out.println("Exception : Give Integer Input");
		}
		
		catch(ArithmeticException e) {
			System.out.println("Exception : Divide by Zero");
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}

	}

}
