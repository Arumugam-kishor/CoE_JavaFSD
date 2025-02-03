package week1;

import java.util.Arrays;
import java.util.Scanner;

public class Task5 {

	public static void splitAndCapitalize(String s) {
		String ss[] = s.split(" ");
		for(int i=0;i<ss.length;i++) {
			char[] c = ss[i].toCharArray();
			if(c[0]>'Z') {
				c[0]-=32;
			}
			ss[i]=new String(c);
		}
		System.out.println(Arrays.toString(ss));
	}

	public static void countOccurrences(String s, String sub) {
		int occurence = 0;
		int index = 0;
		while(true) {
			if(s.indexOf(sub, index)!=-1) {
				occurence++;
				index+=s.indexOf(sub,index)+1;
			}
			
			else break;
		}
		System.out.println(occurence);
	}

	public static void reverseString(String s) {
		StringBuilder sb = new StringBuilder(s);
		System.out.println(sb.reverse());
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		
		reverseString(s);
		
		System.out.println("Enter String to count occurences");
		String sub = sc.next();
		
		countOccurrences(s,sub);
		
		splitAndCapitalize(s);

	}

}
