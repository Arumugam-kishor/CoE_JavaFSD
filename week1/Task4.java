package week1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

class User{
	String name;
	String email;
	
	public User(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "User [name=" + name + ", email=" + email + "]";
	}
	
}

public class Task4 {
	
	List<User> al = new ArrayList<>();
	
	public static void main(String[] args) {
		Task4 t = new Task4();
		t.addUser("abc", "abc@gmail.com");
		t.addUser("xyz","xyz@yahoo.com");
		
		t.saveUsersToFile("users.txt");
		t.loadUsersFromFile("users.txt");
	}
	
	void addUser(String name,String email) {
		al.add(new User(name,email));
	}
	
	void saveUsersToFile(String f) {
		try(BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
            for (User user : al) {
                writer.write(user.toString());
                writer.newLine();
            }
            System.out.println("Users saved successfully.");
        } 
		catch (Exception e) {
            System.out.println("Exception Occured: " + e.getMessage());
        }
	}
	
	void loadUsersFromFile(String f) {
		try (BufferedReader reader = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");      
                if (data.length == 2) {
                    al.add(new User(data[0], data[1]));
                }
            }
            System.out.println("Users loaded successfully.");
        } 
		catch (Exception e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
	}
}
