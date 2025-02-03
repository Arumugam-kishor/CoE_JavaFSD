package week1;

class BankAccount{
	double balance;
	BankAccount(double balance){
		this.balance=balance;
	}
	
	synchronized void deposit(double amount) {
		this.balance+=amount;
		System.out.println(Thread.currentThread().getName()+" "+amount+" added successfully! Balance : "+this.balance);
	}
	
	synchronized void withdraw(double amount) {
		if(amount<=this.balance) {
			this.balance-=amount;
			System.out.println(Thread.currentThread().getName()+" "+amount+" withdrawn successfully! Balance : "+this.balance);

		}
		else {
			System.out.println("Insufficient Balance");
		}
	}
}

public class Task2 {

	public static void main(String[] args) {
		BankAccount ba = new BankAccount(1000);
		
		Runnable task1=()->{ba.deposit(200);ba.withdraw(700);};
		Runnable task2=()->{ba.deposit(500);ba.withdraw(1000);};
		
		Thread t1 = new Thread(task1,"User1");
		Thread t2 = new Thread(task2,"User2");
		
		t1.start();
		t2.start();
		

	}

}
