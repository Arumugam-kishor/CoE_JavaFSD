package week1;

class Node{
	int data;
	Node next;
	
	public Node(int data) {
		super();
		this.data = data;
	}
	
}

class LinkedList {
	Node head;
	void add(int data) {
		Node newNode = new Node(data);
		if(head==null) {
			head=newNode;
		}
		else {
			Node temp = head;
			while(temp.next!=null) {
				temp=temp.next;
			}
			temp.next=newNode;
		}
	}
	
	boolean hasCycles(Node head) {
		Node slow = head;
		Node fast = head;
		while( fast!=null) {
			slow=slow.next;
			fast=fast.next.next;
			if(slow==fast) {
				return true;
			}
		}
		return false;
		
	}
}

public class Task6 {

	public static void main(String[] args) {
		LinkedList ll = new LinkedList();
		ll.add(10);
		ll.add(20);
		ll.add(30);
		ll.add(40);
		
		System.out.println(ll.hasCycles(ll.head));

	}

}
