package java0913;

import java.util.Scanner;
public class test {
		public static void main(String args[]) {
			Linkedlist L = new Linkedlist();
			L.insert("Kim", 100);
			L.insert("Lee", 90);
			L.insert("Park", 80);
			L.insert("Choi", 85);
			L.printAllNodes();
			System.out.println("delete name");
			Scanner a=new Scanner(System.in);
			String b = a.nextLine();
			L.delete(b);
			L.printAllNodes();
		}
	
}
