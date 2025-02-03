package week1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Task7 {

	public static List<Integer> findAnagrams(String s, String p){
		List<Integer> res = new ArrayList<Integer>();
		int pLen = p.length();
		char[] pc = p.toCharArray();
		Arrays.sort(pc);
		int i=0;
		
		while(i<=s.length()-pLen){
			char[] c = s.substring(i, i+pLen).toCharArray();
			Arrays.sort(c);
			if(Arrays.compare(pc, c)==0) res.add(i);		
			i++;
		}
		
		return res;
	}
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		String p = sc.next();
		
		System.out.println(findAnagrams(s,p));

	}

}
