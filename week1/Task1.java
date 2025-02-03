package week1;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

class Task implements Comparable<Task>{

	String id;
	String description;
	int priority;
		
	public Task(String id, String description, int priority) {
		super();
		this.id = id;
		this.description = description;
		this.priority = priority;
	}

	@Override
	public int compareTo(Task t) {
		return Integer.compare(this.priority, t.priority);
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description + ", priority=" + priority + "]";
	}
	
}

class TaskManager{
	
	PriorityQueue<Task> queue = new PriorityQueue<Task>();
	Map<String,Task> map = new HashMap<String, Task>();
	
	void addTask(String id,String description, int priority) {
		Task task = new Task(id,description,priority);
		queue.add(task);
		map.put(id,task);
	}
	
	void removeTask(String id) {
		Task task = map.remove(id);
		queue.remove(task);
		System.out.println(task.id+" Removed Successfully");
	}
	
	Task getHighestPriorityTask() {
		return queue.peek();
		
	}
}

public class Task1 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("1.Add Task\n2.Remove Task\n3.Get High Priority task\n4.Exit");
		
		TaskManager tm = new TaskManager();
		
		int ch = 0;
		while(ch!=4) {
			ch=sc.nextInt();
			switch(ch) {	
			case 1: 
				System.out.print("Id : ");
				String id = sc.next();
				System.out.print("Description : ");
				sc.nextLine();
				String desc = sc.nextLine();
				System.out.print("Priority : ");
				int p = sc.nextInt();
				tm.addTask(id,desc,p);
				break;
			case 2:
				System.out.print("Enter Id: ");
				tm.removeTask(sc.next());
				break;
			case 3:
				System.out.println(tm.getHighestPriorityTask());
				break;
			}
		}
	}
}
